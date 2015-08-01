package model.game.sprites;

public class Sprite
{

    /**
     * Width of the sprite in pixels
     */
    public final int WIDTH;

    /**
     * Height of the sprite in pixels
     */
    public final int HEIGHT;

    private Spritesheet sheet; // The Spritesheet this Sprite is located on
    private int xOffset; // The horizontal offset on the Spritesheet (in pixels)
    private int yOffset; // The vertical offset on the Spritesheet (in pixels)

    private int[] pixels;

    // floor sprites
    public static final Sprite grass01 = new Sprite(Spritesheet.floorSpritesheet, 0, 0, 16, 16);
    public static final Sprite grass02 = new Sprite(Spritesheet.floorSpritesheet, 32, 0, 16, 16);
    public static final Sprite grass03 = new Sprite(Spritesheet.floorSpritesheet, 48, 0, 16, 16);
    public static final Sprite grass04 = new Sprite(Spritesheet.floorSpritesheet, 64, 0, 16, 16);
    public static final Sprite dirt01 = new Sprite(Spritesheet.floorSpritesheet, 16, 0, 16, 16);
    
    // wall sprites
    public static final Sprite wall01 = new Sprite(Spritesheet.wallSpritesheet, 0, 0, 16, 16);
    
    // character sprites
    public static final Sprite player01 = new Sprite(Spritesheet.characterSpritesheet, 0, 0, 16, 16);
    public static final Sprite enemy01 = new Sprite(Spritesheet.characterSpritesheet, 16, 0, 16, 16);
    
    // object sprites
    public static final Sprite redChest01 = new Sprite(Spritesheet.objectSpritesheet, 0, 0, 32, 32);
    public static final Sprite redChest02 = new Sprite(Spritesheet.objectSpritesheet, 32, 0, 32, 32);
    public static final Sprite redChest03 = new Sprite(Spritesheet.objectSpritesheet, 64, 0, 32, 32);
    public static final Sprite redChest04 = new Sprite(Spritesheet.objectSpritesheet, 96, 0, 32, 32);
    public static final Sprite redChest05 = new Sprite(Spritesheet.objectSpritesheet, 128, 0, 32, 32);
    
    // loot sprites
    public static final Sprite coin01 = new Sprite(Spritesheet.lootSpritesheet, 0, 0, 16, 16);
    public static final Sprite coinSparkle1 = new Sprite(Spritesheet.lootSpritesheet, 16, 0, 16, 16);
    public static final Sprite coinSparkle2 = new Sprite(Spritesheet.lootSpritesheet, 32, 0, 16, 16);
    public static final Sprite coinSparkle3 = new Sprite(Spritesheet.lootSpritesheet, 48, 0, 16, 16);
    public static final Sprite coinSparkle4 = new Sprite(Spritesheet.lootSpritesheet, 64, 0, 16, 16);
    public static final Sprite coinSparkle5 = new Sprite(Spritesheet.lootSpritesheet, 80, 0, 16, 16);
    public static final Sprite coinSparkle6 = new Sprite(Spritesheet.lootSpritesheet, 96, 0, 16, 16);
    public static final Sprite coinSparkle7 = new Sprite(Spritesheet.lootSpritesheet, 112, 0, 16, 16);
    public static final Sprite coinSpin1 = new Sprite(Spritesheet.lootSpritesheet, 128, 0, 16, 16);
    public static final Sprite coinSpin2 = new Sprite(Spritesheet.lootSpritesheet, 144, 0, 16, 16);
    public static final Sprite coinSpin3 = new Sprite(Spritesheet.lootSpritesheet, 160, 0, 16, 16);
    
    // spell sprites
    public static final Sprite testSpell01 = new Sprite(Spritesheet.spellSpritesheet, 0, 0, 16, 16);
    public static final Sprite testSpell02 = new Sprite(Spritesheet.spellSpritesheet, 32, 0, 16, 16);
    public static final Sprite testSpell03 = new Sprite(Spritesheet.spellSpritesheet, 48, 0, 16, 16);

    public Sprite(Spritesheet sheet, int xOffset, int yOffset, int width, int height)
    {
        this.sheet = sheet;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.WIDTH = width;
        this.HEIGHT = height;

        pixels = new int[WIDTH * HEIGHT];

        load();
    }

    /**
     * Use the sprite-sheet and the offset information of this sprite to fill
     * the pixels array
     */
    private void load()
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                pixels[x + y * WIDTH] = sheet.getPixel((xOffset + x) + (yOffset + y) * sheet.SIZE);
            }
        }
    }

    /**
     * @param i
     *            Index of the pixel
     * @return A single pixel of the sprite
     */
    public int getPixel(int i)
    {
        return pixels[i];
    }
}
