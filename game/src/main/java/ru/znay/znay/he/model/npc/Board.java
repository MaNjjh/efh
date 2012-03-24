package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class Board extends Entity {

    private String message;

    public Board(String message, int x, int y) {
        this.x = x;
        this.y = y;
        this.message = message;
    }

    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            if (!level.getDialogManager().hasText(this.message)) {
                level.getDialogManager().addTypedText(this.message, 10, 40, PaletteHelper.getColor(5, 555, 555, 555));
            }
        }
    }

    @Override
    public boolean blocks(Entity entity) {
        return false;
    }

    public void render(Screen screen) {

        int col = PaletteHelper.getColor(-1, 0, 222, 333);

        if (System.currentTimeMillis() / 100 % 2 == 0) {
            col = PaletteHelper.getColor(-1, 111, 222, 533);
        }

        screen.render(2, x - 4, y - 5, 0 * Tile.HALF_SIZE, 11 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
    }


}
