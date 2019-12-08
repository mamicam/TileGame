package pl.mamicam.tile_game.items;

import pl.mamicam.tile_game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemManager {
    private Handler handler;
    private List<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }

    public void upload() {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            i.upload();
            if (i.isPickedUp()) {
                it.remove();
            }
        }
    }

    public void render(Graphics graphics) {
        for (Item i : items) {
            i.render(graphics);
        }
    }

    public void addItem(Item item) {
        item.setHandler(handler);
        items.add(item);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
