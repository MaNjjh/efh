package ru.znay.znay.he.model.weapon;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Mob;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class Arrow extends Mob {

    private int vx;
    private int vy;
    private int damage;
    private int timeLife;
    private ETeam ownerTeam;

    public Arrow(ETeam ownerTeam, int x, int y, int vx, int vy, int damage) {
        this.ownerTeam = ownerTeam;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.timeLife = 35;
        this.damage = damage;
    }

    @Override
    public void tick() {

        int speed = 4;

        double xa = speed;
        double ya = Math.sin(random.nextGaussian());

        double betta = Math.atan2(vy, vx);

        double xxa = xa * Math.cos(betta) - ya * Math.sin(betta);
        double yya = ya * Math.cos(betta) + xa * Math.sin(betta);

        if (!move((int) Math.round(xxa), (int) Math.round(yya))) {
            removed = true;
        }


        timeLife--;
        if (timeLife < 0) removed = true;
    }

    @Override
    public void render(Screen screen) {
        int color = PaletteHelper.getColor(-1, 040, 330, 535);
        screen.render(x - 4, y - 5, 0, 8, 8, 8, color, 0);
    }

    public int getDamage() {
        return damage;
    }

    public ETeam getOwnerTeam() {
        return ownerTeam;
    }
}

