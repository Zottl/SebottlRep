package model;

import java.util.ArrayList;
import java.util.List;

import model.game.characters.Player;
import model.game.maps.GameMap;
import model.game.maps.GrassMap01;
import model.game.maps.GrassMap02;
import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.spell.characterSpell.TestSpell;
import model.game.sprites.Sprite;
import model.game.tiles.Tile;
import view.View;
import controller.input.Keyboard;
import controller.input.Mouse;

public class GameData
{
    // Instance for Singleton-pattern
    private static final GameData INSTANCE = new GameData();

    private GameMap currentMap;
    private Player player;

    /**
     * @param mapID
     *            MapID the game starts with
     */
    private GameData()
    {
        changeMap(1);
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
     * Creates the player object. Can only be called once. (If a player object
     * already exists, this method will fail)
     * 
     * @param kb
     *            The keyboard object to control the player object
     * @param ms
     *            The Mouse object to control the player object
     * @return The player object that was created
     */
    public Player createPlayer(Keyboard kb, Mouse ms)
    {
        if (player != null)
        {
            System.err.println("[GameData] Someone tried to create second player object!");
            return null;
        }

        List<Spell> spelllist = new ArrayList<Spell>();
        spelllist.add(new TestSpell());

        player = new Player(View.WIDTH / 2 - Tile.TILESIZE / 2, View.HEIGHT / 2 - Tile.TILESIZE / 2, spelllist, Sprite.player01, new Hitbox(0, 0, 16, 16), kb,
                ms);
        return player;
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

    /**
     * @return The instance of the game data (see Singleton-pattern)
     */
    public static GameData getInstance()
    {
        return INSTANCE;
    }
}
