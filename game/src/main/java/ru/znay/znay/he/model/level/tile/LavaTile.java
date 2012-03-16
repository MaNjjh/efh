package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 11.03.12
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class LavaTile extends Tile {

    private Random random = new Random();

    public LavaTile(int id) {
        super(id);
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        random.setSeed((tickCount + (x / 2 - y) * 4311) / 10 * 54687121l + x * 3271612l + y * 3412987161l);
        screen.render(x * Tile.SIZE, y * Tile.SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, lavaColor, random.nextInt(4));
        screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, lavaColor, random.nextInt(4));
        screen.render(x * Tile.SIZE, y * Tile.SIZE + Tile.HALF_SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, lavaColor, random.nextInt(4));
        screen.render(x * Tile.SIZE + Tile.HALF_SIZE, y * Tile.SIZE + Tile.HALF_SIZE, random.nextInt(4) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, lavaColor, random.nextInt(4));
    }


    @Override
    public void tick() {

    }

    @Override
    public void steppedOn(Level level, int xt, int yt, Entity entity) {

    }
}
