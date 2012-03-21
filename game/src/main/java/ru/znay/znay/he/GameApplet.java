package ru.znay.znay.he;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */
public class GameApplet extends Applet {
    private static final long serialVersionUID = 1L;

    private Game game = new Game();

    public void init() {
        setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
    }

    public void start() {
        game.start();
    }

    public void stop() {
        game.stop();
    }
}