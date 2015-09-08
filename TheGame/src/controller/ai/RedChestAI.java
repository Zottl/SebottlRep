package controller.ai;

import model.game.sprites.Sprite;

public class RedChestAI extends MapObjectAI
{
    public RedChestAI(double animationSpeed)
    {
        super(animationSpeed);
    }

    @Override
    public void advance()
    {
        animate();
    }

    @Override
    protected void animate()
    {
        animationState += animationSpeed;
        int stateNumber = (int) animationState;

        switch (stateNumber)
        {
            case 1:
                parent.setSprite(Sprite.redChest02);
                break;
            case 2:
                parent.setSprite(Sprite.redChest03);
                break;
            case 3:
                parent.setSprite(Sprite.redChest04);
                break;
            case 4:
                parent.setSprite(Sprite.redChest05);
                break;
            case 8:
                parent.setSprite(Sprite.redChest01);
                animationState = 0;
        }
    }
}
