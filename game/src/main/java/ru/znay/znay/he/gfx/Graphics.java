package ru.znay.znay.he.gfx;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.weather.Weather;
import ru.znay.znay.he.gfx.weather.WeatherManager;
import ru.znay.znay.he.model.particle.FireParticle;
import ru.znay.znay.he.model.particle.ParticleSystem;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */

public class Graphics extends Canvas {

    protected Screen screen;
    protected SpriteCollector spriteCollector;
    protected ParticleSystem fireParticles;
    protected WeatherManager weatherManager = new WeatherManager();

    private BufferStrategy bufferStrategy;

    public Graphics() {
        this.screen = new Screen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SPRITES_FILE);
        this.spriteCollector = new SpriteCollector(this.screen.getSprites());

        try {
            this.fireParticles = new ParticleSystem(FireParticle.class, 2000, 0.03, -0.01, 40);
        } catch (Exception e) {
            //ignore
        }

    }

    public void prepareGraphics() {
        this.bufferStrategy = getBufferStrategy();
        if (this.bufferStrategy == null) {
            createBufferStrategy(3);
            requestFocus();
            return;
        }

        // BitmapHelper.fill(this.screen.getViewPort(), 0);
    }

    public void render(boolean isGrey) {
        PaletteHelper.getInstance().wrapPaletteColors(this.screen.getViewPort(), isGrey, this.weatherManager.getWeather());

        if (this.bufferStrategy != null) {
            java.awt.Graphics g = bufferStrategy.getDrawGraphics();
            //g.fillRect(0,0, getWidth(), getHeight());
            g.drawImage(this.screen.getViewPort().getImage(), 0, 0, getWidth(), getHeight(), null);
            g.dispose();
            bufferStrategy.show();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public SpriteCollector getSpriteCollector() {
        return spriteCollector;
    }

    public ParticleSystem getFireParticles() {
        return fireParticles;
    }
}
