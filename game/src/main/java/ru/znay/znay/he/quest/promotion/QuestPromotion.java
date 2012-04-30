package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public interface QuestPromotion {
    void promotion(Player player);
    void setParam(String param);
    void setCount(String count);
}
