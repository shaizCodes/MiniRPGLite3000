package com.isep.rpg;

public class Potion implements Consumable {
    public Potion(int lifePointsBonus, int weaponDamageBonus, int armor, int manaPoints){
        this.lifePointsBonus = lifePointsBonus;
        this.weaponDamageBonus = weaponDamageBonus;
        this.armor = armor;
        this.manaPoints = manaPoints;
    }

    public int lifePointsBonus;
    public int weaponDamageBonus;
    public int armor;
    public int manaPoints;

    public void upgradePotion(int lifePointsBonus, int weaponDamageBonus, int armor, int manaPoints){
        this.lifePointsBonus += lifePointsBonus;
        this.weaponDamageBonus += weaponDamageBonus;
        this.armor += armor;
        this.manaPoints += manaPoints;
    }
}
