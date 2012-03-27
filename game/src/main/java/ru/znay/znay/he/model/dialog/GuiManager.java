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
    private List<GuiPanel> gui = new LinkedList<GuiPanel>();
    public boolean visible = true;

    public GuiManager() {
        gui.add(new GuiTextPanel("приветик как дела? может сходим куда-нибудь? вообще я сегодня свободен",10, 10));
    }

    public void add(GuiPanel GP) {
        gui.add(GP);
    }

    public void render(Screen screen) {
        if (!visible || gui.isEmpty()) {
            return;
        }
        for (GuiPanel GP : gui) {
            GP.render(screen);
        }
    }
}
