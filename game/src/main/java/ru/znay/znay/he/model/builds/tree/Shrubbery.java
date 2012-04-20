package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.GuiMenu;
import ru.znay.znay.he.gfx.gui.GuiTextPanel;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
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
 * Date: 12.04.12
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class Shrubbery extends Tree implements GuiMenu.Callback {
    private boolean berry = false;
    private boolean type = random.nextBoolean();
    private long tick = System.currentTimeMillis() + 60000;
    private long tick2;
    private SpriteCollector spriteCollector;

    public Shrubbery(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 4, 1);
        this.spriteCollector = spriteCollector;
        wrapSprite(berry);
    }

    private void wrapSprite(boolean flag) {
        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(((type) ? 21 : 25) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(20, 30, 40, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(((type) ? 23 : 27) * Tile.HALF_SIZE, 0, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, PaletteHelper.getColor(10, (flag ? 5 : -1), 10, -1)));

        this.sprite = spriteCollector.mergedWrappers("shrubbery_" + (type ? "0" : "1") + (flag ? "_berry" : ""), 1, 0, false);
    }

    @Override
    public void tick() {
        super.tick();

        if (tick > System.currentTimeMillis() || berry) return;

        wrapSprite((berry = !berry));
    }

    @Override
    public void result(int result) {

        GuiManager.getInstance().remove("shrubbery");
        tick2 = System.currentTimeMillis() + 2000;

        if (result == 1) return;

        wrapSprite((berry = !berry));

        tick = System.currentTimeMillis() + 120000;

        //  Тут будет подбор ягод
        //if (result == 0)
        //
        //
    }

    @Override
    public void touchedBy(Entity entity) {
        super.touchedBy(entity);

        if (!berry) return;

        if (tick2 < System.currentTimeMillis() && entity instanceof Player)
            showMenu();
    }

    private void showMenu() {
        GuiManager.getInstance().add(new GuiTextPanel("Среди листьев вы обнаруживаете несколько подозрительных ягод", 4, 4), "shrubbery");

        List<String> strings = new LinkedList<String>();
        strings.add("Собрать");
        strings.add("Уйти");

        ((GuiMenu) GuiManager.getInstance().get("menu")).showMenu(strings, this);
    }
}
