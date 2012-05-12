package ru.znay.znay.he.model.mob.boss;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.item.equipment.Equipment;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 09.05.12
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class Boss extends Mob {
    public static Equipment[][] equipmentDrop = {
            {Equipment.simpleArmor, Equipment.simpleShoes}, // 1-level
            {Equipment.strongBow}, // 2-level
            {Equipment.secondStrongArmor, Equipment.strongShoes}, // 3-level
            {Equipment.thirdStrongArmor}, // 4-level
            {Equipment.rareArmor, Equipment.rareBow, Equipment.rareShoes}, // 5-level
            {Equipment.ultraShoes, Equipment.ultraBow, Equipment.ultraArmor}, // 6-level
            {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};

    @Override
    public boolean canSwim() {
        return true;
    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }
}
