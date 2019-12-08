package pl.mamicam.tile_game.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static Font font28;

    public static BufferedImage dirt, grass, wall, well, stonesPath;
    public static BufferedImage wood, rock, tree, pine, sculpture;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
    public static BufferedImage[] zombieDown, zombieUp, zombieLeft, zombieRight;
    public static BufferedImage[] btnStart;
    public static BufferedImage inventoryScreen;

    public static void init() {
        font28 = FontLoader.loadFont("src/main/resources/fonts/slkscr.ttf", 28);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/textures.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        int height = 32;
        int width = 32;

        wood = sheet.crop(width, height * 3, width, height);

        btnStart = new BufferedImage[2];
        btnStart[0] = sheet.crop(width * 2, height * 6, width * 2, height);
        btnStart[1] = sheet.crop(width * 2, height * 7, width * 2, height);

        playerDown = new BufferedImage[2];
        playerUp = new BufferedImage[2];
        playerLeft = new BufferedImage[2];
        playerRight = new BufferedImage[2];

        playerDown[0] = sheet.crop(0, 0, width, height);
        playerDown[1] = sheet.crop(width, 0, width, height);
        playerUp[0] = sheet.crop(width * 2, 0, width, height);
        playerUp[1] = sheet.crop(width * 3, 0, width, height);
        playerRight[0] = sheet.crop(0, height, width, height);
        playerRight[1] = sheet.crop(width, height, width, height);
        playerLeft[0] = sheet.crop(width * 2, height, width, height);
        playerLeft[1] = sheet.crop(width * 3, height, width, height);

        zombieDown = new BufferedImage[2];
        zombieUp = new BufferedImage[2];
        zombieLeft = new BufferedImage[2];
        zombieRight = new BufferedImage[2];

        zombieDown[0] = sheet.crop(0, height * 4, width, height);
        zombieDown[1] = sheet.crop(width, height * 4, width, height);
        zombieUp[0] = sheet.crop(width * 2, height * 4, width, height);
        zombieUp[1] = sheet.crop(width * 3, height * 4, width, height);
        zombieRight[0] = sheet.crop(0, height * 5, width, height);
        zombieRight[1] = sheet.crop(width, height * 5, width, height);
        zombieLeft[0] = sheet.crop(width * 2, height * 5, width, height);
        zombieLeft[1] = sheet.crop(width * 3, height * 5, width, height);

        dirt = sheet.crop(width, height * 2, width, height);
        grass = sheet.crop(width * 2, height * 2, width, height);
        wall = sheet.crop(width * 3, height * 2, width, height);
        well = sheet.crop(width *3, height * 3, width, height);
        stonesPath = sheet.crop(width * 2, height * 3, width, height);

        tree = sheet.crop(width * 4, height * 2, width, height);
        rock = sheet.crop(width, height * 3, width, height);
        pine = sheet.crop(width * 4, height * 4, width, height);
        sculpture = sheet.crop(width * 4, 0, width, height);
    }
}
