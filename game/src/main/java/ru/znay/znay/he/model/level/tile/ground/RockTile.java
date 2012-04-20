package ru.znay.znay.he.model.level.tile.ground;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 9:54
 * To change this template use File | Settings | File Templates.
 */
public class RockTile extends Tile {
    public RockTile(int id) {
        super(id);
    }

    public void render(Screen screen, Level level, int x, int y) {
        int col = PaletteHelper.getColor(444, 444, 333, 333);
        int transitionColor = PaletteHelper.getColor(111, 122, 555, grassMainColor);

        boolean u = level.getTile(x, y - 1) != this;
        boolean d = level.getTile(x, y + 1) != this;
        boolean l = level.getTile(x - 1, y) != this;
        boolean r = level.getTile(x + 1, y) != this;

        boolean ul = level.getTile(x - 1, y - 1) != this;
        boolean dl = level.getTile(x - 1, y + 1) != this;
        boolean ur = level.getTile(x + 1, y - 1) != this;
        boolean dr = level.getTile(x + 1, y + 1) != this;

        x <<= 4;
        y <<= 4;

        if (!u && !l) {
            if (!ul)
                screen.render(x, y, 0, 0, col, 0);
            else
                screen.render(x, y, 5 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, transitionColor, 3);
        } else
            screen.render(x, y, (l ? 9 : 8) * Tile.HALF_SIZE, (u ? 2 : 1) * Tile.HALF_SIZE, transitionColor, 3);


        if (!u && !r) {
            if (!ur)
                screen.render(x + Tile.HALF_SIZE, y, 0, 0, col, 0);
            else
                screen.render(x + Tile.HALF_SIZE, y, 6 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, transitionColor, 3);
        } else
            screen.render(x + Tile.HALF_SIZE, y, (r ? 7 : 8) * Tile.HALF_SIZE, (u ? 2 : 1) * Tile.HALF_SIZE, transitionColor, 3);


        if (!d && !l) {
            if (!dl)
                screen.render(x, y + Tile.HALF_SIZE, 0, 0, col, 0);
            else
                screen.render(x, y + Tile.HALF_SIZE, 5 * Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, transitionColor, 3);
        } else
            screen.render(x, y + Tile.HALF_SIZE, (l ? 9 : 8) * Tile.HALF_SIZE, (d ? 0 : 1) * Tile.HALF_SIZE, transitionColor, 3);
        if (!d && !r) {
            if (!dr)
                screen.render(x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, 0, 0, col, 0);
            else
                screen.render(x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, 6 * Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, transitionColor, 3);
        } else
            screen.render(x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, (r ? 7 : 8) * Tile.HALF_SIZE, (d ? 0 : 1) * Tile.HALF_SIZE, transitionColor, 3);


    }

    public boolean mayPass(Level level, int x, int y, Entity e) {
        return false;
    }
}
