package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 29.04.12
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class FlowTextIcon extends FlowText {
    private final int xSprite;
    private final int ySprite;
    private final int iconColors;

    public FlowTextIcon(String msg, int x, int y, int textColors, int xSprite, int ySprite, int iconColors) {
        super(msg, x, y, textColors);
        this.xSprite = xSprite;
        this.ySprite = ySprite;
        this.iconColors = iconColors;
    }

    public void render(Screen screen) {
        super.render(screen);
        screen.render(x + message.length() * 6, y, xSprite * Tile.HALF_SIZE, ySprite * Tile.HALF_SIZE, iconColors, 0);
    }
}
