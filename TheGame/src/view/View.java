package view;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.GameData;

public class View extends JFrame
{

    private static final long serialVersionUID = 1L;

    // window size variables
    public static final int WIDTH = 300;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 5;

    private GameScreen gs;

    // creating image
    private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
    // accessing image // converting image object into an array of integers
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public View(GameData data)
    {
        // call every screen once to initialize them now
        gs = new GameScreen(data);
        /* ... more screens ... */

        ImageIcon frameIcon = new ImageIcon("resources/FrameIcon.gif");
        this.setIconImage(frameIcon.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);

        changeScreen(0);
    }

    /**
     * @param SNr
     *            0->GameScreen, ...
     */
    public void changeScreen(int SNr)
    {
        gs.setVisible(false);
        this.remove(gs);
        /* ... more screens ... */

        switch (SNr)
        {
            case (0):
            {
                gs.setVisible(true);
                this.add(gs);
                break;
            }
            /* ... more screens ... */
        }
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void render()
    {
        BufferStrategy bs = gs.getBufferStrategy();

        if (bs == null)
        {
            gs.createBufferStrategy(3);
            return;
        }

        gs.clear();
        gs.render();

        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = gs.getPixel(i);
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, gs.getWidth(), gs.getHeight(), null);
        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public GameScreen getGameScreen()
    {
        return gs;
    }

    @Override
    public synchronized void addKeyListener(KeyListener l)
    {
        super.addKeyListener(l);
        gs.addKeyListener(l);
    }

}
