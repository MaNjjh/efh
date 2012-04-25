package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 25.04.12
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class ItemPromotion implements QuestPromotion {
    private String itemName;
    private int count;
    
    @Override
    public void promotion(Player player) {
        //todo добавитьв выдачу итема игроку
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
