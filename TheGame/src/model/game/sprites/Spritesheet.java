package model.game.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class representing a sprite-sheet. Contains
 */
public class Spritesheet
{
    /**
     * Width and Height of the sprite-sheet in pixels. Sprite-sheets have to be
     * square in this game.
     */
    public final int SIZE;

    private String path;
    private int[] pixels;

    public static final Spritesheet floorSpritesheet = new Spritesheet("/spritesheets/FloorSpritesheet.png", 256);
    public static final Spritesheet wallSpritesheet = new Spritesheet("/spritesheets/WallSpritesheet.png", 256);
    public static final Spritesheet characterSpritesheet = new Spritesheet("/spritesheets/CharacterSpritesheet.png", 256);
    public static final Spritesheet objectSpritesheet = new Spritesheet("/spritesheets/ObjectSpritesheet.png", 256);
    public static final Spritesheet lootSpritesheet = new Spritesheet("/spritesheets/LootSpritesheet.png", 256);
    public static final Spritesheet spellSpritesheet = new Spritesheet("/spritesheets/SpellSpritesheet.png", 256);
    public static final Spritesheet uiSpritesheet = new Spritesheet("/spritesheets/UISpritesheet.png", 256);

    public Spritesheet(String path, int size)
    {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    /**
     * Transform the image file into an array of pixels
     */
    private void load()
    {
        try
        {
            BufferedImage img = ImageIO.read(this.getClass().getResource(path));
            int width = img.getWidth();
            int height = img.getHeight();
            img.getRGB(0, 0, width, height, pixels, 0, width);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param i
     *            Index of the pixel
     * @return A single pixel of the sprite-sheet
     */
    public int getPixel(int i)
    {
        return pixels[i];
    }
}
