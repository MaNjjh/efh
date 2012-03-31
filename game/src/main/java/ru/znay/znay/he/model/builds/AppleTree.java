package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.ESprites;
import ru.znay.znay.he.gfx.helper.SpriteManager;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 22.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class AppleTree extends Tree {

    private int apple = random.nextInt(2);

    public AppleTree(int x, int y, SpriteManager spr) {
        super(x, y, 16, 12);
        sprite = spr.getSprite((apple == 0) ? ESprites.TREE : ESprites.APPLE);
    }

    @Override
    public void touchedBy(Entity entity) {

    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    @Override
    public void render(Screen screen) {

        int xt = (x - xr * 2) - screen.getXOffset();
        int yt = (y - yr * 2 - 24) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);

    }

}
