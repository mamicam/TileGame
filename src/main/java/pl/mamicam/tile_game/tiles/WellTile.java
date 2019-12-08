package pl.mamicam.tile_game.tiles;

import pl.mamicam.tile_game.gfx.Assets;

public class WellTile extends Tile {
    public WellTile(int id) {
        super(Assets.well, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
