package view;

import java.awt.Canvas;
import java.awt.Dimension;

import model.GameData;
import model.game.characters.EnemyNpc;
import model.game.characters.Player;
import model.game.maps.GameMap;
import model.game.sprites.Sprite;
import model.game.tiles.Tile;

public class GameScreen extends Canvas
{

    private static final long serialVersionUID = 1L;

    private GameData data;
    private Player player;
    private EnemyNpc enemy;

    private int[] pixels;

    public int xOffset;
    public int yOffset;

    public GameScreen(View view, GameData data)
    {
        pixels = new int[View.WIDTH * View.SCALE * View.HEIGHT * View.SCALE];

        this.data = data;

        Dimension dim = new Dimension(View.WIDTH * View.SCALE, View.HEIGHT * View.SCALE);
        setPreferredSize(dim);
    }

    /**
     * Method responsible for rendering
     */
    public void render()
    {
        player = data.getPlayer();
        enemy = data.getEnemy();

        this.renderMap(data.getMap());

        // render first enemy
        this.renderSprite(enemy.getSprite(), enemy.getX(), enemy.getY());
        
        // render player
        this.renderSprite(player.getSprite(), player.getX(), player.getY());
    }

    /**
     * Paints the Sprite onto the screen
     * 
     * @param sprite
     *            Sprite to draw
     * @param x
     *            X position of the sprite on the map
     * @param y
     *            Y position of the sprite on the map
     * @param xOffset
     *            Current x offset of the view
     * @param yOffset
     *            Current y offset of the view
     */
    private void renderSprite(Sprite sprite, int x, int y)
    {
        int size = sprite.SIZE;

        // Scaled parameters
        int sx = x * View.SCALE;
        int sy = y * View.SCALE;
        int sWidth = View.WIDTH * View.SCALE;
        int sHeight = View.HEIGHT * View.SCALE;

        // Two loops to select every pixel of the sprite
        for (int px = 0; px < size; px++)
        {
            for (int py = 0; py < size; py++)
            {
                int xPos = sx + px * View.SCALE - xOffset;
                int yPos = sy + py * View.SCALE - yOffset;

                int index = xPos + yPos * sWidth;

                // Draw each pixel multiple times, depending on the scale
                for (int i = 0; i < View.SCALE * View.SCALE; i++)
                {
                    int ix = i % View.SCALE;
                    int iy = i / View.SCALE;

                    // Only draw pixel if it is inside the view
                    if (xPos + ix < sWidth && yPos + iy < sHeight && xPos + ix >= 0 && yPos + iy >= 0)
                    {
                        int col = sprite.getPixel(px + py * size);
                        int alph = col & 0xff000000;

                        // do not render transparent
                        if (alph != 0)
                        {
                            pixels[index + ix + iy * sWidth] = col;
                        }
                    }
                }
            }
        }
    }

    /**
     * Renders the map
     * 
     * @param map
     *            Map to render
     * @param xOffset
     *            Current x offset of the view
     * @param yOffset
     *            Current y offset of the view
     */
    private void renderMap(GameMap map)
    {
        // Scaled parameters
        int sTilesize = Tile.TILESIZE * View.SCALE;
        int sWidth = View.WIDTH * View.SCALE;
        int sHeight = View.HEIGHT * View.SCALE;

        // Two loops running over the screen pixels.
        for (int px = 0; px < sWidth + sTilesize; px += sTilesize)
        {
            if (px >= sWidth) px = sWidth - 1;

            for (int py = 0; py < sHeight + sTilesize; py += sTilesize)
            {
                if (py >= sHeight) py = sHeight - 1;

                int tileX = (px + xOffset) / sTilesize;
                int tileY = (py + yOffset) / sTilesize;
                Sprite sprite = map.getTile(tileX, tileY).getSprite();

                renderSprite(sprite, tileX * Tile.TILESIZE, tileY * Tile.TILESIZE);

                if (py == sHeight - 1) break;
            }

            if (px == sWidth - 1) break;
        }
    }

    /**
     * Center the screen at a certain coordinate
     * 
     * @param x
     *            x coordinate to center
     * 
     * @param y
     *            y coordinate to center
     */
    public void centerScreen(int x, int y)
    {
        int sWidth = View.WIDTH * View.SCALE;
        int sHeight = View.HEIGHT * View.SCALE;
        int sTilesize = Tile.TILESIZE * View.SCALE;

        xOffset = x * View.SCALE - sWidth / 2;
        yOffset = y * View.SCALE - sHeight / 2;

        // Limit Offset, so that the view never leaves the map
        if (xOffset < 0)
        {
            xOffset = 0;
        }
        if (xOffset > (data.getMap().getWidth()) * sTilesize - sWidth)
        {
            xOffset = (data.getMap().getWidth()) * sTilesize - sWidth;
        }
        if (yOffset < 0)
        {
            yOffset = 0;
        }
        if (yOffset > (data.getMap().getHeight()) * sTilesize - sHeight)
        {
            yOffset = (data.getMap().getHeight()) * sTilesize - sHeight;
        }
    }

    /**
     * Clear every pixel of the screen (set them to black)
     */
    public void clear()
    {
        // loop over every pixel and set it to 0
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0;
        }
    }

    /**
     * @param i
     *            Index of the pixel
     * @return The Pixel at index <b>i</b>
     */
    public int getPixel(int i)
    {
        return pixels[i];
    }
}
