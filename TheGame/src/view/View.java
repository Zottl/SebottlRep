package view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View
{
    // window size variables
    public static final int WIDTH = 300;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;

    private JFrame frame;
    private Canvas canvas;

    private GameScreen gs;

    // creating image
    private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
    // accessing image // converting image object into an array of integers
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public View()
    {
        gs = new GameScreen();

        // Prepare the frame
        frame = new JFrame();
        ImageIcon frameIcon = new ImageIcon("resources/FrameIcon.gif");
        frame.setIconImage(frameIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);

        // Prepare the canvas
        canvas = new Canvas();
        Dimension dim = new Dimension(View.WIDTH * View.SCALE, View.HEIGHT * View.SCALE);
        canvas.setPreferredSize(dim);
        canvas.setIgnoreRepaint(true);
        canvas.setVisible(true);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void render(int xOffset, int yOffset)
    {
        try
        {
            SwingUtilities.invokeAndWait(() ->
            {
                BufferStrategy bs = canvas.getBufferStrategy();

                if (bs == null)
                {
                    canvas.createBufferStrategy(2);
                    return;
                }

                gs.clear();
                gs.render(xOffset, yOffset);

                for (int i = 0; i < pixels.length; i++)
                {
                    pixels[i] = gs.getPixel(i);
                }

                Graphics g = bs.getDrawGraphics();
                g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
                g.dispose();
                bs.show();
                Toolkit.getDefaultToolkit().sync();
            });
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public GameScreen getGameScreen()
    {
        return gs;
    }

    public void addKeyListener(KeyListener l)
    {
        frame.addKeyListener(l);
        canvas.addKeyListener(l);
    }

    public synchronized void addMouseListener(MouseListener l)
    {
        frame.addMouseListener(l);
        canvas.addMouseListener(l);
    }

    public synchronized void addMouseMotionListener(MouseMotionListener l)
    {
        frame.addMouseMotionListener(l);
        canvas.addMouseMotionListener(l);
    }

    public void setTitle(String title)
    {
        SwingUtilities.invokeLater(() -> frame.setTitle(title));
    }

    public void addWindowlistener(WindowListener l)
    {
        frame.addWindowListener(l);
    }
}
