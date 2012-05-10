package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 06.04.12
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class MergedPromotion implements QuestPromotion {
    private List<QuestPromotion> questPromotionList = new ArrayList<QuestPromotion>();

    public void add(QuestPromotion questPromotion) {
        this.questPromotionList.add(questPromotion);
    }

    @Override
    public void promotion(Player player) {
        for (QuestPromotion questPromotion : this.questPromotionList) {
            questPromotion.promotion(player);
        }
    }

    @Deprecated
    @Override
    public void setParam(String param) {
    }

    @Deprecated
    @Override
    public void setCount(String count) {
    }
}
