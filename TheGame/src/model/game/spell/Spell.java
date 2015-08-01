package model.game.spell;

import java.util.List;

import model.game.object.Projectile;
import model.game.characters.GameCharacter;

public abstract class Spell
{
    Projectile projectile;
    List<Projectile> activeProjectiles;
    
    public Spell(List<Projectile> activeProjectiles)
    {
        this.activeProjectiles = activeProjectiles;
    }

    public void cast(int xTarget, int yTarget, GameCharacter character)
    {
        
    }
}
