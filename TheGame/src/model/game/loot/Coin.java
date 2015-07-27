package model.game.loot;

import model.game.characters.Character;
import model.game.sprites.Sprite;

public class Coin extends Loot
{

    public Coin(int x, int y)
    {
        super(x, y, 0.2, Sprite.coin01);
    }

    @Override
    public void interact(Character source)
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void advanceAnimation()
    {
        switch (animationState)
        {
            case 0:
                sprite = Sprite.coin02;
                animationState++;
                return;
            case 1:
                sprite = Sprite.coin03;
                animationState++;
                return;
            case 2:
                sprite = Sprite.coin04;
                animationState++;
                return;
            case 3:
                sprite = Sprite.coin05;
                animationState++;
                return;
            case 4:
                sprite = Sprite.coin06;
                animationState++;
                return;
            case 5:
                sprite = Sprite.coin07;
                animationState++;
                return;
            case 6:
                sprite = Sprite.coin08;
                animationState++;
                return;
            case 7:
                sprite = Sprite.coin01;
                animationState++;
                return;
            case 8:
                if (Math.random() > 0.95) animationState = 0;
                return;
            default:
                return;
        }
    }

}
