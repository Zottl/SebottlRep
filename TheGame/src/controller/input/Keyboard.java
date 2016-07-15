package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Class for keyboard inputs
 */
public class Keyboard implements KeyListener
{
    private static boolean[] keys = new boolean[800];
    
    // some Operations have two Keys i.e. movement (up, w)
    private static HashMap<Integer, Integer> keyMappings = new HashMap<Integer, Integer>();
    
    public Keyboard()
    {
        keyMappings.put(KeyEvent.VK_UP, KeyEvent.VK_W);
        keyMappings.put(KeyEvent.VK_DOWN, KeyEvent.VK_S);
        keyMappings.put(KeyEvent.VK_LEFT, KeyEvent.VK_A);
        keyMappings.put(KeyEvent.VK_RIGHT, KeyEvent.VK_D);
    }

    /**
     * Check if a Key is pressed
     * 
     * @param keyCode
     *            keyCode of the key to be checked
     *            
     * @return True if the Key with {@code keyCode} is pressed
     */
    public static boolean isKeyPressed(int keyCode)
    {
        return keys[keyCode] | (keyMappings.get(keyCode) != null ? keys[keyMappings.get(keyCode)] : false);
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
