package ru.znay.znay.he.model.mob;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.Inventory;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.resource.ResourceItem;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.particle.FlowText;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 14.03.12
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public class Bird extends Mob {
    private Inventory inventory = new Inventory();
    private int xt;
    private int yt;
    private double angle;
    private int speed;
    private int xTarget;
    private int yTarget;
    private double angleInc;
    private int coinCount = 0;

    public Bird() {
        this.xr = 2;
        this.yr = 2;
        this.yt = 7;
        this.xt = 0;
        this.angle = 0.0;
        this.speed = 1;
        this.xTarget = 0;
        this.yTarget = 0;
        this.angleInc = 10;
    }

    @Override
    public void tick() {
        super.tick();

        if (random.nextInt(10) == 0) {
            this.angleInc = 7 + random.nextInt(10);
        }

        if (random.nextInt(30) == 0) {
            this.xTarget = 5 * Tile.SIZE + random.nextInt((level.getWidth() - 5) * Tile.SIZE);
            this.yTarget = 5 * Tile.SIZE + random.nextInt((level.getHeight() - 5) * Tile.SIZE);
        }

        if (level.getPlayer() != null && !level.getPlayer().isRemoved()) {

            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;

            int vr = viewRadius * Tile.SIZE;

            if (xd * xd + yd * yd <= vr * vr) {
                this.xTarget = level.getPlayer().getX();
                this.yTarget = level.getPlayer().getY();
            }
        }

        int rr = 10 * Tile.HALF_SIZE;

        List<Entity> entities = level.getEntities(x - rr, y - rr, x + rr, y + rr, null);
        for (Entity entity : entities) {
            if (entity instanceof ItemEntity) {
                this.xTarget = entity.getX();
                this.yTarget = entity.getY();
            }
        }

        double testAngle = (angle + angleInc) <= 360 ? angle + angleInc : (angle + angleInc) - 360;
        double testAngle1 = (angle - angleInc) >= 0 ? angle - angleInc : 360 + (angle - angleInc);

        int xx = x + (int) Math.round(Math.cos(Math.toRadians(testAngle))) * speed;
        int yy = y + (int) Math.round(Math.sin(Math.toRadians(testAngle))) * speed;

        int xd = xTarget - xx;
        int yd = yTarget - yy;

        int dist = xd * xd + yd * yd;

        int xx1 = x - (int) Math.round(Math.cos(Math.toRadians(testAngle))) * speed;
        int yy1 = y - (int) Math.round(Math.sin(Math.toRadians(testAngle))) * speed;

        int xd1 = xTarget - xx1;
        int yd1 = yTarget - yy1;

        int dist1 = xd1 * xd1 + yd1 * yd1;

        if (dist < dist1) {
            angle = testAngle;
        } else if (dist > dist1) {
            angle = testAngle1;
        }

        if (System.currentTimeMillis() % 2 == 0) {

            xt = xt < 3 ? xt + 1 : 0;
        }

        int xa = (int) Math.round(Math.cos(Math.toRadians(angle))) * speed;
        int ya = (int) Math.round(Math.sin(Math.toRadians(angle))) * speed;

        move(xa, ya);
    }

    @Override
    public boolean blocks(Entity entity) {
        return false;
    }

    @Override
    public void die() {
        super.die();

        for (Item item : inventory.getItems()) {
            if (item instanceof ResourceItem) {
                ResourceItem resourceItem = (ResourceItem) item;
                for (int i = 0; i < resourceItem.getCount(); i++) {
                    this.level.add(new ItemEntity(new ResourceItem(resourceItem.getResource()), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
                }
            }
        }
    }

    @Override
    public void touchItem(ItemEntity itemEntity) {
        if (itemEntity.isRemoved() || isRemoved()) return;
        itemEntity.take(this);
        inventory.add(itemEntity.getItem());
        level.add(new FlowText("+1", x, y - Tile.HALF_SIZE, Font.yellowColor));
    }


    @Override
    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            entity.hurt(this, 1, dir);
        }

        super.touchedBy(entity);
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public void render(Screen screen) {

        int xo = x - 4;
        int yo = y - 4;

        int col = PaletteHelper.getColor(-1, 0, 421, 532);
        if (hurtTime > 0)
            col = PaletteHelper.getColor(-1, 10, 511, 500);

        screen.render(angle * speed + 90, xo, yo, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col, 0);
    }
}
