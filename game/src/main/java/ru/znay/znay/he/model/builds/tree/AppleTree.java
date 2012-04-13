package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.Menu;
import ru.znay.znay.he.gfx.gui.TextPanel;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 22.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class AppleTree extends Tree implements Menu.MenuCallback {

    private boolean apple = false;
    private SpriteCollector spriteCollector;
    private long tick = System.currentTimeMillis() + 60000;
    private long tick2;
    private TextPanel textPanel;

    public AppleTree(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 16, 12);
        this.spriteCollector = spriteCollector;
        wrapSprite(apple);
    }

    private void wrapSprite(boolean flag) {
        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(20, 40, 30, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(21 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(10, 10, 20, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(25 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(100, 210, 320, -1)));
        if (apple) {
            spriteCollector.addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, -1, 510, -1)));
        }
        this.sprite = spriteCollector.mergedWrappers("tree" + (apple ? "_apple" : ""), 2, 0, true);

    }

    @Override
    public void result(int result) {
        textPanel.close();
        tick2 = System.currentTimeMillis() + 2000;

        if (result == 1) return;

        wrapSprite((apple = !apple));

        tick = System.currentTimeMillis() + 120000;

        //  Тут будет подбор ягод
        //if (result == 0)
        //
        //
    }

    @Override
    public void touchedBy(Entity entity) {
        super.touchedBy(entity);

        if (!apple) return;

        if (tick2 < System.currentTimeMillis() && entity instanceof Player)
            showMenu();
    }

    public void showMenu() {
        textPanel = new TextPanel("На дереве вы видите несколько спелых яблок", 4, 4);

        GuiManager.getInstance().add(textPanel, "appletree");

        List<String> strings = new LinkedList<String>();
        strings.add("Сорвать");
        strings.add("Уйти");

        ((Menu) GuiManager.getInstance().get("menu")).showMenu(strings, this);
    }

    @Override
    public void render(Screen screen) {

        int xt = (x - xr * 2) - screen.getXOffset();
        int yt = (y - yr * 2 - 24) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);

    }

    @Override
    public void tick() {
        super.tick();

        if (tick > System.currentTimeMillis() || apple) return;

        wrapSprite((apple = !apple));
    }
}
