package ru.znay.znay.he.model.builds.utensils;

import ru.znay.znay.he.gfx.sprite.SpriteCollector;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 02.05.12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class ContainerFactory {
    private static ContainerFactory instance = new ContainerFactory();

    public static ContainerFactory getInstance() {
        return instance;
    }

    private ContainerFactory() {

    }

    public Container createContainer(String name, List<String> items, boolean destroyOnUse, int x,int y, SpriteCollector spriteCollector) {
        if (name.isEmpty()) return null;

        String className = ContainerFactory.class.getPackage().getName() + "." + name;

        try {
            Container container =  (Container) Class.forName(className).newInstance();
            container.setX(x);
            container.setY(y);
            container.setRemoveAfterUse(destroyOnUse);
            container.setItems(items);
            container.wrapSprite(spriteCollector);
            return container;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
