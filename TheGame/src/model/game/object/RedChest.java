package model.game.object;

import model.game.sprites.Sprite;
import model.game.characters.Character;

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
        super(x, y, 0.04, Sprite.redChest01);
        open = false;
    }

    @Override
    public void interact(Character source)
    {
        if (!open)
        {
            // source.giveItem(content);
            open = true;
        }
    }

    @Override
    protected void advanceAnimation()
    {
//        if (!open)
//            return;
        switch (animationState)
        {
            case 0:
                sprite = Sprite.redChest02;
                animationState++;
                return;
            case 1:
                sprite = Sprite.redChest03;
                animationState++;
                return;
            case 2:
                sprite = Sprite.redChest04;
                animationState++;
                return;
            case 3:
                sprite = Sprite.redChest05;
                animationState++;
                return;
            case 4:
                animationState++;
                return;
            case 5:
                animationState++;
                return;
            case 6:
                animationState++;
                return;
            case 7:
                sprite = Sprite.redChest01;
                animationState = 0;
                return;
            default:
                return;
        }
    }
}
