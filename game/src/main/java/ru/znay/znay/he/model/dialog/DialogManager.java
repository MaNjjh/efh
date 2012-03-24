package ru.znay.znay.he.model.dialog;

import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 8:53
 * To change this template use File | Settings | File Templates.
 */
public class DialogManager {
    private List<Text> textList = new ArrayList<Text>();

    public void addTypedText(String msg, int x, int y, int colors) {
        this.textList.add(new TextTyped(msg, x, y, colors, 100));
    }

    public boolean hasText(String msg) {
        for (Text text : textList) {
            if (msg.equals(text.getOriginalMessage())) {
                return true;
            }
        }
        return false;
    }

    public void tick() {
        for (int i = 0; i < textList.size(); i++) {
            Text text = textList.get(i);
            if (text.isRemoved()) {
                textList.remove(i--);
            } else {
                text.tick();
            }
        }

    }

    public void render(Screen screen) {
        for (Text text : textList) {
            Font.renderPanel(text.getMessage(), screen, text.getX(), text.getY(), text.getColors());
        }
    }
}
