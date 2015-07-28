package model.game.characters;

import model.game.maps.GameMap;
import model.game.sprites.Sprite;

// make abstract and let "mage" inherit
public class Player extends Character
{

    public Player(int x, int y, Sprite sprite, GameMap map)
    {
        super(x, y, 1.5, 0.04, sprite, map);
    }

    @Override
    public void interact(Character source)
    {

    }

    @Override
    protected void advanceAnimation()
    {

    }
}
