package ru.znay.znay.he.model.mob.boss;


import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.weapon.Spark;

public class AirWizard extends Boss {
    private int xa, ya;
    private int randomWalkTime = 0;
    private int attackDelay = 0;
    private int attackTime = 0;
    private int attackType = 0;

    public AirWizard(int x, int y) {
        this.x = x;
        this.y = y;
        health = 200;
        this.team = ETeam.ENEMY_TEAM;
    }

    public void tick() {
        super.tick();

        if (attackDelay > 0) {
            dir = (attackDelay - 45) / 4 % 4;
            dir = (dir * 2 % 4) + (dir / 2);
            if (attackDelay < 45) {
                dir = 0;
            }
            attackDelay--;
            if (attackDelay == 0) {
                attackType = 0;
                if (health < maxHealth / 2) attackType = 1;
                if (health < maxHealth / 10) attackType = 2;
                attackTime = 60 * 2;
            }
            return;
        }

        if (attackTime > 0) {
            attackTime--;
            double dir = attackTime * 0.25 * (attackTime % 2 * 2 - 1);
            double speed = (0.7) + attackType * 0.2;
            level.add(new Spark(this, Math.cos(dir) * speed, Math.sin(dir) * speed));
            return;
        }

        if (level.getPlayer() != null && randomWalkTime == 0) {
            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;
            if (xd * xd + yd * yd < 32 * 32) {
                xa = 0;
                ya = 0;
                if (xd < 0) xa = +1;
                if (xd > 0) xa = -1;
                if (yd < 0) ya = +1;
                if (yd > 0) ya = -1;
            } else if (xd * xd + yd * yd > 80 * 80) {
                xa = 0;
                ya = 0;
                if (xd < 0) xa = -1;
                if (xd > 0) xa = +1;
                if (yd < 0) ya = -1;
                if (yd > 0) ya = +1;
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
            if (level.getPlayer() != null && randomWalkTime == 0) {
                int xd = level.getPlayer().getX() - x;
                int yd = level.getPlayer().getY() - y;
                if (random.nextInt(4) == 0 && xd * xd + yd * yd < 50 * 50) {
                    if (attackDelay == 0 && attackTime == 0) {
                        attackDelay = 60 * 2;
                    }
                }
            }
        }
    }

    protected void doHurt(int damage, int attackDir) {
        super.doHurt(damage, attackDir);
        if (attackDelay == 0 && attackTime == 0) {
            attackDelay = 60 * 2;
        }
    }

    public void render(Screen screen) {
        int xt = 0;
        int yt = 14;

        int flip1 = (walkDist >> 3) & 1;
        int flip2 = (walkDist >> 3) & 1;

        if (dir == 1) {
            xt += 2;
        }
        if (dir > 1) {

            flip1 = 0;
            flip2 = ((walkDist >> 4) & 1);
            if (dir == 2) {
                flip1 = 1;
            }
            xt += 4 + ((walkDist >> 3) & 1) * 2;
        }

        int xo = x - 8;
        int yo = y - 11;

        int col1 = PaletteHelper.getColor(-1, 100, 550 - (level.getNumber() % 5) * 100, 555);
        int col2 = PaletteHelper.getColor(-1, 100, 550 - (level.getNumber() % 5) * 100, 532);
        if (health < maxHealth / 10) {
            if (tickTime / 3 % 2 == 0) {
                col1 = PaletteHelper.getColor(-1, 500, 100, 555);
                col2 = PaletteHelper.getColor(-1, 500, 100, 532);
            }
        } else if (health < maxHealth / 2) {
            if (tickTime / 5 % 4 == 0) {
                col1 = PaletteHelper.getColor(-1, 500, 100, 555);
                col2 = PaletteHelper.getColor(-1, 500, 100, 532);
            }
        }
        if (hurtTime > 0) {
            col1 = PaletteHelper.getColor(-1, 555, 555, 555);
            col2 = PaletteHelper.getColor(-1, 555, 555, 555);
        }

        screen.render(xo + Tile.HALF_SIZE * flip1, yo + 0, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip1, yo + 0, (xt + 1) * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);

        screen.render(xo + Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, xt * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
        screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, (xt + 1) * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);

    }


    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            entity.hurt(this, 3 * (level.getNumber() + 1), dir);
        }
        super.touchedBy(entity);
    }
}