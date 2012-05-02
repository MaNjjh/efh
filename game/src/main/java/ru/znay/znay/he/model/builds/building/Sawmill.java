package ru.znay.znay.he.model.builds.building;

import ru.znay.znay.he.gfx.helper.BitmapHelper;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 17.04.12
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class Sawmill extends Building {
    public Sawmill(int x, int y) {
        super(x, y, 0, 0);

        this.sprite = BitmapHelper.loadBitmapFromResources("/buildings/Sawmill.png");
    }
}