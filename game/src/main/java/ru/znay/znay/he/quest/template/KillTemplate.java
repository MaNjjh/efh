package ru.znay.znay.he.quest.template;

import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.quest.AbsQuest;
import ru.znay.znay.he.quest.NextQuest;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */

public class KillTemplate extends AbsQuest {
    private int needToKill;
    private int killed;
    private String mobName;


    public KillTemplate(int needToKill, Class<? extends Mob> clazz, NextQuest nextQuest) {
        this.needToKill = needToKill;
        this.mobName = clazz.getSimpleName().toLowerCase();
       // this.nextQuest = nextQuest;
    }

    public KillTemplate(String mobName, int needToKill) {
        this.mobName = mobName;
        this.needToKill = needToKill;
    }

    public int toCompleted() {
        return needToKill - killed;
    }

    public boolean updateKills(String mobName) {
        if (mobName.toLowerCase().equals(this.mobName)) {
            killed++;
            return true;
        }
        return false;
    }

    @Override
    public boolean isCompleted() {
        return needToKill <= killed;
    }

    public int getNeedToKill() {
        return needToKill;
    }

    public void setNeedToKill(int needToKill) {
        this.needToKill = needToKill;
    }

    public int getKilled() {
        return killed;
    }

    public void setKilled(int killed) {
        this.killed = killed;
    }
}
