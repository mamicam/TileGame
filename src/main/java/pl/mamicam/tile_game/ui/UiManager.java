package pl.mamicam.tile_game.ui;

import pl.mamicam.tile_game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UiManager {
    private Handler handler;
    private List<UiObject> objects;

    public UiManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<>();
    }

    public void upload() {
        for (UiObject o : objects) {
            o.upload();
        }
    }

    public void render(Graphics graphics) {
        for (UiObject o : objects) {
            o.render(graphics);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UiObject o : objects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UiObject o : objects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UiObject o) {
        objects.add(o);
    }

    public void removeObject(UiObject o) {
        objects.remove(o);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setObjects(List<UiObject> objects) {
        this.objects = objects;
    }
}
