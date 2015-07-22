package view;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.GameData;
import model.game.sprites.Sprite;

public class View extends JFrame
{

    private static final long serialVersionUID = 1L;

    // windows size variables
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    
    public int xOffset = 0;
    public int yOffset = 0;

    private GameData data;
    private GameScreen gs;
    
    // creating image
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // accessing image              // converting image object into an array of integers            
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public View(GameData data)
    {
        this.data = data;

        // call every screen once to initialize them now
        gs = new GameScreen(this, data);
        /* ... more screens ... */

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon frameIcon = new ImageIcon("resources/FrameIcon.gif");
        this.setIconImage(frameIcon.getImage());

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
        this.render();
        this.pack();
    }
    
    public void clear()
    {
        // loop over every pixel and set it to 0
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0;
        }
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        this.clear();
        gs.render(xOffset, yOffset);
        
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = gs.getPixel(i);
        }
        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}
