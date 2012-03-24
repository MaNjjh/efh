package ru.znay.znay.he.model.dialog;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 8:45
 * To change this template use File | Settings | File Templates.
 */
public class TextTyped extends Text {
    private int position;
    private int period;
    private long time;

    public TextTyped(String message, int x, int y, int colors, int period) {
        super(message, x, y, colors);
        this.position = 0;
        this.period = period;
        this.time = System.currentTimeMillis();
    }

    public void reset() {
        this.time = System.currentTimeMillis();
        this.position = 0;
        this.timeLife = 100;
        this.isRemoved = false;
    }

    public void tick() {
        if (System.currentTimeMillis() - this.time > this.period) {

            this.position = Math.min(message.length(), this.position + 1);

            this.time = System.currentTimeMillis();
        }

        if (this.position == message.length()) {
            timeLife--;
        }
        super.tick();
    }

    @Override
    public String getMessage() {
        return this.message.substring(0, this.position);
    }
}
