package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 02.04.12
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class PineTree extends Tree {

    public PineTree(int x, int y) {
        super(x, y, 8, 8);

        level.getSpriteCollector().resetWrappers();
        level.getSpriteCollector().addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 16 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 8 * Tile.HALF_SIZE, PaletteHelper.getColor(30, 20, 40, -1)));
        level.getSpriteCollector().addWrapper(new SpriteWrapper(21 * Tile.HALF_SIZE, 16 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 8 * Tile.HALF_SIZE, PaletteHelper.getColor(10, 20, 10, -1)));
        level.getSpriteCollector().addWrapper(new SpriteWrapper(25 * Tile.HALF_SIZE, 16 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 8 * Tile.HALF_SIZE, PaletteHelper.getColor(100, 210, 320, -1)));

        this.sprite = level.getSpriteCollector().mergedWrappers("tree_pine", 1, random.nextInt(2), 0x01000000);
    }
}
