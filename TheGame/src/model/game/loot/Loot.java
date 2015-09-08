package model.game.loot;

import controller.ai.MapObjectAI;
import model.game.object.Hitbox;
import model.game.object.MapObject;
import model.game.sprites.Sprite;

/**
 * Abstract class for loot (movable stuff).
 */
public abstract class Loot extends MapObject
{

    public Loot(int x, int y, Sprite sprite, Hitbox hitbox, MapObjectAI ai)
    {
        super(x, y, 0, sprite, hitbox, ai);
    }
}
