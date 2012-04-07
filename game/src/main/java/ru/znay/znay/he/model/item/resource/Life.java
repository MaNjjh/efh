package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 11.03.12
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class Life extends Resource {
    private int life;

    public Life(int x, int y, int life) {
        super(x, y);
        this.life = life;
        this.time = 600;
        this.xr = 3;
        this.yr = 3;
    }

    public void tick() {
        super.tick();

        if ((zz == 0) && level.getPlayer() != null) {
            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;
            int mag = level.getPlayer().getScore() / 200;
            if (xd * xd + yd * yd < mag * mag + 20 * 20) {
                if (xd < 0) xa = -1;
                if (xd > 0) xa = +1;
                if (yd < 0) ya = -1;
                if (yd > 0) ya = +1;
            }
            move((int) xa, (int) ya);
        }

    }

    public void render(Screen screen) {

        int col = PaletteHelper.getColor(-1, 0, 500, 555);

        if (time < 200) {
            if (System.currentTimeMillis() / 250 % 2 == 0) {
                col = PaletteHelper.getColor(-1, -1, -1, -1);
            }
        }
        screen.render(x - 4, y - 5 - (int) zz, 0 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);

        /*Font.draw(msg, screen, x - msg.length() * 4 + 1, y - (int) (zz) + 1, PaletteHelper.getColor(-1, 0, 0, 0));
        Font.draw(msg, screen, x - msg.length() * 4, y - (int) (zz), col);*/
    }


    public int getLife() {
        return life;
    }
}
