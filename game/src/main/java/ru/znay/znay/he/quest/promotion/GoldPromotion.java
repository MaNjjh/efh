package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 06.04.12
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class GoldPromotion implements QuestPromotion {

    private int gold;

    @Override
    public void promotion(Player player) {
        player.getInventory().add(new ResourceItem(Resource.coin, gold));
    }

    public int getGold() {
        return gold;
    }

    @Override
    public void setParam(String param) {

    }

    @Override
    public void setCount(String count) {
        this.gold = Integer.decode(count);
    }
}
