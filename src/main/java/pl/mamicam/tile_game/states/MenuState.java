package pl.mamicam.tile_game.states;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.gfx.Assets;
import pl.mamicam.tile_game.ui.UiImageButton;
import pl.mamicam.tile_game.ui.UiManager;

import java.awt.*;

public class MenuState extends State {
    private UiManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UiManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UiImageButton(200, 200, 128, 64, Assets.btnStart, () -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
        }));
    }

    @Override
    public void upload() {
        uiManager.upload();

        // Temporarily just go directly to the GameState, skip the menu state!
        handler.getMouseManager().setUiManager(null);
        State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }
}
