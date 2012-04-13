package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 13.04.12
 * Time: 8:15
 * To change this template use File | Settings | File Templates.
 */
public class Apple extends Resource {

    private int size = 0;

    public Apple(int x, int y) {
        super(x, y);

        this.size = random.nextInt(5) == 0 ? 1 : 0;

        this.time = 600;
        this.xr = 3;
        this.yr = 3;
        this.speed = random.nextInt(2) + 1;
    }

    public void render(Screen screen) {

        int col = PaletteHelper.getColor(-1, 0, 310, 555);

        if (time < 200) {
            if (System.currentTimeMillis() / 250 % 2 == 0) {
                col = PaletteHelper.getColor(-1, -1, -1, -1);
            }
        }
        screen.render(x - 4, y - 5 - (int) zz, size * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
    }
}
