package ru.znay.znay.he.model.builds.utensils;

import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.Inventory;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ========================================
 * ItCorp v. 1.0 class library
 * ========================================
 * <p/>
 * http://www.it.ru
 * <p/>
 * (C) Copyright 1990-2011, by ItCorp.
 * <p/>
 * --------------------
 * <Filename>.java
 * --------------------
 * Created on 02.05.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 02.05.12: Original version (WHo)
 * 15:10: Time
 */
public class Container extends Utensils {
    protected Inventory inventory = new Inventory();

    public Container(int x, int y, int xr, int yr) {
        super(x, y, xr, yr);
        List<String> testList = new ArrayList<String>();
        testList.add("r.bow");
        testList.add("coin");
        testList.add("b.coin");
        setItems(testList);
    }

    public void setItems(List<String> items) {
        for (String name : items) {
            Equipment equipment = Equipment.getEquipmentByName(name);
            if (equipment != null) {
                inventory.add(new EquipmentItem(equipment));
                continue;
            }

            Resource resource = Resource.getResourceByName(name);
            if (resource != null) {
                inventory.add(new ResourceItem(resource));
                continue;
            }
        }
    }

    public boolean removeAfterUsed() {
        return true;
    }

    @Override
    public boolean interact(Item item1, Player player, int dir) {
        for (Item item : inventory.getItems()) {
            if (item instanceof ResourceItem) {
                ResourceItem resourceItem = (ResourceItem) item;
                for (int i = 0; i < resourceItem.getCount(); i++) {
                    this.level.add(new ItemEntity(new ResourceItem(resourceItem.getResource()), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
                }
            }
            if (item instanceof EquipmentItem) {
                EquipmentItem equipmentItem = (EquipmentItem) item;
                this.level.add(new ItemEntity(new EquipmentItem(equipmentItem.getEquipment()), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
            }
        }

        inventory.getItems().clear();
        if (removeAfterUsed()) {
            die();
        }
        return true;
    }
}
