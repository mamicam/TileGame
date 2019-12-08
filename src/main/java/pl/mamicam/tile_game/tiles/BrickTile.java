package pl.mamicam.tile_game.tiles;

import pl.mamicam.tile_game.gfx.Assets;

public class BrickTile extends Tile {
    public BrickTile(int id) {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
