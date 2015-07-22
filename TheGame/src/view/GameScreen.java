package view;

import java.awt.Canvas;
import java.awt.Dimension;

import model.GameData;
import model.game.maps.GameMap;
import model.game.tiles.Tile;
import model.game.characters.Character;
import model.game.characters.Player;

public class GameScreen extends Canvas
{

    private static final long serialVersionUID = 1L;

    private View view;
    private GameData data;
    
    private int[] pixels;

    public GameScreen(View view, GameData data)
    {
        pixels = new int[view.width * view.height];
        
        this.view = view;
        this.data = data;

        Dimension dim = new Dimension(view.width, view.height);
        setPreferredSize(dim);
    }

    // Method responsible for rendering
    public void render(int xOffset, int yOffset)
    {
        this.renderMap(data.getMap());
        this.renderCharacter(new Player(10, 10));
    }
    
    private void renderCharacter(Character character)
    {
        int size = character.getSprite().SIZE;
        
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                pixels[x * y] = character.getSprite().getPixel(x + y * size);
            }
        }
        
    }

    private void renderMap(GameMap map)
    {
        for (int x = 0; x < map.getWidth(); x++)
        {
            for (int y = 0; y < map.getHeight(); y++)
            {
                Tile tile = map.getTile(x, y);
                int tileSize = tile.getSprite().SIZE;
                int tileX = tile.getX() * tileSize;
                int tileY = tile.getY() * tileSize;
                
                for (int px = 0; px < tileSize; px++)
                {
                    for (int py = 0; py < tileSize; py++)
                    {
                        if (tileX + tileY * view.width + px + py * tileSize < view.width * view.height)
                        {
                            pixels[tileX + tileY * view.width + px + py * tileSize] = tile.getSprite().getPixel(px + py * tileSize);   
                        }
                    }
                }
            }
        }
    }

    public int getPixel(int i)
    {
        return pixels[i];
    }
}
