package com.isep.rpg;

public class Healer extends SpellCaster {

    public Healer() {
        super(40, 15, 80, 10, 2, 4);
        this.healPower = armor;
        this.maxArmor = armor;
        this.maxWeaponDamage = weaponDamage*2;
    }

    @Override
    public int attack() {
        return weaponDamage;
    }

    @Override
    public int defend() {
        return armor;
    }

    public void heal(Hero hero){
        if(manaPoints==0){return;}
        manaPoints--;
        hero.lifePoints += healPower;
        if(hero.lifePoints > hero.maxLifePoints){
            hero.lifePoints = hero.maxLifePoints;
        }
    }
    @Override
    public void useConsumable(Consumable consumable) {
        super.useConsumable(consumable);
    }
}
