package main;
import javax.swing.SwingUtilities;

import model.GameData;
import view.View;
import controller.GameController;

public class Main
{
    public static void main(String[] args)
    {
        setOpenGL(true);
        enableTraceOutput(false);

        GameData gameData = GameData.getInstance();
        
        // Start the View on the Event-Dispatch Thread
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                View view = new View(gameData);

                // After the view is ready, start the Control-Thread
                GameController gameController = new GameController(view);
                gameController.start();
            }
        });
    }
    
    private static void setOpenGL(boolean active) {
        System.setProperty("sun.java2d.opengl", String.valueOf(active));
    }
    
    private static void enableTraceOutput(boolean active) {
        String value;
        if (active)
            value = "count";
        else
            value = "";
        System.setProperty("sun.java2d.trace", value);
        
    }
}
