package ru.znay.znay.he.gfx.weather;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.tile.Tile;

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
 * Created on 19.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 19.04.12: Original version (WHo)
 * 10:31: Time
 */
public class Separation extends Entity {

    private long timeLife;

    public Separation(int x, int y) {
        this.x = x;
        this.y = y;
        timeLife = 20;
    }


    public void tick() {
        timeLife--;
        if (timeLife < 0) {
            removed = true;
        }
    }

    public void render(Screen screen) {

        int xo = x;
        int yo = y;

        yo += 4;
        int waterColor = PaletteHelper.getColor(-1, -1, -1, 444);
        if (timeLife / 8 % 2 == 0) {
            waterColor = PaletteHelper.getColor(-1, 444, -1, -1);
        }
        screen.render(xo + 0, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 0);
        screen.render(xo + Tile.HALF_SIZE, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 1);

    }
}
