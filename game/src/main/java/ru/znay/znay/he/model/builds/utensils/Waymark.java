package ru.znay.znay.he.model.builds.utensils;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.TypedTextPanel;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 15.04.12
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class Waymark extends Utensils {
    private long tick;
    private String message;

    public Waymark(int x, int y, SpriteCollector spriteCollector, String message) {
        super(x, y, 4, 1);
        this.message = message;
        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(10 * Tile.HALF_SIZE, 5 * Tile.HALF_SIZE, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(320, 421, 430, -1)));
        this.sprite = spriteCollector.mergedWrappers("waymark", 1, 0, true);
    }

    @Override
    public void touchedBy(Entity entity) {

        if (entity instanceof Player) {
            if (tick < System.currentTimeMillis()) {
                tick = System.currentTimeMillis() + 1000;
                GuiManager.getInstance().add(new TypedTextPanel(message, 4, 4, 100), "waymark");
            }
        }

    }
}
