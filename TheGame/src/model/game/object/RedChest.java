package model.game.object;

import model.game.sprites.Sprite;
import controller.collision.CollisionHandler.CollisionStatus;

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
        super(x, y, 0, 0.04, Sprite.redChest01);
    }

    @Override
    public void advanceAnimation()
    {
        animationState += animationSpeed;
        int stateNumber = (int) animationState;

        switch (stateNumber)
        {
            case 1:
                sprite = Sprite.redChest02;
                break;
            case 2:
                sprite = Sprite.redChest03;
                break;
            case 3:
                sprite = Sprite.redChest04;
                break;
            case 4:
                sprite = Sprite.redChest05;
                break;
            case 8:
                sprite = Sprite.redChest01;
                animationState = 0;
        }
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
