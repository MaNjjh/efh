package ru.znay.znay.he.model.npc;

import ru.znay.znay.he.cfg.Constants;
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
 * Date: 12.04.12
 * Time: 12:10
 * To change this template use File | Settings | File Templates.
 */
public class Warp extends Entity {
    private int srcLevel;
    private int dstLevel;
    private int dstX;
    private int dstY;
    private TextPanel textPanel;
    private Player player;
    private long tick;

    public Warp(int srcLevel, int srcX, int srcY, int dstLevel, int dstX, int dstY, SpriteCollector spriteCollector, Player player) {
        this.srcLevel = srcLevel;
        this.x = srcX;
        this.y = srcY;
        this.dstLevel = dstLevel;
        this.dstX = dstX;
        this.dstY = dstY;
        this.player = player;

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(0, 11 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, PaletteHelper.getColor(-1, 0, 222, 333)));

        this.sprite = spriteCollector.mergedWrappers("warp", 2, 0, false);
    }

    @Override
    public void render(Screen screen) {

        int xt = (x - 4) - screen.getXOffset();
        int yt = (y - 5 * 3 - yr) - screen.getYOffset();

        BitmapHelper.drawNormal(sprite, xt, yt, screen.getViewPort(), 0xFF00FF);
    }

    @Override
    public void touchedBy(Entity entity) {
        if (tick > System.currentTimeMillis()) return;

        if (entity instanceof Player) {
            showMenu();
        }
    }

    private void showMenu() {

        String mes;

        if (srcLevel == dstLevel)
            mes = "?";
        else
            mes = " в " + Constants.getLevelName(dstLevel) + "?";

        textPanel = new TextPanel("Совершить переход" + mes, 4, 4);

        GuiManager.getInstance().add(textPanel, "warp_menu");

        List<String> strings = new LinkedList<String>();
        strings.add("Переход");
        strings.add("Отмена");

        ((Menu) GuiManager.getInstance().get("menu")).showMenu(strings, new Menu.Callback() {
            @Override
            public void result(int result) {
                textPanel.close();
                if (result == 0)
                    doWarp();

                tick = System.currentTimeMillis() + 2000;
            }
        });
    }

    private void doWarp() {
        if (srcLevel == dstLevel)
            player.moveToXY(dstX, dstY);
        else
            player.moveLevel(dstLevel, dstX, dstY);
    }
}
