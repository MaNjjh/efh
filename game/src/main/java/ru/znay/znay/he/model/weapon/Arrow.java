package ru.znay.znay.he.model.weapon;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class Arrow extends Mob {

    private int damage;
    private int timeLife;
    private ETeam ownerTeam;
    private double betta;

    public Arrow(ETeam ownerTeam, int x, int y, double vx, double vy, int damage) {
        this.ownerTeam = ownerTeam;
        this.x = x;
        this.y = y;
        this.timeLife = 35;
        this.damage = damage;
        this.betta = Math.atan2(vy, vx);
    }

    @Override
    public void tick() {

        int speed = 4;

        double xa = speed;
        double ya = Math.sin(random.nextGaussian());

        double xxa = xa * Math.cos(betta) - ya * Math.sin(betta);
        double yya = ya * Math.cos(betta) + xa * Math.sin(betta);

        if (!move((int) Math.floor(xxa), (int) Math.floor(yya))) {
            removed = true;
        }

        timeLife--;
        if (timeLife < 0) removed = true;
    }

    @Override
    public boolean isFly() {
        return true;
    }

    @Override
    public void render(Screen screen) {
        int color = PaletteHelper.getColor(-1, 555, 333, 111);
        screen.render(Math.toDegrees(betta) + 90, x - 4, y - 5, 0, 8 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, color, 0);
    }

    public int getDamage() {
        return damage;
    }

    public ETeam getOwnerTeam() {
        return ownerTeam;
    }
}

