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
    
    private static final int SHOW_HEALTHBAR_AFTER_LAST_DAMAGE_TIME = 8000; // how long the healthbar of an enemy is visible after the last damage he received in ms
    private static final int HEALTHBAR_RENDERING_MAX_DISTANCE = 100; // the distance to the player in which to render the enemy healthbars

    public EnvironmentUI(GameScreen gs)
    {
        this.gs = gs;
        this.data = GameData.getInstance();
        this.map = data.getMap();
    }
    
    public void renderEnvironmentUI()
    {
        renderEnemyHealthbars();
    }
    
    private void renderEnemyHealthbars()
    {
        Player player = data.getPlayer();
        
        // TODO: - maybe make a method "renderFloatingHealthbar" which is called by a creature and renders the creatures healthbar
        //         because it might be more performant
        //       - render health bar when enemy is in combat with the player(?)
        //       - make size of healthbar variable
        //       - remove health pixel per pixel instead of barSegments
        for (EnemyNpc enemy : map.getEnemyNpcs())
        {
            // Only render the healthbars of near enemies and enemies that got damaged in the last 8 seconds
            if ((enemy.getHitpoints() <= 0) || ((System.currentTimeMillis() - enemy.getLastTimeDamaged() > SHOW_HEALTHBAR_AFTER_LAST_DAMAGE_TIME) 
                    && player.getCenterDistance(enemy) > HEALTHBAR_RENDERING_MAX_DISTANCE)) continue;

            // Get the amount of bar segments depending on enemy health (max 14 bar segments) 
            int barSegments = (int)((enemy.getHitpoints() * 14) / enemy.getMaxHitpoints());
            
            // Render the health frame
            gs.renderSprite(Sprite.enemyHealthFrame, (int)enemy.getX(), (int)enemy.getY() - 10);
            
            // Render the bar
            for (int i = 0; i < barSegments; i++)
            {
                gs.renderSprite(Sprite.enemyHealthBar, (int)enemy.getX() + 1 + i, (int)enemy.getY() - 9);
            }
        }
    }
}
