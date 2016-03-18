package view;

import model.GameData;
import model.game.characters.EnemyNpc;
import model.game.characters.Player;
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
        Player player = data.getPlayer();
        
        // TODO: healthbar rendering depending on last time dmg received and in combat with player
        for (EnemyNpc enemy : map.getEnemyNpcs())
        {
            // Only render the healthbars of near enemies
            if (player.getCenterDistance(enemy) > 100) continue;
            
            // Get the amount of bar segments depending on enemy health
            int barSegments = (int)((double)enemy.getHitpoints() / (double)enemy.getMaxHitpoints() * 14);
            
            // Render the health frame
            gs.renderSprite(Sprite.enemyHealthFrame, (int)enemy.getX(), (int)enemy.getY() - 10, xOffset, yOffset);
            
            // Render the bar
            for (int i = 0; i < barSegments; i++)
            {
                gs.renderSprite(Sprite.enemyHealthBar, (int)enemy.getX() + 1 + i, (int)enemy.getY() - 9, xOffset, yOffset);
            }
        }
    }
}
