package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Screen;
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
    protected int speed = 1;
    protected int color;

    public Particle(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public void render(Screen screen) {
        int xo = x - screen.getXOffset();
        int yo = y - screen.getYOffset();

        if (zz > 0)
            BitmapHelper.drawPoint(xo, yo, 0, screen.getViewPort());

        if (time > 100)
            BitmapHelper.drawPoint(xo, yo - (int) zz + 1, 0, screen.getViewPort());


        BitmapHelper.drawPoint(xo, yo - (int) zz, color, screen.getViewPort());
    }
}
