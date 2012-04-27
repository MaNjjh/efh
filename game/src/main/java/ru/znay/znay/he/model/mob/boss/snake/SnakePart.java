package ru.znay.znay.he.model.mob.boss.snake;

import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;

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
public class SnakePart extends Mob {
    protected double xa = 0.0;
    protected double ya = 0.0;
    protected int time;

    public void setMovement(double xa, double ya, double rotate) {
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
        List<Entity> entities = level.getEntities(x, y, x, y, ETeam.PLAYER_TEAM);
        for (Entity entity : entities) {
            System.out.println(1);
            entity.hurt(this, 2, dir);
        }
        if (health < 100) {
            this.level.getFireParticles().createExplosion(x, y, 0.5, -1.0, (100 - health) / 30);
        }
    }

    public void touchedBy(Entity entity) {
        if (entity.getTeam() != this.team) {
            entity.hurt(this, 2, dir);
        }
        super.touchedBy(entity);
    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }
}
