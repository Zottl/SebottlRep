package model.game.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.game.object.MapObject;
import model.game.tiles.DirtTile;
import model.game.tiles.Grass01Tile;
import model.game.tiles.Grass02Tile;
import model.game.tiles.Grass03Tile;
import model.game.tiles.Grass04Tile;
import model.game.tiles.Tile;
import model.game.tiles.WallTile;

/**
 * Abstract Class for a map in the game.
 */
public abstract class GameMap
{

    // Width of the map (in pixels)
    private int width;
    // Height of the map (in pixels)
    private int height;

    // The Array of the tiles this map consists of
    private Tile[][] tiles;

    // The list of MapObjects, that are present on this map
    private List<MapObject> objects;

    /**
     * @param tileIDs
     *            The array of TileIDs this GameMap consists of
     */
    public GameMap(int[][] tileIDs)
    {
        int horTileCount = tileIDs.length;
        int vertTileCount = tileIDs[0].length;

        width = horTileCount * Tile.TILESIZE;
        height = vertTileCount * Tile.TILESIZE;

        tiles = new Tile[horTileCount][vertTileCount];

        for (int x = 0; x < horTileCount; x++)
        {
            for (int y = 0; y < vertTileCount; y++)
            {
                // The x and y coordinates are scaled, based on the Tile size
                tiles[x][y] = createTile(x * Tile.TILESIZE, y * Tile.TILESIZE, tileIDs[x][y]);
            }
        }

        objects = new ArrayList<MapObject>();
    }

    /**
     * Make a new Tile to build a map
     * 
     * @param x
     *            X-position of the Tile
     * @param y
     *            Y-position of the Tile
     * @param id
     *            Type of the Tile
     *            <ul>
     *            <li>[00->Grass01]</li>
     *            <li>[01->Dirt]</li>
     *            <li>[02->Wall]</li>
     *            <li>[03->Grass02]</li>
     *            <li>[04->Grass03]</li>
     *            <li>[05->Grass04]</li>
     *            </ul>
     * @return
     */
    private Tile createTile(int x, int y, int id)
    {
        switch (id)
        {
            case 0:
                return new Grass01Tile(x, y);
            case 1:
                return new DirtTile(x, y);
            case 2:
                return new WallTile(x, y);
            case 3:
                return new Grass02Tile(x, y);
            case 4:
                return new Grass03Tile(x, y);
            case 5:
                return new Grass04Tile(x, y);
            default:
                return null;
        }
    }

    /**
     * Load map data from a file
     * 
     * @param filePath
     *            Path to the file that will be read
     * @return An array with the Tile data for the map, that has been extracted
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
     *            x-position of the Tile
     * @param y
     *            y-position of the Tile
     * @return The Tile at position (x,y) on this GameMap
     */
    public Tile getTile(int x, int y)
    {
        // Get the top left position of the Tile
        x -= x % Tile.TILESIZE;
        y -= y % Tile.TILESIZE;

        return tiles[x / Tile.TILESIZE][y / Tile.TILESIZE];
    }

    /**
     * @return The width of this GameMap (in pixels)
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return The height of this GameMap (in pixels)
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param mo
     *            The MapObject to place on this GameMap
     */
    public void addMapObject(MapObject mo)
    {
        objects.add(mo);
    }

    /**
     * @param mo
     *            The MapObject to remove from this GameMap
     */
    public void removeMapObject(MapObject mo)
    {
        objects.remove(mo);
    }

    /**
     * @return The MapObjects that are placed on this GameMap.
     */
    public List<MapObject> getObjects()
    {
        return objects;
    }
}
