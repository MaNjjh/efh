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

    @Override
    public void initParticle(int x, int y, double xa, double ya) {
        super.initParticle(x, y, xa, ya);
        this.time = random.nextInt(4) + 2;
    }

    public void tick() {
        super.tick();

        if (time < 2) {
            color = 0xff0000;
        } else {
            color = 0xff7f00;
        }

        x += xa;
        y += ya;
    }

}