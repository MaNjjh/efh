package ru.znay.znay.he.quest;

import ru.znay.znay.he.model.npc.NPC;
import ru.znay.znay.he.quest.promotion.QuestPromotion;
import ru.znay.znay.he.quest.template.MergedTemplate;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class AbsQuest implements QuestStatus, NextQuest {
    private String id = null;
    private String name;
    private String description;
    private NPC ownerQuest;
    private int goal;
    private int type;
    private boolean complete = false;
    protected AbsQuest nextQuest = null;
    private int nextQuestID = 0;

    private QuestPromotion questPromotion;
    private MergedTemplate mergedTemplate;

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

    @Override
    public boolean hasNextPart() {
        return (nextQuest != null);
    }

    public QuestPromotion getQuestPromotion() {
        return questPromotion;
    }

    public void setQuestPromotion(QuestPromotion questPromotion) {
        this.questPromotion = questPromotion;
    }

    public boolean isType(int type) {
        return ((this.type & type) == type);
    }

    public MergedTemplate getMergedTemplate() {
        return mergedTemplate;
    }

    public void setMergedTemplate(MergedTemplate mergedTemplate) {
        this.mergedTemplate = mergedTemplate;
        this.calcQuestType();
    }

    public void calcQuestType() {
        if (mergedTemplate != null) {
            this.type = mergedTemplate.calcQuestType();
        }
    }

    @Override
    public boolean isCompleted() {
        if (!complete) {
            complete = mergedTemplate.isCompleted();
        }
        return complete;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNextQuestID() {
        return nextQuestID;
    }

    public void setNextQuestID(int nextQuestID) {
        this.nextQuestID = nextQuestID;
    }

    public void setNextQuest(AbsQuest nextQuest) {
        this.nextQuest = nextQuest;
    }

    @Override
    public void initNextQuest(QuestHandler questHandler) {
        this.nextQuest.accept(questHandler);
    }
}
