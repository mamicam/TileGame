package pl.mamicam.tile_game.entities.statics;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.entities.Entity;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
