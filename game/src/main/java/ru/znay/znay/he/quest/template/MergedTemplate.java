package ru.znay.znay.he.quest.template;

import ru.znay.znay.he.model.npc.NpcTrigger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 25.04.12
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class MergedTemplate {
    private List<DefaultTemplate> list = new LinkedList<DefaultTemplate>();

    public void add(DefaultTemplate defaultTemplate) {
        list.add(defaultTemplate);
    }

    public boolean isCompleted() {
        for (DefaultTemplate quest : list)
            if (!quest.isComplete()) {
                return false;
            }
        return true;
    }

    public void incKills(String name) {
        for (DefaultTemplate quest : list) {
            if (quest instanceof KillTemplate) {
                ((KillTemplate) quest).incKill(name);
            }
        }
    }

    public void incItems(String name) {
        for (DefaultTemplate quest : list) {
            if (quest instanceof CollectTemplate) {
                ((CollectTemplate) quest).incCount(name);
            }
        }

    }

    public int calcQuestType() {
        int result = 0;
        for (DefaultTemplate quest : list)
            result |= quest.getType();

        return result;
    }

    public void triggerQuest(NpcTrigger npcTrigger) {
        for (DefaultTemplate template : list) {
            if (template.getType() == TemplateType.MOVE && ((MoveTemplate) template).getNpcID() == npcTrigger.getId()) {
                template.complete();
            }
        }
    }
}
