package ru.znay.znay.he.model.dialog;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 27.03.12
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public class GuiTextPanel extends GuiPanel {
    List<String> mes = new LinkedList<String>();

    public GuiTextPanel(String mes, int posX, int posY) {
        super(posX, posY);


        formatString(mes);

        changed = true;
        visible = true;
    }

    private void formatString(String mes) {
        if (this.mes.size() > 0)
            this.mes.clear();

        if (mes.isEmpty())
            mes = "TEXT";

        final int maxLen = (Constants.SCREEN_WIDTH / Tile.HALF_SIZE) / 2 - 2;
        String temp;
        while (mes.length() > maxLen) {
            System.out.println(">"+mes+"<");
            int i = mes.substring(0, maxLen).lastIndexOf(" ");
            System.out.println(i);
            if (i != -1 && i != maxLen) {
                temp = String.format("%-" + maxLen + "s", mes.substring(0, i));
                mes = mes.substring(i + 1);
            } else {
                temp = mes.substring(0, maxLen);
                mes = mes.substring(maxLen);
            }

            this.mes.add(temp);
        }

        if (mes.length() <= maxLen) {
            this.mes.add(String.format("%-" + maxLen + "s", mes));
        }

        sizeX = maxLen + 2;
        sizeY = this.mes.size() + 2;
    }

    public void setText(String text) {
        formatString(text);

        changed = true;
    }

    @Override
    public void paintF(Screen screen) {
        super.paintF(screen);
        int h = 1;
        int col = PaletteHelper.getColor(5, 555, 555, 555);
        for (String text : mes) {
            Font.drawToBitmap(text, screen, Tile.HALF_SIZE, h * Tile.HALF_SIZE, col, image);
            h++;
        }

    }
}
