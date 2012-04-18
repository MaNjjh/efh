package ru.znay.znay.he.model.builds.building;

import ru.znay.znay.he.gfx.helper.BitmapHelper;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 17.04.12
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public class Bakery extends Building {
    public Bakery(int x,int y)
    {
        super(x,y,170,60);

        this.sprite = BitmapHelper.loadBitmapFromResources("/buildings/Bakery.png");
    }
}
