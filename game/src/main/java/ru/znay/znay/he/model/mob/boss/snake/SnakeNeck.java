package ru.znay.znay.he.model.mob.boss.snake;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
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
 * Created on 27.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 27.04.12: Original version (WHo)
 * 10:05: Time
 */
public class SnakeNeck extends SnakePart {
    protected SnakePart child;
    protected double baseRot = Math.PI * 1.25;
    protected double rot = 0;

    public SnakeNeck(int x, int y, SnakePart child) {
        this.x = x;
        this.y = y;
        this.xr = 6;
        this.yr = 6;
        this.child = child;
        this.health = 100;
    }

    public void tick() {
        super.tick();

        rot = Math.sin(time / 40.0) * Math.cos(time / 13.0) * 0.5;
        rot *= 0.9;
        double rr = baseRot + rot;
        double xa = Math.sin(rr) * 7;
        double ya = Math.cos(rr) * 7;
        if (!child.isRemoved()) {
            child.setMovement(xa + x, ya + y, rr);
        }
    }

    public void setMovement(double xa, double ya, double rotate) {
        this.xa = xa;
        this.ya = ya;
        this.baseRot = rotate;
    }

    public void render(Screen screen) {
        int xd = x - 8;
        int yd = y - 9;
        screen.render(2, xd + 1, yd + 1, 2 * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 0, 0, 0), 0);
        screen.render(2, xd, yd, 2 * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 040, 051, 150), 0);
    }
}
