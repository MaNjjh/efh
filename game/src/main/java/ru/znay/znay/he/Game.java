package ru.znay.znay.he;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.AboutScreen;
import ru.znay.znay.he.gfx.Graphics;
import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.GuiPanel;
import ru.znay.znay.he.gfx.gui.GuiSpeedIndicator;
import ru.znay.znay.he.gfx.gui.GuiStatusPanel;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
    private Level[] levels = new Level[Constants.MAX_LEVEL_COUNT];

    private Player player;
    private int xScroll;
    private int yScroll;
    private Entity selectedEntity;
    public static boolean soundOn = false;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void init() {
        InputHandler.getInstance(this);

        player = new Player(this);

        Sound.backMusic.loop();

        for (int i = 0; i < Constants.MAX_LEVEL_COUNT; i++) {
            this.levels[i] = new Level(i, this);
        }

        changeLevel(0);
    }

    public void tick() {
        if (!hasFocus()) {
            InputHandler.getInstance(null).releaseAll();
        } else {
            InputHandler.getInstance(null).tick();

            if (InputHandler.getInstance(null).sound.clicked) {
                soundOn = !soundOn;
                if (soundOn) {
                    Sound.backMusic.loop();
                } else {
                    Sound.backMusic.stop();
                }
            }

            if (AboutScreen.getInstance().isShow()) {
                AboutScreen.getInstance().tick();
            } else {
                if (player.isRemoved()) {
                    if (InputHandler.getInstance(null).action.clicked) {
                        changeLevelByDir(0);
                    }
                }

                GuiManager.getInstance().tick();

                if (!GuiManager.mainMenu) {
                    this.level.tick();
                    Tile.tickCount++;

                    this.weatherManager.tick(level);
                    mouseTick();
                }
            }
        }

    }

    private void mouseTick() {
        int mx = InputHandler.getInstance(null).getXMousePos();
        int my = InputHandler.getInstance(null).getYMousePos();
        int r = 3;

        for (Entity entity : this.level.getEntities(mx - r, my - r, mx + r, my + r, null)) {
            entity.mouseMotion();
        }

        if (InputHandler.getInstance(null).mouse.clicked) {
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

        if (AboutScreen.getInstance().isShow()) {
            AboutScreen.getInstance().render(screen);
        } else {
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

            GuiPanel panel;
            if ((panel = GuiManager.getInstance().get("money")) != null) {
                ResourceItem coin = player.getInventory().findResource(Resource.coin);
                ResourceItem bigCoin = player.getInventory().findResource(Resource.bigCoin);
                int score = ((coin != null ? coin.getCount() : 0) + (bigCoin != null ? (bigCoin.getCount() << 1) : 0));//String.format("%s/%s", (coin != null ? coin.getCount() : 0), (bigCoin != null ? bigCoin.getCount() : 0));

                ((GuiStatusPanel) panel).setText(score);
            }

            if ((panel = GuiManager.getInstance().get("health")) != null)
                ((GuiStatusPanel) panel).setText2(player.getHealth() + "/" + player.getCurrentState().getEndurance());

            if ((panel = GuiManager.getInstance().get("speed")) != null)
                ((GuiSpeedIndicator) panel).changeSpeed(player.getSlowPeriod());

            GuiManager.getInstance().render(this.screen);

            if (player.isRemoved()) {
                String msg = "вы мертвы!";
                Font.draw(msg, this.screen, (Constants.SCREEN_WIDTH - msg.length() * 8) >> 1, 100, PaletteHelper.getColor(555, 111, 111, 115));
                msg = "нажмите пробел чтобы начать играть";
                Font.draw(msg, this.screen, (Constants.SCREEN_WIDTH - msg.length() * 8) >> 1, 120, PaletteHelper.getColor(555, 111, 111, 115));
            }
        }

        super.render(player.isRemoved());
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

    public void changeLevelByDir(int dir) {
        changeLevel(this.level.getNumber() + dir);
    }

    public void changeLevel(int level) {

        if (this.level != null) {
            this.level.remove(player);
        }

        this.level = this.levels[level % Constants.MAX_LEVEL_COUNT];

        this.level.add(player);

        GuiManager.getInstance().initDefaultGui(this.level);

        Random random = new Random();
/*
        for (int i = 0; i < 3; i++) {
            this.level.add(new Guardian(player.getX() + random.nextInt(61) - 30, player.getY() + random.nextInt(61) - 30));
        }
*/


        //this.level.add(new Chest(player.getX() - 10, player.getY() - 10, level.getSpriteCollector()));
        //this.level.add(new AirWizard(player.getX() - 10, player.getY() - 10));
        //this.level.add(new StoneMan(player.getX() - 30, player.getY() - 10));

        InputHandler.getInstance(null).releaseAll();
    }
}
