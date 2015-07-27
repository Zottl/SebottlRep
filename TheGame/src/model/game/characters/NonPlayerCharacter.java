package model.game.characters;

import model.game.maps.GameMap;
import model.game.sprites.Sprite;

public abstract class NonPlayerCharacter extends Character
{
    public NonPlayerCharacter(int x, int y, Sprite sprite, GameMap map)
    {
        super(x, y, 10, 0.04, sprite, map);
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
