package controller.ai;

import model.GameData;
import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;

public class CoinAI extends MapObjectAI
{
    private int sparkleDelay;

    public CoinAI(double animationSpeed)
    {
        super(animationSpeed);
    }

    @Override
    public void advance()
    {
        animate();
    }

    @Override
    public void collisionWith(CollisionStatus cs)
    {
        if (cs == CollisionStatus.PLAYER_BODY)
        {
            GameData.getInstance().getMap().removeMapObject(parent);
        }
    }

    @Override
    public CollisionStatus[] getRelevantCollisionStatuses()
    {
        CollisionStatus[] statuses = { CollisionStatus.PLAYER_BODY };
        return statuses;
    }

    @Override
    public void animate()
    {
        animationState += animationSpeed;
        int aniStaNum = (int) animationState;

        switch (aniStaNum)
        {
            case 1:
                if (sparkleDelay == 0)
                {
                    parent.setSprite(Sprite.coinSparkle1);
                }
                else
                {
                    sparkleDelay--;
                    animationState--;
                }
                break;
            case 2:
                parent.setSprite(Sprite.coinSparkle2);
                break;
            case 3:
                parent.setSprite(Sprite.coinSparkle3);
                break;
            case 4:
                parent.setSprite(Sprite.coinSparkle4);
                break;
            case 5:
                parent.setSprite(Sprite.coinSparkle5);
                break;
            case 6:
                parent.setSprite(Sprite.coinSparkle6);
                break;
            case 7:
                parent.setSprite(Sprite.coinSparkle7);
                break;
            case 8:
                parent.setSprite(Sprite.coin01);
                animationState = 0;
                sparkleDelay = (int) (Math.random() * 40);
        }
    }
}
