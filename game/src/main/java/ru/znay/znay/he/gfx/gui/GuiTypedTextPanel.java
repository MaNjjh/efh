package ru.znay.znay.he.gfx.gui;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 27.03.12
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class GuiTypedTextPanel extends GuiTextPanel {
    private int position;
    private int period;
    private long time;
    private int timeLife;

    public GuiTypedTextPanel(String mes, int posX, int posY, int colorText, int colorPanel, int period) {
        super(mes, posX, posY, colorText, colorPanel);
        init(period);
    }

    public GuiTypedTextPanel(String mes, int posX, int posY, int colorText, int period) {
        super(mes, posX, posY, colorText);
        init(period);
    }

    public GuiTypedTextPanel(String mes, int posX, int posY, int period) {
        super(mes, posX, posY);
        init(period);
    }


    private void init(int period) {
        this.position = 1;
        this.period = period;
        this.time = System.currentTimeMillis();
        this.timeLife = 100;
        setText(originalMessage.substring(0, this.position));
    }

    @Override
    public void tick() {
        if (closed || !visible) return;

        if (System.currentTimeMillis() - this.time > this.period) {

            this.position = Math.min(originalMessage.length(), this.position + 1);

            setText(originalMessage.substring(0, this.position));

            this.time = System.currentTimeMillis();
        }

        if (this.position == originalMessage.length()) {
            timeLife--;
        }
        if (timeLife <= 0) close();
    }
}
