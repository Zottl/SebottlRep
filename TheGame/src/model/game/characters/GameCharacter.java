package model.game.characters;

import java.util.List;

import controller.ai.GameCharacterAI;
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
    
    // ai for this character
    protected GameCharacterAI ai;

    protected int maxHitpoints;
    protected int hitpoints;
    protected long lastTimeDamaged = 0L;

    // Constructor
    public GameCharacter(int x, int y, double movementSpeed, int maxHitpoints, List<Spell> spells, Sprite sprite, Hitbox hitbox, MapObjectAI ai)
    {
        super(x, y, movementSpeed, sprite, hitbox, ai);

        this.ai = (GameCharacterAI)ai;
        this.hitpoints = maxHitpoints;
        this.maxHitpoints = maxHitpoints;
        this.spells = spells;
    }

    public void shoot(int xTarget, int yTarget)
    {
        Spell spell = spells.get(0);

        spell.cast(xTarget, yTarget, this);
    }
    
    public void death ()
    {
        ai.deathAnimation();
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

    /**
     * @return hitpoints of the character
     */
    public int getHitpoints()
    {
        return hitpoints;
    }

    /**
     * @param hitpoints
     *            hitpoints to be set for the character
     */
    public void setHitpoints(int hitpoints)
    {
        this.hitpoints = hitpoints;
    }

    /**
     * @return maxHitpoints of the character
     */
    public int getMaxHitpoints()
    {
        return maxHitpoints;
    }

    /**
     * @param maxHitpoints
     *            maxHitpoints to be set for the character
     */
    public void setMaxHitpoints(int maxHitpoints)
    {
        this.maxHitpoints = maxHitpoints;
    }
    
    /**
     * @return last time the character got damaged
     */
    public long getLastTimeDamaged()
    {
        return this.lastTimeDamaged;
    }
    
    /**
     * @param lastTimeDamaged
     *            last time the character got damaged
     */
    public void setLastTimeDamaged(long lastTimeDamaged)
    {
        this.lastTimeDamaged = lastTimeDamaged;
    }       
}
