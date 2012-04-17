package ru.znay.znay.he.model.item;

import ru.znay.znay.he.gfx.model.Screen;

/**
 * ========================================
 * ItCorp v. 1.0 class library
 * ========================================
 * <p/>
 * http://www.it.ru
 * <p/>
 * (C) Copyright 1990-2011, by ItCorp.
 * <p/>
 * --------------------
 * <Filename>.java
 * --------------------
 * Created on 17.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 17.04.12: Original version (WHo)
 * 15:28: Time
 */
public interface ListItem {
    void renderInventory(Screen screen, int x, int y);
}
