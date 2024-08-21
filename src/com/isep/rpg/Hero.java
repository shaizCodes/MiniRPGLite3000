package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    public Hero(int lifePoints, int armor, int weaponDamage, int nPotion, int nFood){
        this.lifePoints = lifePoints;
        this.maxLifePoints = lifePoints;
        this.armor = armor;
        this.weaponDamage = weaponDamage;
        this.potions = new ArrayList<>();
        for(int n=0; n<nPotion; n++){
            this.potions.add(new Potion(50, 30, 15, 3));
        }
        this.lembas = new ArrayList<>();
        for(int n=0; n<nFood; n++){
            this.lembas.add(new Food(20, 20));
        }
    }

    public int lifePoints;
    public int maxLifePoints;
    public int armor;
    public int weaponDamage;
    public List<Potion> potions;
    public List<Food> lembas;

    public int healPower;
    public int maxArmor;
    public int maxWeaponDamage;

    public abstract int attack();
    public abstract int defend();
    public abstract void useConsumable(Consumable consumable);

    @Override
    public String toString(){
        return ((this instanceof Warrior ? "Warrior":"Hunter")+"(Hero) <lifePoints: "+lifePoints+", armor: "+armor+", weaponDamage: "+weaponDamage+", nPotions: "+potions.size()+", nLembas: "+lembas.size()+">");
    }
}