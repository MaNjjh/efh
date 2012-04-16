package ru.znay.znay.he.model.weapon.arrow;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
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
 * Created on 16.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 16.04.12: Original version (WHo)
 * 16:06: Time
 */
public class Arrow extends Entity {

    private double betta;
    protected int damage;
    private int timeLife;
    private ETeam ownerTeam;
    protected int color;
    protected int speed;

    public Arrow(ETeam ownerTeam, int x, int y, double vx, double vy, int damageBonus) {
        this.ownerTeam = ownerTeam;
        this.x = x;
        this.y = y;
        this.timeLife = 35;
        this.damage = (random.nextInt(3) + 1) + damageBonus;
        this.betta = Math.atan2(vy, vx);
        this.color = PaletteHelper.getColor(-1, 333, 111, 222);
        this.speed = 4;
    }

    @Override
    public void tick() {

        double xa = speed;
        double ya = Math.sin(random.nextGaussian());

        double xxa = xa * Math.cos(betta) - ya * Math.sin(betta);
        double yya = ya * Math.cos(betta) + xa * Math.sin(betta);

        if (!move((int) Math.floor(xxa), (int) Math.floor(yya))) {
            removed = true;
        }

        timeLife--;
        if (timeLife < 0) removed = true;
    }

    @Override
    public void render(Screen screen) {
        screen.render(Math.toDegrees(betta) + 90, x - xr, y - yr, 0, 8 * Tile.HALF_SIZE, color, 0);
    }

    @Override
    public boolean canFly() {
        return true;
    }

    public int getDamage() {
        return damage;
    }

    public ETeam getOwnerTeam() {
        return ownerTeam;
    }
}
