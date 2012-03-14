package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 05.03.12
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class Particle extends Entity {

    protected int time = 0;
    protected double xa, ya, za;
    protected double xx, yy, zz;

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
        xx = x;
        yy = y;
        zz = 2;
        xa = random.nextGaussian() * 0.3;
        ya = random.nextGaussian() * 0.2;
        za = random.nextFloat() * 0.7 + 2;
        time = 60;
    }

    public void tick() {
        time--;
        if (time < 0) {
            removed = true;
        }
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
