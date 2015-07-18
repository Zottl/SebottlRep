import model.GameData;
import view.View;
import control.GameControl;


public class Main {

    public static void main(String[] args) {
        View view = new View();
        GameData gd = new GameData();
        GameControl gc = new GameControl(gd, view);
        
        gc.start();
    }

}
