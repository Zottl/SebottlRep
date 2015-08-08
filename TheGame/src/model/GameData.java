package model;

import java.util.ArrayList;
import java.util.List;

import model.game.characters.Player;
import model.game.maps.GameMap;
import model.game.maps.GrassMap01;
import model.game.maps.GrassMap02;
import model.game.object.Hitbox;
import model.game.spell.Spell;
import model.game.spell.TestSpell;
import model.game.sprites.Sprite;
import model.game.tiles.Tile;
import view.View;

public class GameData
{
    // Instance for Singleton-pattern
    private static final GameData INSTANCE = new GameData();

    private GameMap currentMap;
    public Player player;

    /**
     * @param mapID
     *            MapID the game starts with
     */
    private GameData()
    {
        changeMap(1);

        List<Spell> spelllist = new ArrayList<Spell>();
        spelllist.add(new TestSpell(currentMap));

        player = new Player(View.WIDTH / 2 - Tile.TILESIZE / 2, View.HEIGHT / 2 - Tile.TILESIZE / 2, spelllist, Sprite.player01, new Hitbox(0, 0, 16, 16));
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

    public static GameData getInstance()
    {
        return INSTANCE;
    }
}
