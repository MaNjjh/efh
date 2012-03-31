package ru.znay.znay.he.model.level.tile.liquid;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

public class WaterTile extends Tile {
    public WaterTile(int id) {
        super(id);
        connectsToSand = true;
        connectsToWater = true;
        slowPeriod = 3;
        liquid = true;
    }

    public void render(Screen screen, Level level, int x, int y) {
        random.setSeed((tickCount + (x / 2 - y) * 4311) / 10 * 54687121l + x * 3271612l + y * 3412987161l);

        int transitionColor1 = PaletteHelper.getColor(3, waterMainColor, dirtMainColor - 111, dirtMainColor);
        int transitionColor2 = PaletteHelper.getColor(3, waterMainColor, sandMainColor - 110, sandMainColor);

        boolean u = !level.getTile(x, y - 1).connectsToWater;
        boolean d = !level.getTile(x, y + 1).connectsToWater;
        boolean l = !level.getTile(x - 1, y).connectsToWater;
        boolean r = !level.getTile(x + 1, y).connectsToWater;

        boolean su = u && level.getTile(x, y - 1).connectsToSand;
        boolean sd = d && level.getTile(x, y + 1).connectsToSand;
        boolean sl = l && level.getTile(x - 1, y).connectsToSand;
        boolean sr = r && level.getTile(x + 1, y).connectsToSand;

        if (!u && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, waterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, (l ? 14 : 15) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (su || sl) ? transitionColor2 : transitionColor1, 0);

        if (!u && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, waterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, (r ? 16 : 15) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (su || sr) ? transitionColor2 : transitionColor1, 0);

        if (!d && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, waterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, (l ? 14 : 15) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (sd || sl) ? transitionColor2 : transitionColor1, 0);
        if (!d && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, waterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, (r ? 16 : 15) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (sd || sr) ? transitionColor2 : transitionColor1, 0);
    }

    @Override
    public void tick(Level level, int xt, int yt) {

        int xn = xt;
        int yn = yt;

        if (random.nextBoolean())
            xn += random.nextInt(2) * 2 - 1;
        else
            yn += random.nextInt(2) * 2 - 1;

        if (level.getTile(xn, yn) == Tile.hole) {
            level.setTile(xn, yn, this, 0);
        }
    }

    @Override
    public boolean mayPass(Level level, int x, int y, Entity e) {
        return e.canSwim();
    }
}
