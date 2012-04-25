package ru.znay.znay.he.quest.template;

import ru.znay.znay.he.model.Mob;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 25.04.12
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public class KillTemplate2 extends DefaultTemplate {
    private int needToKill;
    private int currentKills = 0;
    private String mobName;

    public KillTemplate2(int needToKill, String mobName) {
        super(TemplateType.KILL);
        init(needToKill, 0, mobName);
    }

    public KillTemplate2(int needToKill, int currentKills, String mobName) {
        super(TemplateType.KILL);

        init(needToKill, currentKills, mobName);
    }

    private void init(int needToKill, int currentKills, String mobName) {
        this.needToKill = needToKill;
        this.currentKills = currentKills;
        this.mobName = mobName;
        this.checkStatus();
    }

    private void checkStatus() {
        if (needToKill <= currentKills)
            this.complete();
    }

    public void incKill(String name) {
        if (complete) return;
        if (this.mobName.equalsIgnoreCase(name)) {
            currentKills++;
            checkStatus();
        }
    }
}
