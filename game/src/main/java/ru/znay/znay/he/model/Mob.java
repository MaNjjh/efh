package ru.znay.znay.he.model;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.particle.TextParticle;
import ru.znay.znay.he.model.weapon.Arrow;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
public class Mob extends Entity {

    private final static int DEFAULT_KNOCKBACK = 6;
    protected long tickTime = 0;
    protected int hurtTime = 0;
    protected int walkDist = 0;
    protected int dir = 0;
    protected int health = 10;
    protected int xKnockback, yKnockback;
    protected int viewRadius = 4;
    protected Mob target = null;

    @Override
    public void tick() {
        tickTime++;
        if (!this.isFly() && level.getTile(x >> 4, y >> 4) == Tile.lava) {
            hurt(this, 4, dir ^ 1);
        }

        if (health <= 0) {
            die();
        }

        if (hurtTime > 0) hurtTime--;
    }

    @Override
    public boolean move(int xa, int ya) {
        if (xKnockback < 0) {
            move2(-1, 0);
            xKnockback++;
        }
        if (xKnockback > 0) {
            move2(1, 0);
            xKnockback--;
        }
        if (yKnockback < 0) {
            move2(0, -1);
            yKnockback++;
        }
        if (yKnockback > 0) {
            move2(0, 1);
            yKnockback--;
        }
        if (hurtTime > 0) return true;
        if (xa != 0 || ya != 0) {
            walkDist++;
            if (xa < 0) dir = 2;
            if (xa > 0) dir = 3;
            if (ya < 0) dir = 1;
            if (ya > 0) dir = 0;
        }
        return super.move(xa, ya);
    }

    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Arrow) {
            Arrow arrow = (Arrow) entity;
            if (this.team != arrow.getOwnerTeam() && !(this instanceof Arrow)) {
                hurt(this, arrow.getDamage(), arrow.getDir());
                arrow.setRemoved(true);
            }
        }
    }

    @Override
    public void hurt(Mob mob, int damage, int attackDir) {
        doHurt(damage, attackDir);
    }

    protected void doHurt(int damage, int attackDir) {
        if (hurtTime > 0) return;

        int col = PaletteHelper.getColor(-1, 500, 500, 500);

        if (this.team == ETeam.PLAYER_TEAM) {
            col = PaletteHelper.getColor(-1, 505, 505, 505);
        }

        level.add(new TextParticle("" + damage, x, y, col));
        health -= damage;
        if (attackDir == 0) yKnockback = +DEFAULT_KNOCKBACK;
        if (attackDir == 1) yKnockback = -DEFAULT_KNOCKBACK;
        if (attackDir == 2) xKnockback = -DEFAULT_KNOCKBACK;
        if (attackDir == 3) xKnockback = +DEFAULT_KNOCKBACK;
        hurtTime = 10;
    }

    public boolean findStartPos(Level level) {
        int x = random.nextInt(level.getWidth());
        int y = random.nextInt(level.getHeight());
        int xx = x * Tile.SIZE + Tile.HALF_SIZE;
        int yy = y * Tile.SIZE + Tile.HALF_SIZE;

        if (level.getPlayer() != null) {
            int xd = level.getPlayer().getX() - xx;
            int yd = level.getPlayer().getY() - yy;
            if (xd * xd + yd * yd < 80 * 80) return false;
        }

        this.x = xx;
        this.y = yy;

        if (!this.isFly()) {
            int r = level.getMonsterDensity() * Tile.SIZE;
            if (level.getEntities(xx - r, yy - r, xx + r, yy + r, null).size() > 0) return false;
            if (!level.getTile(x, y).mayPass(level, x, y, this)) return false;
        }

        return true;
    }

    protected void die() {
        removed = true;
    }

    public long getTickTime() {
        return tickTime;
    }

    public int getHurtTime() {
        return hurtTime;
    }

    public int getWalkDist() {
        return walkDist;
    }

    public int getDir() {
        return dir;
    }

    public int getHealth() {
        return health;
    }

    public int getxKnockback() {
        return xKnockback;
    }

    public int getyKnockback() {
        return yKnockback;
    }
}
