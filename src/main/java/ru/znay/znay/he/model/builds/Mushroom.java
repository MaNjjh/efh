package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.item.Coin;
import ru.znay.znay.he.model.item.Life;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 12.03.12
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class Mushroom extends Mob {

    private int tx = 0;
    private Long time;
    public final static int cost = 300;

    public Mushroom(int x, int y) {
        this.x = x;
        this.y = y;
        this.xr = 1;
        this.yr = 1;
        this.team = ETeam.PLAYER_TEAM;
        this.time = System.currentTimeMillis();
    }

    public Mushroom() {
        this.team = ETeam.PLAYER_TEAM;
        this.time = System.currentTimeMillis();
    }

    @Override
    public void touchedBy(Entity entity) {
        if ((entity.getTeam() != this.team)) {
            entity.touchedBy(this);
        }
    }

    public void tick() {
        super.tick();
        if (System.currentTimeMillis() - this.time > 10000) {

            if (tx == 2) {
                if (this.level != null) {
                    for (int i = 0; i < 5; i++) {
                        this.level.add(new Coin(x, y, random.nextInt(5) + 1));
                    }
                }
            }


            if (tx < 2) {
                tx++;
            }

            this.time = System.currentTimeMillis();


        }

    }

    @Override
    public boolean blocks(Entity entity) {
        return true;
    }

    public void render(Screen screen) {

        int col = PaletteHelper.getColor(-1, 0, 510, 552);

        screen.render(x - 4, y - 5, tx * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);

        /*Font.draw(msg, screen, x - msg.length() * 4 + 1, y - (int) (zz) + 1, PaletteHelper.getColor(-1, 0, 0, 0));
        Font.draw(msg, screen, x - msg.length() * 4, y - (int) (zz), col);*/
    }
}
