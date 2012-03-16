package ru.znay.znay.he.model.level.tile;

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
        screen.render(x * SIZE, y * SIZE, 0 * HALF_SIZE, 0, HALF_SIZE, HALF_SIZE, grassColor, 0);
        screen.render(x * SIZE + HALF_SIZE, y * SIZE, 1 * HALF_SIZE, 0, HALF_SIZE, HALF_SIZE, grassColor, 1);
        screen.render(x * SIZE, y * SIZE + HALF_SIZE, 2 * HALF_SIZE, 0, HALF_SIZE, HALF_SIZE, grassColor, 2);
        screen.render(x * SIZE + HALF_SIZE, y * SIZE + HALF_SIZE, 3 * HALF_SIZE, 0, HALF_SIZE, HALF_SIZE, grassColor, 3);
    }

    @Override
    public void tick() {

    }
}
