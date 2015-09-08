package model.game.loot;

import controller.ai.CoinAI;
import model.game.object.Hitbox;
import model.game.sprites.Sprite;

public class Coin extends Loot
{
    public Coin(int x, int y)
    {
        super(x, y, Sprite.coin01, new Hitbox(3, 4, 9, 9), new CoinAI(0.1));
    }

    @Override
    public boolean isGhost()
    {
        return false;
    }
}
