package view.ui.button;

import java.awt.event.KeyEvent;

import model.game.sprites.Sprite;
import view.GameScreen;
import view.View;
import view.ui.UIButton;
import view.ui.Bag;


public class BagButton extends UIButton
{
    public boolean isBagOpened;
    
    private Bag bag;
    
    public BagButton(GameScreen gs)
    {
        super(gs, Sprite.bag, View.WIDTH - Sprite.bag.WIDTH - 1, View.HEIGHT - Sprite.bag.HEIGHT - 1, KeyEvent.VK_B);
        
        this.bag = new Bag(gs, this);
    }
    
    @Override
    public void onClick()
    {
        // TODO (ssc): this has to be set in the bag class too
        // open the bag if it is not opened already
        if (!isBagOpened)
        {
            gs.getUserInterface().addUIElement(bag);
            // while the bag is opened we add the hovered overlay
            gs.getUserInterface().addUIElement(overlay);
            isBagOpened = true;
        }
        else
        {
            gs.getUserInterface().removeUIElement(bag);
            gs.getUserInterface().removeUIElement(overlay);
            isBagOpened = false;
        }
    }
}
