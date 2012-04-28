package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.quest.AbsQuest;
import ru.znay.znay.he.quest.QuestHandler;
import ru.znay.znay.he.quest.template.MoveTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 24.04.12
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class NpcTrigger extends Entity {
    private int id;
    private QuestHandler questHandler;
    
    public NpcTrigger(int x, int y, int id, QuestHandler questHandler) {
        this.x = x;
        this.y = y;
        this.xr = 2;
        this.yr = 2;
        this.id = id;
        this.questHandler = questHandler;
    }



    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            questHandler.onTrigger(this);
        }
    }

    public int getId() {
        return id;
    }
}
