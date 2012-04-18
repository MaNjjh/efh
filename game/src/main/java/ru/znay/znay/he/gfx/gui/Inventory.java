package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 18.04.12
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class Inventory extends Panel{
    public Inventory(int x,int y)
    {
        this.x = x;
        this.y = y;

        this.image = BitmapHelper.loadBitmapFromResources("/testinventory.png");
        visible = true;
    }

    @Override
    protected void paintF(Screen screen) {

    }

    @Override
    public void render(Screen screen) {
        BitmapHelper.copy(this.image, 0, 0, x, y, this.image.getWidth(), this.image.getHeight(), screen.getViewPort(), 0xFFFF00FF);
    }
}
