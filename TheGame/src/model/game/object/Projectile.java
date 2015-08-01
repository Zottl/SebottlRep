package model.game.object;

import model.game.characters.GameCharacter;
import model.game.sprites.Sprite;

public class Projectile extends MapObject
{
    private int xTarget, yTarget, xOrigin, yOrigin;
    private double moveSpeed;    

    public Projectile(int x, int y, int xTarget, int yTarget, double moveSpeed, double animationSpeed, Sprite sprite)
    {
        super(x, y, animationSpeed, sprite);
        
        this.xOrigin = x;
        this.yOrigin = y;
        this.xTarget = xTarget;
        this.yTarget = yTarget;
        this.moveSpeed = moveSpeed;
    }

    public void move ()
    {  
        double dirX = xTarget - xOrigin - sprite.WIDTH / 2;
        double dirY = yTarget - yOrigin - sprite.HEIGHT / 2;
        
        double angle = Math.atan2(dirY, dirX);
        
        int xTravel = (int) (Math.ceil(Math.cos(angle) * moveSpeed));
        int yTravel = (int) (Math.ceil(Math.sin(angle) * moveSpeed));
        
        //System.out.println(" ytarget: " + yTarget + " y: " + yOrigin + " diry: " + dirY);
        
        x += xTravel;
        y += yTravel;
    }
    
    @Override
    public void interact(GameCharacter source)
    {
        
    }

    @Override
    protected void advanceAnimation()
    {
        switch (animationState)
        {
            case 0:
                sprite = Sprite.testSpell01;
                animationState++;
                return;
            case 1:
                sprite = Sprite.testSpell02;
                animationState++;
                return;
            case 2:
                sprite = Sprite.testSpell03;
                animationState++;
                return;
            case 3:
                sprite = Sprite.testSpell02;
                animationState++;
                return;
            case 4: 
                sprite = Sprite.testSpell03;
                animationState = 0;
                return;
            default:
                return;
        }
    }

}
