package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;

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
        player.touchItem(new ItemEntity(new EquipmentItem(Equipment.getEquipmentByName(itemName.toLowerCase())), 0, 0));
    }

    public String getItemName() {
        return itemName;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void setParam(String param) {
        this.itemName = param;
    }

    @Override
    public void setCount(String count) {
        this.count = Integer.decode(count);
    }
}
