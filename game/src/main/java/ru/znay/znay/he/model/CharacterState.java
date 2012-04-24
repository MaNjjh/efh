package ru.znay.znay.he.model;

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
 * Created on 24.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 24.04.12: Original version (WHo)
 * 16:48: Time
 */
public class CharacterState {
    private int force = 0;
    private int endurance = 0;
    private int speed = 0;
    private int defense = 0;
    private int regeneration = 0;

    public CharacterState(int defense, int endurance, int force, int regeneration, int speed) {
        this.defense = defense;
        this.endurance = endurance;
        this.force = force;
        this.regeneration = regeneration;
        this.speed = speed;
    }

    public CharacterState() {
    }

    public CharacterState mergeStates(CharacterState characterState) {
        CharacterState result = new CharacterState();
        result.setDefense(characterState.getDefense() + this.defense);
        result.setEndurance(characterState.getEndurance() + this.endurance);
        result.setForce(characterState.getForce() + this.force);
        result.setRegeneration(characterState.getRegeneration() + this.regeneration);
        result.setSpeed(characterState.getSpeed() + this.speed);
        return result;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
