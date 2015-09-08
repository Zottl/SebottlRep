package model.game.object;

import controller.CollisionHandler.CollisionStatus;
import controller.ai.ProjectileAI;
import model.game.sprites.Sprite;

public class Projectile extends MapObject
{
    public Projectile(double xOrigin, double yOrigin, int xTarget, int yTarget, double moveSpeed, Sprite sprite)
    {
        super((int) xOrigin, (int) yOrigin, moveSpeed, sprite, new Hitbox(0, 0, 16, 16), new ProjectileAI(0.04));

        // Calculate the vector from the center of this projectile to the target
        // position
        int dirX = (int) (xTarget - getCenterX());
        int dirY = (int) (yTarget - getCenterY());

        // Transform that vector into an angle in degrees (0° is right,
        // counter-clockwise)
        super.direction = ((int) Math.toDegrees(-Math.atan2(dirY, dirX)) + 360) % 360;
    }

    @Override
    public boolean isGhost()
    {
        // Projectiles explode or pass through solid objects, rather than
        // collide with them (exploding is handled separately in the AI)
        return true;
    }

    @Override
    public CollisionStatus getCollisionStatus()
    {
        return CollisionStatus.HURT_ENEMY;
    }
}
