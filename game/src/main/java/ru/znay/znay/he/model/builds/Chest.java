package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 10.04.12
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class Chest extends Mob {
    public Chest(int x, int y, SpriteCollector spriteCollector) {
        this.x = x;
        this.y = y;
        this.xr = 4;
        this.yr = 1;

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(2 * Tile.HALF_SIZE, 5 * Tile.HALF_SIZE, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(30, 20, 40, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(4 * Tile.HALF_SIZE, 5 * Tile.HALF_SIZE, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(10, 20, 10, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(6 * Tile.HALF_SIZE, 5 * Tile.HALF_SIZE, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(100, 210, 320, -1)));

        this.sprite = spriteCollector.mergedWrappers("chest", 1, random.nextInt(2), true);
    }

    @Override
    public void render(Screen screen) {
        int xt = (x - xr * 2) - screen.getXOffset();
        int yt = (y - (yr << 1) * 3 - yr) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);
    }
}
