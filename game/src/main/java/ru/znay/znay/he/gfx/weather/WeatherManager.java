package ru.znay.znay.he.gfx.weather;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.Level;

import java.util.Random;

/**
 * ========================================
 * ItCorp v. 1.0 class library
 * ========================================
 * <p/>
 * http://www.it.ru
 * <p/>
 * (C) Copyright 1990-2011, by ItCorp.
 * <p/>
 * --------------------
 * <Filename>.java
 * --------------------
 * Created on 18.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 18.04.12: Original version (WHo)
 * 16:11: Time
 */
public class WeatherManager {
    private Weather currentWeather = new Weather();
    private int rainTime = 0;
    private int speed = 2;
    private Random random = new Random();

    public void tick(Level level) {

        currentWeather.setStorm(false);

        currentWeather.setRain(false);

        if (rainTime > 0) {
            rainTime--;
            if (random.nextInt(500) == 0 && speed < 4) {
                speed++;
            }
            currentWeather.setRain(true);
            if (rainTime > 100) {
                level.add(new Rain(level.getPlayer(), -0.5, 1, speed));
            }
        }

        if (random.nextInt(10000) == 0 && rainTime <= 0) {
            speed = 2;
            rainTime = 5000;
        }

        if (currentWeather.isRain() && rainTime < 4000 && rainTime > 100 && random.nextInt(20) == 0) {

            currentWeather.setStorm(true);
        }
    }

    public void render(Screen screen) {

    }

    public Weather getWeather() {
        return currentWeather;
    }

}
