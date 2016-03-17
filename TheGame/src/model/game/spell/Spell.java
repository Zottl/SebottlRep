package model.game.spell;

import model.GameData;
import model.game.characters.GameCharacter;
import model.game.maps.GameMap;
import model.game.object.Projectile;

public abstract class Spell
{
    Projectile projectile;
    GameMap map;
    long cooldownTime;
    long cooldownStart;

    public Spell()
    {
        // standard cooldown of 500ms
        this.cooldownTime = 500;
        this.map = GameData.getInstance().getMap();        
    }

    public Spell(long cooldownTime)
    {
        this.cooldownTime = cooldownTime;
        this.map = GameData.getInstance().getMap();
    }
    
    /**
     * Checks if a spell is on Cooldown
     */
    public boolean isCooldown()
    {
        if (System.currentTimeMillis() < (cooldownStart + cooldownTime))
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Casts the spell
     * 
     * @param character
     *            the GameCharacter which casts the spell
     */
    public void cast(GameCharacter character)
    {

    }
    
    /**
     * Casts the spell
     * 
     * @param xTarget
     *            X coordinate of the target location
     * @param y
     *            Y coordinate of the target location
     */
    public void cast(int xTarget, int yTarget, GameCharacter character)
    {

    }
}
