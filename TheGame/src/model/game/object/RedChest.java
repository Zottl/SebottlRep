package model.game.object;

import model.game.sprites.Sprite;

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
        super(x, y, Sprite.redChest);
    }

    @Override
    public void interact(Character source)
    {
        // source.giveItem(content);
    }
}
