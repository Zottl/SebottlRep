package model.game.characters;

import model.game.sprites.Sprite;

public class Player extends Character
{
    public Player(int x, int y)
    {
        super(x, y, 10, Sprite.player01);
    }
}
