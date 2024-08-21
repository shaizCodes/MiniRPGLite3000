package com.isep.rpg;

public class BasicEnemy extends Enemy {
    public BasicEnemy(){
        /*
          Life Points range: 30 to 50
          Weapon Damage range: 20 to 45
         */
        super((int)(Math.random()*20+30), (int)(Math.random()*25+20));
    }

    @Override
    public int attack() {
        //range: 20 to 45
        return weaponDamage;
    }

    @Override
    public int defend(){
        return 0; // no armor
    }

    @Override
    public String toString() {
        return "BasicEnemy(Enemy) <lifePoints: "+lifePoints+", weaponDamage: "+weaponDamage+">";
    }
}
