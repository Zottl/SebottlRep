package view;

import java.awt.Canvas;
import java.awt.Dimension;

import model.GameData;
import model.game.characters.Player;
import model.game.maps.GameMap;
import model.game.object.MapObject;
import model.game.sprites.Sprite;
import model.game.tiles.Tile;

/**
 * Class that handles the rendering of the game.
 */
public class GameScreen extends Canvas
{

    private static final long serialVersionUID = 1L;

    private GameData data;
    private Player player;

    private int[] pixels;

    public int xOffset;
    public int yOffset;

    /**
     * Class that handles the rendering of the game.
     * 
     * @param data
     *            Game data that will be rendered.
     */
    public GameScreen(GameData data)
    {
        pixels = new int[View.WIDTH * View.SCALE * View.HEIGHT * View.SCALE];

        this.data = data;

        Dimension dim = new Dimension(View.WIDTH * View.SCALE, View.HEIGHT * View.SCALE);
        this.setPreferredSize(dim);
        this.setIgnoreRepaint(true);
    }

    /**
     * Method responsible for rendering
     */
    public void render()
    {
        player = data.getPlayer();

        this.renderMap(data.getMap());

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
        int spriteWidth = sprite.WIDTH;
        int spriteHeight = sprite.HEIGHT;
        int scale = View.SCALE;
        int scaledViewWidth = View.WIDTH * scale;
        int scaledViewHeight = View.HEIGHT * scale;

        // Two loops to select every pixel of the sprite
        for (int px = 0; px < spriteWidth; px++)
        {
            for (int py = 0; py < spriteHeight; py++)
            {
                int xPos = (x + px - xOffset) * scale;
                int yPos = (y + py - yOffset) * scale;

                int index = xPos + yPos * scaledViewWidth;

                // Draw each pixel multiple times, depending on the scale
                for (int i = 0; i < scale * scale; i++)
                {
                    int ix = i % scale;
                    int iy = i / scale;

                    // Only draw pixel if it is inside the view
                    if (xPos + ix < scaledViewWidth && yPos + iy < scaledViewHeight && xPos + ix >= 0 && yPos + iy >= 0)
                    {
                        int col = sprite.getPixel(px + py * spriteWidth);
                        int alph = col & 0xff000000;

                        // do not render transparent
                        if (alph != 0)
                        {
                            pixels[index + ix + iy * scaledViewWidth] = col;
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
        int tilesize = Tile.TILESIZE;
        int width = View.WIDTH;
        int height = View.HEIGHT;

        int horTileCount = (int) Math.ceil(width / (double) tilesize);
        int vertTileCount = (int) Math.ceil(height / (double) tilesize);

        // Two loops running over the screen pixels.
        for (int x = 0; x <= horTileCount; x++)
        {
            /*
             * Because the view width is not a multiple of the tile size, we
             * need to be careful not to select a pixel that is outside of the
             * view (and therefore maybe outside of the map) while also making
             * sure to cover the whole map. Because of this, we check the x
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

                renderSprite(tile.getSprite(), tile.getX(), tile.getY());
            }
        }

        for (MapObject mo : map.getObjects())
        {
            renderSprite(mo.getSprite(), mo.getX(), mo.getY());
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
        // Scaled parameters
        int width = View.WIDTH;
        int height = View.HEIGHT;
        int mapWidth = data.getMap().getWidth();
        int mapHeight = data.getMap().getHeight();

        xOffset = x - width / 2;
        yOffset = y - height / 2;

        // Limit Offset, so that the view never leaves the map
        if (xOffset < 0)
        {
            xOffset = 0;
        }
        if (xOffset > mapWidth - width)
        {
            xOffset = mapWidth - width;
        }
        if (yOffset < 0)
        {
            yOffset = 0;
        }
        if (yOffset > mapHeight - height)
        {
            yOffset = mapHeight - height;
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
     * @return The Pixel at index {@code i}
     */
    public int getPixel(int i)
    {
        return pixels[i];
    }
}
