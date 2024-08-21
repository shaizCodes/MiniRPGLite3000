package com.isep.rpg;

public abstract class Enemy {
    public Enemy(int lifePoints, int weaponDamage){
        this.lifePoints = lifePoints;
        this.weaponDamage = weaponDamage;
    }
    public int lifePoints;
    public int weaponDamage;
    public abstract int attack();
    public abstract int defend();
}
