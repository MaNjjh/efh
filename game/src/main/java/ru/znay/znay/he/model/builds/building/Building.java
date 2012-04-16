package ru.znay.znay.he.model.builds.building;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Mob;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 16.04.12
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class Building extends Mob {

    public Building (int x,int y,int xr,int yr)
    {
        this.x = x;
        this.y = y;
        this.xr = xr;
        this.yr = yr;
    }

    @Override
    public void render(Screen screen) {

        int xt = x - screen.getXOffset();
        int yt = y - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFFF0F0F0);
    }
}
