package model.game.characters;

import java.util.List;

import model.game.maps.GameMap;
import model.game.spell.Spell;
import model.game.sprites.Sprite;

public class Player extends GameCharacter
{

    public Player(int x, int y, List<Spell> spells, Sprite sprite, GameMap map)
    {
        super(x, y, 1.5, spells, 0.04, sprite, map);
    }

    @Override
    public void interact(GameCharacter source)
    {

    }

    @Override
    protected void advanceAnimation()
    {

    }
}

