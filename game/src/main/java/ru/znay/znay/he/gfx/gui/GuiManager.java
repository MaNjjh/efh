package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.Game;
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
    private Map<String, GuiPanel> panels = new HashMap<String, GuiPanel>();

    private static GuiManager guiManager = null;

    public static boolean isOpenedMenu = false;

    private GuiManager() {

    }

    @Deprecated
    private boolean findSamePanel(GuiPanel findPanel) {
        for (GuiPanel panel : panels.values()) {
            if (panel.getX() == findPanel.getX() && panel.getY() == findPanel.getY()) {
                System.out.println("duplicate panel found " + findPanel.toString());
                return false;
            }
        }
        return false;
    }

    private boolean findSamePanel(String name) {
        Set<String> keys = panels.keySet();
        for (String key : keys) {
            if (name.equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }

    public void add(GuiPanel panel, String name) {
        if (!findSamePanel(name)) {
            panels.put(name, panel);
        } else System.out.println("duplicate " + name);
    }

    public GuiPanel get(String name) {
        return panels.get(name);
    }

    public void tick() {
        /*Iterator<GuiPanel> items = panels.values().iterator();
        GuiPanel panel;

        for (; items.hasNext(); )
            if ((panel = items.next()).isClosed())
                items.remove();
            else panel.tick();*/
        List<String> strings = new LinkedList<String>();

        Set<String> keys = panels.keySet();
        for (String key : keys) {
            GuiPanel panel = panels.get(key);
            if (panel.closed)
                strings.add(key);
            else
                panel.tick();
        }

        for (String s : strings) {
            System.out.println("remove " + s);
            panels.remove(s);
        }
    }

    public void render(Screen screen) {
        for (GuiPanel panel : panels.values()) {
            panel.render(screen);
        }
    }

    public void remove(String name) {
        GuiPanel p;
        if ((p = panels.get(name)) != null) p.close();
    }

    public static GuiManager getInstance() {
        if (guiManager == null) {
            guiManager = new GuiManager();
        }

        return guiManager;
    }

    public void initDefaultGui(Game game) {
        panels.clear();

        add(new GuiStatusPanel(10, 220, 3, 3, 123, PaletteHelper.getColor(430, 430, 540, -1)), "money");
        add(new GuiStatusPanel(100, 220, 5, 3, 123, PaletteHelper.getColor(300, 555, 311, -1)), "health");
        add(new GuiSpeedIndicator(150, 220, PaletteHelper.getColor(531, 531, 531, -1), game.getScreen()), "speed");
        add(new GuiInventory(1, 5), "inventory");
        add(new GuiMenu(50, 100), "menu");
    }
}
