package ru.znay.znay.he.quest;

import ru.znay.znay.he.gfx.helper.TextFileHelper;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.npc.NpcTrigger;
import ru.znay.znay.he.quest.template.TemplateType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 26.04.12
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public class QuestManager {
    List<AbsQuest> quests;

    public QuestManager(Level level) {
        quests = (new QuestParser()).parseQuests(TextFileHelper.LoadTextDB(level.getNumber() + ".txt"), level);

        for (AbsQuest quest : quests) {
            //quest.accept(questHandler);
            if (quest.getId().equals("1")) quest.accept(level.getQuestHandler());
            if (quest.getNextQuestID() > 0) {
                for (AbsQuest nquest : quests) {
                    if (quest.getNextQuestID() == Integer.decode(nquest.getId())) {
                        quest.setNextQuest(nquest);
                    }
                }
            }
        }
    }


    public AbsQuest getQuestById(int id) {
        for (AbsQuest quest:quests){
            if (Integer.decode(quest.getId()) == id){
                return quest;
            }
        }
        return null;
    }

    public AbsQuest getQuestByName(String name) {
        for (AbsQuest quest:quests){
            if (quest.getName().equalsIgnoreCase(name)){
                return quest;
            }
        }
        return null;
    }
}
