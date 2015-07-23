import model.GameData;
import view.View;
import control.GameControl;

public class Main
{
    public static void main(String[] args)
    {
        GameData gd = new GameData(1);
        View view = new View(gd);
        GameControl gc = new GameControl(gd, view);

        gc.start();
    }
}
