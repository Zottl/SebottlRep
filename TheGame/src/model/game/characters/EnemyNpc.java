package model.game.characters;

import java.util.List;

import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;
import controller.ai.EnemyAI;

public class EnemyNpc extends NonPlayerCharacter
{
    public EnemyNpc(int x, int y, List<Spell> spells, Sprite sprite, Hitbox hitbox)
    {
        super(x, y, 0.2, 50, spells, sprite, hitbox, new EnemyAI(0.04));
    }

    @Override
    public CollisionStatus getCollisionStatus()
    {
        return CollisionStatus.ENEMY_BODY;
    }
}
