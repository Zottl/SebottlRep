package controller.collision;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import model.game.maps.GameMap;
import model.game.maps.GameMap.MapChangedEvent;
import model.game.object.MapObject;

/**
 * Class to manage the collision detection in the game. A map needs to be loaded
 * via loadMapCollision().
 */
public class CollisionHandler implements Observer
{

    public enum CollisionStatus
    {
        EMPTY, SOLID, HURT_PLAYER, HURT_ENEMY, HEAL_PLAYER, HEAL_ENEMY
    };

    private CollisionStatus[][] tileLayer;
    private ListOfMapObjects[][] objectLayer;

    /**
     * Class to manage the collision detection in the game. A map needs to be
     * loaded via loadMapCollision().
     */
    public CollisionHandler()
    {
    }

    /**
     * Check if the position at the given coordinate has a specific collision
     * status
     * 
     * @param status
     *            Status to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the pixel
     * @param y
     *            Y coordinate of the pixel
     * @return {@code true} if the pixel is affected the given status.
     */
    public boolean checkCollision(CollisionStatus status, int x, int y)
    {
        boolean hasStatus = tileLayer[x][y] == status;
        for (ObjectLayerEntry ole : objectLayer[x][y])
        {
            hasStatus |= ole.cs == status;
        }
        return hasStatus;
    }
    
    /**
     * Check if the position at the given coordinate has a specific collision
     * status
     * 
     * @param stati
     *            Stati to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the pixel
     * @param y
     *            Y coordinate of the pixel
     * @return {@code true} if the pixel is affected the given status.
     */
    public boolean checkCollision(CollisionStatus[] stati, int x, int y)
    {
        for (CollisionStatus status : stati)
        {
            if (checkCollision(status, x, y))
            {
                return true;
            }            
        }
        
        return false;
    }

    /**
     * Check if the rectangle with the given coordinate and size lies on a pixel
     * with a specific collision status
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
     * @return {@code true} if the pixel is affected the given status.
     */
    public boolean checkCollisionRectangle(CollisionStatus status, int x, int y, int width, int height)
    {
        boolean hasStatus = false;
        for (int rx = x; rx < width; rx++)
        {
            for (int ry = y; ry < height; ry++)
            {
                hasStatus |= checkCollision(status, rx, ry);
            }
        }
        return hasStatus;
    }
    
    /**
     * Check if the rectangle with the given coordinate and size lies on a pixel
     * with a specific collision status
     * 
     * @param stati
     *            Stati to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the upper left corner of the rectangle
     * @param y
     *            Y coordinate of the upper left corner of the rectangle
     * @param width
     *            Width of the rectangle
     * @param height
     *            Height of the rectangle
     * @return {@code true} if the pixel is affected the given status.
     */
    public boolean checkCollisionRectangle(CollisionStatus[] stati, int x, int y, int width, int height)
    {
        for (CollisionStatus status : stati)
        {
            if (checkCollisionRectangle(status, x, y, width, height))
            {
                return true;
            }            
        }
        
        return false;
    }

    /**
     * Check if position with the given coordinate has a specific collision
     * status and return the Set of MapObjects that are responsible for that.
     * 
     * @param status
     *            Status to check for (e.g. {@code CollisionStatus.SOLID})
     * @param x
     *            X coordinate of the upper left corner of the rectangle
     * @param y
     *            Y coordinate of the upper left corner of the rectangle
     * @return A Set of MapObjects that are responsible for the status of this
     *         position.
     */
    public Set<MapObject> getCollidingObject(CollisionStatus status, int x, int y)
    {
        Set<MapObject> moSet = new HashSet<MapObject>();
        for (ObjectLayerEntry ole : objectLayer[x][y])
        {
            if (ole.cs == status) moSet.add(ole.mo);
        }
        return moSet;
    }

    /**
     * Check if the rectangle with the given coordinate and size lies on a pixel
     * with a specific collision status and return the Set of MapObjects that
     * are responsible for that.
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
    public Set<MapObject> getCollidingObjectRectangle(CollisionStatus status, int x, int y, int width, int height)
    {
        Set<MapObject> moSet = new HashSet<MapObject>();
        for (int rx = x; rx < width; rx++)
        {
            for (int ry = y; ry < height; ry++)
            {
                moSet.addAll(getCollidingObject(status, rx, ry));
            }
        }
        return moSet;
    }

    /**
     * Prepare the collision status data according to the given map
     * 
     * @param map
     *            GameMap to load
     */
    public void loadMapCollision(GameMap map)
    {
        int width = map.getWidth();
        int height = map.getHeight();

        map.addObserver(this);

        tileLayer = new CollisionStatus[width][height];
        objectLayer = new ListOfMapObjects[width][height];

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                tileLayer[x][y] = map.getTile(x, y).isSolid() ? CollisionStatus.SOLID : CollisionStatus.EMPTY;
                objectLayer[x][y] = new ListOfMapObjects();
            }
        }

        for (MapObject mo : map.getObjects())
        {
            addObject(mo);
        }
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
            }
            else
            {
                addObject(event.mapObject);
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
            boolean remove = (boolean) arg;

            if (remove)
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
     *            MapObject that will be added
     */
    private void addObject(MapObject mo)
    {
        int moX = mo.getX();
        int moY = mo.getY();
        int moWidth = mo.getSprite().WIDTH;
        int moHeight = mo.getSprite().HEIGHT;

        mo.addObserver(this);

        for (int x = 0; x < moWidth; x++)
        {
            for (int y = 0; y < moHeight; y++)
            {
                objectLayer[x + moX][y + moY].add(new ObjectLayerEntry(mo));
            }
        }
    }

    /**
     * Remove every pointer to a MapObject inside the objectLayer
     * 
     * @param mo
     *            MapObject that will be removed
     */
    private void removeObject(MapObject mo)
    {
        int moX = mo.getX();
        int moY = mo.getY();
        int moWidth = mo.getSprite().WIDTH;
        int moHeight = mo.getSprite().HEIGHT;
        mo.deleteObserver(this);

        for (int x = 0; x < moWidth; x++)
        {
            for (int y = 0; y < moHeight; y++)
            {
                if (!objectLayer[x + moX][y + moY].remove(mo))
                {
                    System.err.println("[WARNING] CollisionHandler: Bad remove in object layer!");
                }
            }
        }
    }

    /**
     * Class for LinkedLists of MapObjects. Needed because Java does not allow
     * generic types in Arrays. (TASK Find a more elegant solution)
     */
    private class ListOfMapObjects extends LinkedList<ObjectLayerEntry>
    {
        private static final long serialVersionUID = 1L;
    }
}
