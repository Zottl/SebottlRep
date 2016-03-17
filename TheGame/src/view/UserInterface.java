package view;

import model.GameData;
import model.game.sprites.Sprite;

public class UserInterface
{
    private GameScreen gs;
    private GameData data;

    public UserInterface(GameScreen gs)
    {
        this.gs = gs;
        this.data = GameData.getInstance();
    }

    public void renderUI(int xOffset, int yOffset)
    {
        renderHealthBar(xOffset, yOffset);
    }

    private void renderHealthBar(int xOffset, int yOffset)
    {
        int maxHealth = data.getPlayer().getMaxHitpoints();
        int health = data.getPlayer().getHitpoints();

        // Properties of the HealthBar sprites
        int sideWidth = 32, sideHealth = 25;
        int midWidth = 10, midHealth = 10;
        int barOffset = sideWidth - sideHealth;

        // Render the bar
        for (int i = 0; i < health; i++)
        {
            Sprite sprite;
            switch (i % 6)
            {
                case 1:
                    sprite = Sprite.healthBar01;
                    break;
                case 2:
                    sprite = Sprite.healthBar02;
                    break;
                case 3:
                    sprite = Sprite.healthBar03;
                    break;
                case 4:
                    sprite = Sprite.healthBar04;
                    break;
                case 5:
                    sprite = Sprite.healthBar05;
                    break;
                default:
                    sprite = Sprite.healthBar06;
                    break;
            }
            gs.renderSprite(sprite, xOffset + barOffset + i, yOffset, xOffset, yOffset);
        }

        // Render the frame
        int healthSections = (maxHealth - 2 * sideHealth) / midHealth;

        gs.renderSprite(Sprite.healthFrameLeft, xOffset, yOffset, xOffset, yOffset);
        for (int i = 0; i < healthSections; i++)
        {
            gs.renderSprite(Sprite.healthFrameMid, xOffset + sideWidth + i * midWidth, yOffset, xOffset, yOffset);
        }
        gs.renderSprite(Sprite.healthFrameRight, xOffset + sideWidth + healthSections * midWidth, yOffset, xOffset, yOffset);
    }
}