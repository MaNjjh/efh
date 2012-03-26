package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.Level;

public class HoleTile extends Tile {

    public HoleTile(int id) {
        super(id);
        connectsToSand = true;
        connectsToWater = true;
        connectsToSwamp = true;
        connectsToLava = true;
    }

    public void render(Screen screen, Level level, int x, int y) {

        int transitionColor1 = PaletteHelper.getColor(3, holeMainColor, dirtMainColor - 111, dirtMainColor);
        int transitionColor2 = PaletteHelper.getColor(3, holeMainColor, sandMainColor - 110, sandMainColor);

        boolean u = !level.getTile(x, y - 1).connectsToLiquid();
        boolean d = !level.getTile(x, y + 1).connectsToLiquid();
        boolean l = !level.getTile(x - 1, y).connectsToLiquid();
        boolean r = !level.getTile(x + 1, y).connectsToLiquid();

        boolean su = u && level.getTile(x, y - 1).connectsToSand;
        boolean sd = d && level.getTile(x, y + 1).connectsToSand;
        boolean sl = l && level.getTile(x - 1, y).connectsToSand;
        boolean sr = r && level.getTile(x + 1, y).connectsToSand;

        if (!u && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, 0, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, holeColor, 0);
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, (l ? 14 : 15) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (su || sl) ? transitionColor2 : transitionColor1, 0);

        if (!u && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, 1 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, holeColor, 0);
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, (r ? 16 : 15) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (su || sr) ? transitionColor2 : transitionColor1, 0);

        if (!d && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, holeColor, 0);
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, (l ? 14 : 15) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (sd || sl) ? transitionColor2 : transitionColor1, 0);
        if (!d && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, holeColor, 0);
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, (r ? 16 : 15) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, (sd || sr) ? transitionColor2 : transitionColor1, 0);
    }

}
