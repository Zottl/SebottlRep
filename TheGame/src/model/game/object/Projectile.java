package model.game.object;

import model.game.characters.GameCharacter;
import model.game.sprites.Sprite;

public class Projectile extends MapObject
{
    public Projectile(double xOrigin, double yOrigin, int xTarget, int yTarget, double moveSpeed, double animationSpeed, Sprite sprite)
    {
        super((int) xOrigin, (int) yOrigin, moveSpeed, animationSpeed, sprite);

        double dirX = xTarget - xOrigin - sprite.WIDTH / 2;
        double dirY = yTarget - yOrigin - sprite.HEIGHT / 2;

        super.direction = (int) Math.toDegrees(Math.atan2(dirY, dirX));
    }

    @Override
    public void interact(GameCharacter source)
    {

    }

    @Override
    public void advanceAnimation()
    {
        animationState += animationSpeed;
        int stateNumber = (int) animationState;
        switch (stateNumber)
        {
            case 1:
                sprite = Sprite.testSpell02;
            case 2:
                sprite = Sprite.testSpell03;
            case 3:
                sprite = Sprite.testSpell02;
            case 4:
                sprite = Sprite.testSpell01;
                animationState = 0;
        }
    }

    @Override
    public boolean canCollide()
    {
        // Projectiles explode or pass through solid objects, rather than
        // collide with them (exploding is handled separately in the
        // collideWith() method)
        return false;
    }

    @Override
    public void collideWith(MapObject mo)
    {
        // TODO explode!
    }

}
