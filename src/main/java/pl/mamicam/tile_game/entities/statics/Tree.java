package pl.mamicam.tile_game.entities.statics;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.gfx.Assets;
import pl.mamicam.tile_game.items.Item;
import pl.mamicam.tile_game.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void upload() {

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
