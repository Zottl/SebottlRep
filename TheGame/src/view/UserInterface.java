package view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import view.ui.UIButton;
import view.ui.UIElement;
import controller.GameController;
import model.GameData;
import model.game.sprites.Sprite;

public class UserInterface
{
    private GameScreen gs;
    private GameData data;
    
    // The list of UIElements, that are present on this UserInterface
    private List<UIElement> elements;

    public UserInterface(GameScreen gs)
    {
        this.data = GameData.getInstance();
        this.gs = gs;
        
        elements = new ArrayList<UIElement>();
        
        UIButton testButton = new UIButton(gs, Sprite.bag, View.WIDTH - Sprite.bag.WIDTH - 1, View.HEIGHT - Sprite.bag.HEIGHT - 1, KeyEvent.VK_B);
        
        elements.add(testButton);
    }

    /**
     * Renders the UserInterface
     */
    public void renderUI()
    {
        renderHealthBar();
        
        for(UIElement element : this.getUIElements())
        {
            gs.renderSprite(element.sprite, element.screenX + GameController.xScreenOffset, element.screenY + GameController.yScreenOffset, GameController.xScreenOffset, 
                    GameController.yScreenOffset);
        }
    }
    
    /**
     * @return The list of the UIElements that are placed on the Screen
     */
    public List<UIElement> getUIElements()
    {
        return new ArrayList<UIElement>(elements);
    }
    
    /**
     * @param uiEl
     *            The UIElement to place on this UserInterface
     */
    public void addUIElement(UIElement uiEl)
    {
        elements.add(uiEl);
    }

    /**
     * @param uiEl
     *            The UIElement to remove from this UserInterface
     */
    public void removeUIElement(UIElement uiEl)
    {
        if (elements.remove(uiEl))
        {
        }
        else
        {
            System.err.println("[UserInterface]: Removal of an UIElement has failed!");
        }
    }

    private void renderHealthBar()
    {
        int maxHealth = data.getPlayer().getMaxHitpoints();
        int health = data.getPlayer().getHitpoints();

        // Properties of the HealthBar sprites
        int sideWidth = 32, sideHealth = 25;
        int midWidth = 10, midHealth = 10;
        int barOffset = sideWidth - sideHealth;
        
        int xOffset = GameController.xScreenOffset;
        int yOffset = GameController.yScreenOffset;

        // Render the bar
        for (int i = 0; i < health; i++)
        {
            Sprite sprite;
            switch (i % 6)
            {
                case 1:
                    sprite = Sprite.healthBar01;
                    break;
                case 2:
                    sprite = Sprite.healthBar02;
                    break;
                case 3:
                    sprite = Sprite.healthBar03;
                    break;
                case 4:
                    sprite = Sprite.healthBar04;
                    break;
                case 5:
                    sprite = Sprite.healthBar05;
                    break;
                default:
                    sprite = Sprite.healthBar06;
                    break;
            }
            gs.renderSprite(sprite, xOffset + barOffset + i, yOffset, xOffset, yOffset);
        }

        // Render the frame
        int healthSections = (maxHealth - 2 * sideHealth) / midHealth;

        gs.renderSprite(Sprite.healthFrameLeft, xOffset, yOffset, xOffset, yOffset);
        for (int i = 0; i < healthSections; i++)
        {
            gs.renderSprite(Sprite.healthFrameMid, xOffset + sideWidth + i * midWidth, yOffset, xOffset, yOffset);
        }
        gs.renderSprite(Sprite.healthFrameRight, xOffset + sideWidth + healthSections * midWidth, yOffset, xOffset, yOffset);
    }
}
