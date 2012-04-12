package ru.znay.znay.he.model;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.item.FurnitureItem;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.furniture.Furniture;
import ru.znay.znay.he.model.item.resource.Coin;
import ru.znay.znay.he.model.item.resource.Life;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.particle.FlowText;
import ru.znay.znay.he.model.weapon.Arrow;
import ru.znay.znay.he.sound.Sound;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */

public class Player extends Mob {
    private Game game;
    private Item activeItem;
    private int score = 0;
    private int clearFogRadius = 4;
    private long goldTime;
    private int goldCollect;

    public Player(Game game) {
        this.team = ETeam.PLAYER_TEAM;
        this.game = game;
        this.bloodColor = 0xcc00cc;
        this.slowPeriod = 4;
    }

    @Override
    public void tick() {

        int xa = 0;
        int ya = 0;

        InputHandler inputHandler = InputHandler.getInstance();

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

        if (inputHandler.attack.clicked) {
            take();

            /*if (level.getEntities(x - Tile.HALF_SIZE, y - Tile.HALF_SIZE, x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, null).size() == 1) {
                if (score >= Mushroom.cost) {
                    if (level != null) {
                        level.add(new Mushroom(x, y));
                        score -= Mushroom.cost;
                    }
                }
            }*/
        }

        if (inputHandler.mouse.down && activeItem == null) {

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

        if (activeItem instanceof FurnitureItem) {
            yt += 2;
        }

        screen.render(xo + Tile.HALF_SIZE * flip1, yo + 0, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip1, yo + 0, (xt + 1) * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        if (!isSwimming()) {
            screen.render(xo + Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, xt * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
            screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, (xt + 1) * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
        }

        if (activeItem instanceof FurnitureItem) {
            Furniture furniture = ((FurnitureItem) activeItem).getFurniture();
            furniture.x = x;
            furniture.y = yo;
            furniture.render(screen);

        }
    }

    @Override
    public void touchItem(Resource resource) {
        if (resource instanceof Coin) {
            Coin coin = (Coin) resource;
            score += coin.getCost();
            goldCollect += coin.getCost();
            if (System.currentTimeMillis() - goldTime > 100) {
                level.add(new FlowText("+" + goldCollect, x, y - Tile.HALF_SIZE, Font.yellowColor));
                goldCollect = 0;
                goldTime = System.currentTimeMillis();
                Sound.pickup.play();
            }
        }

        if (resource instanceof Life) {
            Life life = (Life) resource;
            health += life.getLife();
        }

        resource.setRemoved(true);
    }

    public boolean take() {
        boolean done = false;

        int yo = -2;

        if (activeItem == null) {
            int range = 12;
            if (dir == 0 && interact(x - 8, y + 4 + yo, x + 8, y + range + yo)) done = true;
            if (dir == 1 && interact(x - 8, y - range + yo, x + 8, y - 4 + yo)) done = true;
            if (dir == 3 && interact(x + 4, y - 8 + yo, x + range, y + 8 + yo)) done = true;
            if (dir == 2 && interact(x - range, y - 8 + yo, x - 4, y + 8 + yo)) done = true;
        } else {
            int xt = x >> 4;
            int yt = (y + yo) >> 4;
            int r = 12;
            if (dir == 0) yt = (y + r + yo) >> 4;
            if (dir == 1) yt = (y - r + yo) >> 4;
            if (dir == 2) xt = (x - r) >> 4;
            if (dir == 3) xt = (x + r) >> 4;

            if (xt >= 0 && yt >= 0 && xt < level.getWidth() && yt < level.getHeight()) {
                level.getTile(xt, yt).interact(level, xt, yt, this, activeItem, dir);

                activeItem.interactOn(level.getTile(xt, yt), level, xt, yt, this, dir);

                if (activeItem.isDepleted()) {
                    activeItem = null;
                }
            }
        }
        return done;

    }

    private boolean interact(int x0, int y0, int x1, int y1) {
        List<Entity> entities = level.getEntities(x0, y0, x1, y1, null);
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);

            if (e instanceof Furniture) {
                Furniture f = (Furniture) e;
                f.take(this);
                return true;
            }

        }
        return false;
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
        return Math.min(clearFogRadius + score / 1000, 10);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Item getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(Item activeItem) {
        this.activeItem = activeItem;
    }

    public void moveXY(int x, int y) {
        this.x = x;
        this.y = y;
        InputHandler.getInstance().releaseAll();
    }
    
    public void moveLevel(int newLevel,int x,int y)
    {
        this.game.loadLevel(newLevel);
        this.x = x;
        this.y = y;
        InputHandler.getInstance().releaseAll();
    }
}
