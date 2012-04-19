package ru.znay.znay.he.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final Sound backMusic = new Sound("/sound/111.mid");
    public static final Sound pickup = new Sound("/sound/pickup.wav");
    public static final Sound playerHurt = new Sound("/sound/playerhurt.wav");
    public static final Sound monsterHurt = new Sound("/sound/monsterhurt.wav");

    private AudioClip clip;

    public void init() {

    }

    private Sound(String name) {
        try {
            clip = Applet.newAudioClip(Sound.class.getResource(name));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            new Thread() {
                public void run() {
                    clip.play();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        try {
            new Thread() {
                public void run() {
                    clip.loop();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            new Thread() {
                public void run() {
                    clip.stop();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}