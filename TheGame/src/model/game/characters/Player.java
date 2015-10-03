package model.game.characters;

import java.util.List;

import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;
import controller.ai.PlayerAI;
import controller.input.Keyboard;
import controller.input.Mouse;

public class Player extends GameCharacter
{

    public Player(int x, int y, List<Spell> spells, Sprite sprite, Hitbox hitbox, Keyboard kb, Mouse ms)
    {
        super(x, y, 1.5, 80, spells, sprite, hitbox, new PlayerAI(0.04, kb, ms));
    }

    @Override
    public CollisionStatus getCollisionStatus()
    {
        return CollisionStatus.PLAYER_BODY;
    }
}
