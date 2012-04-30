package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.GuiMiniMap;
import ru.znay.znay.he.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 30.04.12
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class MarkPromotion implements QuestPromotion {
    private int x;
    private int y;
    private int flag;

    @Override
    public void promotion(Player player) {
        if (flag == 1) {
            ((GuiMiniMap) GuiManager.getInstance().get("minimap")).showMark(x << 4, y << 4);
        } else {
            ((GuiMiniMap) GuiManager.getInstance().get("minimap")).hideMark();
        }
    }

    @Override
    public void setParam(String param) {
        int d = param.indexOf(":");
        x = Integer.decode(param.substring(0, d));
        y = Integer.decode(param.substring(d + 1));
    }

    @Override
    public void setCount(String count) {
        flag = Integer.decode(count);
    }
}
