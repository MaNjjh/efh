package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.Level;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 */
public class GlassTile extends Tile {

    private Random random = new Random();

    public GlassTile(int id) {
        super(id);
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x * Tile.SIZE, y * Tile.SIZE, 0 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, grassColor, 0);
        screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE, 1 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, grassColor, 1);
        screen.render(x * Tile.SIZE, y * Tile.SIZE + Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, grassColor, 2);
        screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, grassColor, 3);
    }

    @Override
    public void tick() {

    }
}
