package pl.mamicam.tile_game.gfx;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.entities.Entity;
import pl.mamicam.tile_game.tiles.Tile;

public class GameCamera {
    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    public void centerOfEntity(Entity entity) {
        xOffset = (entity.getX() - handler.getWidth() / 2) + (entity.getWidth() / 2);
        yOffset = entity.getY() - (handler.getHeight() / 2) + (entity.getHeight() / 2);
        checkBlankSpace();
    }

    public void move(float xAmout, float yAmout) {
        xOffset += xAmout;
        yOffset += yAmout;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
