package model.game.characters;

import model.game.maps.GameMap;
import model.game.sprites.Sprite;

public class NonPlayerCharacter extends Character
{
    public NonPlayerCharacter(int x, int y, Sprite sprite, GameMap map)
    {
        super(x, y, 10, sprite, map);
    }
}
