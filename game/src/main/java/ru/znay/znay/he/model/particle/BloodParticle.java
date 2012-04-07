package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public class BloodParticle extends Particle {

    private int colors;

    private int flip;

    public BloodParticle(int x, int y, int colors) {
        super(x, y);
        this.colors = colors;
        flip = random.nextInt(4);
        this.time = 100 +  random.nextInt(100);

    }

    public void tick() {
        super.tick();
    }

    public void render(Screen screen) {
        screen.render(x, y, 0, 2 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 0, 0, 0), flip);
        screen.render(x, y - (int) (zz) + 1, 0, 2 * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 0, 0, 0), flip);
        screen.render(x, y - (int) (zz), 0, 2 * Tile.HALF_SIZE, colors, flip);
    }

}
