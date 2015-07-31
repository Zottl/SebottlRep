package model.game.loot;

import model.game.characters.GameCharacter;
import model.game.sprites.Sprite;

public class Coin extends Loot
{
    private int sparkleDelay;

    public Coin(int x, int y)
    {
        super(x, y, 0.2, Sprite.coin01);
    }

    @Override
    public void interact(GameCharacter source)
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void advanceAnimation()
    {
        boolean sparkling = false;
        switch (animationState)
        {
            case 0:
                if (sparkling)
                {
                    sprite = Sprite.coinSparkle1;
                    animationState++;
                }
                else
                {
                    sprite = Sprite.coinSpin1;
                    animationState = 8;

                }
                return;
            case 1:
                sprite = Sprite.coinSparkle2;
                animationState++;
                return;
            case 2:
                sprite = Sprite.coinSparkle3;
                animationState++;
                return;
            case 3:
                sprite = Sprite.coinSparkle4;
                animationState++;
                return;
            case 4:
                sprite = Sprite.coinSparkle5;
                animationState++;
                return;
            case 5:
                sprite = Sprite.coinSparkle6;
                animationState++;
                return;
            case 6:
                sprite = Sprite.coinSparkle7;
                animationState++;
                return;
            case 7:
                sprite = Sprite.coin01;
                animationState = 13;
                sparkleDelay = (int) (Math.random() * 40);
                return;
            case 8:
                sprite = Sprite.coinSpin2;
                animationState++;
                return;
            case 9:
                sprite = Sprite.coinSpin3;
                animationState++;
                return;
            case 10:
                sprite = Sprite.coinSpin2;
                animationState++;
                return;
            case 11:
                sprite = Sprite.coinSpin1;
                animationState++;
                return;
            case 12:
                sprite = Sprite.coin01;
                animationState = 0;
                return;
            case 13:
                if (sparkleDelay == 0)
                    animationState = 0;
                else
                    sparkleDelay--;
                return;
            default:
                return;
        }
    }
}
