package com.isep.rpg;

public class Mage extends SpellCaster {

    public Mage() {
        super(40, 15, 40, 50, 2, 4);
        this.healPower = armor;
        this.maxArmor = armor;
        this.maxWeaponDamage = weaponDamage*2;
    }

    @Override
    public int attack() {
        if(manaPoints == 0){return 0;}
        manaPoints--;
        return weaponDamage;
    }

    @Override
    public int defend() {
        //should better make it defend a hero
        return armor;
    }

    @Override
    public void useConsumable(Consumable consumable) {
        super.useConsumable(consumable);
    }
}