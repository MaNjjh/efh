package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 22.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class AppleTree extends Tree {

    private int apple = random.nextInt(2);

    public AppleTree(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 16, 12);
        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(20, 40, 30, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(21 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(10, 10, 20, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(25 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(100, 210, 320, -1)));
        if (apple != 0) {
            spriteCollector.addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, -1, 510, -1)));
        }
        this.sprite = spriteCollector.mergedWrappers("tree" + (apple == 0 ? "_apple" : ""), 2, random.nextInt(2), true);
    }

    @Override
    public void render(Screen screen) {

        int xt = (x - xr * 2) - screen.getXOffset();
        int yt = (y - yr * 2 - 24) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);

    }

}
