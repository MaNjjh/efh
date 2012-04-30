package ru.znay.znay.he.quest;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.GuiTypedTextPanel;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.npc.NpcTrigger;
import ru.znay.znay.he.quest.promotion.GoldPromotion;
import ru.znay.znay.he.quest.promotion.PromotionFactory;
import ru.znay.znay.he.quest.template.TemplateType;
import ru.znay.znay.he.sound.Sound;

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
    private Player player;

    public QuestHandler(Player player) {
        this.player = player;
    }

    public QuestHandler() {

    }

    public boolean accept(AbsQuest absQuest) {
        if (absQuest == null) return false;
        String id = absQuest.getId();

        if (this.quests.get(id) != null) return false;

        this.quests.put(id, absQuest);
        GuiManager.getInstance().add(new GuiTypedTextPanel(absQuest.getDescription(), 4, 4, 100), "quest_accept");
        return true;
    }

    public void onTrigger(NpcTrigger npcTrigger) {
        for (String key : quests.keySet()) {
            AbsQuest quest = quests.get(key);
            if (quest.isType(TemplateType.MOVE)) {
                quest.getMergedTemplate().triggerQuest(npcTrigger);
                checkQuest(quest);
            }
        }
    }


    public void updateKills(Mob mob) {
        for (String key : this.quests.keySet()) {
            AbsQuest quest = this.quests.get(key);
            if (quest.isType(TemplateType.KILL)) {
                quest.getMergedTemplate().incKills(mob.getClass().getSimpleName());
                checkQuest(quest);
            }
        }
    }

    private void checkQuest(AbsQuest quest) {
        if (quest.isCompleted()) {
            String message = String.format("Квест '%s' закончен! Поздравляем!", quest.getName());

            GuiManager.getInstance().add(new GuiTypedTextPanel(message, 4, 40, 50), "quest_checkQuest");

            if (quest.getQuestPromotion() != null) {
                quest.getQuestPromotion().promotion(this.player);
            }

            if (quest.hasNextPart()) {
                quest.nextQuest.accept(this);
            }

            Sound.questBegin.play();

            this.quests.remove(quest.getId());
        }
    }

    @Deprecated
    public void checkAllQuest() {
        for (String key : this.quests.keySet()) {
            checkQuest(this.quests.get(key));
        }
    }

   /* public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        GoldPromotion promotion = (GoldPromotion) PromotionFactory.getInstance().createPromotion("Price");
        promotion.setGold(10);
        System.out.println(promotion.getGold());
    }  */

    public void setPlayer(Player player) {
        this.player = player;
    }
}
