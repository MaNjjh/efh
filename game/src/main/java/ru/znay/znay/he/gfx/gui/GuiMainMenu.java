package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 09.05.12
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class GuiMainMenu extends GuiMenu implements GuiMenu.Callback{
    private Game game;

    public GuiMainMenu(Game game) {
        super(Constants.SCREEN_WIDTH / 3, Constants.SCREEN_HEIGHT / 2);

        panels.add(new GuiTextPanel("Играть",x,y,GuiManager.FONT_COLOR,GuiManager.MENU_COLOR_SEL));
        panels.add(new GuiTextPanel("Управление",x,y+ Tile.HALF_SIZE*3,GuiManager.FONT_COLOR,GuiManager.PANEL_COLOR));
        //panels.add(new GuiTextPanel("Авторы",x,y+ Tile.HALF_SIZE*6,GuiManager.FONT_COLOR,GuiManager.MENU_COLOR_SEL));

        callback = this;

        this.game = game;
        this.currentCell = 0;
        this.visible = false;
        this.changed = true;
    }

    @Override
    public void tick() {
        super.tick();

        if (InputHandler.getInstance(null).mainMenu.clicked) {
            this.setVisible(!visible);
        }
    }

    @Override
    public boolean result(int result) {
        switch (result) {
            case 0:
                hide();
                //game.setPause(false);
                GuiManager.isOpenedMenu = false;
                break;
            case 1:
                GuiManager.getInstance().get("helpPanel").show();
                return true;
            case 2:
                break;
        }

        return false;
    }

    @Override
    public void setVisible(boolean v) {
        super.setVisible(v);
        GuiManager.isOpenedMenu = v;
        GuiManager.mainMenu = v;
        //game.setPause(v);
    }
}
