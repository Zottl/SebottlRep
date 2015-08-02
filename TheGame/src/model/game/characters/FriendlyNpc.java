package model.game.characters;

import java.util.List;

import model.game.spell.Spell;
import model.game.sprites.Sprite;

public class FriendlyNpc extends NonPlayerCharacter
{
    public FriendlyNpc(int x, int y, List<Spell> spells, Sprite sprite)
    {
        super(x, y, spells, sprite);
    }
}
