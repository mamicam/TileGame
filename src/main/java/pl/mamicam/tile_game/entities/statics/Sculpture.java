package pl.mamicam.tile_game.entities.statics;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.gfx.Assets;
import pl.mamicam.tile_game.items.Item;
import pl.mamicam.tile_game.tiles.Tile;

import java.awt.*;

public class Sculpture extends StaticEntity {

    public Sculpture(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
    }

    @Override
    public void upload() {

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.sculpture, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
