package controller.ai;

import java.awt.event.KeyEvent;

import model.game.characters.Player;
import model.game.object.MapObject;
import controller.CollisionHandler.CollisionStatus;
import controller.input.Keyboard;
import controller.input.Mouse;

public class PlayerAI extends GameCharacterAI
{
    private Player parent;

    private int invincibilityFrames;
    private boolean mouseClicked;

    public PlayerAI(double animationSpeed)
    {
        super(animationSpeed);
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
        if (Mouse.mouseButton == 1 && !mouseClicked)
        {
            mouseClicked = true;

            parent.shoot(Mouse.getMouseMapX(), Mouse.getMouseMapY());
        }

        if (Mouse.mouseButton == -1)
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
        if (Keyboard.isKeyPressed(KeyEvent.VK_UP) && !Keyboard.isKeyPressed(KeyEvent.VK_DOWN))
        {
            if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && !Keyboard.isKeyPressed(KeyEvent.VK_LEFT))
                dir = 45;
            else if (!Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && Keyboard.isKeyPressed(KeyEvent.VK_LEFT))
                dir = 135;
            else
                dir = 90;
        }
        else if (!Keyboard.isKeyPressed(KeyEvent.VK_UP) && Keyboard.isKeyPressed(KeyEvent.VK_DOWN))
        {
            if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && !Keyboard.isKeyPressed(KeyEvent.VK_LEFT))
                dir = 315;
            else if (!Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && Keyboard.isKeyPressed(KeyEvent.VK_LEFT))
                dir = 225;
            else
                dir = 270;
        }
        else
        {
            if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && !Keyboard.isKeyPressed(KeyEvent.VK_LEFT))
                dir = 0;
            else if (!Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && Keyboard.isKeyPressed(KeyEvent.VK_LEFT))
                dir = 180;
            else
                dir = -1;
        }
        parent.setDirection(dir);
    }
}
