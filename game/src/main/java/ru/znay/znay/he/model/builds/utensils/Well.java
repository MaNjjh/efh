package ru.znay.znay.he.model.builds.utensils;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 10.04.12
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class Well extends Utensils {
    public Well(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 4, 1);

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(0, Tile.HALF_SIZE, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(111, 222, 333, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(2 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(2, 3, 4, -1)));

        this.sprite = spriteCollector.mergedWrappers("well", 1, 0, false);
    }
}
