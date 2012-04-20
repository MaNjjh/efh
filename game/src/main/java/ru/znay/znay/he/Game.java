package ru.znay.znay.he;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.Graphics;
import ru.znay.znay.he.gfx.gui.Inventory;
import ru.znay.znay.he.gfx.weather.WeatherManager;
import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.Panel;
import ru.znay.znay.he.gfx.gui.SpeedIndicator;
import ru.znay.znay.he.gfx.gui.StatusPanel;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.mob.AirWizard;
import ru.znay.znay.he.model.npc.village.StoneMan;
import ru.znay.znay.he.sound.Sound;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 12:37
 * To change this template use File | Settings | File Templates.
 */
public class Game extends Graphics implements Runnable {

    private boolean running = false;
    private Level level;
    private Player player;
    private int xScroll;
    private int yScroll;
    private Entity selectedEntity;
    private WeatherManager weatherManager = new WeatherManager();

    public void start() {
        running = true;
        //Sound.backMusic.loop();
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void init() {
        InputHandler.getInstance().init(this);
        this.player = new Player(this);

        GuiManager.getInstance().initDefaultGui(this);

        this.loadLevel(0);
    }

    public void tick() {
        if (!hasFocus()) {
            InputHandler.getInstance().releaseAll();
        } else {
            InputHandler.getInstance().tick();

            if (player.isRemoved()) {
                if (InputHandler.getInstance().action.clicked) {
                    loadLevel(this.level);
                }
            }

            this.level.tick();
            Tile.tickCount++;

            this.weatherManager.tick(level);
            mouseTick();
        }

    }

    private void mouseTick() {
        int mx = InputHandler.getInstance().getXMousePos();
        int my = InputHandler.getInstance().getYMousePos();
        int r = 3;

        for (Entity entity : this.level.getEntities(mx - r, my - r, mx + r, my + r, null)) {
            entity.mouseMotion();
        }

        if (InputHandler.getInstance().mouse.clicked) {
            selectedEntity = null;

            for (Entity entity : this.level.getEntities(mx - r, my - r, mx + r, my + r, null)) {
                if (entity.canMouseSelected()) {
                    selectedEntity = entity;
                    break;
                }
            }
        }
    }

    public void render(int fps) {

        prepareGraphics();

        xScroll = this.player.getX() - this.screen.getViewPort().getWidth() / 2;
        yScroll = this.player.getY() - (this.screen.getViewPort().getHeight() - Tile.HALF_SIZE) / 2;
        if (xScroll < Tile.SIZE) xScroll = Tile.SIZE;
        if (yScroll < Tile.SIZE) yScroll = Tile.SIZE;
        if (xScroll > this.level.getWidth() * Tile.SIZE - this.screen.getViewPort().getWidth() - Tile.SIZE) {
            xScroll = this.level.getWidth() * Tile.SIZE - this.screen.getViewPort().getWidth() - Tile.SIZE;
        }
        if (yScroll > this.level.getHeight() * Tile.SIZE - this.screen.getViewPort().getHeight() - Tile.SIZE) {
            yScroll = this.level.getHeight() * Tile.SIZE - this.screen.getViewPort().getHeight() - Tile.SIZE;
        }

        level.renderBackground(this.screen, xScroll, yScroll);
        level.renderSprites(this.screen, xScroll, yScroll);

        if (selectedEntity != null) {
            Font.draw("selected", this.screen, selectedEntity.getX() - xScroll, selectedEntity.getY() - yScroll, PaletteHelper.getColor(-1, 111, 111, 555));
        }

        level.renderFog(this.screen, xScroll, yScroll);

        Panel panel;
        if ((panel = GuiManager.getInstance().get("money")) != null) {
            ResourceItem coin = player.getInventory().findResource(Resource.coin);
            ResourceItem bigCoin = player.getInventory().findResource(Resource.bigCoin);
            String score = String.format("%s/%s", (coin != null ? coin.getCount() : 0), (bigCoin != null ? bigCoin.getCount() : 0));

            ((StatusPanel) panel).setText2(score);
        }

        if ((panel = GuiManager.getInstance().get("health")) != null)
            ((StatusPanel) panel).setText(player.getHealth());

        if ((panel = GuiManager.getInstance().get("speed")) != null)
            ((SpeedIndicator) panel).changeSpeed(player.getSlowPeriod());

        GuiManager.getInstance().render(this.screen);

        //Font.renderFrame(this.screen, "меню", 4, 4, 11, 11);
        //Font.renderPanel("фпс: " + fps + " объектов: " + this.level.getEntities().size(), this.screen, 10, 10, PaletteHelper.getColor(5, 555, 555, 555));
        //Font.renderPanel("золото: " + player.getScore() + " жизни: " + player.getHealth() + " скорость: " + player.getSlowPeriod(), this.screen, 10, Constants.SCREEN_HEIGHT - 20, PaletteHelper.getColor(5, 555, 555, 555));
        //Font.draw("fps: " + fps + " obj: " + this.level.getEntities().size(), this.screen, 10, 10, PaletteHelper.getColor(-1, 111, 111, 511));
        //Font.draw("score: " + player.getScore() + " life: " + player.getHealth(), this.screen, 10, 18, PaletteHelper.getColor(-1, 111, 111, 511));
        if (player.isRemoved()) {

            String msg = "конец игры";
            Font.draw(msg, this.screen, (Constants.SCREEN_WIDTH - msg.length() * 8) >> 1, 100, PaletteHelper.getColor(555, 111, 111, 115));
            msg = "нажмите пробел чтобы начать играть";
            Font.draw(msg, this.screen, (Constants.SCREEN_WIDTH - msg.length() * 8) >> 1, 120, PaletteHelper.getColor(555, 111, 111, 115));
        }

        super.render(player.isRemoved(), this.weatherManager.getWeather());
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        int lastFrames = 0;
        long lastTimer1 = System.currentTimeMillis();

        init();

        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;
                shouldRender = false;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render(lastFrames);
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                lastFrames = frames;
                System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();

        Dimension dimension = new Dimension(Constants.SCREEN_WIDTH * Constants.SCREEN_SCALE, Constants.SCREEN_HEIGHT * Constants.SCREEN_SCALE);
        game.setMinimumSize(dimension);
        game.setMaximumSize(dimension);
        game.setPreferredSize(dimension);

        JFrame frame = new JFrame(Constants.GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }

    public Level getLevel() {
        return level;
    }

    public int getXScroll() {
        return xScroll;
    }


    public int getYScroll() {
        return yScroll;
    }

    public void loadLevel(int i) {
        this.level = new Level(this.player, i, this);
        //this.level.add(new AirWizard(player.getX() - 10, player.getY() - 10));
        //this.level.add(new StoneMan(player.getX() - 10, player.getY() - 10));

        //this.level.add(new House(player.getX(), player.getY() + 50));

        InputHandler.getInstance().releaseAll();
    }

    public void loadLevel(Level level) {
        loadLevel(level.getNumber());
    }
}
