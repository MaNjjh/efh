package ru.znay.znay.he.lua;

import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

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
        luaState = LuaStateFactory.newLuaState();
        luaState.openLibs();
        luaState.LdoFile(fileFullName);
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
