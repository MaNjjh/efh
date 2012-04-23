package ru.znay.znay.he.model.builds.building;

import ru.znay.znay.he.gfx.helper.BitmapHelper;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 17.04.12
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 */
public class Warehouse extends Building {
    public Warehouse(int x, int y) {
        super(x, y, 20, 20);

        this.sprite = BitmapHelper.loadBitmapFromResources("/buildings/Warehouse.png");
    }
}
