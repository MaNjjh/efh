package ru.znay.znay.he.gfx.gui.prefab;

import ru.znay.znay.he.gfx.gui.Menu;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 08.04.12
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public class MainMenu extends Parent {
    private static MainMenu mainMenu = null;

    private MainMenu() {
        super();
        strings.add("Управление");
        strings.add("Отмена");
    }

    public static MainMenu getMainMenu() {
        if (mainMenu == null)
            mainMenu = new MainMenu();

        return mainMenu;
    }

    @Override
    public void result(int result) {
        switch (result) {
            default:
                return;
        }
    }
}
