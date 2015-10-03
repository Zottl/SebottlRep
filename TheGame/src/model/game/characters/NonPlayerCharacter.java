package model.game.characters;

import java.util.List;

import controller.ai.MapObjectAI;
import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

public abstract class NonPlayerCharacter extends GameCharacter
{
    public NonPlayerCharacter(int x, int y, double movementSpeed, int maxHitpoints, List<Spell> spells, Sprite sprite, Hitbox hitbox, MapObjectAI ai)
    {
        super(x, y, movementSpeed, maxHitpoints, spells, sprite, hitbox, ai);
    }
}
