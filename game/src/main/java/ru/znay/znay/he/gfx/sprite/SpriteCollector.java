package ru.znay.znay.he.gfx.sprite;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ASTarasov
 * Date: 02.04.12
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public class SpriteCollector {
    private List<SpriteWrapper> spriteWrappers = new ArrayList<SpriteWrapper>();
    private Map<String, Bitmap> sprites = new HashMap<String, Bitmap>();
    private int width = 0;
    private int height = 0;
    private Bitmap source;

    public SpriteCollector(Bitmap source) {
        this.source = source;
    }

    public void resetWrappers() {
        this.spriteWrappers.clear();
    }

    public void addWrapper(SpriteWrapper spriteWrapper) {
        this.width = Math.max(spriteWrapper.getWidth(), this.width);
        this.height = Math.max(spriteWrapper.getHeight(), this.height);
        this.spriteWrappers.add(spriteWrapper);
    }

    public Bitmap mergedWrappers(String name, int scale, int bits, boolean drawAura) {
        Bitmap result = this.sprites.get(name);
        if (result != null) {
            return result;
        }

        if (this.spriteWrappers.size() == 0) {
            return null;
        }

        result = new Bitmap(this.width * scale, this.height * scale);
        BitmapHelper.fill(result, 0xFF00FF);


        for (SpriteWrapper spriteWrapper : this.spriteWrappers) {
            BitmapHelper.scaleDraw(this.source, scale, 0, 0, spriteWrapper.getXo(), spriteWrapper.getYo(), spriteWrapper.getWidth(), spriteWrapper.getHeight(), spriteWrapper.getColors(), bits, result);
        }

        if (drawAura) {
            BitmapHelper.drawAura(result, 0xFF00FF, 0);
        }

        this.sprites.put(name, result);

        return result;
    }
}
