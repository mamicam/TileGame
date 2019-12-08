package pl.mamicam.tile_game.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UiImageButton extends UiObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public UiImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void upload() {
    }

    @Override
    public void render(Graphics graphics) {
        if (hovering) {
            graphics.drawImage(images[1], (int) x, (int) y, width, height, null);
        } else {
            graphics.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
