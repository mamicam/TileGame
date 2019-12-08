package pl.mamicam.tile_game;

import pl.mamicam.tile_game.display.Display;
import pl.mamicam.tile_game.gfx.Assets;
import pl.mamicam.tile_game.gfx.GameCamera;
import pl.mamicam.tile_game.input.KeyManager;
import pl.mamicam.tile_game.input.MouseManager;
import pl.mamicam.tile_game.states.GameState;
import pl.mamicam.tile_game.states.MenuState;
import pl.mamicam.tile_game.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    Thread thread;

    //States
    public State gameState;
    public State menuState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;


    //Camera
    private GameCamera gameCamera;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        //Handler
        Handler handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        State gameState = new GameState(handler);
        State menuState = new MenuState(handler);
        State.setState(gameState);
    }

    private void update() {
        keyManager.upload();
        if (State.getState() != null) {
            State.getState().upload();
        }
    }

    private void render() {
        BufferStrategy bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        // Clear Screen
        graphics.clearRect(0, 0, width, height);
        //Draw Here!
        if (State.getState() != null) {
            State.getState().render(graphics);
        }
        // End Drawing!
        bufferStrategy.show();
        graphics.dispose();
    }

    @Override
    public void run() {
        init();

        float fps = 60f;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer = now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
       try {
           thread.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
}
