package ru.znay.znay.he.model.builds;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;
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

    public Mushroom() {
        this.xr = 1;
        this.yr = 1;
        this.time = System.currentTimeMillis();
    }

    public void tick() {
        super.tick();
        if (System.currentTimeMillis() - this.time > 10000) {

            if (tx == 2) {
                if (this.level != null) {

                    int count = level.getNumber() * 2 + 4;

                    for (int i = 0; i < count; i++) {
                        this.level.add(new ItemEntity(new ResourceItem(Resource.coin), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
                        if (random.nextInt(10) == 0) {
                            this.level.add(new ItemEntity(new ResourceItem(Resource.bigCoin), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
                        }
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

        int col = PaletteHelper.getColor(-1, level.getNumber() * 10, 510 + level.getNumber() * 10, 552 + level.getNumber());

        screen.render(x - 4, y - 5, tx * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);

        /*Font.draw(message, screen, x - message.length() * 4 + 1, y - (int) (zz) + 1, PaletteHelper.getColor(-1, 0, 0, 0));
        Font.draw(message, screen, x - message.length() * 4, y - (int) (zz), col);*/
    }
}
