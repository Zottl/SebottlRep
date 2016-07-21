package view.ui;

import model.game.sprites.Sprite;
import view.GameScreen;
import view.ui.button.BagButton;

public class Bag extends UIElement
{
    private BagButton bagButton;
    
    public Bag(GameScreen gs, BagButton bagButton)
    {
        super(gs, Sprite.bag, bagButton.screenX - bagButton.sprite.WIDTH, bagButton.screenY - bagButton.sprite.WIDTH);
        
        this.bagButton = bagButton;
    }
    
    @Override
    public void onClick()
    {
        // for now we just click the bagButton to close the Bag. Maybe change this later
        bagButton.onClick();
    }
}
