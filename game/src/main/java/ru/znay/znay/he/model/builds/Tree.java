package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 22.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class Tree extends Mob {

    //яблоки на потом оставлю
    //private Long time;

    public Tree(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xr = 1;
        this.yr = 1;
        this.team = ETeam.ENEMY_TEAM;
        //this.time = System.currentTimeMillis();
    }

    public Tree()
    {
        this.xr = 1;
        this.yr = 1;
        this.team = ETeam.ENEMY_TEAM;
    }

    @Override
    public void touchedBy(Entity entity) {

    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    public void render(Screen screen) {

        int col = PaletteHelper.getColor(1,990, 500, -1);

        screen.render(x-4 , y-5 , 17 * Tile.HALF_SIZE, 0 , 4*Tile.HALF_SIZE, 4*Tile.HALF_SIZE, col, 0);

        /*Font.draw(msg, screen, x - msg.length() * 4 + 1, y - (int) (zz) + 1, PaletteHelper.getColor(-1, 0, 0, 0));
        Font.draw(msg, screen, x - msg.length() * 4, y - (int) (zz), col);*/
    }
}
