package ru.znay.znay.he.model.particle;

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
 * Created on 20.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 20.04.12: Original version (WHo)
 * 11:06: Time
 */
public class SmashParticle extends Particle {
    public SmashParticle(int x, int y, int color) {
        super(x, y, color);
    }
    public void tick() {
        super.tick();
        if (zz != 0) {
            xx += xa;
            yy += ya;
            zz += za;
            if (zz < 0) {
                zz = 0;
                za *= -0.5;
                xa *= 0.6;
                ya *= 0.6;
            }
            za -= 0.15;
            x = (int) xx;
            y = (int) yy;
        }
    }
}
