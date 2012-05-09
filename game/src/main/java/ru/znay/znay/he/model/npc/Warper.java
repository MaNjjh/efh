package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 09.05.12
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class Warper extends Entity {

    private boolean isUp;

    public Warper(int x, int y, boolean isUp) {
        this.x = x;
        this.y = y;
        this.isUp = isUp;
    }

    @Override
    public void render(Screen screen) {

        int xd = x - 8;
        int yd = y - 9;

        int colors = PaletteHelper.getColor(-1, 0, 222, 333);

        screen.render(2, xd, yd, 0, 11 * Tile.HALF_SIZE, colors, 0);
    }

    @Override
    public void touchedBy(Entity entity) {

        if (entity instanceof Player) {
            this.level.getGame().changeLevelByDir(isUp ? 1 : -1);
        }
    }
}
