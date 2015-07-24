package model.game.sprites;

public class Sprite
{
    /**
     * Width and Height of the sprite in pixels. Sprites have to be square in
     * this game.
     */
    public final int SIZE;

    private Spritesheet sheet;
    private int x;
    private int y;

    private int[] pixels;

    public static final Sprite grass01 = new Sprite(Spritesheet.floorSpritesheet, 0, 0, 16);
    public static final Sprite grass02 = new Sprite(Spritesheet.floorSpritesheet, 2, 0, 16);
    public static final Sprite grass03 = new Sprite(Spritesheet.floorSpritesheet, 3, 0, 16);
    public static final Sprite grass04 = new Sprite(Spritesheet.floorSpritesheet, 4, 0, 16);
    public static final Sprite dirt01 = new Sprite(Spritesheet.floorSpritesheet, 1, 0, 16);
    public static final Sprite wall01 = new Sprite(Spritesheet.wallSpritesheet, 0, 0, 16);
    public static final Sprite player01 = new Sprite(Spritesheet.characterSpritesheet, 0, 0, 16);

    public Sprite(Spritesheet sheet, int x, int y, int size)
    {
        this.sheet = sheet;
        this.x = x;
        this.y = y;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    /**
     * Transform the sprite-sheet and the position of this sprite into an array
     * of pixels
     */
    private void load()
    {
        for (int x = 0; x < SIZE; x++)
        {
            for (int y = 0; y < SIZE; y++)
            {
                pixels[x + y * SIZE] = sheet.getPixel((x + this.x * SIZE) + (y + this.y * SIZE) * sheet.SIZE);
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
