package view;

import model.GameData;
import model.game.sprites.Sprite;

/**
 * Class that handles the rendering of the game.
 */
public class GameScreen
{
    private GameData data;
    private MapRenderer mr;
    private EnvironmentUI eui;
    private UserInterface ui;

    private int[] pixels;

    /**
     * Class that handles the rendering of the game.
     * 
     * @param data
     *            Game data that will be rendered.
     */
    public GameScreen()
    {
        pixels = new int[View.WIDTH * View.SCALE * View.HEIGHT * View.SCALE];

        data = GameData.getInstance();
        mr = new MapRenderer(this);
        eui = new EnvironmentUI(this);
        ui = new UserInterface(this);
    }

    /**
     * Method responsible for rendering
     */
    public void render(int xOffset, int yOffset)
    {
        mr.renderMap(data.getMap(), xOffset, yOffset);
        eui.renderEnvironmentUI(xOffset, yOffset);
        ui.renderUI();
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
    public void renderSprite(Sprite sprite, int x, int y, int xOffset, int yOffset)
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
                        int alph = col & 0xFF000000;

                        // TODO: partial transparency is not handled correctly here
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
     * @return The UserInterface
     */
    public UserInterface getUserInterface()
    {
        return this.ui;
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
