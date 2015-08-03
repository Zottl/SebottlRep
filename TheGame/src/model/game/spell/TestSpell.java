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
        // Place the center of the projectile onto the center of the character
        Sprite sprite = Sprite.testSpell01;
        int x = (int) (character.getCenterX() - sprite.WIDTH / 2.0);
        int y = (int) (character.getCenterY() - sprite.HEIGHT / 2.0);

        Projectile projectile = new Projectile(x, y, xTarget, yTarget, 3.0, 0.2, sprite);

        map.addMapObject(projectile);
    }
}
