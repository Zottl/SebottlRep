package model.game.characters;

import model.game.maps.GameMap;
import model.game.sprites.Sprite;

public class Player extends Character
{

    public Player(int x, int y, Sprite sprite, GameMap map)
    {
        super(x, y, 1.5, sprite, map);
    }
}
