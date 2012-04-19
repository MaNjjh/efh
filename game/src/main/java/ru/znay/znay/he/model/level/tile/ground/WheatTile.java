package ru.znay.znay.he.model.level.tile.ground;

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
 * Created on 18.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 18.04.12: Original version (WHo)
 * 15:46: Time
 */
public class WheatTile extends GrassTile {
    public WheatTile(int id) {
        super(id);
        connectsToGrass = true;
    }


}
