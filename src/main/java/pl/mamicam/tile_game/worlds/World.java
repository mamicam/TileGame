package pl.mamicam.tile_game.worlds;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.entities.EntityManager;
import pl.mamicam.tile_game.entities.creatures.Player;
import pl.mamicam.tile_game.entities.statics.Pine;
import pl.mamicam.tile_game.entities.statics.Rock;
import pl.mamicam.tile_game.entities.statics.Sculpture;
import pl.mamicam.tile_game.entities.statics.Tree;
import pl.mamicam.tile_game.items.ItemManager;
import pl.mamicam.tile_game.tiles.Tile;
import pl.mamicam.tile_game.utils.Utils;

import java.awt.*;

public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private EntityManager entityManager;
    private ItemManager itemManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Tree(handler, 300, 300));
        entityManager.addEntity(new Tree(handler, 500, 100));
        entityManager.addEntity(new Tree(handler, 900, 1000));
        entityManager.addEntity(new Tree(handler, 800, 750));
        entityManager.addEntity(new Tree(handler, 60, 70));
        entityManager.addEntity(new Tree(handler, 1000, 1000));
        entityManager.addEntity(new Pine(handler, 900, 900));
        entityManager.addEntity(new Pine(handler, 700, 650));
        entityManager.addEntity(new Pine(handler, 400, 1000));
        entityManager.addEntity(new Pine(handler, 80, 900));
        entityManager.addEntity(new Rock(handler, 100, 450));
        entityManager.addEntity(new Rock(handler, 1000, 90));
        entityManager.addEntity(new Rock(handler, 540, 880));
        entityManager.addEntity(new Rock(handler, 250, 250));
        entityManager.addEntity(new Rock(handler, 990, 500));
        entityManager.addEntity(new Sculpture(handler, 200, 800));
        entityManager.addEntity(new Sculpture(handler, 900, 150));
        entityManager.addEntity(new Sculpture(handler, 400, 600));
        entityManager.addEntity(new Sculpture(handler, 1000, 300));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void upload() {
        itemManager.upload();
        entityManager.upload();
    }

    public void render(Graphics graphics) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(graphics, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        itemManager.render(graphics);
        entityManager.render(graphics);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.stonesPath;
        }
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.praseInt(tokens[0]);
        height = Utils.praseInt(tokens[1]);
        spawnX = Utils.praseInt(tokens[2]);
        spawnY = Utils.praseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.praseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
