package view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import model.GameData;
import model.game.characters.Player;
import model.game.maps.GameMap;
import model.game.tiles.Tile;

public class GameScreen extends Canvas
{

    private static final long serialVersionUID = 1L;

    private View view;
    private GameData data;

    public GameScreen(View view, GameData data)
    {
        this.view = view;
        this.data = data;

        Dimension dim = new Dimension(view.frameWidth, view.frameHeight);
        setPreferredSize(dim);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        paintMap(g, data.getMap());
        paintCharacter(g, data.getPlayer());
    }

    private void paintCharacter(Graphics g, Player player)
    {
        g.drawImage(player.getTexture(), player.getX(), player.getY(), null);
    }

    private void paintMap(Graphics g, GameMap map)
    {
        for (int x = 0; x < map.getWidth(); x++)
        {
            for (int y = 0; y < map.getHeight(); y++)
            {
                Tile tile = map.getTile(x, y);
                int tileX = tile.getX() * GameData.TILE_SIZE;
                int tileY = tile.getY() * GameData.TILE_SIZE;
                g.drawImage(tile.getTexture(), tileX, tileY, null);
            }
        }
    }
}
