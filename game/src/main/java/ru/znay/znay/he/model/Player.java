package ru.znay.znay.he.model;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.builds.Mushroom;
import ru.znay.znay.he.model.item.Coin;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.Life;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.weapon.Arrow;
import ru.znay.znay.he.sound.Sound;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */

public class Player extends Mob {
    private InputHandler inputHandler;

    private Game game;
    private int score = 1000;
    private int clearFogRadius = 4;

    public Player(Game game, InputHandler inputHandler) {
        this.team = ETeam.PLAYER_TEAM;
        this.inputHandler = inputHandler;
        this.game = game;
        this.bloodColor = PaletteHelper.getColor(-1, 0, 0, 505);
        this.slowPeriod = 4;
    }

    @Override
    public void tick() {

        int xa = 0;
        int ya = 0;

        if (inputHandler.up.down) {
            ya--;
        }
        if (inputHandler.down.down) {
            ya++;
        }
        if (inputHandler.left.down) {
            xa--;
        }
        if (inputHandler.right.down) {
            xa++;
        }

        if (inputHandler.attack.down) {
            if (level.getEntities(x - Tile.HALF_SIZE, y - Tile.HALF_SIZE, x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, null).size() == 1) {
                if (score >= Mushroom.cost) {
                    if (level != null) {
                        level.add(new Mushroom(x, y));
                        score -= Mushroom.cost;
                    }
                }
            }
        }

        if (inputHandler.mouse.down) {

            int xDiff = inputHandler.getXMousePos() - x;
            int yDiff = inputHandler.getYMousePos() - y;

            double m = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

            double vx = xDiff / m;
            double vy = yDiff / m;

            if (tickTime % 9 == 0) {
                this.level.add(new Arrow(this.team, x, y, vx, vy, random.nextInt(this.score / 1000 + 3) + 1));
            }
        }

        move(xa, ya);

        super.tick();
    }

    @Override
    public void touchedBy(Entity entity) {

        if (entity instanceof Coin) {
            Coin coin = (Coin) entity;
            score += coin.getCost();
        }

        if (entity instanceof Life) {
            Life life = (Life) entity;
            health += life.getLife();
        }

        if (entity instanceof Item) {
            Sound.pickup.play();
            entity.setRemoved(true);
        }
        if ((entity.getTeam() != this.team)) {
            entity.touchedBy(this);
        }
    }

    @Override
    public void render(Screen screen) {

        int xt = 8;
        int yt = 14;

        int flip1 = (walkDist >> 3) & 1;
        int flip2 = (walkDist >> 3) & 1;

        if (dir == 1) {
            xt += 2;
        }
        if (dir > 1) {

            flip1 = 0;
            flip2 = ((walkDist >> 4) & 1);
            if (dir == 2) {
                flip1 = 1;
            }
            xt += 4 + ((walkDist >> 3) & 1) * 2;
        }


        int xo = x - 8;
        int yo = y - 11;
        if (isSwimming()) {
            yo += 4;
            int waterColor = PaletteHelper.getColor(-1, -1, -1, 444);
            if (tickTime / 8 % 2 == 0) {
                waterColor = PaletteHelper.getColor(-1, 444, -1, -1);
            }
            screen.render(xo + 0, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 0);
            screen.render(xo + Tile.HALF_SIZE, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 1);
        }

        int col1 = PaletteHelper.getColor(-1, 100, 500, 555);
        int col2 = PaletteHelper.getColor(-1, 100, 500, 532);

        if (hurtTime > 0) {
            col1 = PaletteHelper.getColor(-1, 555, 555, 555);
            col2 = PaletteHelper.getColor(-1, 555, 555, 555);
        }
        screen.render(xo + Tile.HALF_SIZE * flip1, yo + 0, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip1, yo + 0, (xt + 1) * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        if (!isSwimming()) {
            screen.render(xo + Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, xt * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
            screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, (xt + 1) * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
        }
    }

    @Override
    public boolean canSwim() {
        return true;
    }

    public boolean findStartPos(Level level) {
        while (true) {
            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getWidth());
            if (level.getTile(x, y) == Tile.grass) {
                this.x = x * Tile.SIZE + Tile.HALF_SIZE;
                this.y = y * Tile.SIZE + Tile.HALF_SIZE;
                return true;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getClearFogRadius() {
        return clearFogRadius + score / 1000;
    }
}
