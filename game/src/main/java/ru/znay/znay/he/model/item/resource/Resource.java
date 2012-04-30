package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.HashMap;
import java.util.Map;

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
 * 15:20: Time
 */
public class Resource {

    public static final Map<String, Resource> resources = new HashMap<String, Resource>();

    public static Resource life = new Resource("Life", 1, 4, PaletteHelper.getColor(-1, 0, 500, 555));
    public static Resource apple = new Resource("Apple", 0, 3, PaletteHelper.getColor(-1, 0, 510, 555));
    public static Resource coin = new Resource("Coin", 0, 3, PaletteHelper.getColor(-1, 0, 552, 555));
    public static Resource bigCoin = new Resource("B.Coin", 1, 3, PaletteHelper.getColor(-1, 0, 552, 555));
    public static Resource berry = new Resource("Berry", 0, 3, PaletteHelper.getColor(-1, 0, 510, 555));

    private final String name;
    private final int xSprite;
    private final int ySprite;
    private final int color;

    public Resource(String name, int xSprite, int ySprite, int color) {
        if (name.length() > 6) throw new RuntimeException("Name cannot be longer than six characters!");
        this.name = name;
        this.xSprite = xSprite;
        this.ySprite = ySprite;
        this.color = color;
        resources.put(name, this);
    }

    public static Resource getEquipmentByName(String name) {
        return resources.get(name);
    }

    public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
        return false;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getxSprite() {
        return xSprite;
    }

    public int getySprite() {
        return ySprite;
    }
}
