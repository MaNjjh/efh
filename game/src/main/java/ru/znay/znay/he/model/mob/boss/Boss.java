package ru.znay.znay.he.model.mob.boss;

import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.npc.Warper;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 09.05.12
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class Boss extends Mob {
    public static Equipment[][] equipmentDrop = {
           // {Equipment.ultraShoes, Equipment.ultraBow, Equipment.ultraArmor}, // 1-level
            {Equipment.simpleShoes}, // 1-level
            {Equipment.strongBow, Equipment.secondArmor}, // 2-level
            {Equipment.secondStrongArmor}, // 2-level
            {Equipment.thirdArmor}, // 3-level
            {Equipment.thirdStrongArmor, Equipment.strongShoes}, // 4-level
            {Equipment.rareArmor, Equipment.rareBow, Equipment.rareBow}, // 5-level
            {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
}
