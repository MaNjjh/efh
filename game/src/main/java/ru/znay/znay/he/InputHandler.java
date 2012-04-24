package ru.znay.znay.he;

import ru.znay.znay.he.cfg.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander Tarasov
 * Date: 29.12.11
 * Time: 9:40
 */
public class InputHandler implements KeyListener, MouseMotionListener, MouseListener {

    private static InputHandler inputHandler = null;

    private int xMousePos;
    private int yMousePos;

    private Cursor emptyCursor, defaultCursor;
    private Game game;

    public void mouseDragged(MouseEvent e) {
        xMousePos = e.getX();
        yMousePos = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        xMousePos = e.getX();
        yMousePos = e.getY();
    }

    public Cursor getEmptyCursor() {
        return emptyCursor;
    }

    public Cursor getDefaultCursor() {
        return defaultCursor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouse.toggle(true);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouse.toggle(false);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getXMousePos() {
        return xMousePos / Constants.SCREEN_SCALE + this.game.getXScroll();
    }


    public int getYMousePos() {
        return yMousePos / Constants.SCREEN_SCALE + this.game.getYScroll();
    }


    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        public void tick() {
            if (absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }

    public List<Key> keys = new ArrayList<Key>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key action = new Key();
    public Key mouse = new Key();
    public Key miniMap = new Key();
    public Key inventory = new Key();

    public void releaseAll() {
        for (Key key : keys) {
            key.down = false;
        }
    }

    public void tick() {

        for (Key key : keys) {
            key.tick();
        }

    }

    private InputHandler(Game game) {
        this.game = game;
        game.addKeyListener(this);
        game.addMouseMotionListener(this);
        game.addMouseListener(this);

        emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
        defaultCursor = game.getCursor();
    }

    private InputHandler() {

    }

    public void init(Game game) {
        this.game = game;
        game.addKeyListener(this);
        game.addMouseMotionListener(this);
        game.addMouseListener(this);

        emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
        defaultCursor = game.getCursor();
    }

    public static InputHandler getInstance() {
        if (inputHandler == null)
            inputHandler = new InputHandler();

        return inputHandler;
    }

    public void keyPressed(KeyEvent ke) {
        toggle(ke, true);
    }

    public void keyReleased(KeyEvent ke) {
        toggle(ke, false);
    }

    private void toggle(KeyEvent ke, boolean pressed) {
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD8) up.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD2) down.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD4) left.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD6) right.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_M) miniMap.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_I) inventory.toggle(pressed);

        if (ke.getKeyCode() == KeyEvent.VK_SPACE) action.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) action.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD0) action.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_INSERT) action.toggle(pressed);

        if (ke.getKeyCode() == KeyEvent.VK_C) action.toggle(pressed);
    }

    public void keyTyped(KeyEvent ke) {
    }
}
