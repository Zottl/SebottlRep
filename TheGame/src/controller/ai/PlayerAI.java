package controller.ai;

import model.game.characters.Player;
import model.game.object.MapObject;
import controller.CollisionHandler.CollisionStatus;
import controller.input.Keyboard;
import controller.input.Mouse;

public class PlayerAI extends MapObjectAI
{
    private Player parent;

    private Keyboard keyboard;
    private Mouse mouse;

    private int invincibilityFrames;
    private boolean mouseClicked;

    public PlayerAI(double animationSpeed, Keyboard keyboard, Mouse mouse)
    {
        super(animationSpeed);
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    @Override
    public void registerParent(MapObject mo)
    {
        super.registerParent(mo);

        this.parent = (Player) super.parent;
    }

    @Override
    public void advance()
    {
        animate();

        handleKeyboardInput();
        handleMouseInput();

        if (invincibilityFrames > 0)
        {
            invincibilityFrames--;
        }
    }

    @Override
    protected void animate()
    {
        // TODO
    }

    @Override
    public void collisionWith(CollisionStatus cs)
    {
        if (cs == CollisionStatus.ENEMY_BODY)
        {
            if (invincibilityFrames == 0)
            {
                parent.setHitpoints(parent.getHitpoints() - 1);
                invincibilityFrames = 5;
            }
        }
    }

    @Override
    public CollisionStatus[] getRelevantCollisionStatuses()
    {
        // TODO
        return super.getRelevantCollisionStatuses();
    }

    /**
     * Handles user mouse input (shooting spells currently)
     */
    private void handleMouseInput()
    {
        if (mouse.getButton() == 1 && !mouseClicked)
        {
            mouseClicked = true;

            parent.shoot(mouse.getMouseMapX(), mouse.getMouseMapY());
        }

        if (mouse.getButton() == -1)
        {
            mouseClicked = false;
        }
    }

    /**
     * Handles user keyboard input (moving the player currently)
     */
    private void handleKeyboardInput()
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
