package model.game.characters;

import model.game.sprites.Sprite;

public class NonPlayerCharacter extends Character
{
    public NonPlayerCharacter(int x, int y)
    {
        super(x, y, 10, Sprite.player01);
    }
}
