package ru.znay.znay.he.model.level.tile.ground;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.Level;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 10.04.12
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
public class RandomGrassTile extends GrassTile {
    private int type = random.nextInt(100);

    public RandomGrassTile(int id) {
        super(id);
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        super.render(screen, level, x, y);
        if (type < 60) return;
        if (type < 75) {

            return;
        }

        if (type < 85) {

            return;
        }

    }
}
