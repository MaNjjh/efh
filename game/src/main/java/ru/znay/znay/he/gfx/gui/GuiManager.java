package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 26.03.12
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class GuiManager {
    private Map<String, Panel> panels = new HashMap<String, Panel>();

    private static GuiManager guiManager = null;

    public static boolean isOpenedMenu = false;

    private GuiManager() {

        //add(new StatusPanel(30,30,3,3,123, PaletteHelper.getColor(555, 555, 555, -1)));

        //panelList.add(new TextPanel("Мы долго ждали приветствуем тебя", 10, 10, PaletteHelper.getColor(5, 555, 555, 555)));
    }

    private boolean findSamePanel(Panel findPanel) {
        for (Panel panel : panels.values()) {
            if (panel.getX() == findPanel.getX() && panel.getY() == findPanel.getY()) {
                return true;
            }
        }
        return false;
    }

    public void add(Panel panel, String name) {
        if (!findSamePanel(panel)) {
            panels.put(name, panel);
        }
    }

    public Panel get(String name) {
        return panels.get(name);
    }

    public void tick() {
        Iterator<Panel> items = panels.values().iterator();
        Panel panel;

        for (; items.hasNext(); )
            if ((panel = items.next()).isClosed()) items.remove();
            else panel.tick();
    }

    public void render(Screen screen) {
        for (Panel panel : panels.values()) {
            panel.render(screen);
        }
    }

    public void remove(String name) {
        Panel p;
        if ((p = panels.get(name)) != null) p.close();
    }

    public static GuiManager getInstance() {
        if (guiManager == null)
            guiManager = new GuiManager();

        return guiManager;
    }
}
