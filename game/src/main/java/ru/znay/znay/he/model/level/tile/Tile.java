package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.ground.*;
import ru.znay.znay.he.model.level.tile.liquid.DeepWaterTile;
import ru.znay.znay.he.model.level.tile.liquid.LavaTile;
import ru.znay.znay.he.model.level.tile.liquid.SwampTile;
import ru.znay.znay.he.model.level.tile.liquid.WaterTile;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class Tile {
    public static final int MAX_TILES = 256;
    public static final int SIZE = 16;
    public static final int HALF_SIZE = 8;

    public int lavaMainColor = 500;
    public int grassMainColor = 141;
    public int dirtMainColor = 322;
    public int sandMainColor = 550;
    public int roadMainColor = 431;
    public int waterMainColor = 005;
    public int deepWaterMainColor = 002;

    public int holeMainColor = 111;
    public int swampMainColor = 240;


    public int holeColor = PaletteHelper.getColor(111, holeMainColor, 110, 110);
    public int dirtColor = PaletteHelper.getColor(211, dirtMainColor, 433, 544);
    public int grassColor = PaletteHelper.getColor(030, grassMainColor, 252, 353);
    public int lavaColor = PaletteHelper.getColor(500, lavaMainColor, 520, 550);
    public int sandColor = PaletteHelper.getColor(552, sandMainColor, 440, 440);
    public int roadColor = PaletteHelper.getColor(431, roadMainColor, roadMainColor - 110, 330);
    public int deepWaterColor = PaletteHelper.getColor(004, deepWaterMainColor, 114, 114);
    public int waterColor = PaletteHelper.getColor(005, waterMainColor, 115, 115);
    public int swampColor = PaletteHelper.getColor(-1, swampMainColor, swampMainColor - 110, -1);

    public static final int GRASS_TILE = 0;
    public static final int ROCK_TILE = 1;
    public static final int LAVA_TILE = 2;
    public static final int SAND_TILE = 3;
    public static final int WATER_TILE = 4;
    public static final int HOLE_TILE = 5;
    public static final int ROAD_TILE = 6;
    public static final int SWAMP_TILE = 7;
    public static final int DEEP_WATER_TILE = 8;

    public static Tile[] tiles = new Tile[MAX_TILES];
    public static Tile grass = new GrassTile(0);
    public static Tile rock = new RockTile(1);
    public static Tile lava = new LavaTile(2);
    public static Tile sand = new SandTile(3);
    public static Tile water = new WaterTile(4);
    public static Tile hole = new HoleTile(5);
    public static Tile road = new RoadTile(6);
    public static Tile swamp = new SwampTile(7);
    public static Tile deepWater = new DeepWaterTile(8);
    public static int tickCount = 0;

    public boolean connectsToGrass = false;
    public boolean connectsToSand = false;
    public boolean connectsToLava = false;
    public boolean connectsToSwamp = false;
    public boolean connectsToWater = false;

    protected int slowPeriod = 10;
    protected Random random = new Random();
    protected boolean liquid = false;
    protected byte id;

    public Tile(int id) {
        this.id = (byte) id;
        if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids!");
        tiles[id] = this;

    }

    public void render(Screen screen, Level level, int x, int y) {

    }

    public void tick(Level level, int xt, int yt) {
    }

    public boolean mayPass(Level level, int x, int y, Entity e) {
        return true;
    }

    public void bumpedInto(Level level, int xt, int yt, Entity entity) {
    }

    public void steppedOn(Level level, int xt, int yt, Entity entity) {
        if (!entity.canFly() && entity instanceof Mob) {
            ((Mob) entity).setSlowPeriod(this.slowPeriod);
        }
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public boolean connectsToLiquid() {
        return connectsToWater || connectsToLava || connectsToSwamp;
    }

    public boolean isLiquid() {
        return liquid;
    }
}
