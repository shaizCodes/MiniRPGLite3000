package com.isep.rpg;

public abstract class SpellCaster extends Hero {
    public SpellCaster(int lifePoints, int manaPoints, int armor, int weaponDamage, int nPotion, int nFood) {
        super(lifePoints, armor, weaponDamage, nPotion, nFood);
        this.manaPoints = manaPoints;
    }

    public int manaPoints;

    @Override
    public void useConsumable(Consumable consumable) {
        if (consumable instanceof Potion) {
            if (potions.isEmpty()) {
                System.out.println("Have no potion to consume");
                return;
            }
            this.manaPoints += 3;
            //so that armor health ranges between 0 and 30
            armor += (armor <= maxArmor-((Potion)consumable).armor ? ((Potion)consumable).armor : 0);
            //so that hero health remains between 1 and 80
            lifePoints += (lifePoints <= maxLifePoints-((Potion)consumable).lifePointsBonus ? ((Potion)consumable).lifePointsBonus : 0);
            //so that hero weaponDamage ranges between 0 and 30
            weaponDamage += (weaponDamage<=maxWeaponDamage-((Potion)consumable).weaponDamageBonus ? ((Potion)consumable).weaponDamageBonus : 0);
            this.potions.remove(potions.size() - 1);
        } else {
            if (lifePoints < 100) {
                if (lembas.isEmpty()) {
                    System.out.println("Have no food to consume");
                    return;
                }
                lifePoints += (lifePoints <= maxLifePoints-((Food)consumable).lifePointsBonus ? ((Food)consumable).lifePointsBonus : 0);
                weaponDamage += (weaponDamage<=maxWeaponDamage-((Food)consumable).weaponDamageBonus ? ((Food)consumable).weaponDamageBonus : 0);
                this.lembas.remove(lembas.size() - 1);
            }
        }
    }

    @Override
    public String toString(){
        return ((this instanceof Mage ? "Mage" : "Healer")+"(Hero) <lifePoints: "+lifePoints+", manaPoints: "+manaPoints+", armor: "+armor+", weaponDamage: "+weaponDamage+", nPotions: "+potions.size()+", nLembas: "+lembas.size()+">");
    }
}
