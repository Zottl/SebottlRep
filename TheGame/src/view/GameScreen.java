package view;

import model.GameData;
import model.game.maps.GameMap;
import model.game.object.MapObject;
import model.game.sprites.Sprite;
import model.game.tiles.Tile;

/**
 * Class that handles the rendering of the game.
 */
public class GameScreen
{
    private GameData data;

    private int[] pixels;

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
    }

    /**
     * Method responsible for rendering
     */
    public void render(int xOffset, int yOffset)
    {
        this.renderMap(data.getMap(), xOffset, yOffset);
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
    private void renderSprite(Sprite sprite, int x, int y, int xOffset, int yOffset)
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
    private void renderMap(GameMap map, int xOffset, int yOffset)
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

                renderSprite(tile.getSprite(), (int) tile.getX(), (int) tile.getY(), xOffset, yOffset);
            }
        }

        for (MapObject mo : map.getObjects())
        {
            renderSprite(mo.getSprite(), (int) mo.getX(), (int) mo.getY(), xOffset, yOffset);
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
