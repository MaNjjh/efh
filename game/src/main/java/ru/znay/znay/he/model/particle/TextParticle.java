package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 05.03.12
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class TextParticle extends SmashParticle {
    protected String message;

    public TextParticle(String message, int x, int y, int color) {
        super(x, y, color);
        this.message = message;
    }

    public void tick() {
        super.tick();
    }

    public void render(Screen screen) {
        // Font.draw(message, screen, x - message.length() * 4, y, PaletteHelper.getColor(-1, 0, 0, 0));
        Font.draw(message, screen, x - message.length() * 4 + 1, y - (int) (zz) + 1, PaletteHelper.getColor(-1, 0, 0, 0));
        Font.draw(message, screen, x - message.length() * 4, y - (int) (zz), color);
    }

}
