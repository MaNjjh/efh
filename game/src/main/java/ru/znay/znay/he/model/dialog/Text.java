package ru.znay.znay.he.model.dialog;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 8:56
 * To change this template use File | Settings | File Templates.
 */
public class Text {
    protected String message;
    protected int x;
    protected int y;
    protected int colors;
    protected boolean isRemoved = false;
    protected int timeLife = 100;

    public Text(String message, int x, int y, int colors) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.colors = colors;
    }

    public void tick() {
        if (timeLife <= 0) isRemoved = true;
    }

    public String getOriginalMessage() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemove(boolean remove) {
        isRemoved = remove;
    }
}
