package model.game.object;

import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;
import controller.ai.RedChestAI;

/**
 * A Chest that can be placed on a GameMap (red)
 */
public class RedChest extends MapObject
{
    // private Item content;

    /**
     * A Chest that can be placed on a GameMap (red)
     * 
     * @param x
     *            X-coordinate of the chest
     * @param y
     *            Y-coordinate of the chest
     */
    public RedChest(int x, int y)
    {
        super(x, y, 0, Sprite.redChest01, new Hitbox(0, 0, 26, 21), new RedChestAI(0.04));
    }

    @Override
    public CollisionStatus getCollisionStatus()
    {
        return CollisionStatus.SOLID;
    }

    @Override
    public boolean isGhost()
    {
        return false;
    }
}
