package model.game.characters;

import java.util.List;

import controller.ai.MapObjectAI;
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
    protected List<Spell> spells;

    protected int hitpoints;

    // Constructor
    public GameCharacter(int x, int y, double movementSpeed, List<Spell> spells, Sprite sprite, Hitbox hitbox, MapObjectAI ai)
    {
        super(x, y, movementSpeed, sprite, hitbox, ai);

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

    public int getHitpoints()
    {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints)
    {
        this.hitpoints = hitpoints;
    }
}
