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
        double dirX = xTarget - xOrigin;
        double dirY = yTarget - yOrigin;
        
        double angle = Math.atan2(dirY, dirX);
        
        int xTravel = (int) (Math.ceil(Math.cos(angle) * moveSpeed));
        int yTravel = (int) (Math.ceil(Math.sin(angle) * moveSpeed));
        
        System.out.println(" xtarget: " + xTarget + " x: " + x + " dirX: " + dirX);
        
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
        
    }

}
