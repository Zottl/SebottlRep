package model.game;

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

    // Constructor
    public Character(int x, int y, int speed)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move(int dir, int amount)
    {

        if (dir == DIR_UP && dir != DIR_DOWN)
        {
            y += amount;
        }
        else if (dir == DIR_DOWN && dir != DIR_UP)
        {
            y -= amount;
        }

        if (dir == DIR_RIGHT && dir != DIR_LEFT)
        {
            x += amount;
        }
        else if (dir == DIR_LEFT && dir != DIR_RIGHT)
        {
            x -= amount;
        }
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
}
