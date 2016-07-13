package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for keyboard inputs
 */
public class Keyboard implements KeyListener
{
    private boolean[] keys = new boolean[800];
    public boolean up, right, down, left, b;

    public void update()
    {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        b = keys[KeyEvent.VK_B];
    }

    /**
     * Reset the pressed buttons
     */
    public void reset()
    {
        keys = new boolean[800];
    }

    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e)
    {

    }
}
