package ru.znay.znay.he.gfx;

import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 12.05.12
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class AboutScreen {
    private static AboutScreen aboutScreen = null;

    public static AboutScreen getInstance() {
        if (aboutScreen == null) {
            aboutScreen = new AboutScreen();
        }
        return aboutScreen;
    }

    private boolean show = false;
    private List<String> strings = new LinkedList<String>();
    private int textY = 240;
    private long tick;

    public AboutScreen() {
        strings.add("над игрой работали;");
        strings.add("программист");
        strings.add("Александр Сергеевич");
        strings.add("сайт: www.znay-znay.ru");
        strings.add("email: mr.gmaker@yandex.ru");
        strings.add(" ");
        strings.add("программист");
        strings.add("Денис Сергеевич");
        strings.add("email: denuss@gmail.com");
        strings.add(" ");

        strings.add("отдельное спасибо всем тестерам");
        strings.add(" ");

        strings.add("исходный код и все материалы по игре");
        strings.add("можно найти на:");
        strings.add("http://code.google.com/p/efh/");
    }

    public void tick() {
        if (InputHandler.getInstance(null).mainMenu.clicked) {
            show = false;
        }

        if (tick < System.currentTimeMillis()) {
            tick = System.currentTimeMillis() + 50;
            textY--;

            if (textY < 0) {
                show = false;
            }
        }
    }

    public void render(Screen screen) {
        BitmapHelper.fill(screen.getViewPort(), 0x000000);

        for (int i = 0; i < strings.size(); i++) {
            Font.draw(strings.get(i), screen, 0, textY + (i * Tile.HALF_SIZE), GuiManager.FONT_COLOR);
        }
    }

    public boolean isShow() {
        return show;
    }

    public void show() {
        show = true;
        textY = 240;
    }
}
