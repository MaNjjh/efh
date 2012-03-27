package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.helper.SpriteManager;
import ru.znay.znay.he.gfx.helper.StaticModel;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 22.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class AppleTree extends Tree {

    private int apple = random.nextInt(2);

    public AppleTree(int x, int y, SpriteManager spr) {
        super(x,y,16,12);
        sprite = spr.getSprite((apple == 0) ? StaticModel.Apple : StaticModel.Apple2);
    }


    @Override
    public void touchedBy(Entity entity) {

    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

}
