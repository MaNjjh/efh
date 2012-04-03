package ru.znay.znay.he.quest;

import ru.znay.znay.he.model.npc.NPC;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbsQuest implements QuestStatus {
    private String id = null;
    private String name;
    private String description;
    private NPC ownerQuest;
    private int goal;

    private QuestPromotion questPromotion;

    public boolean accept(QuestHandler questHandler) {
        if (id == null) id = UUID.randomUUID().toString();
        return questHandler.accept(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NPC getOwnerQuest() {
        return ownerQuest;
    }

    public void setOwnerQuest(NPC ownerQuest) {
        this.ownerQuest = ownerQuest;
    }

    public String getId() {
        return id;
    }

    public QuestPromotion getQuestPromotion() {
        return questPromotion;
    }

    public void setQuestPromotion(QuestPromotion questPromotion) {
        this.questPromotion = questPromotion;
    }
}
