package model.game.characters;

import java.util.List;

import model.game.object.MapObject;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

/**
 * Abstract Class for movable entities in the game.
 */
public abstract class GameCharacter extends MapObject
{
    // list of Spells for this character
    private List<Spell> spells;

    // Constructor
    public GameCharacter(int x, int y, double speed, List<Spell> spells, double animationSpeed, Sprite sprite)
    {
        super(x, y, speed, animationSpeed, sprite);

        this.spells = spells;
    }

    public void shoot(int xTarget, int yTarget)
    {
        Spell spell = spells.get(0);

        spell.cast(xTarget, yTarget, this);
    }

    @Override
    public boolean canCollide()
    {
        return true;
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
