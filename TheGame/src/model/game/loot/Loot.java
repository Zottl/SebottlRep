package model.game.loot;

import model.game.object.MapObject;
import model.game.sprites.Sprite;

/**
 * Abstract class for loot (movable stuff).
 */
public abstract class Loot extends MapObject
{

    public Loot(int x, int y, double animationSpeed, Sprite sprite)
    {
        super(x, y, animationSpeed, sprite);
    }

}
