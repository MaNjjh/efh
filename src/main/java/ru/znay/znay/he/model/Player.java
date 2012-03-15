package ru.znay.znay.he.model;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.builds.Mushroom;
import ru.znay.znay.he.model.item.Coin;
import ru.znay.znay.he.model.item.Life;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.weapon.Arrow;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Mob {
    private InputHandler inputHandler;
    private int animX = 0;
    private int color;
    private Game game;
    private int score = 1000;
    private int clearFogRadius = 4;


    public Player(Game game, InputHandler inputHandler) {
        this.team = ETeam.PLAYER_TEAM;
        this.inputHandler = inputHandler;
        this.game = game;
        this.color = PaletteHelper.getColor(-1, 100, 522, 555);
    }

    @Override
    public void tick() {
        int xa = 0;
        int ya = 0;
        if (inputHandler.up.down) ya--;
        if (inputHandler.down.down) ya++;
        if (inputHandler.left.down) xa--;
        if (inputHandler.right.down) xa++;

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

        color = PaletteHelper.getColor(-1, 100, 522, 555);

        if (inputHandler.mouse.down) {

            int tx = inputHandler.getXMousePos() - x;
            int ty = inputHandler.getYMousePos() - y;

            double m = Math.sqrt(tx * tx + ty * ty);

            tx = (int) Math.round(tx / m);
            ty = (int) Math.round(ty / m);

            if (tickTime % 6 == 0)
                this.level.add(new Arrow(this.team, x, y, tx, ty, random.nextInt(this.score / 1000 + 3) + 1));

            color = PaletteHelper.getColor(-1, 111, 444, 555);
        }
        move(xa, ya);
        super.tick();
    }

    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Coin) {
            Coin coin = (Coin) entity;
            score += coin.getCost();
            coin.setRemoved(true);
        }
        if (entity instanceof Life) {
            Life life = (Life) entity;
            health += life.getLife();
            life.setRemoved(true);
        }
        if ((entity.getTeam() != this.team)) {
            entity.touchedBy(this);
        }
    }

    @Override
    public void render(Screen screen) {
        int xo = x - 4;
        int yo = y - 6;
        screen.render(xo, yo, 0, 6 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, color, 0);
    }

    public boolean findStartPos(Level level) {
        while (true) {
            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getWidth());
            if (level.getTile(x, y) == Tile.glass) {
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
