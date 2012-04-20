package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Screen;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public class BloodParticle extends Particle {


    public BloodParticle(int x, int y, int color) {
        super(x, y, color);

        this.time = 100 + random.nextInt(300);

    }

    public void tick() {
        super.tick();
        if (time < 100)
            if (time % 10 == 0) {
                int rr = (this.color >> 16) & 0xff;
                int gg = (this.color >> 8) & 0xff;
                int bb = this.color & 0xff;

                this.color = ((rr >> 2) << 16) | ((gg >> 2) << 8) | (bb >> 2);
            }

    }
}
