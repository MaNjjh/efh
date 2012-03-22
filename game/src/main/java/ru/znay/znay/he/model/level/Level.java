package ru.znay.znay.he.model.level;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.builds.Mushroom;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.mob.Bird;
import ru.znay.znay.he.model.mob.SlimeFactory;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class Level {

    private Random random = new Random();

    private int width;
    private int height;

    private Player player;

    private byte[] tiles;

    private Fog fog;
    private int monsterDensity = 4;

    private List<Entity>[] entitiesInTiles;

    private List<Entity> entities = new ArrayList<Entity>();


    private Comparator<Entity> spriteSorter = new Comparator<Entity>() {
        public int compare(Entity e0, Entity e1) {
            if (e1.getY() < e0.getY()) return 1;
            if (e1.getY() > e0.getY()) return -1;
            return 0;
        }
    };

    @SuppressWarnings("unchecked")
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new byte[width * height];

        fog = new Fog(width, height);

        entitiesInTiles = new ArrayList[width * height];

        for (int i = 0; i < width * height; i++) {
            entitiesInTiles[i] = new ArrayList<Entity>();
        }
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                setTile(x, y, Tile.grass, 0);
                /*if (random.nextInt(40) == 0) {
                    setTile(x, y, Tile.rock, 0);
                }*/
                if (x < 2 || y < 2 || x > width - 3 || y > height - 3) {
                    setTile(x, y, Tile.lava, 0);
                }
                if (random.nextInt(10)==0) {
                    setTile(x, y, Tile.sand, 0);
                }
                if (random.nextInt(3)==0) {
                    setTile(x, y, Tile.water, 0);
                }
            }
        }


        trySpawn();
    }

    public void trySpawn() {
        for (int i = 0; i < 10; i++) {

            Mob mob = new SlimeFactory();
            if (mob.findStartPos(this)) {
                add(mob);
            }

            mob = new Mushroom();
            if (mob.findStartPos(this)) {
                add(mob);
            }

            mob = new Bird();
            if (mob.findStartPos(this)) {
                add(mob);
            }
        }

    }

    public void tick() {

        for (int i = 0; i < this.width * this.height / 50; i++) {
            int xt = random.nextInt(this.width);
            int yt = random.nextInt(this.height);
            getTile(xt, yt).tick(this, xt, yt);
        }

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            int xto = entity.getX() >> 4;
            int yto = entity.getY() >> 4;

            entity.tick();

            if (entity.isRemoved()) {
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
        int xo = xScroll >> 4;
        int yo = yScroll >> 4;
        int w = (screen.getViewPort().getWidth() + Tile.SIZE - 1) >> 4;
        int h = (screen.getViewPort().getHeight() + Tile.SIZE - 1) >> 4;


        screen.setOffset(xScroll, yScroll);
        for (int y = yo; y <= h + yo; y++) {
            for (int x = xo; x <= w + xo; x++) {
                if (x < 0 || y < 0 || x >= this.width || y >= this.height) continue;
                if (fog.getFog(x, y)) continue;
                rowSprites.addAll(entitiesInTiles[x + y * this.width]);
            }
            if (rowSprites.size() > 0) {
                sortAndRender(screen, rowSprites);
            }
            rowSprites.clear();
        }
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
                fog.render(screen, x - 1, y - 1);
                fog.render(screen, x, y);
            }
        }
        screen.setOffset(0, 0);
    }


    public void add(Entity entity) {
        if (entity instanceof Player) {
            player = (Player) entity;
            fog.clearFog2(player.getX() >> 4, player.getY() >> 4, player.getClearFogRadius());
        }
        entities.add(entity);
        entity.init(this);

        insertEntity(entity.getX() >> 4, entity.getY() >> 4, entity);
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

    public int getMonsterDensity() {
        return monsterDensity;
    }

    public void setMonsterDensity(int monsterDensity) {
        this.monsterDensity = monsterDensity;
    }
}
