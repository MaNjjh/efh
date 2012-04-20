package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.model.Entity;

import java.util.ArrayList;
import java.util.List;

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
 * Created on 20.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 20.04.12: Original version (WHo)
 * 11:00: Time
 */
public class ParticleSystem extends Entity {
    private List<Particle> particles = new ArrayList<Particle>();
    private int time;

    public ParticleSystem(int x, int y, int count, int color, int time, int speed) {
        this.x = x;
        this.y = y;
        this.time = time;
        for (int i = 0; i < count; i++) {
            Particle particle = new Particle(x, y, color);
            particle.setSpeed(speed);
            this.particles.add(particle);
        }
    }

    public void tick() {

        time--;
        if (time < 0) {
            removed = true;
            return;
        }

        for (Particle particle : particles) {
            particle.tick();
        }
    }


}
