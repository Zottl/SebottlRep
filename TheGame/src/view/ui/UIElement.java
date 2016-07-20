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
    
    protected boolean hoverStateBefore;
    
    public UIElement (GameScreen gs, Sprite sprite, int posX, int posY)
    {
        this.screenX = posX;
        this.screenY = posY;
        
        this.gs = gs;
        
        this.sprite = sprite;
    }
    
    /**
     * updating the UIElement
     */
    public void update()
    {
        // check if hoverstate has changed and call appropriate event
        if (!hoverStateBefore && hoverState())
        {
            hoverStateBefore = true;
            onMouseEnter();
        }
        else if (hoverStateBefore && !hoverState())
        {
            hoverStateBefore = false;
            onMouseLeave();
        }
    }
    
    /**
     * sets the isHovered flag
     */
    private boolean hoverState()
    {
        // TODO (ssc): maybe change the "/ View.SCALE" part
        if (Mouse.posX / View.SCALE > screenX && Mouse.posX / View.SCALE < screenX + sprite.HEIGHT
                && Mouse.posY / View.SCALE > screenY && Mouse.posY / View.SCALE < screenY + sprite.WIDTH)
        {
            return true;
        }
        return false;
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
    protected void onClick()
    {
        return;
    }
}
