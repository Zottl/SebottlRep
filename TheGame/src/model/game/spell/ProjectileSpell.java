package model.game.spell;

import model.game.characters.GameCharacter;
import model.game.object.Projectile;
import model.game.sprites.Sprite;

public abstract class ProjectileSpell extends Spell
{
    Sprite sprite;
    double moveSpeed;

    public ProjectileSpell(Sprite sprite, double moveSpeed, int cooldownTime)
    {
        this.sprite = sprite;
        this.moveSpeed = moveSpeed;
        super.cooldownTime = cooldownTime;
    }

    public void cast(int xTarget, int yTarget, GameCharacter character)
    {
        //TODO: if the cooldown is below a certain point maybe cast it autmatically after cd is over
        // Check if spell is on cooldown
        if (!isCooldown())
        {
            // Start cooldown timer
            this.cooldownStart = System.currentTimeMillis();

            // Place the center of the projectile onto the center of the
            // character
            int x = (int) (character.getCenterX() - sprite.WIDTH / 2.0);
            int y = (int) (character.getCenterY() - sprite.HEIGHT / 2.0);

            Projectile projectile = new Projectile(x, y, xTarget, yTarget, moveSpeed, sprite);

            // TODO: Check if this can be centralized
            map.addMapObject(projectile);
        }
    }
}
