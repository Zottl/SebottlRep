package view;

import controller.GameController;
import model.game.maps.GameMap;
import model.game.object.MapObject;
import model.game.tiles.Tile;

/**
 * Class that handles the rendering of game maps.
 */
public class MapRenderer
{
    private GameScreen gs;

    public MapRenderer(GameScreen gs)
    {
        this.gs = gs;
    }

    /**
     * Renders the map
     * 
     * @param map
     *            Map to render
     */
    public void renderMap(GameMap map)
    {
        int tilesize = Tile.TILESIZE;
        int width = View.WIDTH;
        int height = View.HEIGHT;
        
        int xOffset = GameController.xScreenOffset;
        int yOffset = GameController.yScreenOffset;

        int horTileCount = (int) Math.ceil(width / (double) tilesize);
        int vertTileCount = (int) Math.ceil(height / (double) tilesize);

        // Two loops running over the screen pixels.
        for (int x = 0; x <= horTileCount; x++)
        {
            /*
             * Because the view width is not a multiple of the tile size, we
             * need to be careful not to select a pixel that is outside of the
             * view (and therefore maybe outside of the map) while also making
             * sure to cover the whole screen. Because of this, we check the x
             * coordinate width-1 and the y coordinate height-1 manually (the
             * pixels at the lower and right borders).
             */
            int xPos = x < horTileCount ? x * tilesize : width - 1;
            xPos += xOffset;

            for (int y = 0; y <= vertTileCount; y++)
            {
                int yPos = y < vertTileCount ? y * tilesize : height - 1;
                yPos += yOffset;

                Tile tile = map.getTile(xPos, yPos);

                gs.renderSprite(tile.getSprite(), (int) tile.getX(), (int) tile.getY());
            }
        }

        for (MapObject mo : map.getObjects())
        {
            gs.renderSprite(mo.getSprite(), (int) mo.getX(), (int) mo.getY());
        }
    }
}
