package ru.znay.znay.he.lua;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 16.03.12
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class LuaScriptLoader {
    private LuaState luaState;

    public LuaScriptLoader(String fileFullName) {
        try {
            luaState = LuaStateFactory.newLuaState();
            luaState.openLibs();
            File jarFile = new File(LuaScriptLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            luaState.LdoFile(new File(jarFile, "scripts" + File.separator + fileFullName).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeScript() {
        luaState.close();
    }

    public void runScriptFunction(String functionName, Object obj) {
        this.luaState.getGlobal(functionName);
        this.luaState.pushJavaObject(obj);
        this.luaState.call(1, 0);
    }
}
