package ru.znay.znay.he.model.level;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.builds.tree.AppleTree;
import ru.znay.znay.he.model.builds.tree.FirTree;
import ru.znay.znay.he.model.builds.tree.PineTree;
import ru.znay.znay.he.model.builds.tree.Shrubbery;
import ru.znay.znay.he.model.builds.tree.TreeStump;
import ru.znay.znay.he.model.builds.utensils.Well;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.mob.SlimeFactory;
import ru.znay.znay.he.model.mob.boss.AirWizard;
import ru.znay.znay.he.model.mob.boss.snake.Snake;
import ru.znay.znay.he.model.mob.boss.snake.SnakeNeck;
import ru.znay.znay.he.model.mob.boss.snake.SnakePart;
import ru.znay.znay.he.model.npc.Guardian;
import ru.znay.znay.he.model.npc.Warper;
import ru.znay.znay.he.model.particle.ParticleSystem;
import ru.znay.znay.he.sound.Sound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class Level {
    private final static int GRASS_TILE = 0xFFFFFFFF;
    private final static int WATER_TILE = 0xFF0000FF;
    private final static int DEEP_WATER_TILE = 0xFF0000CC;
    private final static int SAND_TILE = 0xFFFFFF00;
    private final static int LAVA_TILE = 0xFFFF7F00;
    private final static int ROAD_TILE = 0xFF6B6B6B;
    private final static int SWAMP_TILE = 0xFF90FF00;
    private final static int ROCK_TILE = 0xFFF79090;

    private final static int HOLE_TILE = 0xFF606060;
    private final static int APPLE_TREE = 0xFF00FF00;
    private final static int FIR_TREE = 0xFF00CC00;
    private final static int TREE_STUMP = 0xFF008800;
    private final static int PLAYER_SPAWN = 0xFFFF0000;
    private final static int SHRUBBERY = 0xFF005500;

    private int number;

    private Random random = new Random();

    private int width;
    private int height;

    private Game game;

    private Player player;

    private byte[] tiles;

    private Fog fog;
    private int monsterDensity = 6;
    private long tickTime = 0;
    private List<Entity>[] entitiesInTiles;

    private int mobCount = 0;

    private List<Entity> entities = new ArrayList<Entity>();

    private int respX = 0;
    private int respY = 0;

    private Comparator<Entity> spriteSorter = new Comparator<Entity>() {
        public int compare(Entity e0, Entity e1) {
            if (e1.getY() < e0.getY()) return 1;
            if (e1.getY() > e0.getY()) return -1;
            return 0;
        }
    };

    public Level(int level, Game game) {

        this.number = level;
        this.game = game;


        this.loadTiles(level);

        this.loadObjects(level);

        trySpawn();

        Sound.startGame.play();
    }

    @SuppressWarnings("unchecked")
    private void loadTiles(int level) {
        Bitmap map = BitmapHelper.loadBitmapFromResources("/maps/" + level + ".png");

        this.width = map.getWidth();
        this.height = map.getHeight();

        this.fog = new Fog(this.width, this.height, true);

        this.tiles = new byte[this.width * this.height];

        this.entitiesInTiles = new ArrayList[this.width * this.height];
        for (int i = 0; i < this.width * height; i++) {
            this.entitiesInTiles[i] = new ArrayList<Entity>();
        }

        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                int value = map.getPixels()[i + j * this.width];
                int xx = (i << 4) + Tile.HALF_SIZE;
                int yy = (j << 4) + Tile.HALF_SIZE;
                switch (value) {
                    case GRASS_TILE: {
                        setTile(i, j, Tile.grass, 0);
                        break;
                    }
                    case WATER_TILE: {
                        setTile(i, j, Tile.water, 0);
                        break;
                    }
                    case DEEP_WATER_TILE: {
                        setTile(i, j, Tile.deepWater, 0);
                        break;
                    }
                    case SAND_TILE: {
                        setTile(i, j, Tile.sand, 0);
                        break;
                    }
                    case ROAD_TILE: {
                        setTile(i, j, Tile.road, 0);
                        break;
                    }
                    case SWAMP_TILE: {
                        setTile(i, j, Tile.swamp, 0);
                        break;
                    }
                    case HOLE_TILE: {
                        setTile(i, j, Tile.hole, 0);
                        break;
                    }
                    case ROCK_TILE: {
                        setTile(i, j, Tile.rock, 0);
                        break;
                    }
                    case APPLE_TREE: {
                        add(new AppleTree(xx, yy));
                        break;
                    }
                    case FIR_TREE: {
                        int r = random.nextInt(100);
                        if (r < 72) break;
                        if (r < 75) {
                            add(new TreeStump(xx, yy, this.game.getSpriteCollector()));
                            break;
                        }
                        if (random.nextBoolean()) {
                            add(new FirTree(xx, yy, this.game.getSpriteCollector()));
                        } else {
                            add(new PineTree(xx, yy, this.game.getSpriteCollector()));
                        }
                        break;
                    }
                    case TREE_STUMP: {
                        add(new TreeStump(xx, yy, this.game.getSpriteCollector()));
                        break;
                    }
                    case SHRUBBERY: {
                        add(new Shrubbery(xx, yy, this.game.getSpriteCollector()));
                        break;
                    }

                    case LAVA_TILE: {
                        setTile(i, j, Tile.lava, 0);
                        break;
                    }
                }
            }
        }
    }

    private void loadObjects(int level) {
        Bitmap map = BitmapHelper.loadBitmapFromResources("/maps/" + level + "O.png");
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                int value = map.getPixels()[i + j * map.getWidth()];
                int xx = (i << 4) + Tile.HALF_SIZE;
                int yy = (j << 4) + Tile.HALF_SIZE;

                if (value == 0xFFFFFFFF) continue;
                if (value == 0xFF20FFFF) {
                    SnakePart prev = new Snake(xx, yy);
                    this.add(prev);
                    for (int a = 0; a < 16; a++) {
                        prev = new SnakeNeck(xx, yy, prev);
                        this.add(prev);
                    }
                    continue;
                } else if (value == 0xFF21FFFF) {
                    this.add(new AirWizard(xx, yy));

                    continue;
                }
                switch (((value >> 16) & 0xFF)) {
                    case 0xFF: {
                        respX = xx;
                        respY = yy;


                        this.add(new Warper(respX - 30, respY - 30, true));

                        if (level != 0) {
                            this.add(new Warper(respX + 30, respY - 30, false));
                        }

                        //if (level == 0) {

                        this.add(new Guardian(respX + random.nextInt(61) - 30, respY + random.nextInt(61) - 30));
                        this.add(new Guardian(respX + random.nextInt(61) - 30, respY + random.nextInt(61) - 30));
                        this.add(new Guardian(respX + random.nextInt(61) - 30, respY + random.nextInt(61) - 30));

                        //}
                        break;
                    }
                    case 0x40:
                        switch (((value >> 8) & 0xFF)) {
                            case 0x05:
                                add(new Well(xx, yy, this.game.getSpriteCollector()));
                                break;

                        }
                }
            }
        }
    }

    public void trySpawn() {
        for (int i = 0; i < 100; i++) {

            Mob mob = new SlimeFactory();
            if (mob.findStartPos(this)) {
                add(mob);
            }

            /*mob = new Bird();
            if (mob.findStartPos(this)) {
                add(mob);
            }*/
        }

    }

    public void tick() {

        for (int i = 0; i < this.width * this.height / 50; i++) {
            int xt = random.nextInt(this.width);
            int yt = random.nextInt(this.height);
            getTile(xt, yt).tick(this, xt, yt);
        }

        GuiManager.getInstance().tick();

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            int xto = entity.getX() >> 4;
            int yto = entity.getY() >> 4;

            entity.tick();

            if (entity.isRemoved()) {
                if (entity instanceof Mob) mobCount--;
                entities.remove(i--);
                removeEntity(xto, yto, entity);
            } else {
                int xt = entity.getX() >> 4;
                int yt = entity.getY() >> 4;

                if (xto != xt || yto != yt) {
                    removeEntity(xto, yto, entity);
                    insertEntity(xt, yt, entity);
                    if (entity instanceof Player) {
                        fog.clearFog2(xt, yt, ((Player) entity).getClearFogRadius());
                    }
                }
            }
        }
        this.game.getFireParticles().tick();
        tickTime++;
    }

    private void insertEntity(int x, int y, Entity entity) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) return;
        entitiesInTiles[x + y * this.width].add(entity);
    }

    private void removeEntity(int x, int y, Entity entity) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) return;
        entitiesInTiles[x + y * this.width].remove(entity);
    }


    private void sortAndRender(Screen screen, List<Entity> list) {
        Collections.sort(list, spriteSorter);
        for (Entity entity : list) {
            entity.render(screen);

            if (Constants.isDebugMode) {
                int xt = entity.getX() - screen.getXOffset();
                int yt = entity.getY() - screen.getYOffset();
                int xr = entity.getXr();
                int yr = entity.getYr();

                BitmapHelper.drawLine(xt - xr, yt - yr, xt + xr, yt - yr, 0xff00, screen.getViewPort());
                BitmapHelper.drawLine(xt - xr, yt - yr, xt - xr, yt + yr, 0xff00, screen.getViewPort());
                BitmapHelper.drawLine(xt - xr, yt + yr, xt + xr, yt + yr, 0xff00, screen.getViewPort());
                BitmapHelper.drawLine(xt + xr, yt + yr, xt + xr, yt - yr, 0xff00, screen.getViewPort());
            }
        }
    }


    public void renderBackground(Screen screen, int xScroll, int yScroll) {
        int xo = xScroll >> 4;
        int yo = yScroll >> 4;
        int w = (screen.getViewPort().getWidth() + Tile.SIZE - 1) >> 4;
        int h = (screen.getViewPort().getHeight() + Tile.SIZE - 1) >> 4;
        screen.setOffset(xScroll, yScroll);
        for (int y = yo; y <= h + yo; y++) {
            for (int x = xo; x <= w + xo; x++) {
                getTile(x, y).render(screen, this, x, y);

            }
        }
        screen.setOffset(0, 0);
    }

    private List<Entity> rowSprites = new ArrayList<Entity>();

    public void renderSprites(Screen screen, int xScroll, int yScroll) {

        int offScreen = 4;
        int xo = Math.max((xScroll >> 4), 0);
        int yo = Math.max((yScroll >> 4), 0);
        int w = ((screen.getViewPort().getWidth() + Tile.SIZE - 1) >> 4) + offScreen;
        int h = ((screen.getViewPort().getHeight() + Tile.SIZE - 1) >> 4) + offScreen;

        screen.setOffset(xScroll, yScroll);
        for (int y = yo - offScreen; y <= h + yo; y++) {
            for (int x = xo - offScreen; x <= w + xo; x++) {
                if (x < 0 || y < 0 || x >= this.width || y >= this.height) continue;
                // if (fog.getFog(x, y)) continue;
                rowSprites.addAll(entitiesInTiles[x + y * this.width]);
            }
            if (rowSprites.size() > 0) {
                sortAndRender(screen, rowSprites);
            }
            rowSprites.clear();
        }
        this.game.getFireParticles().render(screen);
        screen.setOffset(0, 0);
    }

    public void renderFog(Screen screen, int xScroll, int yScroll) {
        int xo = xScroll >> 4;
        int yo = yScroll >> 4;
        int w = (screen.getViewPort().getWidth() + Tile.SIZE - 1) >> 4;
        int h = (screen.getViewPort().getHeight() + Tile.SIZE - 1) >> 4;
        screen.setOffset(xScroll, yScroll);
        for (int y = yo; y <= h + yo; y++) {
            for (int x = xo; x <= w + xo; x++) {
                fog.render(screen, x, y);
            }
        }
        screen.setOffset(0, 0);
    }

    public void add(Entity entity) {
        if (entity instanceof Player) {
            player = (Player) entity;
            player.setX(respX);
            player.setY(respY);
            fog.clearFog2(player.getX() >> 4, player.getY() >> 4, player.getClearFogRadius());
        }

        entity.setRemoved(false);
        entities.add(entity);
        entity.init(this);

        if (entity instanceof Mob) mobCount++;

        insertEntity(entity.getX() >> 4, entity.getY() >> 4, entity);
    }

    public void remove(Entity e) {
        entities.remove(e);
        int xto = e.getX() >> 4;
        int yto = e.getY() >> 4;
        removeEntity(xto, yto, e);
    }

    public List<Entity> getEntities(int x0, int y0, int x1, int y1, ETeam team) {
        List<Entity> result = new ArrayList<Entity>();
        int xt0 = (x0 >> 4) - 1;
        int yt0 = (y0 >> 4) - 1;
        int xt1 = (x1 >> 4) + 1;
        int yt1 = (y1 >> 4) + 1;
        for (int y = yt0; y <= yt1; y++) {
            for (int x = xt0; x <= xt1; x++) {
                if (x < 0 || y < 0 || x >= this.width || y >= this.height) continue;
                List<Entity> entities = entitiesInTiles[x + y * this.width];
                for (Entity e : entities) {

                    boolean isTeam = team == null || team == e.getTeam();

                    if (isTeam) {
                        if (e.intersects(x0, y0, x1, y1)) result.add(e);
                    }
                }
            }
        }
        return result;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) return Tile.rock;
        return Tile.tiles[tiles[x + y * this.width]];
    }

    public void setTile(int x, int y, Tile tile, int dataVal) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) return;
        tiles[x + y * this.width] = tile.getId();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMobCount() {
        return mobCount;
    }

    public int getMonsterDensity() {
        return Math.max(0, monsterDensity - number);
    }

    public void setMonsterDensity(int monsterDensity) {
        this.monsterDensity = monsterDensity;
    }

    public Fog getFog() {
        return fog;
    }

    public int getNumber() {
        return number;
    }

    public ParticleSystem getFireParticles() {
        return game.getFireParticles();
    }

    public SpriteCollector getSpriteCollector() {
        return game.getSpriteCollector();
    }

    public Game getGame() {
        return game;
    }
}
