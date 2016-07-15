package model.game.characters;

import java.util.List;

import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;
import controller.ai.PlayerAI;

public class Player extends GameCharacter
{

    public Player(int x, int y, List<Spell> spells, Sprite sprite, Hitbox hitbox)
    {
        super(x, y, 1.5, 80, spells, sprite, hitbox, new PlayerAI(0.04));
    }

    @Override
    public CollisionStatus getCollisionStatus()
    {
        return CollisionStatus.PLAYER_BODY;
    }
}
