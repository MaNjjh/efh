package ru.znay.znay.he.model.mob;

import ru.znay.znay.he.model.Mob;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 30.04.12
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class MobFactory {
    private static MobFactory instance = new MobFactory();

    public static MobFactory getInstance() {
        return instance;
    }

    private MobFactory() {

    }

    public Mob createMob(String name, boolean boss) {
        if (name.isEmpty()) return null;

        //final StringBuilder result = new StringBuilder(name.length());

        //name = name.toLowerCase();

        //result.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1));

        String className = MobFactory.class.getPackage().getName() + (boss ? ".boss." : "") + "." + name;

        try {
            return (Mob) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
