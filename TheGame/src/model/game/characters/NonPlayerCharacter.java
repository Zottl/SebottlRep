package model.game.characters;

import java.util.List;

import model.game.object.MapObject;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

public abstract class NonPlayerCharacter extends GameCharacter
{
    public NonPlayerCharacter(int x, int y, List<Spell> spells, Sprite sprite)
    {
        super(x, y, 1.5, spells, 0.04, sprite);
    }

    @Override
    public void interact(GameCharacter source)
    {

    }

    @Override
    public void advanceAnimation()
    {

    }

    @Override
    public void collideWith(MapObject mo)
    {
        // TODO
    }
}
