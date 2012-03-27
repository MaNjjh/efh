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
public class AppleTree extends Mob {

    private int flip = 0;
    private int apple = random.nextInt(2);

    public AppleTree(int x, int y, SpriteManager spr) {
        this.x = x;

        this.y = y;
        this.xr = 16;
        this.yr = 12;
        this.flip = random.nextInt(2);
        sprite = spr.getSprite((apple == 0) ? StaticModel.Apple : StaticModel.Apple2);
    }


    public AppleTree() {
        this.xr = 16;
        this.yr = 12;
        this.flip = random.nextInt(2);
    }

    @Override
    public void touchedBy(Entity entity) {

    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    public void render(Screen screen) {

        int xt = (x - xr * 2) - screen.getXOffset();
        int yt = (y - yr * 2 - 24) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);

    }
}
