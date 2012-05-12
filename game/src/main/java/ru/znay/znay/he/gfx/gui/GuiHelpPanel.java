package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.cfg.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 03.05.12
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */
public class GuiHelpPanel extends GuiTextPanel {

    public GuiHelpPanel(int x, int y) {
        super("", x, y);

        List<String> messages = new LinkedList<String>();
        messages.add("Передвижение:");
        messages.add("w - вверх");
        messages.add("s - вниз");
        messages.add("a - налево");
        messages.add("d - направо");
        messages.add("взаимодействие:");
        messages.add("пробел - использовать");
        messages.add("q - употребить яблоко");
        messages.add("e - употребить ягоду");
        messages.add("интерфейс:");
        messages.add("z - вкл\\выкл звук");
        messages.add("i - инвентарь");
        messages.add("esc - главное меню");
        messages.add("h - это окно");

        this.setFormatedText(messages, ((int) (Constants.SCREEN_WIDTH - Constants.SCREEN_HEIGHT * 0.40)) >> 3);
        visible = false;
    }

    @Override
    public void tick() {

        if (InputHandler.getInstance(null).helpWindow.clicked) {
            setVisible(!visible);
        }
    }
}
