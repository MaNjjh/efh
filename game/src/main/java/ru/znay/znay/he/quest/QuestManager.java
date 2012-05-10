package ru.znay.znay.he.quest;

import ru.znay.znay.he.gfx.helper.TextFileHelper;
import ru.znay.znay.he.model.level.Level;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 26.04.12
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class QuestManager {
    List<AbsQuest> quests;

    public QuestManager(QuestHandler questHandler) {
        quests = new QuestParser().parseQuests(TextFileHelper.LoadQuestDB());
    }


    public AbsQuest getQuestById(int id) {
        for (AbsQuest quest : quests) {
            if (Integer.decode(quest.getId()) == id) {
                return quest;
            }
        }
        return null;
    }

    public AbsQuest getQuestByName(String name) {
        for (AbsQuest quest : quests) {
            if (quest.getName().equalsIgnoreCase(name)) {
                return quest;
            }
        }
        return null;
    }
}
