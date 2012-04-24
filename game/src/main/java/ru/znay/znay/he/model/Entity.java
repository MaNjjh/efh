package ru.znay.znay.he.model;

import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class Entity {
    protected final Random random = new Random();
    protected int x, y;
    protected int xr = 4;
    protected int yr = 4;
    protected boolean removed = false;
    protected Level level;
    protected ETeam team = ETeam.ENEMY_TEAM;
    protected Bitmap sprite;
    protected boolean mouseMotion;

    public void init(Level level) {
        this.level = level;
    }

    public boolean intersects(int x0, int y0, int x1, int y1) {
        return !(x + xr < x0 || y + yr < y0 || x - xr > x1 || y - yr > y1);
    }

    public void render(Screen screen) {

    }

    public boolean move(int xa, int ya) {
        if (xa != 0 || ya != 0) {
            boolean stopped = true;
            if (xa != 0 && move2(xa, 0)) stopped = false;
            if (ya != 0 && move2(0, ya)) stopped = false;
            if (!stopped) {
                int xt = x >> 4;
                int yt = y >> 4;
                level.getTile(xt, yt).steppedOn(level, xt, yt, this);
            }

            return !stopped;
        }
        return true;
    }

    public boolean canSwim() {
        return false;
    }

    protected boolean isSwimming() {
        Tile tile = level.getTile(x >> 4, y >> 4);
        return tile.isLiquid();
    }

    public void touchItem(ItemEntity itemEntity) {
    }

    protected boolean move2(int xa, int ya) {
        if (xa != 0 && ya != 0) throw new IllegalArgumentException("Move2 can only move along one axis at a time!");

        int xto0 = ((x) - xr) >> 4;
        int yto0 = ((y) - yr) >> 4;
        int xto1 = ((x) + xr) >> 4;
        int yto1 = ((y) + yr) >> 4;

        int xt0 = ((x + xa) - xr) >> 4;
        int yt0 = ((y + ya) - yr) >> 4;
        int xt1 = ((x + xa) + xr) >> 4;
        int yt1 = ((y + ya) + yr) >> 4;

        for (int yt = yt0; yt <= yt1; yt++) {
            for (int xt = xt0; xt <= xt1; xt++) {
                if (xt >= xto0 && xt <= xto1 && yt >= yto0 && yt <= yto1) continue;
                level.getTile(xt, yt).bumpedInto(level, xt, yt, this);

                if (!this.canFly() && !level.getTile(xt, yt).mayPass(level, xt, yt, this)) {
                    return false;
                }

            }
        }

        List<Entity> isInside = level.getEntities(x + xa - xr, y + ya - yr, x + xa + xr, y + ya + yr, null);
        for (Entity entity : isInside) {
            if (entity == this) continue;

            entity.touchedBy(this);
        }

        if (!this.canFly()) {
            List<Entity> wasInside = level.getEntities(x - xr, y - yr, x + xr, y + yr, null);
            isInside.removeAll(wasInside);
            for (Entity entity : isInside) {
                if (entity == this) continue;

                if (entity.blocks(this)) {
                    return false;
                }
            }
        }

        x += xa;
        y += ya;

        return true;
    }

    public void mouseMotion() {
        mouseMotion = true;
    }

    public boolean canMouseSelected() {
        return false;
    }

    public void hurt(Mob mob, int damage, int attackDir) {
    }

    public void touchedBy(Entity entity) {

    }

    public boolean interact(Item item, Player player, int dir) {
        return false;
    }

    public boolean canFly() {
        return false;
    }

    public boolean blocks(Entity entity) {
        return false;
    }

    public void tick() {
        mouseMotion = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXr() {
        return xr;
    }

    public void setXr(int xr) {
        this.xr = xr;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public ETeam getTeam() {
        return team;
    }
}
