package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.Level;

public class SandTile extends Tile {
    public SandTile(int id) {
        super(id);
        connectsToSand = true;
        slowPeriod = 6;
    }


    public void render(Screen screen, Level level, int x, int y) {

        int transitionColor = PaletteHelper.getColor(sandMainColor - 110, sandMainColor, sandMainColor - 110, dirtMainColor);

        boolean u = !level.getTile(x, y - 1).connectsToSand;
        boolean d = !level.getTile(x, y + 1).connectsToSand;
        boolean l = !level.getTile(x - 1, y).connectsToSand;
        boolean r = !level.getTile(x + 1, y).connectsToSand;

        if (!u && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, 3 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, sandColor, 0);
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + 0, (l ? 11 : 12) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, transitionColor, 0);

        if (!u && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, 0, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, sandColor, 0);
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + 0, (r ? 13 : 12) * Tile.HALF_SIZE, (u ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, transitionColor, 0);

        if (!d && !l) {
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, sandColor, 0);
        } else
            screen.render(x * Tile.SIZE + 0, y * Tile.SIZE + Tile.HALF_SIZE, (l ? 11 : 12) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, transitionColor, 0);

        if (!d && !r) {
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, sandColor, 0);
        } else
            screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, (r ? 13 : 12) * Tile.HALF_SIZE, (d ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, transitionColor, 0);
    }

}
