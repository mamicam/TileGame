package pl.mamicam.tile_game.inventory;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.gfx.Assets;
import pl.mamicam.tile_game.gfx.Text;
import pl.mamicam.tile_game.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    private int invX = 64;
    private int invY = 48;
    private int invHeight = 384;
    private int invListCenterX = invX + 171;
    private int invListCenterY = invY + (invHeight / 2) + 5;

    private int selectedItem = 0;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
    }

    public void upload() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }
        if (!active) {
            return;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            selectedItem--;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            selectedItem++;
        }

        if (selectedItem < 0) {
            selectedItem = inventoryItems.size() - 1;
        } else if (selectedItem >= inventoryItems.size()) {
            selectedItem = 0;
        }
    }

    public void render(Graphics graphics) {
        if (!active) {
            return;
        }
        int invWidth = 512;
        graphics.drawImage(Assets.inventoryScreen, invX,invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        if (len == 0) {
            return;
        }

        for (int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) {
                continue;
            }
            int invListSpacing = 30;
            if (i == 0) {
                Text.drawString(graphics, ">" + inventoryItems.get(selectedItem + i).getName() + "<", invListCenterX,
                        invListCenterY + (i * invListSpacing), true, Color.YELLOW, Assets.font28);
            } else {
                Text.drawString(graphics, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                        invListCenterY + (i * invListSpacing), true, Color.WHITE, Assets.font28);
            }
        }

        Item item = inventoryItems.get(selectedItem);
        int invImageY = 82;
        int invImageX = 452;
        graphics.drawImage(item.getTexture(), invImageX, invImageY, invWidth, invHeight, null);
        int invCountX = 484;
        int invCountY = 172;
        Text.drawString(graphics, Integer.toString(item.getCount()), invCountX, invCountY, true,
                Color.WHITE, Assets.font28);
    }

    public void addItem (Item item) {
        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}
