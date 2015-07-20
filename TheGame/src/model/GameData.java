package model;

import model.game.characters.Player;
import model.game.maps.GameMap;
import model.game.maps.GrassMap01;
import model.game.maps.GrassMap02;

public class GameData
{

    public static final int TILE_SIZE = 30;

    private GameMap currentMap;
    private Player player;

    /**
     * @param mapID
     *            MapID the game starts with
     */
    public GameData(int mapID)
    {
        super();
        changeMap(mapID);
        this.player = new Player(35, 35);
    }

    /**
     * @param mapID
     *            ID of the map to change to [01->GrassMap01] [02->GrassMap02]
     */
    public void changeMap(int mapID)
    {
        switch (mapID)
        {
            case 1:
                currentMap = new GrassMap01();
                break;
            case 2:
                currentMap = new GrassMap02();
                break;
            default:
                break;
        }
    }

    /**
     * @return The map that is currently loaded
     */
    public GameMap getMap()
    {
        return currentMap;
    }

    /**
     * @return The player character
     */
    public Player getPlayer()
    {
        return player;
    }
}
