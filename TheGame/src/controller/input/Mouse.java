package controller.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import controller.GameController;

/**
 * Class for mouse inputs
 */
public class Mouse implements MouseListener, MouseMotionListener
{
    GameController gc;

    private int xOffset;
    private int yOffset;

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public void update(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void mousePressed(MouseEvent e)
    {
        mouseB = e.getButton();
        System.out.println("x: " + mouseX + " y: " + mouseY + " mapX: " + this.getMouseMapX() + " mapY: " + this.getMouseMapY());
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
     * @return the x coordinate of the mouse on the map
     */
    public int getMouseMapX()
    {
        return mouseX + xOffset;
    }

    /**
     * @return the y coordinate of the mouse on the map
     */
    public int getMouseMapY()
    {
        return mouseY + yOffset;
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
