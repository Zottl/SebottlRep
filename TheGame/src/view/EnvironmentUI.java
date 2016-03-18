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
        
        // TODO: render health bar when enemy is in combat with the player(?)
        for (EnemyNpc enemy : map.getEnemyNpcs())
        {
            // Only render the healthbars of near enemies and enemies that got damaged in the last 8 seconds
            if ((System.currentTimeMillis() - enemy.getLastTimeDamaged() > 8000) && player.getCenterDistance(enemy) > 100) continue;
            
            // Get the amount of bar segments depending on enemy health (max 14 bar segments) 
            int barSegments = (int)((enemy.getHitpoints() * 14) / enemy.getMaxHitpoints());
            
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
