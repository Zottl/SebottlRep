package controller.ai;

import model.game.characters.EnemyNpc;
import model.game.object.MapObject;
import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;
import controller.GameController;

public class EnemyAI extends GameCharacterAI
{
    private EnemyNpc parent;
    private int timer;

    private final int HURTSTATE = 1000;
    private final int DEATHSTATE = 2000;

    private Sprite hurtSprite;

    public EnemyAI(double animationSpeed)
    {
        super(animationSpeed);

        hurtSprite = new Sprite(Sprite.enemy01);

        // Transform all visible pixels to red for the hurtSprite
        for (int i = 0; i < hurtSprite.HEIGHT * hurtSprite.WIDTH; i++)
        {
            if ((hurtSprite.getPixel(i) & 0xff000000) != 0)
            {
                hurtSprite.setPixel(i, 0xFFDD0000);
            }
        }
    }

    @Override
    public void advance()
    {
        switch (state)
        {
            case 0: // Start idle time
                // Between 1 and 2 seconds
                timer = (int) ((Math.random() + 1) * GameController.UPS);
                state = 1;
                break;
            case 1: // Idle time
                if (timer <= 0)
                {
                    state = 2;
                }
                else
                {
                    timer--;
                }
                break;
            case 2: // Start wander time
                // Between 0.5 and 1.5 seconds
                timer = (int) ((Math.random() + 0.5) * GameController.UPS);
                state = 3;
                // Choose a random direction to wander to
                parent.setDirection((int) (Math.random() * 360));
                break;
            case 3: // Wander time
                if (timer <= 0)
                {
                    state = 0;
                    parent.setDirection(-1);
                }
                else
                {
                    timer--;
                }
                break;
            case HURTSTATE:
                // 1 second stun animation
                timer = 1 * GameController.UPS;
                parent.setDirection(-1);
                state = HURTSTATE + 1;
                System.out.println("[EnemyAI]: [Debug] Enemy health remaining: " + parent.getHitpoints());
                break;
            case HURTSTATE + 1:
                if (timer <= 0)
                {
                    state = 0;
                    parent.setSprite(Sprite.enemy01);
                }
                else
                {
                    timer--;
                    if ((timer % 10 == 0) && (timer / 10) % 2 == 0)
                    {
                        parent.setSprite(hurtSprite);
                    }
                    else if ((timer % 10 == 0) && (timer / 10) % 2 == 1)
                    {
                        parent.setSprite(Sprite.enemy01);
                    }
                }
                break;
        }
    }

    public void deathAnimation()
    {
        // also handle map object removal here
        
        
        Sprite deathAnimSprite = new Sprite(Sprite.enemy01);

        // Transform all visible pixels to red for the hurtSprite
        for (int i = 0; i < deathAnimSprite.HEIGHT * deathAnimSprite.WIDTH; i++)
        {
            if ((deathAnimSprite.getPixel(i) & 0xff000000) != 0)
            {
                deathAnimSprite.setPixel(i, 0xFF666666);
            }
        }
    }
    
    @Override
    public void collisionWith(CollisionStatus cs)
    {
        if (cs == CollisionStatus.HURT_ENEMY && state != HURTSTATE && state != HURTSTATE + 1)
        {
            state = HURTSTATE;
            parent.setHitpoints(parent.getHitpoints() - 5);
            parent.setLastTimeDamaged(System.currentTimeMillis());
            
            if (parent.getHitpoints() <= 0)
            {
                System.out.println("DEAD");
                state = DEATHSTATE;
                parent.death();
            }
         }
    }

    @Override
    public void registerParent(MapObject mo)
    {
        super.registerParent(mo);

        this.parent = (EnemyNpc) super.parent;
    }
}
