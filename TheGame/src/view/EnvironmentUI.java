package view;

import model.GameData;
import model.game.characters.EnemyNpc;
import model.game.maps.GameMap;
import model.game.sprites.Sprite;

public class EnvironmentUI
{
    private GameScreen gs;
    private GameData data;
    private GameMap map;

    public EnvironmentUI(GameScreen gs)
    {
        this.gs = gs;
        this.data = GameData.getInstance();        
        this.map = data.getMap();
    }
    
    public void renderEnvironmentUI(int xOffset, int yOffset)
    {
        renderEnemyHealthbars(xOffset, yOffset);
    }
    
    private void renderEnemyHealthbars(int xOffset, int yOffset)
    {
        for (EnemyNpc enemy : map.getEnemyNpcs())
        {
            // get the amount on bar segments depending on enemy health
            int barSegments = (int)((double)enemy.getHitpoints() / (double)enemy.getMaxHitpoints() * 14);
            
            // render the health frame
            gs.renderSprite(Sprite.enemyHealthFrame, (int)enemy.getX(), (int)enemy.getY() - 10, xOffset, yOffset);
            
            // Render the bar
            for (int i = 0; i < barSegments; i++)
            {
                gs.renderSprite(Sprite.enemyHealthBar, (int)enemy.getX() + 1 + i, (int)enemy.getY() - 9, xOffset, yOffset);
            }
        }
    }
}
