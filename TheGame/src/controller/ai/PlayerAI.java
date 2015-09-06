package controller.ai;

import controller.input.Keyboard;
import controller.input.Mouse;

public class PlayerAI extends MapObjectAI
{
    private Keyboard keyboard;
    private Mouse mouse;

    public PlayerAI(Keyboard keyboard, Mouse mouse)
    {
        super();
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    @Override
    public void advance()
    {
        setPlayerDirection();

        // TODO more sprite stuff
    }

    private void setPlayerDirection()
    {
        int dir;
        if (keyboard.up && !keyboard.down)
        {
            if (keyboard.right && !keyboard.left)
                dir = 45;
            else if (!keyboard.right && keyboard.left)
                dir = 135;
            else
                dir = 90;
        }
        else if (!keyboard.up && keyboard.down)
        {
            if (keyboard.right && !keyboard.left)
                dir = 315;
            else if (!keyboard.right && keyboard.left)
                dir = 225;
            else
                dir = 270;
        }
        else
        {
            if (keyboard.right && !keyboard.left)
                dir = 0;
            else if (!keyboard.right && keyboard.left)
                dir = 180;
            else
                dir = -1;
        }
        parent.setDirection(dir);
    }
}
