package ru.znay.znay.he.model.dialog;

import ru.znay.znay.he.gfx.model.Screen;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 26.03.12
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class GuiManager {
    private LinkedList<GuiPanel> _gui = new LinkedList<GuiPanel>();
    public boolean Visible = true;

    public GuiManager()
    {
         _gui.add(new GuiPanel(10,10,5,7));
    }

    public void add(GuiPanel GP) {
        _gui.add(GP);
    }

    public void render(Screen screen) {
        if (!Visible || _gui.isEmpty())
            return;
        for (GuiPanel GP : _gui)
            GP.render(screen);
    }
}
