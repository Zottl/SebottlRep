package model.game.characters;

import java.util.List;

import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

public abstract class NonPlayerCharacter extends GameCharacter
{
    public NonPlayerCharacter(int x, int y, List<Spell> spells, Sprite sprite, Hitbox hitbox)
    {
        super(x, y, 1.5, spells, 0.04, sprite, hitbox);
    }

    @Override
    public void advanceAnimation()
    {

    }
}
