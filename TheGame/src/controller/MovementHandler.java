package controller;

import model.GameData;
import model.game.object.MapObject;
import controller.CollisionHandler.CollisionStatus;
import controller.ai.MapObjectAI;

/**
 * Handles the movement of all MapObjects
 */
public class MovementHandler
{
    private GameData data;
    private CollisionHandler ch;

    /**
     * The set of collision statuses that are impassable for non-ghost objects
     */
    private final CollisionStatus[] IMPASSABLE = { CollisionStatus.SOLID, CollisionStatus.OOB };

    /**
     * Handles the movement of all MapObjects
     * 
     * @param ch
     *            The collision handler
     * @param keyboard
     *            The keyboard
     */
    public MovementHandler(CollisionHandler ch)
    {
        this.data = GameData.getInstance();
        this.ch = ch;
    }

    /**
     * Move every MapObject one step forward
     */
    public void moveObjects()
    {
        // Move the objects
        for (MapObject mo : data.getMap().getObjects())
        {
            MapObjectAI ai = mo.getAI();
            if (ai != null)
            {
                ai.advance();
            }

            if (mo.getDirection() == -1)
            {
                // If the object is currently not moving, then move it towards
                // the center of the pixel it currently is positioned on
                adjustSubpixelPosition(mo);
            }
            else
            {
                moveObject(mo);

                // Fire collision events
                int newX = mo.getHitboxX();
                int newY = mo.getHitboxY();
                int width = mo.getHitboxWidth();
                int height = mo.getHitboxHeight();

                // Get all relevant CollisionStatuses
                if (ai != null)
                {
                    for (CollisionStatus cs : ai.getReleventCollisionStatuses())
                    {
                        if (ch.checkCollisionRectangle(cs, newX, newY, width, height))
                        {
                            ai.collisionWith(cs);
                        }
                    }
                }

                // Get all colliding MapObjects to 
                for (MapObject colMO : ch.getCollidingObjectsRectangle(newX, newY, width, height))
                {
                    MapObjectAI colAI = colMO.getAI();
                    if (colAI != null) colAI.collisionWith(mo.getCollisionStatus());
                }
            }
        }
    }

    /**
     * Moves the MapObject towards the middle of the pixel
     * 
     * @param mo
     *            MapObject to adjust the position on
     */
    private void adjustSubpixelPosition(MapObject mo)
    {
        // Get the data of the MapObject
        double speed = mo.getMovementSpeed();
        double x = mo.getX();
        double y = mo.getY();

        // Get the exact pixel of the position of the MapObject
        int xPix = (int) x;
        int yPix = (int) y;

        double newX = x;
        double newY = y;

        boolean positionChanged = false;

        // Get the subpixel ratio of the position of the MapObject
        double xSub = x % 1;
        double ySub = y % 1;

        // Slowly adjust the subpixel ratio to the center of the pixel
        // (at the speed of the object)
        if (xSub > 0.5)
        {
            newX = Math.max(xPix + 0.5, xPix + (xSub - speed));
            positionChanged = true;
        }
        else if (xSub < 0.5)
        {
            newX = Math.min(xPix + 0.5, xPix + (xSub + speed));
            positionChanged = true;
        }

        if (ySub > 0.5)
        {
            newY = Math.max(yPix + 0.5, yPix + (ySub - speed));
            positionChanged = true;
        }
        else if (ySub < 0.5)
        {
            newY = Math.min(yPix + 0.5, yPix + (ySub + speed));
            positionChanged = true;
        }

        if (positionChanged) mo.moveTo(newX, newY);
    }

    /**
     * Moves a single MapObject according to its direction and speed values
     * 
     * @param mo
     *            MapObject to move
     */
    private void moveObject(MapObject mo)
    {
        double x = mo.getX();
        double y = mo.getY();
        int direction = mo.getDirection();
        double distance = mo.getMovementSpeed();

        double xTravel = calcXTravel(direction, distance);
        double yTravel = calcYTravel(direction, distance);

        int width = mo.getHitboxWidth();
        int height = mo.getHitboxHeight();

        if (!mo.isGhost() && ch.checkCollisionRectangle(IMPASSABLE, (int) (x + xTravel), (int) (y + yTravel), width, height))
        {
            /*
             * A collision occurred! Try to go as far horizontally/vertically as
             * possible (whichever happens to be the cause for the collision)
             * and then go the remaining distance along the other direction.
             */

            int xDir = calcXDir(direction);
            int yDir = calcYDir(direction);

            if (ch.checkCollisionRectangle(IMPASSABLE, (int) (x + distance * xDir), (int) y, width, height))
            {
                /*
                 * The horizontal movement was the problem! Go horizontally as
                 * far as possible, then move the remaining distance vertically.
                 */
                xTravel = calcMaxHorDist(x, y, width, height, distance, xDir);
                if (yDir != 0)
                {
                    double restDistance = pythagorasCH(Math.abs(xTravel), distance);
                    yTravel = calcMaxVerDist((x + xTravel), y, width, height, restDistance, yDir);
                }

                mo.moveTo(x + xTravel, y + yTravel);
            }
            else
            {
                /*
                 * The vertical movement was the problem! Go vertically as far
                 * as possible, then move the remaining distance horizontally.
                 */
                yTravel = calcMaxVerDist(x, y, width, height, distance, yDir);
                if (xDir != 0)
                {
                    double restDistance = pythagorasCH(Math.abs(yTravel), distance);
                    xTravel = calcMaxHorDist(x, (y + yTravel), width, height, restDistance, xDir);
                }

                mo.moveTo(x + xTravel, y + yTravel);
            }
        }
        else
        {
            mo.moveTo(x + xTravel, y + yTravel);
        }
    }

    /**
     * Calculate the maximal possible horizontal distance the given rectangle
     * may travel before colliding
     * 
     * @param x
     *            X coordinate of the rectangle
     * @param y
     *            Y coordinate of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @param maxDist
     *            Maximum travel distance
     * @param dir
     *            Direction of the horizontal movement (should be -1, 0 or 1)
     * @return The amount of pixels the rectangle may move horizontally
     */
    private double calcMaxHorDist(double x, double y, int width, int height, double maxDist, int dir)
    {
        if (dir == 0) return 0;

        for (int d = 0; d < maxDist; d++)
        {
            double xTravel = (maxDist - d) * dir;
            if (!ch.checkCollisionRectangle(IMPASSABLE, (int) (x + xTravel), (int) y, width, height))
            {
                return xTravel;
            }
        }
        return 0;
    }

    /**
     * Calculate the maximal possible vertical distance the given rectangle may
     * travel before colliding
     * 
     * @param x
     *            X coordinate of the rectangle
     * @param y
     *            Y coordinate of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @param maxDist
     *            Maximum travel distance
     * @param dir
     *            Direction of the vertical movement (should be -1, 0 or 1)
     * @return The amount of pixels the rectangle may move vertically
     */
    private double calcMaxVerDist(double x, double y, int width, int height, double maxDist, int dir)
    {
        if (dir == 0) return 0;

        for (int d = 0; d < maxDist; d++)
        {
            double yTravel = (maxDist - d) * dir;
            if (!ch.checkCollisionRectangle(IMPASSABLE, (int) x, (int) (y + yTravel), width, height))
            {
                return yTravel;
            }
        }
        return 0;
    }

    /**
     * Calculate the distance on the x-axis for the given movement.
     * 
     * @param direction
     *            Direction of the movement in degrees
     * @param distance
     *            Distance of the movement
     * @return The distance to move in x-direction (rounded to the 5. decimal
     *         point)
     */
    private double calcXTravel(int direction, double distance)
    {
        return Math.round(Math.cos(Math.toRadians(direction)) * distance * 10000) / 10000.0;
    }

    /**
     * Calculate the distance on the y-axis for the given movement.
     * 
     * @param direction
     *            Direction of the movement in degrees
     * @param distance
     *            Distance of the movement
     * @return The distance to move in y-direction (rounded to the 5. decimal
     *         point)
     */
    private double calcYTravel(int direction, double distance)
    {
        return Math.round(-Math.sin(Math.toRadians(direction)) * distance * 10000) / 10000.0;
    }

    /**
     * Calculate the length of the third side of a right triangle
     * 
     * @param cathetus
     *            One of the known catheti of the right triangle
     * @param hypothenuse
     *            The hypothenuse of the right triangle
     * @return The length of the missing cathetus of the right triangle
     */
    private double pythagorasCH(double cathetus, double hypothenuse)
    {
        return Math.sqrt(hypothenuse * hypothenuse - cathetus * cathetus);
    }

    /**
     * Transform a direction in degrees into a travel direction on the x-axis
     * 
     * @param dir
     *            Direction in degrees
     * @return The x-axis direction (-1, 0 or 1)
     */
    private int calcXDir(int dir)
    {
        if (dir < 90 || dir > 270)
            return 1;
        else if (dir > 90 && dir < 270)
            return -1;
        else
            return 0;
    }

    /**
     * Transform a direction in degrees into a travel direction on the y-axis
     * 
     * @param dir
     *            Direction in degrees
     * @return The y-axis direction (-1, 0 or 1)
     */
    private int calcYDir(int dir)
    {
        if (dir > 180 && dir < 360)
            return 1;
        else if (dir < 180 && dir > 0)
            return -1;
        else
            return 0;
    }
}
