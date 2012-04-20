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
 * Created on 18.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 18.04.12: Original version (WHo)
 * 15:59: Time
 */
public class FireParticle extends Particle {

    public FireParticle(int x, int y) {
        super(x, y, 0xFF7F00);
        this.time = 60 * 2;
        this.speed = 1;
        this.zz = 0;
    }

    public void tick() {
        super.tick();
        double xxa = speed;
        double yya = speed;//Math.sin(x);

        double betta = Math.atan2(-1, -1);

        xa = xxa * Math.cos(betta) - yya * Math.sin(betta);
        ya = yya * Math.cos(betta) + xxa * Math.sin(betta);

        x += xa;
        y += ya;

    }

}
