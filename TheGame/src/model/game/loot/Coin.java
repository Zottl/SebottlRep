package model.game.loot;

import model.game.characters.GameCharacter;
import model.game.object.MapObject;
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
        // TODO
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
            case 2:
                sprite = Sprite.coinSparkle2;
            case 3:
                sprite = Sprite.coinSparkle3;
            case 4:
                sprite = Sprite.coinSparkle4;
            case 5:
                sprite = Sprite.coinSparkle5;
            case 6:
                sprite = Sprite.coinSparkle6;
            case 7:
                sprite = Sprite.coinSparkle7;
            case 8:
                sprite = Sprite.coin01;
                animationState = 0;
                sparkleDelay = (int) (Math.random() * 40);
        }
    }

    @Override
    public boolean canCollide()
    {
        return true;
    }

    @Override
    public void collideWith(MapObject mo)
    {
        // TODO
    }
}
