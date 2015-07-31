package model.game.spell;

import java.util.List;

import model.game.object.Projectile;
import model.game.sprites.Sprite;
import model.game.characters.GameCharacter;

public class Spell
{
    Projectile projectile;
    List<Projectile> activeProjectiles;
    
    public Spell(List<Projectile> activeProjectiles)
    {
        this.activeProjectiles = activeProjectiles;
    }

    public void cast(int xTarget, int yTarget, GameCharacter character)
    {
        Projectile projectile = new Projectile(character.getX(), character.getY(), xTarget, yTarget, 1.3, 0.04, Sprite.testSpell01);
        
        activeProjectiles.add(projectile);
    }
}
