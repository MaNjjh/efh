package ru.znay.znay.he.gfx.gui.prefab;

import ru.znay.znay.he.gfx.gui.Menu;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 08.04.12
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class Flora extends Parent{
    private static Flora flora = null;

    public static Flora getMainMenu() {
        if (flora == null)
            flora = new Flora();

        return flora;
    }

    @Override
    public void result(int result) {
        switch (result) {
            default:
                return;
        }
    }
}
