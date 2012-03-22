package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;

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
        connectsToGrass = true;
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(2, x * SIZE, y * SIZE, 4 * HALF_SIZE, 0, HALF_SIZE, HALF_SIZE, PaletteHelper.getColor(333, 222, 0, 333), 0);
    }

    @Override
    public boolean mayPass(Level level, int x, int y, Entity e) {
        return false;
    }

}
