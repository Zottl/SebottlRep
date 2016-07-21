package view.ui;

import view.GameScreen;
import view.View;
import controller.input.Mouse;
import model.game.sprites.Sprite;

public class UIElement
{
    public int screenX;
    public int screenY;
    public Sprite sprite;
    
    protected GameScreen gs;
    
    private boolean hoverStateBefore;
    private boolean mouseClicked;
    
    public UIElement (GameScreen gs, Sprite sprite, int screenX, int screenY)
    {
        this.screenX = screenX;
        this.screenY = screenY;
        
        this.gs = gs;
        
        this.sprite = sprite;
    }
    
    /**
     * updating the UIElement
     */
    public void update()
    {
        // check if hoverstate has changed and call appropriate event
        if (!hoverStateBefore && isHovering())
        {
            hoverStateBefore = true;
            onMouseEnter();
        }
        else if (hoverStateBefore && !isHovering())
        {
            hoverStateBefore = false;
            onMouseLeave();
        }
        
        if (isHovering()) handleMouseInput();
    }
    
    /**
     * sets the isHovered flag
     */
    public boolean isHovering()
    {
        // TODO (ssc): maybe change the "/ View.SCALE" part. This causes some inaccuracies
        if (Mouse.posX / View.SCALE > screenX && Mouse.posX / View.SCALE < screenX + sprite.HEIGHT
                && Mouse.posY / View.SCALE > screenY && Mouse.posY / View.SCALE < screenY + sprite.WIDTH)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Handles user mouse input
     */
    private void handleMouseInput()
    {
        if (Mouse.mouseButton == 1 && !mouseClicked)
        {
            mouseClicked = true;

            onClick();
        }

        if (Mouse.mouseButton == -1)
        {
            mouseClicked = false;
        }
    }
    
    /**
     * Mouse enter event
     */
    protected void onMouseEnter()
    {
        return;
    }
    
    /**
     * Mouse leave event
     */
    protected void onMouseLeave()
    {
        return;
    }
    
    /**
     * click event
     */
    public void onClick()
    {
        return;
    }
}
