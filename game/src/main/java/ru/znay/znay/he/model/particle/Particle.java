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

    public void initParticle(int x, int y, double xa, double ya) {
        this.x = x;
        this.y = y;
        this.xa = xa;
        this.ya = ya;
    }

    public Particle() {
    }

    public void tick() {
        time--;
        if (time < 0) {
            removed = true;
        }
    }

    public void render(Screen screen) {
        int xo = x - screen.getXOffset();
        int yo = y - screen.getYOffset();

        if (zz > 0)
            BitmapHelper.drawPoint(xo, yo, 0, screen.getViewPort());

        //if (time > 100)
            BitmapHelper.drawPoint(xo, yo - (int) zz + 1, 0, screen.getViewPort());


        BitmapHelper.drawPoint(xo, yo - (int) zz, color, screen.getViewPort());
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void addXaYa(double xa, double ya) {
        this.xa += xa;
        this.ya += ya;
    }

    public double getXa() {
        return xa;
    }

    public void setXa(double xa) {
        this.xa = xa;
    }

    public double getYa() {
        return ya;
    }

    public void setYa(double ya) {
        this.ya = ya;
    }

    public double getZa() {
        return za;
    }

    public void setZa(double za) {
        this.za = za;
    }

    public double getXx() {
        return xx;
    }

    public void setXx(double xx) {
        this.xx = xx;
    }

    public double getYy() {
        return yy;
    }

    public void setYy(double yy) {
        this.yy = yy;
    }

    public double getZz() {
        return zz;
    }

    public void setZz(double zz) {
        this.zz = zz;
    }
}
