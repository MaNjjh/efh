package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 06.04.12
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class PricePromotion implements QuestPromotion {

    private int price;

    @Override
    public void promotion(Player player) {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
