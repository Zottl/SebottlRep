package model.game.tiles;

import model.game.sprites.Sprite;

public class WallTile extends Tile
{

    public WallTile(int x, int y)
    {
        super(x, y, false, Sprite.wall01);
    }
}
