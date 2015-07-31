package model.game.characters;

import java.util.List;

import model.game.maps.GameMap;
import model.game.object.MapObject;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

/**
 * Abstract Class for movable entities in the game.
 */
public abstract class GameCharacter extends MapObject
{
    private double speed;
    private double speedBuildup;

    private GameMap map;
    
    // list of Spells for this character
    private List<Spell> spells;

    // Constructor
    public GameCharacter(int x, int y, double speed, List<Spell> spells, double animationSpeed, Sprite sprite, GameMap map)
    {
        super(x, y, animationSpeed, sprite);

        this.speed = speed;
        this.spells = spells;
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
        double speed = this.speed;

        if (xTravel != 0 && yTravel != 0)
        {
            speed /= Math.sqrt(2);

            if (collision(xTravel, yTravel))
            {
                move(xTravel, 0);
                move(0, yTravel);

                return;
            }
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
        int characterX2 = x + xTravel + sprite.WIDTH - 1;
        int characterY1 = y + yTravel;
        int characterY2 = y + yTravel + sprite.HEIGHT - 1;

        boolean solidCollision = false;

        // collision with map border
        if (characterX1 < 0 || characterX2 >= map.getWidth()) return true;
        if (characterY1 < 0 || characterY2 >= map.getHeight()) return true;

        // collision with solid tile
        solidCollision |= map.getTile(characterX1, characterY1).isSolid();
        solidCollision |= map.getTile(characterX1, characterY2).isSolid();
        solidCollision |= map.getTile(characterX2, characterY1).isSolid();
        solidCollision |= map.getTile(characterX2, characterY2).isSolid();

        return solidCollision;
    }

    public void shoot(int xTarget, int yTarget)
    {
        Spell spell = spells.get(0);
        
        spell.cast(xTarget, yTarget, this);
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
}
