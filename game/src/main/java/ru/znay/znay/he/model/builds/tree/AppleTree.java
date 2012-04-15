package ru.znay.znay.he.model.builds.tree;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.Menu;
import ru.znay.znay.he.gfx.gui.TextPanel;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.resource.Apple;
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
public class AppleTree extends Tree {

    private boolean apple = false;
    private SpriteCollector spriteCollector;

    public AppleTree(int x, int y, SpriteCollector spriteCollector) {
        super(x, y, 16, 12);
        this.spriteCollector = spriteCollector;
    }

    private void wrapSprite(boolean drawAura) {
        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(20, 40, 30, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(21 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(10, 10, 20, -1)));
        spriteCollector.addWrapper(new SpriteWrapper(25 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(100, 210, 320, -1)));
        if (apple) {
            spriteCollector.addWrapper(new SpriteWrapper(17 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, 4 * Tile.HALF_SIZE, PaletteHelper.getColor(310, 400, 510, -1)));
        }
        this.sprite = spriteCollector.mergedWrappers("tree" + (apple ? "_apple" : ""), 2, 0, drawAura);

    }


    @Override
    public boolean interact(Item item, Player player, int dir) {

        if (!apple) return false;

        showMenu();

        return false;
    }


    public void showMenu() {
        GuiManager.getInstance().add(new TextPanel("На дереве вы видите несколько спелых яблок", 4, 4), "appletree");

        List<String> strings = new LinkedList<String>();
        strings.add("Сорвать");
        strings.add("Уйти");

        ((Menu) GuiManager.getInstance().get("menu")).showMenu(strings, new Menu.Callback() {
            @Override
            public void result(int result) {

                GuiManager.getInstance().remove("appletree");

                if (result == 1) return;

                wrapSprite((apple = !apple));

                tickTime = System.currentTimeMillis();

                //  Тут будет подбор ягод
                if (result == 0) {
                    for (int i = 0; i < random.nextInt(3) + 4; i++) {
                        level.add(new Apple(x, y));
                    }
                }
            }
        });
    }

    @Override
    public void render(Screen screen) {

        wrapSprite(false);

        if (mouseMotion && apple) {
            wrapSprite(true);
        }

        int xt = (x - xr * 2) - screen.getXOffset();
        int yt = (y - yr * 2 - 24) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);

    }

    @Override
    public void tick() {
        super.tick();

        if (System.currentTimeMillis() - tickTime < 2000 || apple) return;
        apple = !apple;

    }
}
