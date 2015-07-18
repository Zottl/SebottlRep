package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameScreen extends Canvas {

    private static final long serialVersionUID = 1L;

    private View view;

    public GameScreen(View view) {
        this.view = view;
        Dimension dim = new Dimension(view.frameWidth, view.frameHeight);
        setPreferredSize(dim);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.RED);
        g.fillRect(10, 10, (view.frameWidth - 20), (view.frameHeight - 20));
    }
}
