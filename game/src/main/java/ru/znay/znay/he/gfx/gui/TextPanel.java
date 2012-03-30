package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 27.03.12
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public class TextPanel extends Panel {

    protected int colorText;
    protected String originalMessage;

    List<String> formatedText = new LinkedList<String>();

    public TextPanel(String message, int posX, int posY, int colorText, int colorPanel) {
        super(posX, posY, colorPanel);
        init(message, colorText);
    }

    public TextPanel(String message, int posX, int posY, int colorText) {
        super(posX, posY);
        init(message, colorText);
    }

    public TextPanel(String message, int posX, int posY) {
        super(posX, posY);
        init(message, PaletteHelper.getColor(5, 555, 555, 555));
    }


    private void init(String message, int colorText) {
        this.originalMessage = message;
        this.colorText = colorText;
        this.changed = true;
        this.visible = true;

        formatString(message);
    }

    protected void formatString(String message) {
        if (this.formatedText.size() > 0)
            this.formatedText.clear();

        if (message.isEmpty())
            message = "TEXT";

        final int maxLen = (Constants.SCREEN_WIDTH / Tile.HALF_SIZE) / 2 + 5;
        String temp;
        int strWidth = 0;
        while (message.length() > maxLen) {
            int i = message.substring(0, maxLen).lastIndexOf(" ");
            if (i != -1 && i != maxLen) {
                temp = message.substring(0, i);
                message = message.substring(i + 1);
            } else {
                temp = message.substring(0, maxLen);
                message = message.substring(maxLen);
            }

            strWidth = Math.max(strWidth, temp.length());
            this.formatedText.add(temp);
        }

        if (message.length() <= maxLen) {
            this.formatedText.add(message);
        }

        sizeX = (this.formatedText.size() == 1) ? message.length() + 2 : maxLen + 2;
        sizeY = this.formatedText.size() + 2;
    }

    public void setText(String text) {
        formatString(text);

        changed = true;
    }

    @Override
    public void paintF(Screen screen) {
        super.paintF(screen);
        int h = 1;
        for (String text : formatedText) {
            Font.drawToBitmap(text, screen, Tile.HALF_SIZE, h * Tile.HALF_SIZE, colorText, image);
            h++;
        }
    }
}
