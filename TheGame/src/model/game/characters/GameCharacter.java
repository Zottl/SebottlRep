package model.game.characters;

import java.util.List;

import model.game.object.Hitbox;
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
    public GameCharacter(int x, int y, double speed, List<Spell> spells, double animationSpeed, Sprite sprite, Hitbox hitbox)
    {
        super(x, y, speed, animationSpeed, sprite, hitbox);

        this.spells = spells;
    }

    public void shoot(int xTarget, int yTarget)
    {
        Spell spell = spells.get(0);

        spell.cast(xTarget, yTarget, this);
    }

    @Override
    public boolean isGhost()
    {
        return false;
    }

    /**
     * @return speed of the character
     */
    public double getMovementSpeed()
    {
        return movementSpeed;
    }

    /**
     * @param speed
     *            speed to be set for the character
     */
    public void setSpeed(double speed)
    {
        this.movementSpeed = speed;
    }
}
