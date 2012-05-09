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
    private Equipment[][] equipmentDrop = {
            {Equipment.simpleShoes}, // 1-level
            {Equipment.strongBow, Equipment.secondArmor}, // 2-level
            {Equipment.secondStrongArmor}, // 2-level
            {Equipment.thirdArmor}, // 3-level
            {Equipment.thirdStrongArmor, Equipment.strongShoes}, // 4-level
            {Equipment.rareArmor, Equipment.rareBow, Equipment.rareBow}, // 5-level
            {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};

    @Override
    public void die() {
        super.die();

        int numLevel = this.level.getNumber();
        for (int i = 0; i < equipmentDrop[numLevel].length; i++) {
            this.level.add(new ItemEntity(new EquipmentItem(equipmentDrop[numLevel][i]), x + random.nextInt(31) - 15, y + random.nextInt(31) - 15));
        }

        int offsetX = 0;
        int offsetY = 0;
        int xx = 0;
        int yy = 0;
        while (true) {

            xx = (level.getPlayer().getX() >> 4) + offsetX;
            yy = (level.getPlayer().getY() >> 4) + offsetY;

            offsetX += random.nextInt(2) * 2 - 1;
            offsetY += random.nextInt(2) * 2 - 1;

            if (xx == level.getPlayer().getX() >> 4 && yy == level.getPlayer().getY() >> 4) continue;
            if (level.getTile(xx, yy) == Tile.lava) continue;
            break;
        }

        level.add(new Warper(xx << 4, yy << 4, true));
    }
}
