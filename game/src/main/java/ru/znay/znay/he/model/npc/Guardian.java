package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.model.CharacterState;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.weapon.Weapon;
import ru.znay.znay.he.model.weapon.arrow.EArrowType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 14.04.12
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class Guardian extends NPC {
    public Guardian(int x, int y) {
        super(x, y);
        this.currentState = this.currentState.mergeStates(new CharacterState(10, 10, 10, 10, 10, 5));
    }

    public void tick() {
        super.tick();

        if (level.getPlayer() != null && randomWalkTime == 0) {
            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;
            /*if (xd * xd + yd * yd < 32 * 32) {
                xa = 0;
                ya = 0;
                if (xd < 0) xa = +1;
                if (xd > 0) xa = -1;
                if (yd < 0) ya = +1;
                if (yd > 0) ya = -1;
            } else */if (xd * xd + yd * yd > 80 * 80) {
                xa = 0;
                ya = 0;
                if (xd < 0) xa = -1;
                if (xd > 0) xa = +1;
                if (yd < 0) ya = -1;
                if (yd > 0) ya = +1;
            } else if (xd * xd + yd * yd < 30 * 30) {
                xa = 0;
                ya = 0;
            }
        }

        int speed = (tickTime % 4) == 0 ? 0 : 1;

        if (!move(xa * speed, ya * speed) || random.nextInt(100) == 0) {
            randomWalkTime = 30;
            xa = (random.nextInt(3) - 1);
            ya = (random.nextInt(3) - 1);
        }

        if (randomWalkTime > 0) {
            randomWalkTime--;
        }


        if (canAttack()) {
            if (target == null || target.isRemoved()) {
                int rr = 10 * Tile.HALF_SIZE;

                List<Entity> entities = level.getEntities(x - rr, y - rr, x + rr, y + rr, ETeam.ENEMY_TEAM);
                for (Entity entity : entities) {
                    if (entity instanceof Mob) {
                        target = (Mob) entity;
                        break;
                    }
                }
            } else {

                int xDiff = target.getX() - x;
                int yDiff = target.getY() - y;

                double m = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

                if (m > 150) target = null;

                double vx = xDiff / m;
                double vy = yDiff / m;

                if (tickTime % currentState.getAttackDelay() == 0) {
                    Weapon.fire(EArrowType.SIMPLE, this.team, x, y, vx, vy, currentState.getForce(), level);
                }
            }
        }

    }

}
