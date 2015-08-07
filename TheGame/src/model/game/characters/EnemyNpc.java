package model.game.characters;

import java.util.List;

import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

public class EnemyNpc extends NonPlayerCharacter
{
    public EnemyNpc(int x, int y, List<Spell> spells, Sprite sprite, Hitbox hitbox)
    {
        super(x, y, spells, sprite, hitbox);
    }
}
