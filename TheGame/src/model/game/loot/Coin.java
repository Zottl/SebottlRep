package model.game.loot;

import model.game.object.Hitbox;
import model.game.sprites.Sprite;

public class Coin extends Loot
{
    private int sparkleDelay;

    public Coin(int x, int y)
    {
        super(x, y, 0.2, Sprite.coin01, new Hitbox(3, 4, 9, 9));
    }

    @Override
    public void advanceAnimation()
    {
        animationState += animationSpeed;
        int stateNumber = (int) animationState;

        switch (stateNumber)
        {
            case 1:
                if (sparkleDelay == 0)
                {
                    sprite = Sprite.coinSparkle1;
                }
                else
                {
                    sparkleDelay--;
                    animationState--;
                }
                break;
            case 2:
                sprite = Sprite.coinSparkle2;
                break;
            case 3:
                sprite = Sprite.coinSparkle3;
                break;
            case 4:
                sprite = Sprite.coinSparkle4;
                break;
            case 5:
                sprite = Sprite.coinSparkle5;
                break;
            case 6:
                sprite = Sprite.coinSparkle6;
                break;
            case 7:
                sprite = Sprite.coinSparkle7;
                break;
            case 8:
                sprite = Sprite.coin01;
                animationState = 0;
                sparkleDelay = (int) (Math.random() * 40);
        }
    }

    @Override
    public boolean isGhost()
    {
        return false;
    }
}
