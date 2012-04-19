package ru.znay.znay.he.gfx.weather;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;

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
 * 9:51: Time
 */
public class Rain extends Entity {

    double xa;
    double ya;

    int timeLife;

    public Rain(Player player, double xa, double ya, int speed) {

        if (random.nextInt(2) == 0) {
            this.x = (player.getX() - Constants.SCREEN_WIDTH + random.nextInt(Constants.SCREEN_WIDTH << 1));
            this.y = (player.getY() - (Constants.SCREEN_HEIGHT >> 1) - 10);
        } else {
            this.x = (player.getX() + (Constants.SCREEN_WIDTH >> 1) - 10);
            this.y = (player.getY() - Constants.SCREEN_HEIGHT + random.nextInt(Constants.SCREEN_HEIGHT << 1));
        }

        this.xa = xa * speed;
        this.ya = ya * speed;

        timeLife = random.nextInt(150);
    }

    public void tick() {

        timeLife--;
        if (timeLife < 0) {
            removed = true;
            level.add(new Separation(x, y));
            return;
        }

        this.x += xa;
        this.y += ya;
    }

    public void render(Screen screen) {

        int xo = x - screen.getXOffset();
        int yo = y - screen.getYOffset();
        BitmapHelper.drawLine(xo, yo, xo + (int) xa, yo + (int) ya, 0xffffff, screen.getViewPort());
        /* BitmapHelper.drawPoint(xo, yo, 0xffffff, screen.getViewPort());
 BitmapHelper.drawPoint(xo + 1, yo + 1, 0xffffff, screen.getViewPort());
 BitmapHelper.drawPoint(xo - 1, yo - 1, 0xffffff, screen.getViewPort());
 BitmapHelper.drawPoint(xo - 1, yo + 1, 0xffffff, screen.getViewPort());
 BitmapHelper.drawPoint(xo + 1, yo - 1, 0xffffff, screen.getViewPort());*/
    }
}
