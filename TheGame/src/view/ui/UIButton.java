package view.ui;

import view.GameScreen;
import model.game.sprites.Sprite;

public class UIButton extends UIElement
{
    protected UIElement overlay;
    
    public UIButton(GameScreen gs, Sprite sprite, int screenX, int screenY, int keyboardButton)
    {
        super(gs, sprite, screenX, screenY);
        
        overlay = new UIElement(gs, this.findHoverOverlay(sprite), screenX, screenY);
    }

    /**
     * @return An overlay sprite with a fitting size
     */
    private Sprite findHoverOverlay(Sprite sprite)
    {
        if (sprite.WIDTH == 16 && sprite.HEIGHT == 16)
        {
            return Sprite.hoveredOverlay;
        }
        else return null;
    }

    @Override
    protected void onMouseEnter()
    {
        // add the HoverOverlay
        gs.getUserInterface().addUIElement(overlay);
    }
    
    @Override
    protected void onMouseLeave()
    {
        // remove the hover Overlay
        gs.getUserInterface().removeUIElement(overlay);
    }

    @Override
    public void onClick()
    {
        return;
    }
    
}
