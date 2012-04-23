package ru.znay.znay.he.model.particle;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private final double[] SIN = new double[360];
    private final double[] COS = new double[360];

    private List<Particle> particles = new ArrayList<Particle>();
    private double wind;
    private double gravity;
    private long lastTime;
    private int delay;


    private Comparator<Particle> particleComparator = new Comparator<Particle>() {
        public int compare(Particle e0, Particle e1) {
            if (e1.getTime() < e0.getTime()) return 1;
            if (e1.getTime() > e0.getTime()) return -1;
            return 0;
        }
    };


    public ParticleSystem(Class<? extends Particle> clazz, int count, double wind, double gravity, int delay) throws IllegalAccessException, InstantiationException {
        this.wind = wind;
        this.gravity = gravity;
        this.delay = delay;
        initSinCos();
        for (int i = 0; i < count; i++) {
            Particle particle = clazz.newInstance();
            particle.setRemoved(true);
            this.particles.add(particle);
        }
    }

    public void createExplosion(int x, int y, double xa, double ya, int count) {
        count = Math.min(particles.size(), count);
        while (count > 0) {
            count--;
            int angle = random.nextInt(360);
            updateDeadParticle(x + random.nextInt(9) - 4, y + random.nextInt(5) - 2, xa + COS[angle], ya + SIN[angle]);
        }
    }

    public void tick() {
        if (System.currentTimeMillis() - lastTime > delay) {
            for (Particle particle : particles) {
                if (particle.isRemoved()) continue;
                particle.tick();

                particle.addXaYa(wind, gravity);
            }
            Collections.sort(particles, particleComparator);
            lastTime = System.currentTimeMillis();
        }
    }

    public void render(Screen screen) {
        for (Particle particle : particles) {
            if (particle.isRemoved()) continue;

            particle.render(screen);
        }
    }

    private void updateDeadParticle(int x, int y, double xa, double ya) {
        for (Particle particle : particles) {
            if (particle.isRemoved()) {
                particle.setRemoved(false);
                particle.initParticle(x, y, xa, ya);
                break;
            }
        }
    }

    private void initSinCos() {
        for (int i = 0; i < 360; i++) {
            COS[i] = Math.cos(Math.toRadians(i));
            SIN[i] = Math.sin(Math.toRadians(i));
        }
    }
}
