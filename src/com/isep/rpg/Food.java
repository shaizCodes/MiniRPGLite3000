package com.isep.rpg;

public class Food implements Consumable {
    public Food(int lifePointsBonus, int weaponDamageBonus){
        this.lifePointsBonus += lifePointsBonus;
        this.weaponDamageBonus += weaponDamageBonus;
    }

    public int lifePointsBonus = 0;
    public int weaponDamageBonus = 0;

    public void upgradeFood(int lifePointsBonus, int weaponDamageBonus){
        this.lifePointsBonus += lifePointsBonus;
        this.weaponDamageBonus += weaponDamageBonus;
    }
}
