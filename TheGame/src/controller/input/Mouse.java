package controller.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.GameController;
import view.View;

/**
 * Class for mouse inputs
 */
public class Mouse implements MouseListener, MouseMotionListener
{

    public static int posX = -1;
    public static int posY = -1;
    public static int mouseButton = -1;

    /**
     * Reset the pressed buttons
     */
    public void reset()
    {
        mouseButton = -1;
    }

    public void mousePressed(MouseEvent e)
    {
        mouseButton = e.getButton();
    }

    public void mouseReleased(MouseEvent e)
    {
        // set the mouseButton to -1 to reset the button pressed before
        mouseButton = -1;
    }

    public void mouseMoved(MouseEvent e)
    {
        // update mouse Position
        posX = e.getX();
        posY = e.getY();
    }

    /**
     * @param xOffset
     *          current x offset of the map
     */
    public static int getMouseMapX()
    {
        return posX / View.SCALE + GameController.xScreenOffset;
    }

    /**
     * @param yOffset
     *          current y offset of the map
     */
    public static int getMouseMapY()
    {
        return posY / View.SCALE + GameController.yScreenOffset;
    }

    public void mouseDragged(MouseEvent e)
    {
        // update mouse Position
        posX = e.getX();
        posY = e.getY();
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
