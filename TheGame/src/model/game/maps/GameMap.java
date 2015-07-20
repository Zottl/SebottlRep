package model.game.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.game.tiles.DirtTile;
import model.game.tiles.GrassTile;
import model.game.tiles.Tile;
import model.game.tiles.WallTile;

/**
 * Abstract Class for a map in the game.
 */
public abstract class GameMap
{

    // Width of the map (in tiles)
    private int width;
    // Height of the map (in tiles)
    private int height;

    // The Array of the tiles this map consists of
    private Tile[][] tiles;

    /**
     * @param tileIDs
     *            The array of TileIDs this map consists of
     */
    public GameMap(int[][] tileIDs)
    {
        width = tileIDs.length;
        height = tileIDs[0].length;

        tiles = new Tile[width][height];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tiles[x][y] = createTile(x, y, tileIDs[x][y]);
            }
        }
    }

    /**
     * Make a new Tile to build a map
     * 
     * @param x
     *            x-position of the tile
     * @param y
     *            y-position of the tile
     * @param id
     *            Type of the tile [01->Grass] [02->Dirt] [03->Wall]
     * @return
     */
    private Tile createTile(int x, int y, int id)
    {
        switch (id)
        {
            case 0:
                return new GrassTile(x, y);
            case 1:
                return new DirtTile(x, y);
            case 2:
                return new WallTile(x, y);
            default:
                return null;
        }
    }

    /**
     * Load map data from a file
     * 
     * @param filePath
     *            Path to the file that will be read
     * @return An array with the tile data for the map, that has been extracted
     *         from the file
     */
    protected static int[][] loadMap(String filePath)
    {
        int[][] tileIDs = null;
        try
        {
            Scanner s = new Scanner(new File(filePath));
            s.useDelimiter(",");

            int width = s.nextInt();
            int height = s.nextInt();
            s.nextLine();

            tileIDs = new int[width][height];

            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    tileIDs[x][y] = s.nextInt();
                }
                if (s.hasNextLine()) s.nextLine();
            }
            s.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return tileIDs;
    }

    /**
     * @param x
     *            x-position of the tile
     * @param y
     *            y-position of the tile
     * @return The tile at position (x,y) on this map
     */
    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    /**
     * @return The width of this map
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return The height of this map
     */
    public int getHeight()
    {
        return height;
    }
}
