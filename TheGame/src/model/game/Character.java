package model.game;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Abstract Class for movable entities in the game.
 *
 */
public abstract class Character
{

    public static int DIR_UP = 0b1000;
    public static int DIR_DOWN = 0b0100;
    public static int DIR_LEFT = 0b0010;
    public static int DIR_RIGHT = 0b0001;

    private int x;
    private int y;

    private int speed;

    private Image texture;

    private GrassTile tile;

    // Constructor
    public Character(int x, int y, int speed, String texturePath)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        try
        {
            this.texture = ImageIO.read(Tile.class.getResource(texturePath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void move(int xTravel, int yTravel)
    {
        if (!collision(xTravel, yTravel))
        {
            x += xTravel;
            y += yTravel;
        }
    }

    // method for handling collision
    private boolean collision(int xTravel, int yTravel)
    {
        tile = new GrassTile(x + xTravel, y + yTravel);

        return tile.isSolid();
    }

    // get x coordinate of the character
    public int getX()
    {
        return x;
    }

    // get y coordinate of the character
    public int getY()
    {
        return y;
    }

    // get speed of the character
    public int getSpeed()
    {
        return speed;
    }

    // set speed of the character
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    // set character sprite
    public Image getTexture()
    {
        return texture;
    }
}
