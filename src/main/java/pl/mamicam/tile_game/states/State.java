package pl.mamicam.tile_game.states;

import pl.mamicam.tile_game.Handler;

import java.awt.*;

public abstract class State {
    private static State currentState = null;
    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    //CLASS
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void upload();
    public abstract void render(Graphics graphics);
}
