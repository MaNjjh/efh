package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.builds.tree.Tree;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 29.03.12
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class GuiMiniMap extends GuiPanel {
    private Level level;
    private Bitmap miniMap;
    private Bitmap resizedMiniMap;
    private int tick = 10;

    public GuiMiniMap(int posX, int posY, Level level) {

        super(posX, posY, (level.getWidth() >> 1) >> 3, (level.getHeight() >> 1) >> 3, PaletteHelper.getColor(-1, 530, 0, 111));
        this.level = level;
        this.miniMap = new Bitmap(level.getWidth(), level.getHeight());
        this.resizedMiniMap = new Bitmap(level.getWidth() >> 1, level.getHeight() >> 1);
    }

    public void markObject(int x, int y, int color) {

        int xx = Math.min(1 + (x >> 4), level.getWidth() - 1);
        int yy = Math.min(0 + (y >> 4), level.getHeight() - 1);

        this.miniMap.getPixels()[xx + yy * this.miniMap.getWidth()] = color;
        xx = Math.min(1 + (x >> 4), level.getWidth() - 1);
        yy = Math.min(1 + (y >> 4), level.getHeight() - 1);

        this.miniMap.getPixels()[xx + yy * this.miniMap.getWidth()] = color;
        xx = Math.min(0 + (x >> 4), level.getWidth() - 1);
        yy = Math.min(1 + (y >> 4), level.getHeight() - 1);

        this.miniMap.getPixels()[xx + yy * this.miniMap.getWidth()] = color;
        xx = Math.min(0 + (x >> 4), level.getWidth() - 1);
        yy = Math.min(0 + (y >> 4), level.getHeight() - 1);

        this.miniMap.getPixels()[xx + yy * this.miniMap.getWidth()] = color;

    }

    public void tick() {
        tick++;
        if (InputHandler.getInstance().miniMap.clicked) {
            visible = !visible;
            changed = true;
        }
        if (tick < 45 || !visible) return;
        tick = 0;
        for (int j = 0; j < level.getHeight(); j++) {
            for (int i = 0; i < level.getWidth(); i++) {
                if (level.getFog() != null && level.getFog().getFog(i, j)) {
                    //if (!true) {
                    this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0;
                } else {
                    switch (level.getTile(i, j).getId()) {
                        case Tile.GRASS_TILE: {
                            this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0xcc00;
                            break;
                        }
                        case Tile.WATER_TILE: {
                            this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0x1ff;
                            break;
                        }
                        case Tile.DEEP_WATER_TILE: {
                            this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0x1cc;
                            break;
                        }
                        case Tile.SAND_TILE: {
                            this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0xffff00;
                            break;
                        }
                        case Tile.ROAD_TILE: {
                            this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0x6B6B6B;
                            break;
                        }
                        default: {
                            this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0xcc00;
                        }
                    }


                    for (Entity entity : level.getEntities((i - 1) << 4, (j - 1) << 4, (i + 1) << 4, (j + 1) << 4, null)) {

                        if (!(entity instanceof Mob) || !(entity instanceof Tree)) {
                            continue;
                        }

                        int color = 0xaabbcc;

                        if (entity instanceof Mob) {
                            color = entity.getTeam() == ETeam.ENEMY_TEAM ? 0xFF0000 : 0xFFCC00;
                        }
                        if (entity instanceof Tree) {
                            color = 0x009900;
                        }

                        markObject(entity.getX(), entity.getY(), color);
                    }

                }
                markObject(level.getPlayer().getX(), level.getPlayer().getY(), 0x1cc);

                /*if (i < 2 || j < 2 || i >= level.getWidth() - 1 || j >= level.getHeight() - 1) {
                    this.miniMap.getPixels()[i + j * this.miniMap.getWidth()] = 0xffcc00;
                }*/
            }
        }

        Graphics2D g = resizedMiniMap.getImage().createGraphics();
        g.drawImage(this.miniMap.getImage(), 0, 0, resizedMiniMap.getWidth(), resizedMiniMap.getHeight(), null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public void render(Screen screen) {
        if (!visible) return;
        super.render(screen);
        BitmapHelper.copy(resizedMiniMap, 0, 0, x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, resizedMiniMap.getWidth(), resizedMiniMap.getHeight(), screen.getViewPort());

    }
}
