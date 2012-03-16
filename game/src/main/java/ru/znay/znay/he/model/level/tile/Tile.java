package ru.znay.znay.he.model.level.tile;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.level.Level;

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

    public int dirtColor = PaletteHelper.getColor(211, 322, 433, 544);
    public int grassColor = PaletteHelper.getColor(030, 141, 252, 353);
    public int lavaColor = PaletteHelper.getColor(500, 500, 520, 550);

    public static Tile[] tiles = new Tile[MAX_TILES];
    public static Tile glass = new GlassTile(0);
    public static Tile rock = new RockTile(1);
    public static Tile lava = new LavaTile(2);
    public static int tickCount = 0;

    protected byte id;

    public Tile(int id) {
        this.id = (byte) id;
        if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids!");
        tiles[id] = this;

    }

    public void render(Screen screen, Level level, int x, int y) {

    }

    public void tick() {

    }

    public boolean mayPass(Level level, int x, int y, Entity e) {
        return true;
    }

    public void bumpedInto(Level level, int xt, int yt, Entity entity) {
    }

    public void steppedOn(Level level, int xt, int yt, Entity entity) {
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }
}
