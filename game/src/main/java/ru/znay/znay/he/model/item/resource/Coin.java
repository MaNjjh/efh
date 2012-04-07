package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 07.03.12
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public class Coin extends Resource {
    private int cost;
    private int size = 0;

    public Coin(int x, int y, int cost) {
        super(x, y);

        this.size = random.nextInt(15) == 0 ? 1 : 0;

        this.cost = cost + size * cost;
        this.time = 600;
        this.xr = 3;
        this.yr = 3;
        this.speed = random.nextInt(2) + 1;
    }

    public void tick() {
        super.tick();

        if ((zz == 0) && level.getPlayer() != null) {
            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;
            //int mag =0*level.getPlayer().getScore() / 200;
            if (xd * xd + yd * yd < /*mag * mag +*/ 20 * 20) {
                if (xd < 0) xa = -1;
                if (xd > 0) xa = +1;
                if (yd < 0) ya = -1;
                if (yd > 0) ya = +1;
            }
            move((int) xa * speed, (int) ya * speed);
        }

    }

    public void render(Screen screen) {

        int col = PaletteHelper.getColor(-1, 0, 552, 555);

        if (time < 200) {
            if (System.currentTimeMillis() / 250 % 2 == 0) {
                col = PaletteHelper.getColor(-1, -1, -1, -1);
            }
        }
        screen.render(x - 4, y - 5 - (int) zz, size * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
