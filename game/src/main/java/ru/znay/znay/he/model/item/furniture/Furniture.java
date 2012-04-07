package ru.znay.znay.he.model.item.furniture;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.FurnitureItem;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 07.04.12
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class Furniture extends Entity {
    private int pushTime = 0;
    private int pushDir = -1;
    protected String name;
    private Player shouldTake;

    public Furniture(String name) {
        this.name = name;
        xr = 3;
        yr = 3;
    }

    @Override
    public void tick() {
        if (shouldTake != null) {
            removed = true;
            shouldTake.setActiveItem(new FurnitureItem(this));
            shouldTake = null;
        }

        if (pushDir == 0) move(0, +1);
        if (pushDir == 1) move(0, -1);
        if (pushDir == 2) move(-1, 0);
        if (pushDir == 3) move(+1, 0);
        pushDir = -1;
        if (pushTime > 0) pushTime--;
    }

    public boolean blocks(Entity e) {
        return true;
    }

    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Player && pushTime == 0) {
            pushDir = ((Player) entity).getDir();
            pushTime = 10;
        }
    }

    public void take(Player player) {
        shouldTake = player;
    }
}
