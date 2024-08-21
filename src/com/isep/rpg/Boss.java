package com.isep.rpg;

public class Boss extends Enemy {
    public Boss(){
        /*
          Life Points range: 150 to 250
          Weapon Damage range: 50 to 100
         */
        super((int)(Math.random()*100+150), (int)(Math.random()*50+50));
    }

    @Override
    public int attack() {
        //range: 50 to 100
        return weaponDamage;
    }

    @Override
    public int defend() {
        return 0; // no armor
    }

    @Override
    public String toString(){
        return "Boss(Enemy) <lifePoints: "+lifePoints+", weaponDamage: "+weaponDamage+">";
    }
}
