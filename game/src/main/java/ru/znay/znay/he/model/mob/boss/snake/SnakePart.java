package ru.znay.znay.he.model.mob.boss.snake;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.mob.boss.Boss;

import java.util.List;

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
 * 10:01: Time
 */
public class SnakePart extends Boss {
    protected double xa = 0.0;
    protected double ya = 0.0;
    protected int time;

    public void setMovement(double xa, double ya, double rotate) {
    }

    public boolean canRegenerate() {
        return true;
    }

    public void tick() {
        super.tick();
        if (xa > 0.00001) {
            x = (int) xa;
        }
        if (ya > 0.00001) {
            y = (int) ya;
        }
        time++;
        List<Entity> entities = level.getEntities(x, y, x, y, null);
        for (Entity entity : entities) {
            doHurt(entity);
        }
        if (health < maxHealth) {
            this.level.getFireParticles().createExplosion(x, y, 0.5, -1.0, (maxHealth - health) / 30);
        }

        if (health < maxHealth / 10) {
            this.level.setTile(x >> 4, y >> 4, Tile.lava, 0);
        }

    }

    private void doHurt(Entity entity) {
        if (entity instanceof SnakePart) return;
        entity.hurt(this, 2 * (level.getNumber() + 1), dir);
    }

    public void touchedBy(Entity entity) {
        doHurt(entity);
        super.touchedBy(entity);
    }

    @Override
    public void hurt(Mob mob, int damage, int attackDir) {
        if (health < 50) {
            time = 0;
        }
        super.hurt(mob, damage, attackDir);
    }
}
