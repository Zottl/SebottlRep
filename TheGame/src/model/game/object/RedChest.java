package model.game.object;

import controller.collision.CollisionHandler.CollisionStatus;
import model.game.sprites.Sprite;
import model.game.characters.GameCharacter;

/**
 * A Chest that can be placed on a GameMap (red)
 */
public class RedChest extends MapObject
{
    // private Item content;
    private boolean open;

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
        open = false;
    }

    @Override
    public void interact(GameCharacter source)
    {
        if (!open)
        {
            // source.giveItem(content);
            open = true;
        }
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
            case 2:
                sprite = Sprite.redChest03;
            case 3:
                sprite = Sprite.redChest04;
            case 4:
                sprite = Sprite.redChest05;
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
    public boolean canCollide()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void collideWith(MapObject mo)
    {
        throw new UnsupportedOperationException("RedChest can not collide with anything.");
    }
}
