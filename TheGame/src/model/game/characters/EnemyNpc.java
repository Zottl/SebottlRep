package model.game.characters;

import java.util.List;
import model.game.maps.GameMap;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

public class EnemyNpc extends NonPlayerCharacter
{
    public EnemyNpc(int x, int y, List<Spell> spells, Sprite sprite, GameMap map)
    {
        super(x, y, spells, sprite, map);
    }
}
