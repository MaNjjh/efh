package ru.znay.znay.he.model.level.tile.liquid;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 26.03.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class DeepWaterTile extends Tile {

    public DeepWaterTile(int id) {
        super(id);
        connectsToWater = true;
    }

    public void render(Screen screen, Level level, int x, int y) {
        random.setSeed((tickCount + (x / 2 - y) * 4311) / 10 * 54687121l + x * 3271612l + y * 3412987161l);

        int transitionColor1 = PaletteHelper.getColor(2, deepWaterMainColor, deepWaterMainColor + 3, deepWaterMainColor + 3);
        int transitionColor2 = PaletteHelper.getColor(2, deepWaterMainColor, deepWaterMainColor + 3, deepWaterMainColor + 3);

        boolean u = level.getTile(x, y - 1).getId() != id;
        boolean d = level.getTile(x, y + 1).getId() != id;
        boolean l = level.getTile(x - 1, y).getId() != id;
        boolean r = level.getTile(x + 1, y).getId() != id;

        boolean su = u && level.getTile(x, y - 1).connectsToWater;
        boolean sd = d && level.getTile(x, y + 1).connectsToWater;
        boolean sl = l && level.getTile(x - 1, y).connectsToWater;
        boolean sr = r && level.getTile(x + 1, y).connectsToWater;

        if (!u && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, deepWaterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, (l ? 14 : 15) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (su || sl) ? transitionColor2 : transitionColor1, 0);

        if (!u && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, deepWaterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, (r ? 16 : 15) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (su || sr) ? transitionColor2 : transitionColor1, 0);

        if (!d && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, deepWaterColor, random.nextInt(4));
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, (l ? 14 : 15) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (sd || sl) ? transitionColor2 : transitionColor1, 0);
        if (!d && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, deepWaterColor, random.nextInt(4));
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
