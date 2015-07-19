package control;

import model.GameData;
import view.View;

public class GameControl
{

    boolean running;

    GameData gd;
    View gv;

    public GameControl(GameData gd, View gv)
    {
        this.gd = gd;
        this.gv = gv;
    }

    public void run()
    {
        while (running)
        {
            System.out.println("Game loop running!");
        }
    }

    public void start()
    {
        running = true;
        run();
    }

    public void stop()
    {
        running = false;
    }
}
