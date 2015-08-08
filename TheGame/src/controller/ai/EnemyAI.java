package controller.ai;

import controller.GameController;

public class EnemyAI extends MapObjectAI
{
    private int timer;

    public EnemyAI()
    {
        super();
    }

    @Override
    public void advance()
    {
        switch (state)
        {
            case 0: // Start idle time
                // Between 1 and 2 seconds
                timer = (int) ((Math.random() + 1) * GameController.UPS);
                state = 1;
                break;
            case 1: // Idle time
                if (timer <= 0)
                {
                    state = 2;
                }
                else
                {
                    timer--;
                }
                break;
            case 2: // Start wander time
                // Between 0.5 and 1.5 seconds
                timer = (int) ((Math.random() + 0.5) * GameController.UPS);
                state = 3;
                // Choose a random direction to wander to
                target.setDirection((int) (Math.random() * 360));
                break;
            case 3: // Wander time
                if (timer <= 0)
                {
                    state = 0;
                    target.setDirection(-1);
                }
                else
                {
                    timer--;
                }
                break;
        }
    }

}
