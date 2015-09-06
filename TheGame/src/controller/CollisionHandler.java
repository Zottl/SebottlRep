package controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import model.game.maps.GameMap;
import model.game.maps.GameMap.MapChangedEvent;
import model.game.object.MapObject;
import model.game.tiles.Tile;

/**
 * Class to manage the collision detection in the game. A map needs to be loaded
 * via loadMapCollision().
 */
public class CollisionHandler implements Observer
{

    /**
     * Values that represent different types of collision (e.g. solid, hurtful)
     */
    public enum CollisionStatus
    {
        EMPTY, SOLID, HURT_PLAYER, HURT_ENEMY, HEAL_PLAYER, HEAL_ENEMY, OOB, ENEMY_BODY, PLAYER_BODY
    };

    private ListOfMapObjects[][] objectLayer;

    // Width and height of the currently loaded map
    private int mapWidth, mapHeight;

    /**
     * Class to manage the collision detection in the game. A map needs to be
     * loaded via loadMapCollision().
     */
    public CollisionHandler()
    {
        this.mapWidth = 0;
        this.mapHeight = 0;
    }

    /**
     * Prepare the collision status data according to the given map
     * 
     * @param map
     *            {@link GameMap} to load
     */
    public void loadMapCollision(GameMap map)
    {
        mapWidth = map.getWidth();
        mapHeight = map.getHeight();

        map.addObserver(this);

        objectLayer = new ListOfMapObjects[mapWidth][mapHeight];

        for (int x = 0; x < mapWidth; x++)
        {
            for (int y = 0; y < mapHeight; y++)
            {
                objectLayer[x][y] = new ListOfMapObjects();

                Tile currentTile = map.getTile(x, y);
                objectLayer[x][y].add(currentTile);
            }
        }

        for (MapObject mo : map.getObjects())
        {
            addObject(mo);
            mo.addObserver(this);
        }
    }

    /**
     * Check if the position at the given coordinate has a specific collision
     * status
     * 
     * @param status
     *            Status to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the position
     * @param y
     *            Y coordinate of the position
     * @return {@code true} if the position is affected by the given status.
     */
    public boolean checkCollision(CollisionStatus status, int x, int y)
    {
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
        {
            // Here the position is out of bounds
            return status == CollisionStatus.OOB;
        }

        for (MapObject mo : objectLayer[x][y])
        {
            if (mo.getCollisionStatus() == status)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the position at the given coordinate has a one of multiple
     * collision statuses
     * 
     * @param statuses
     *            Statuses to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the position
     * @param y
     *            Y coordinate of the position
     * @return {@code true} if the position is affected by one of the given
     *         statuses.
     */
    public boolean checkCollision(CollisionStatus[] statuses, int x, int y)
    {
        for (CollisionStatus status : statuses)
        {
            if (checkCollision(status, x, y))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the rectangle with the given coordinate and size lies on a
     * position with a specific collision status
     * 
     * @param status
     *            Status to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the upper left corner of the rectangle
     * @param y
     *            Y coordinate of the upper left corner of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @return {@code true} if one of the positions inside the rectangle are
     *         affected by the given status.
     */
    public boolean checkCollisionRectangle(CollisionStatus status, int x, int y, int width, int height)
    {
        for (int rx = x; rx < x + width; rx++)
        {
            for (int ry = y; ry < y + height; ry++)
            {
                if (checkCollision(status, rx, ry))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check if the rectangle with the given coordinate and size lies on a pixel
     * with one of multiple collision statuses
     * 
     * @param statuses
     *            Statuses to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the upper left corner of the rectangle
     * @param y
     *            Y coordinate of the upper left corner of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @return {@code true} if the one positions inside the rectangle are
     *         affected by one of the given statuses.
     */
    public boolean checkCollisionRectangle(CollisionStatus[] statuses, int x, int y, int width, int height)
    {
        for (CollisionStatus status : statuses)
        {
            if (checkCollisionRectangle(status, x, y, width, height))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the position with the given coordinate has a specific collision
     * status and return the Set of MapObjects that are responsible for that.
     * 
     * @param status
     *            Status to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the position
     * @param y
     *            Y coordinate of the position
     * @return A Set of MapObjects that are responsible for the status of this
     *         position.
     */
    public Set<MapObject> getCollidingObjects(CollisionStatus status, int x, int y)
    {
        Set<MapObject> moSet = new HashSet<MapObject>();
        for (MapObject mo : objectLayer[x][y])
        {
            if (mo.getCollisionStatus() == status) moSet.add(mo);
        }

        return moSet;
    }

    /**
     * Return the Set of MapObjects on this position.
     * 
     * @param x
     *            X coordinate of the position
     * @param y
     *            Y coordinate of the position
     * @return A Set of MapObjects on the given position
     */
    public Set<MapObject> getCollidingObjects(int x, int y)
    {
        Set<MapObject> moSet = new HashSet<MapObject>();

        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
        {
            return moSet;
        }

        for (MapObject mo : objectLayer[x][y])
        {
            moSet.add(mo);
        }

        return moSet;
    }

    /**
     * Check if the rectangle with the given coordinate and size lies on a
     * position with a specific collision status and return the Set of
     * MapObjects that are responsible for that.
     * 
     * @param status
     *            Status to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the upper left corner of the rectangle
     * @param y
     *            Y coordinate of the upper left corner of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @return A Set of MapObjects that are responsible for the status of the
     *         positions inside the rectangle.
     */
    public Set<MapObject> getCollidingObjectsRectangle(CollisionStatus status, int x, int y, int width, int height)
    {
        Set<MapObject> moSet = new HashSet<MapObject>();
        for (int rx = x; rx < x + width; rx++)
        {
            for (int ry = y; ry < y + height; ry++)
            {
                moSet.addAll(getCollidingObjects(status, rx, ry));
            }
        }

        return moSet;
    }

    /**
     * Return the Set of MapObjects inside this rectangle
     * 
     * @param x
     *            X coordinate of the upper left corner of the rectangle
     * @param y
     *            Y coordinate of the upper left corner of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @return A Set of MapObjects inside the given rectangle
     */
    public Set<MapObject> getCollidingObjectsRectangle(int x, int y, int width, int height)
    {
        Set<MapObject> moSet = new HashSet<MapObject>();
        for (int rx = x; rx < x + width; rx++)
        {
            for (int ry = y; ry < y + height; ry++)
            {
                moSet.addAll(getCollidingObjects(rx, ry));
            }
        }

        return moSet;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof GameMap)
        {
            /*
             * If the Observable was the GameMap, it means a MapObect was either
             * created or deleted, as specified in the event.
             */
            MapChangedEvent event = (MapChangedEvent) arg;

            if (event.removed)
            {
                removeObject(event.mapObject);
                event.mapObject.deleteObserver(this);
            }
            else
            {
                addObject(event.mapObject);
                event.mapObject.addObserver(this);
            }
        }
        else if (o instanceof MapObject)
        {
            /*
             * If the Observable was a MapObject, it means this MapObect has
             * changed and needs to be repositioned. This is a two step process:
             */
            /*
             * BEFORE THE MAPOBJECT CHANGES the MapObject should call this
             * Method with the argument true, so that it can be removed from the
             * object layer.
             */
            /*
             * AFTER THE MAPOBJECT CHANGES the MapObject should call this Method
             * with the argument false, so that it can be reinserted into the
             * object layer.
             */

            MapObject mo = (MapObject) o;
            boolean moveCompleted = (boolean) arg;

            if (!moveCompleted)
            {
                removeObject(mo);
            }
            else
            {
                addObject(mo);
            }
        }
    }

    /**
     * Insert pointers to a MapObject into the objectLayer
     * 
     * @param mo
     *            {@link MapObject} that will be added
     */
    private void addObject(MapObject mo)
    {
        int moX = mo.getHitboxX();
        int moY = mo.getHitboxY();
        int moWidth = mo.getHitboxWidth();
        int moHeight = mo.getHitboxHeight();

        for (int x = 0; x < moWidth; x++)
        {
            for (int y = 0; y < moHeight; y++)
            {
                int posX = moX + x;
                int posY = moY + y;

                if (posX >= 0 && posX < mapWidth && posY >= 0 && posY < mapHeight)
                {
                    objectLayer[posX][posY].add(mo);
                }
            }
        }
    }

    /**
     * Remove every pointer to a MapObject inside the objectLayer
     * 
     * @param mo
     *            {@link MapObject} that will be removed
     */
    private void removeObject(MapObject mo)
    {
        int moX = mo.getHitboxX();
        int moY = mo.getHitboxY();
        int moWidth = mo.getHitboxWidth();
        int moHeight = mo.getHitboxHeight();

        for (int x = 0; x < moWidth; x++)
        {
            for (int y = 0; y < moHeight; y++)
            {
                int posX = moX + x;
                int posY = moY + y;

                if (posX >= 0 && posX < mapWidth && posY >= 0 && posY < mapHeight)
                {
                    if (!objectLayer[posX][posY].remove(mo))
                    {
                        System.err.println("[WARNING] CollisionHandler: Bad remove in object layer!");
                    }
                }
            }
        }
    }

    /**
     * Class for LinkedLists of MapObjects. Needed because Java does not allow
     * generic types in Arrays. (TASK Find a more elegant solution)
     */
    private class ListOfMapObjects extends LinkedList<MapObject>
    {
        private static final long serialVersionUID = 1L;
    }
}
