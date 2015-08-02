package model.game.spell;

import model.game.characters.GameCharacter;
import model.game.maps.GameMap;
import model.game.object.Projectile;

public abstract class Spell
{
    Projectile projectile;
    GameMap map;

    public Spell(GameMap map)
    {
        this.map = map;
    }

    public void cast(int xTarget, int yTarget, GameCharacter character)
    {

    }
}
