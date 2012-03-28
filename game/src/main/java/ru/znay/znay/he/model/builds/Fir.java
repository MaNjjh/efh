package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.SpriteManager;
import ru.znay.znay.he.gfx.helper.StaticModel;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 28.03.12
 * Time: 10:14
 * To change this template use File | Settings | File Templates.
 */
public class Fir extends Tree {
    public Fir(int x, int y, SpriteManager spr) {
        super(x, y, 10, 10);
        sprite = spr.getSprite(StaticModel.FIR);
    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    @Override
    public void render(Screen screen) {

        int xt = (x - xr * 2 - xr - 1) - screen.getXOffset();
        int yt = (y - (yr << 2) * 2 - (yr << 2) + 4) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);

    }
}
