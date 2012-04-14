package ru.znay.znay.he.model.npc;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 14.04.12
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class Loony extends NPC {
    public Loony(int x, int y) {
        super(x, y);
    }

    public void tick() {
        super.tick();

        if (level.getPlayer() != null && randomWalkTime == 0) {
            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;
            if (xd * xd + yd * yd < 32 * 32) {
                xa = 0;
                ya = 0;
                if (xd < 0) xa = +1;
                if (xd > 0) xa = -1;
                if (yd < 0) ya = +1;
                if (yd > 0) ya = -1;
            } else if (xd * xd + yd * yd > 80 * 80) {
                xa = 0;
                ya = 0;
                if (xd < 0) xa = -1;
                if (xd > 0) xa = +1;
                if (yd < 0) ya = -1;
                if (yd > 0) ya = +1;
            }
        }

        int speed = (tickTime % 4) == 0 ? 0 : 1;
        if (!move(xa * speed, ya * speed) || random.nextInt(100) == 0) {
            randomWalkTime = 30;
            xa = (random.nextInt(3) - 1);
            ya = (random.nextInt(3) - 1);
        }
    }

}
