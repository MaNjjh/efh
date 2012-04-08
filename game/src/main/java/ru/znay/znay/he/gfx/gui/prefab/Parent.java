package ru.znay.znay.he.gfx.gui.prefab;

import ru.znay.znay.he.gfx.gui.Menu;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 08.04.12
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class Parent implements Menu.MenuCallback {
    protected List<String> strings = new LinkedList<String>();

    protected Parent() {

    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public abstract void result(int result) ;
}
