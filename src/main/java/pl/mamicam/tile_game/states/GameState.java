package pl.mamicam.tile_game.states;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.worlds.World;

import java.awt.*;

public class GameState extends State {
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"src/main/resources/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void upload() {
        world.upload();

    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
    }
}
