package model.game.characters;

import model.game.maps.GameMap;
import model.game.sprites.Sprite;

public class FriendlyNpc extends NonPlayerCharacter
{
    public FriendlyNpc(int x, int y, Sprite sprite, GameMap map)
    {
        super(x, y, sprite, map);
    }
}
