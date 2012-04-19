package ru.znay.znay.he.gfx.weather;

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
 * 16:08: Time
 */
public class Weather {
    private boolean isRain = false;
    private boolean isStorm = false;

    public Weather(boolean storm, boolean rain) {
        isStorm = storm;
        isRain = rain;
    }

    public Weather() {
    }

    public boolean isRain() {
        return isRain;
    }

    public void setRain(boolean rain) {
        isRain = rain;
    }

    public boolean isStorm() {
        return isStorm;
    }

    public void setStorm(boolean storm) {
        isStorm = storm;
    }
}
