package ru.znay.znay.he;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.Graphics;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.npc.Board;

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
    private InputHandler inputHandler = new InputHandler(this);
    private Player player;
    private int xScroll;
    private int yScroll;

    public void start() {
        running = true;
        //Sound.backMusic.loop();
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void init() {
        this.player = new Player(this, this.inputHandler);
        this.level = new Level(player, 0);
        this.level.add(new Board("Мы долго ждали приветствуем тебя! Ты узнаешь много нового и забудешь много старого!", player.getX() - 20, player.getY() - 20));
        this.inputHandler.releaseAll();
    }

    public void tick() {
        if (!hasFocus()) {
            this.inputHandler.releaseAll();
        } else {
            this.inputHandler.tick();

            if (player.isRemoved()) {
                if (inputHandler.attack.down) {
                    init();
                }
            }

            this.level.tick();
            Tile.tickCount++;
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
        level.renderFog(this.screen, xScroll, yScroll);
        level.getGuiManager().render(this.screen);
        //Font.renderFrame(this.screen, "меню", 4, 4, 11, 11);
        //Font.renderPanel("фпс: " + fps + " объектов: " + this.level.getEntities().size(), this.screen, 10, 10, PaletteHelper.getColor(5, 555, 555, 555));
        Font.renderPanel("золото: " + player.getScore() + " жизни: " + player.getHealth() + " slow: " + player.getSlowPeriod(), this.screen, 10, Constants.SCREEN_HEIGHT - 20, PaletteHelper.getColor(5, 555, 555, 555));
        //Font.draw("fps: " + fps + " obj: " + this.level.getEntities().size(), this.screen, 10, 10, PaletteHelper.getColor(-1, 111, 111, 511));
        //Font.draw("score: " + player.getScore() + " life: " + player.getHealth(), this.screen, 10, 18, PaletteHelper.getColor(-1, 111, 111, 511));
        if (player.isRemoved()) {

            String msg = "конец игры";
            Font.draw(msg, this.screen, (Constants.SCREEN_WIDTH - msg.length() * 8) >> 1, 100, PaletteHelper.getColor(555, 111, 111, 115));
            msg = "нажмите пробел чтобы начать играть";
            Font.draw(msg, this.screen, (Constants.SCREEN_WIDTH - msg.length() * 8) >> 1, 120, PaletteHelper.getColor(555, 111, 111, 115));
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

}
