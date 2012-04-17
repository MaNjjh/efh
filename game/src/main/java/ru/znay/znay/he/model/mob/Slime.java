package ru.znay.znay.he.model.mob;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.item.resource.ItemEntity;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Slime extends Mob {
    private int xa, ya;
    private int jumpTime = 0;

    public Slime(int x, int y) {
        this.x = x;
        this.y = y;
        this.viewRadius = 6;
    }

    @Override
    public void tick() {
        super.tick();
        int speed = 1;
        if (!move(xa * speed, ya * speed) || random.nextInt(40) == 0) {
            if (jumpTime <= -10) {
                xa = (random.nextInt(3) - 1);
                ya = (random.nextInt(3) - 1);

                int vr = viewRadius * Tile.SIZE;

                if (target == null) {
                    List<Entity> playerTeam = level.getEntities(x - vr, y - vr, x + vr, y + vr, ETeam.PLAYER_TEAM);
                    for (Entity entity : playerTeam) {
                        if (entity instanceof Mob) {
                            target = (Mob) entity;
                            break;
                        }
                    }
                }

                if (target != null) {
                    int xd = target.getX() - x;
                    int yd = target.getY() - y;

                    if (xd < 0) xa = -1;
                    if (xd > 0) xa = +1;
                    if (yd < 0) ya = -1;
                    if (yd > 0) ya = +1;

                    if (xd * xd + yd * yd > vr * vr || target.isRemoved()) {
                        target = null;
                    }
                }

                if (xa != 0 || ya != 0) jumpTime = 10;
            }
        }

        jumpTime--;
        if (jumpTime == 0) {
            xa = ya = 0;
        }
    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }


    @Override
    public void touchedBy(Entity entity) {
        if (entity.getTeam() != this.team) {
            entity.hurt(this, 1, dir);
        }
        super.touchedBy(entity);
    }

    @Override
    public void die() {
        super.die();
        int count = random.nextInt(8) + 4;
        for (int i = 0; i < count; i++) {
            this.level.add(new ItemEntity(new ResourceItem(Resource.coin), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
        }
        if (random.nextInt(20) == 0) {
            this.level.add(new ItemEntity(new ResourceItem(Resource.life), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));

        }
    }

    @Override
    public void render(Screen screen) {

        int xt = 0;
        int yt = 6;

        int xo = x - 4;
        int yo = y - 4;

        if (jumpTime > 0) {
            xt += 1;
            yo -= 4;
        }
        int col = PaletteHelper.getColor(-1, 10, 252, 555);
        if (hurtTime > 0)
            col = PaletteHelper.getColor(-1, 10, 511, 555);

        screen.render(xo, yo, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
    }
}
