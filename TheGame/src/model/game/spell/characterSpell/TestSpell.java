package model.game.spell.characterSpell;

import model.game.spell.ProjectileSpell;
import model.game.sprites.Sprite;

public class TestSpell extends ProjectileSpell
{

    public TestSpell()
    {
        super(Sprite.testSpell01, 3.0, 200);
    }
}
