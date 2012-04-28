package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 24.04.12
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class NpcTrigger extends Entity {
    private int id;

    public NpcTrigger(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.xr = 2;
        this.yr = 2;
        this.id = id;
    }


    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            level.getQuestHandler().onTrigger(this);
        }
    }

    public int getId() {
        return id;
    }
}
