package ru.znay.znay.he.model.weapon;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.mob.AirWizard;

import java.util.List;

public class Spark extends Entity {
    private int lifeTime;
    public double xa, ya;
    public double xx, yy;
    private int time;
    private Mob owner;

    public Spark(Mob owner, double xa, double ya) {
        this.owner = owner;
        xx = this.x = owner.getX();
        yy = this.y = owner.getY();
        xr = 0;
        yr = 0;

        this.xa = xa;
        this.ya = ya;

        lifeTime = 60 * 10 + random.nextInt(30);
    }

    public void tick() {
        time++;
        if (time >= lifeTime) {
            removed = true;
            return;
        }
        xx += xa;
        yy += ya;
        x = (int) xx;
        y = (int) yy;
        List<Entity> toHit = level.getEntities(x, y, x, y, null);
        for (Entity e : toHit) {
            if (e instanceof Mob && !(e instanceof AirWizard)) {
                e.hurt(owner, 1, ((Mob) e).getDir() ^ 1);
            }
        }
    }

    public void render(Screen screen) {
        if (time >= lifeTime - 6 * 20) {
            if (time / 6 % 2 == 0) return;
        }

        int xt = 8;
        int yt = 13;

        screen.render(x - 4, y - 4 - 2, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 555, 555, 555), random.nextInt(4));
        screen.render(x - 4, y - 4 + 2, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 000, 000, 000), random.nextInt(4));
    }
}
