package pl.mamicam.tile_game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //STATIC STUFF HERE
    public static Tile[] tiles = new Tile[256];
    public static final Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile brickTile = new BrickTile(2);
    public static Tile well = new WellTile(3);
    public static Tile stonesPath = new StonesPathTile(4);
    //CLASS
    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void upload() {

    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}
