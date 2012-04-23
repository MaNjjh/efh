package ru.znay.znay.he.model.weapon.arrow;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;

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
 * 16:31: Time
 */
public class FireArrow extends Arrow {

    public FireArrow(ETeam ownerTeam, int x, int y, double vx, double vy, int damageBonus) {
        super(ownerTeam, x, y, vx, vy, damageBonus * 3 + 3);
        this.color = PaletteHelper.getColor(-1, 300, 500, 200);
        this.speed = 5;
    }

    @Override
    public void tick() {
        super.tick();
        level.getFireParticles().createExplosion(x, y, 0.5, -0.6, 4);

    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
    }

}
