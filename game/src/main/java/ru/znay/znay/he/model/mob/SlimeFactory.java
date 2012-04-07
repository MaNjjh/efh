package ru.znay.znay.he.model.mob;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.resource.Coin;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class SlimeFactory extends Mob {

    private final static int MIN_SPAWN_TIME = 300;

    private int spawnTime;

    public SlimeFactory() {
        this.xr = 6;
        this.yr = 4;
        this.health = 50;
        this.spawnTime = MIN_SPAWN_TIME + random.nextInt(MIN_SPAWN_TIME);
    }

    @Override
    public void tick() {
        super.tick();

        if (hurtTime <= 0) spawnTime--;
        if (spawnTime <= 0) {
            spawnTime = MIN_SPAWN_TIME + random.nextInt(MIN_SPAWN_TIME);
            level.add(new Slime(x, y));
        }
    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    @Override
    public void die() {
        super.die();
        int count = random.nextInt(6) + 2;
        for (int i = 0; i < count; i++) {
            this.level.add(new Coin(x, y, random.nextInt(20) + 5));
        }
    }

    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            entity.hurt(this, 2, dir);
        }
        super.touchedBy(entity);
    }

    @Override
    public void render(Screen screen) {

        int xt = 0;
        int yt = 6;

        int xo = x - 8;
        int yo = y - 11;

        int col = PaletteHelper.getColor(-1, 10, 252, 555);
        if (hurtTime > 0)
            col = PaletteHelper.getColor(-1, 10, 511, 555);

        screen.render(2, xo, yo, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col, 0);
    }
}
