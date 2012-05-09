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

    @Override
    public void die() {
        super.die();
        level.add(new ItemEntity(new EquipmentItem(Equipment.rareBow), x + random.nextInt(31) - 15, y + random.nextInt(31) - 15));
        level.add(new ItemEntity(new EquipmentItem(Equipment.rareArmor), x + random.nextInt(31) - 15, y + random.nextInt(31) - 15));
        level.add(new ItemEntity(new EquipmentItem(Equipment.rareShoes), x + random.nextInt(31) - 15, y + random.nextInt(31) - 15));


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
