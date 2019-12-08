package pl.mamicam.tile_game.entities.creatures;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.entities.Entity;
import pl.mamicam.tile_game.gfx.Animation;
import pl.mamicam.tile_game.gfx.Assets;
import pl.mamicam.tile_game.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {
    // Animations
    private Animation animDown, animUp, animLeft, animRight;

    //Attack timer
    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURES_WIDTH, Creature.DEFAULT_CREATURES_HEIGHT);

        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        animDown = new Animation(500, Assets.playerDown);
        animUp = new Animation(500, Assets.playerUp);
        animLeft = new Animation(500, Assets.playerLeft);
        animRight = new Animation(500, Assets.playerRight);

        inventory = new Inventory(handler);
    }

    @Override
    public void upload() {
        //Animation
        animDown.upload();
        animUp.upload();
        animRight.upload();
        animLeft.upload();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOfEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.upload();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer += System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        if (inventory.isActive()) {
            return;
        }

        Rectangle collisionBox = getCollisionBounds(0,0);
        Rectangle attackRectangle = new Rectangle();
        int attackRectSize = 20;
        attackRectangle.width = attackRectSize;
        attackRectangle.height = attackRectSize;

        if (handler.getKeyManager().aUp) {
            attackRectangle.x = collisionBox.x + collisionBox.width / 2 - attackRectSize / 2;
            attackRectangle.y = collisionBox.y - attackRectSize;
        } else if (handler.getKeyManager().aDown) {
            attackRectangle.x = collisionBox.x + collisionBox.width / 2 - attackRectSize / 2;
            attackRectangle.y = collisionBox.y + collisionBox.height;
        } else if (handler.getKeyManager().aLeft) {
            attackRectangle.x = collisionBox.x - attackRectSize;
            attackRectangle.y = collisionBox.y + collisionBox.height / 2 - attackRectSize / 2;
        } else if (handler.getKeyManager().aRight) {
            attackRectangle.x = collisionBox.x + collisionBox.width;
            attackRectangle.y = collisionBox.y + collisionBox.height / 2 - attackRectSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity entity : handler.getWorld().getEntityManager().getEntities()) {
            if (entity.equals(this)) {
                continue;
            }
            if (entity.getCollisionBounds(0,0).intersects(attackRectangle)) {
                entity.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die() {
        System.out.println("You loose");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (inventory.isActive()) {
            return;
        }

        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void postRender(Graphics graphics) {
        inventory.render(graphics);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
