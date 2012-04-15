package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class NPC extends Mob {
    protected int xStart;
    protected int yStart;
    protected int randomWalkTime = 0;
    protected int xa, ya;
    protected int color1;
    protected int color2;
    protected int xtStart;
    protected int ytStart;

    public NPC(int x, int y) {
        this.x = x;
        this.y = y;
        this.xStart = x;
        this.yStart = y;
        this.team = ETeam.NEUTRAL_TEAM;
        this.color1 = PaletteHelper.getColor(-1, 100, 232, 532);
        this.color2 = PaletteHelper.getColor(-1, 100, 232, 532);
        this.xtStart = 0;
        this.ytStart = 14;
    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    @Override
    public boolean canSwim() {
        return true;
    }

    @Override
    public boolean canMouseSelected() {
        return true;
    }

    @Override
    public void render(Screen screen) {

        int xt = xtStart;
        int yt = ytStart;

        int flip1 = (walkDist >> 3) & 1;
        int flip2 = (walkDist >> 3) & 1;

        if (dir == 1) {
            xt += 2;
        }
        if (dir > 1) {

            flip1 = 0;
            flip2 = ((walkDist >> 4) & 1);
            if (dir == 2) {
                flip1 = 1;
            }
            xt += 4 + ((walkDist >> 3) & 1) * 2;
        }


        int xo = x - 8;
        int yo = y - 11;

        if (isSwimming()) {
            yo += 4;
            int waterColor = PaletteHelper.getColor(-1, -1, -1, 444);
            if (tickTime / 8 % 2 == 0) {
                waterColor = PaletteHelper.getColor(-1, 444, -1, -1);
            }
            screen.render(xo + 0, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 0);
            screen.render(xo + Tile.HALF_SIZE, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 1);
        }


        if (hurtTime > 0) {
            color1 = PaletteHelper.getColor(-1, 555, 555, 555);
            color2 = PaletteHelper.getColor(-1, 555, 555, 555);
        }

        screen.render(xo + Tile.HALF_SIZE * flip1, yo + 0, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, color1, flip1);
        screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip1, yo + 0, (xt + 1) * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, color1, flip1);
        if (!isSwimming()) {
            screen.render(xo + Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, xt * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, color2, flip2);
            screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, (xt + 1) * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, color2, flip2);
        }
    }

}
