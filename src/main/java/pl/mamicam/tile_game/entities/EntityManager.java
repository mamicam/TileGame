package pl.mamicam.tile_game.entities;

import pl.mamicam.tile_game.Handler;
import pl.mamicam.tile_game.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private Handler handler;
    private Player player;
    private List<Entity> entities;
    private Comparator<Entity> renderSorter = (a, b) -> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
            return -1;
        }
        return 1;
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void upload() {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()){
            Entity entity = it.next();
            entity.upload();
            if (!entity.isActive()) {
                it.remove();
            }
        }
        entities.sort(renderSorter);
    }


    public void render(Graphics graphics) {
        for (Entity entity : entities) {
            entity.render(graphics);
        }
        player.postRender(graphics);
    }
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities)  {
        this.entities = entities;
    }
}
