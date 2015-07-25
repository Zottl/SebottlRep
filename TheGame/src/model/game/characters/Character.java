package model.game.characters;

import model.game.maps.GameMap;
import model.game.sprites.Sprite;
import model.game.tiles.Tile;

/**
 * Abstract Class for movable entities in the game.
 */
public abstract class Character
{
    private int x;
    private int y;

    private double speed;
    private double speedBuildup;

    private Sprite sprite;
    private GameMap map;

    // Constructor
    public Character(int x, int y, double speed, Sprite sprite, GameMap map)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = sprite;
        this.map = map;
    }

    /**
     * Handles character movement
     * 
     * @param xTravel
     *            Travel distance in x position
     * @param yTravel
     *            Travel distance in y position
     */
    public void move(int xTravel, int yTravel)
    {
        if (xTravel != 0 && yTravel != 0)
        {
            move(xTravel, 0);
            move(0, yTravel);

            return;
        }

        if (speedBuildup < 1)
        {
            speedBuildup += speed;
        }

        while (speedBuildup >= 1 && !collision(xTravel, yTravel))
        {
            x += xTravel;
            y += yTravel;

            speedBuildup--;
        }
    }

    /**
     * Handles character collision
     * 
     * @param xTravel
     *            Travel distance in x direction
     * @param yTravel
     *            Travel distance in y direction
     * @return True when character is colliding
     */
    public boolean collision(int xTravel, int yTravel)
    {
        int characterX1 = x + xTravel;
        int characterX2 = x + xTravel + sprite.SIZE - 1;
        int characterY1 = y + yTravel;
        int characterY2 = y + yTravel + sprite.SIZE - 1;

        boolean solidCollision = false;

        // collision with map border
        if (characterX1 < 0 || characterX2 >= map.getWidth() * Tile.TILESIZE) return true;
        if (characterY1 < 0 || characterY2 >= map.getHeight() * Tile.TILESIZE) return true;

        // collision with solid tile
        solidCollision |= map.getTile(characterX1 / Tile.TILESIZE, characterY1 / Tile.TILESIZE).isSolid();
        solidCollision |= map.getTile(characterX1 / Tile.TILESIZE, characterY2 / Tile.TILESIZE).isSolid();
        solidCollision |= map.getTile(characterX2 / Tile.TILESIZE, characterY1 / Tile.TILESIZE).isSolid();
        solidCollision |= map.getTile(characterX2 / Tile.TILESIZE, characterY2 / Tile.TILESIZE).isSolid();

        return solidCollision;
    }

    /**
     * @return x coordinate of the character
     */
    public int getX()
    {
        return x;
    }

    /**
     * @param x
     *            x coordinate to be set for the character
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * @return y coordinate of the character
     */
    public int getY()
    {
        return y;
    }

    /**
     * @param y
     *            y coordinate to be set for the character
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * @return speed of the character
     */
    public double getSpeed()
    {
        return speed;
    }

    /**
     * @param speed
     *            speed to be set for the character
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * @return sprite of the character
     */
    public Sprite getSprite()
    {
        return sprite;
    }
}
