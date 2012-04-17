package ru.znay.znay.he.model.item;

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
 * Created on 17.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 17.04.12: Original version (WHo)
 * 15:04: Time
 */
public class Inventory {
    public List<Item> items = new ArrayList<Item>();

    public void add(Item item) {
        add(items.size(), item);
    }

    public void add(int slot, Item item) {
        if (item instanceof ResourceItem) {
            ResourceItem toTake = (ResourceItem) item;
            ResourceItem has = findResource(toTake.getResource());
            if (has == null) {
                items.add(slot, toTake);
            } else {
                has.addCount(toTake.getCount());
            }
        } else {
            items.add(slot, item);
        }
    }

    public ResourceItem findResource(Resource resource) {
        for (Item item : items) {
            if (item instanceof ResourceItem) {
                ResourceItem has = (ResourceItem) item;
                if (has.getResource() == resource) return has;
            }
        }
        return null;
    }

    public boolean hasResources(Resource r, int count) {
        ResourceItem ri = findResource(r);
        if (ri == null) return false;
        return ri.getCount() >= count;
    }

    public boolean removeResource(Resource r, int count) {
        ResourceItem ri = findResource(r);
        if (ri == null) return false;
        if (ri.getColor() < count) return false;
        ri.addCount(-count);
        if (ri.getCount() <= 0) items.remove(ri);
        return true;
    }

    public int count(Item item) {
        if (item instanceof ResourceItem) {
            ResourceItem ri = findResource(((ResourceItem) item).getResource());
            if (ri != null) return ri.getCount();
        } else {
            int count = 0;
            for (Item item1 : items) {
                if (item1.matches(item)) count++;
            }
            return count;
        }
        return 0;
    }

    public List<Item> getItems() {
        return items;
    }
}
