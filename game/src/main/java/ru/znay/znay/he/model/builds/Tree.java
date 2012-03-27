package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.SpriteManager;
import ru.znay.znay.he.gfx.helper.StaticModel;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 27.03.12
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class Tree extends Mob {
    public Tree(int x, int y, int xr, int yr) {
        this.x = x;
        this.y = y;
        this.xr = xr;
        this.yr = yr;
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
