package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 08.04.12
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class TreeStump extends Tree {

    private int type = random.nextInt(2);

    public TreeStump(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 4, 1);

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(((type == 0)?21:25) * Tile.HALF_SIZE, Tile.HALF_SIZE<<1, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(100, 210, 320, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(((type == 0)?23:27) * Tile.HALF_SIZE, Tile.HALF_SIZE<<1, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(100, 210, 320, -1)));

        this.sprite = spriteCollector.mergedWrappers("tree_stump", 1, random.nextInt(2), true);
    }
}
