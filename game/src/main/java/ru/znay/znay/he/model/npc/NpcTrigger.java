package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.quest.AbsQuest;
import ru.znay.znay.he.quest.template.MoveTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 24.04.12
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class NpcTrigger extends Entity {
    MoveTemplate quest = null;

    public NpcTrigger(int x, int y, int w, int h, MoveTemplate quest) {
        this.x = x;
        this.y = y;
        this.xr = w;
        this.yr = h;
        this.quest = quest;
    }

    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            quest.complete();
        }
    }
}
