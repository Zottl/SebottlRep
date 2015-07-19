package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import model.game.GrassTile;
import model.game.Tile;

public class GameScreen extends Canvas
{

    private static final long serialVersionUID = 1L;

    private View view;

    private Tile testtile;

    public GameScreen(View view)
    {
        this.view = view;
        Dimension dim = new Dimension(view.frameWidth, view.frameHeight);
        setPreferredSize(dim);

        testtile = new GrassTile(60, 30);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.RED);
        g.fillRect(10, 10, (view.frameWidth - 20), (view.frameHeight - 20));
        g.drawImage(testtile.getTexture(), 40, 50, null);
    }
}
