package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 12.04.12
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class Shrubbery extends Tree{
    
    private boolean berry = random.nextBoolean();
    private boolean type = random.nextBoolean();
    
    public Shrubbery(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 4, 4);
        spriteCollector.resetWrappers();
        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(((type)?21:25) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(100, 210, 320, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(((type)?23:27) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor((berry?100:-1), 210, 320, -1)));

        this.sprite = spriteCollector.mergedWrappers("shrubbery_"+(type?"0":"1") + (berry ? "_berry" : ""), 2, random.nextInt(2), true);
    }
}
