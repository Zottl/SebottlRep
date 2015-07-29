package controller.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Class for mouse inputs
 */
public class Mouse implements MouseListener, MouseMotionListener
{
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public void mousePressed(MouseEvent e)
    {
        mouseB = e.getButton();
    }

    public void mouseReleased(MouseEvent e)
    {
        // set the mouseButton to -1 to reset the button pressed before
        mouseB = -1;
    }

    public void mouseMoved(MouseEvent e)
    {
        // update mouse Position
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * @return x coordinate of the mouse
     */
    public static int getX()
    {
        return mouseX;
    }

    /**
     * @return y coordinate of the mouse
     */
    public static int getY()
    {
        return mouseY;
    }

    /**
     * @return pressed mouse button
     */
    public static int getButton()
    {
        return mouseB;
    }

    public void mouseDragged(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }
    
    public void mouseClicked(MouseEvent e)
    {
        
    }
}
