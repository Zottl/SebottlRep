package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;

    public int frameWidth = 800;
    public int frameHeight = 600;
    
    private GameScreen gs;
    
    ImageIcon frameIcon = new ImageIcon("resources/FrameIcon.gif");

    public View() {
    	
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TheGame");
        this.setIconImage(frameIcon.getImage());
        
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);

        // call every screen once to initialize them now
        gs = new GameScreen(this);
        /* ... more screens ... */

        changeScreen(0);
    }

    /**
     * @param SNr
     *            0->GameScreen, ...
     */
    public void changeScreen(int SNr) {
        gs.setVisible(false);
        this.remove(gs);
        /* ... more screens ... */

        switch (SNr) {
        case (0): {
            gs.setVisible(true);
            this.add(gs);
            System.out.println("Was here");
            break;
        }
        /* ... more screens ... */
        }
        this.pack();
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        try {
            gs.repaint();
            /* ... more screens ... */
        } catch (Exception e) {
            System.err.println("Something went wrong repainting!");
            System.exit(ERROR);
        }
    }
}
