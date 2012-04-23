package ru.znay.znay.he.model.item.furniture;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 07.04.12
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class Bucket extends Furniture {
    private long tickTime;

    private boolean isFull = false;

    public Bucket(int x, int y) {
        super("Ведро");
        this.x = x;
        this.y = y;

    }

    @Override
    public void render(Screen screen) {
        tickTime++;
        int xo = x - 8;
        int yo = y - 8;

        if (level.getTile(x >> 4, y >> 4) == Tile.water) {
            isFull = true;
        }
        int col = PaletteHelper.getColor(-1, 111, 420, 111);


        if (isFull) {
            if (tickTime / 300 % 2 == 0) {
                col = PaletteHelper.getColor(-1, 222, 222, 004);
            } else {
                col = PaletteHelper.getColor(-1, 333, 222, 004);
            }
        }

        int col2 = PaletteHelper.getColor(-1, 333, 222, 111);


        if (mouseMotion) {
            col = PaletteHelper.getColor(-1, 111, 555, 111);
            col2 = PaletteHelper.getColor(-1, 333, 555, 111);
        }

        screen.render(xo, yo, 0 * Tile.HALF_SIZE, 9 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
        screen.render(xo + Tile.HALF_SIZE, yo, 1 * Tile.HALF_SIZE, 9 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);

        screen.render(xo, yo + Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, (9 + 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col2, 0);
        screen.render(xo + Tile.HALF_SIZE, yo + Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, (9 + 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col2, 0);
    }


    @Override
    public boolean canSwim() {
        return false;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }
}
