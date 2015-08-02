package model.game.spell;

import model.game.characters.GameCharacter;
import model.game.maps.GameMap;
import model.game.object.Projectile;
import model.game.sprites.Sprite;

public class TestSpell extends Spell
{

    public TestSpell(GameMap map)
    {
        super(map);
    }

    public void cast(int xTarget, int yTarget, GameCharacter character)
    {
        System.out.println("charX: " + character.getX() + " charY: " + character.getY() + " targetX: " + xTarget + " targetY: " + yTarget);
        Projectile projectile = new Projectile(character.getX(), character.getY(), xTarget, yTarget, 3.0, 0.2, Sprite.testSpell01);

        map.addMapObject(projectile);
    }
}
