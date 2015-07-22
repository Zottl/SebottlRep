package model.game.tiles;

import model.game.sprites.Sprite;

public class DirtTile extends Tile
{

    public DirtTile(int x, int y)
    {
        super(x, y, false, Sprite.dirt01);
    }
}
