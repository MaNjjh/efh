package ru.znay.znay.he.model.mob.boss.snake;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.CharacterState;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;
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
 * 10:07: Time
 */
public class Snake extends SnakePart {
    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.xr = 6;
        this.yr = 6;
        this.health = 200;
        currentState = new CharacterState(0, 100, 0, 0, 0);
        this.team = ETeam.ENEMY_TEAM;
    }

    public void setMovement(double xa, double ya, double rotate) {
        this.xa = xa;
        this.ya = ya;
    }

    public void render(Screen screen) {
        int xd = x - 8;
        int yd = y - 7;

        int col = PaletteHelper.getColor(-1, 500 - (level.getNumber() % 5) * 100, 411- (level.getNumber() % 4) * 100, 322);
        if (hurtTime > 0)
            col = PaletteHelper.getColor(-1, 555, 555, 555);

        screen.render(2, xd + 1, yd + 1, 2 * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 0, 0, 0), 2);
        screen.render(2, xd, yd, 2 * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, col, 2);
    }
}
