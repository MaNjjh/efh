package ru.znay.znay.he.quest;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.TypedTextPanel;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.quest.template.KillTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestHandler {
    private Map<String, AbsQuest> quests = new HashMap<String, AbsQuest>();
    private GuiManager guiManager;

    public QuestHandler(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    public boolean accept(AbsQuest absQuest) {
        if (absQuest == null) return false;

        String id = absQuest.getId();

        if (this.quests.get(id) != null) return false;

        this.quests.put(id, absQuest);
        this.guiManager.add(new TypedTextPanel(absQuest.getDescription(), 4, 4, 50));
        return true;
    }

    public void updateKills(Mob mob) {
        for (String key : this.quests.keySet()) {
            AbsQuest quest = this.quests.get(key);
            if (quest instanceof KillTemplate) {
                ((KillTemplate) quest).updateKills(mob.getClass().getSimpleName());
            }
        }
    }

    public void checkAllQuest() {
        for (String key : this.quests.keySet()) {
            AbsQuest quest = this.quests.get(key);
            if (quest.isCompleted()) {
                String message = String.format("Квест '%s' закончен! Поздравляем!", quest.getName());

                guiManager.add(new TypedTextPanel(message, 4, 4, 50));
                this.quests.remove(key);
            }
        }
    }
}
