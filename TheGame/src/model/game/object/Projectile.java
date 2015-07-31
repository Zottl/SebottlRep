package model.game.object;

import model.game.characters.GameCharacter;
import model.game.sprites.Sprite;


// make abstract
public class Projectile extends MapObject
{
    private int xTarget, yTarget;
    private double moveSpeed;    

    public Projectile(int x, int y, int xTarget, int yTarget, double moveSpeed, double animationSpeed, Sprite sprite)
    {
        super(x, y, animationSpeed, sprite);
        
        this.xTarget = xTarget;
        this.yTarget = yTarget;
        this.moveSpeed = moveSpeed;
    }

    public void move ()
    {
        int dirX = xTarget - x;
        int dirY = yTarget - y;
        
        double angle = Math.atan2(dirY, dirX); // * 180 / Math.PI;
        
        int xTravel = (int) (Math.cos(angle) * moveSpeed);
        int yTravel = (int) (Math.sin(angle) * moveSpeed);
        
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
