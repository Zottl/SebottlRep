package controller;

import java.util.List;

import model.GameData;
import model.game.object.MapObject;
import model.game.sprites.Sprite;
import controller.collision.CollisionHandler;
import controller.collision.CollisionHandler.CollisionStatus;
import controller.input.Keyboard;

public class MovementHandler
{

    private GameData data;
    private CollisionHandler ch;
    private Keyboard keyboad;

    public MovementHandler(GameData data, CollisionHandler ch, Keyboard keyboard)
    {
        this.data = data;
        this.ch = ch;
        this.keyboad = keyboard;
    }

    public void moveObjects()
    {
        // Adjust the direction according to AI and User-Inputs
        setPlayerDirection();

        // Move the objects
        List<MapObject> objects = data.getMap().getObjects();
        for (MapObject mo : objects)
        {
            int dir = mo.getDirection();
            if (dir == -1)
            {
                // If the object is currently not moving, then move it towards
                // the center of the pixel it currently is positioned on
                adjustSubpixelPosition(mo);
            }
            else
            {
                moveObject(mo);
            }
        }
    }

    private void setPlayerDirection()
    {
        int dir;
        if (keyboad.up && !keyboad.down)
        {
            if (keyboad.right && !keyboad.left)
                dir = 315;
            else if (!keyboad.right && keyboad.left)
                dir = 45;
            else
                dir = 0;
        }
        else if (!keyboad.up && keyboad.down)
        {
            if (keyboad.right && !keyboad.left)
                dir = 225;
            else if (!keyboad.right && keyboad.left)
                dir = 135;
            else
                dir = 180;
        }
        else
        {
            if (keyboad.right && !keyboad.left)
                dir = 270;
            else if (!keyboad.right && keyboad.left)
                dir = 90;
            else
                dir = -1;
        }
        data.player.setDirection(dir);
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
        double speed = mo.getSpeed();
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
     * @param direction
     * @param distance
     */
    private void moveObject(MapObject mo)
    {
        double x = mo.getX();
        double y = mo.getY();
        int direction = mo.getDirection();
        double distance = mo.getSpeed();

        double xDist = calcXTravel(direction, distance);
        double yDist = calcYTravel(direction, distance);

        Sprite sprite = mo.getSprite();
        
        CollisionStatus[] coll = {CollisionStatus.SOLID, CollisionStatus.OOB};
        
        if (mo.canCollide() && ch.checkCollisionRectangle(coll, (int) (x + xDist), (int) (y + yDist), sprite.WIDTH, sprite.HEIGHT))
        {
            /*
             * A collision occurred! Try to go as far horizontally/vertically as
             * possible (whichever happens to be the cause for the collision)
             * and then go the remaining distance along the other direction.
             */
            if (ch.checkCollisionRectangle(coll, (int) (x + distance), (int) y, sprite.WIDTH, sprite.HEIGHT))
            {
                /*
                 * The horizontal movement was the problem! Go horizontally as
                 * far as possible, then move the remaining distance vertically.
                 */
                xDist = horizontalDistance((int) x, (int) y, sprite.WIDTH, sprite.HEIGHT, distance);
                yDist = verticalDistance((int) (x + xDist), (int) y, sprite.WIDTH, sprite.HEIGHT, pythagorasCH(xDist, distance));

                mo.moveTo(x + xDist, y + yDist);
            }
            else if (ch.checkCollisionRectangle(coll, (int) x, (int) (y + distance), sprite.WIDTH, sprite.HEIGHT))
            {
                /*
                 * The vertical movement was the problem! Go vertically as far
                 * as possible, then move the remaining distance horizontally.
                 */
                yDist = verticalDistance((int) x, (int) y, sprite.WIDTH, sprite.HEIGHT, distance);
                xDist = horizontalDistance((int) x, (int) (y + yDist), sprite.WIDTH, sprite.HEIGHT, pythagorasCH(yDist, distance));

                mo.moveTo(x + xDist, y + yDist);
            }
        }
        else
        {
            mo.moveTo(x + xDist, y + yDist);
        }
    }

    /**
     * Calculate the maximal possible horizontal distance, the given rectangle
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
     * @param distance
     *            Maximum travel distance
     * @return The amount of pixels the rectangle may move horizontally
     */
    private double horizontalDistance(double x, double y, int width, int height, double distance)
    {
        CollisionStatus[] coll = {CollisionStatus.SOLID, CollisionStatus.OOB};
        for (double newX = x + distance - 1; newX > (x + 1); newX--)
        {
            if (!ch.checkCollisionRectangle(coll, (int) newX, (int) y, width, height))
            {
                return ((int) newX) + 0.5 - x;
            }
        }
        return 0;
    }

    /**
     * Calculate the maximal possible vertical distance, the given rectangle may
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
     * @param distance
     *            Maximum travel distance
     * @return The amount of pixels the rectangle may move vertically
     */
    private double verticalDistance(double x, double y, int width, int height, double distance)
    {
        CollisionStatus[] coll = {CollisionStatus.SOLID, CollisionStatus.OOB};
        for (double newY = y + distance - 1; newY > (y + 1); newY--)
        {
            if (!ch.checkCollisionRectangle(coll, (int) x, (int) newY, width, height))
            {
                return ((int) newY) + 0.5 - y;
            }
        }
        return 0;
    }

    private double calcXTravel(int direction, double distance)
    {
        return Math.sin(Math.toRadians(direction)) * distance;
    }

    private double calcYTravel(int direction, double distance)
    {
        return Math.cos(Math.toRadians(direction)) * distance;
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
}
