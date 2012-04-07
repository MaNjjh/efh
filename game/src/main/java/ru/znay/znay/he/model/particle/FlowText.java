package ru.znay.znay.he.model.particle;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 08.04.12
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
public class FlowText extends TextParticle {


    public FlowText(String msg, int x, int y, int col) {
        super(msg, x, y, col);
        zz = 0;
        time = 30;
    }

    @Override
    public void tick() {
        super.tick();
        y--;
    }
}
