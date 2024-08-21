package com.isep.rpg;

import java.util.List;
import java.util.ArrayList;
import com.isep.utils.InputParser;

import static java.lang.Thread.sleep;

public class Game {
    public Game() {
        this.inputParser = new InputParser();
        this.heroes = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    private final List<Hero> heroes;
    private final List<Enemy> enemies;
//    private int playerTurn;
    private final InputParser inputParser;

    //Starts the game
    public void playGame() throws InterruptedException {
        printIntro();
        String user = getUsername().trim(); // in the future, this variable can be useful
        generateCombat();
        beginFight();
    }

    public void generateCombat() {
        inputParser.clearConsole();
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
        inputParser.printMessage("SELECT THE NUMBER OF HEROES IN YOUR TEAM:");
        int nHeroes = inputParser.readInt();
        inputParser.clearConsole();
        //Choosing the heroes for a fight
        generateHeroes(nHeroes);
        generateEnemies(nHeroes);
    }

    //Generates <nHeroes> heroes
    private void generateHeroes(int nHeroes) {
        while (heroes.size() != nHeroes) {
            //Decides how many more heroes can join the squad
            int remainingHeroes = nHeroes-heroes.size();
            inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
            //Decides the class of the hero
            int heroClass = inputParser.readInt("YOU CAN HAVE " + remainingHeroes + " HEROES, SELECT WISELY\n(1). HUNTER\n(2). WARRIOR\n(3). HEALER\n(4). MAGE", 4);
            //Decides the number of that class hero
            int heroClassCount = inputParser.readInt("HOW MANY HEROES OF THIS CLASS DO YOU WANT IN SQUAD?", remainingHeroes);
            if (heroClassCount <= remainingHeroes) {
                //Adds that many class heroes to the user squad
                for (int n = 0; n<heroClassCount; n++) {
                    heroes.add(heroClass == 1 ? new Hunter() : (heroClass == 2 ? new Warrior() : (heroClass == 3 ? new Healer() : new Mage())));
                }
            } else {
                inputParser.printMessage("Please be mindful. Only " + remainingHeroes + " heroes can join your army");
            }
            inputParser.clearConsole();
        }
    }

    //Generates <nEnemies> enemies
    private void generateEnemies(int nEnemies) {
        while (enemies.size() != nEnemies) {
            int enemyClass = (int) (Math.round(Math.random() * 2));
            enemies.add(enemyClass==1 ? new BasicEnemy() : new Boss());
        }
    }

    //Does basic introduction of the game
    private void printIntro() {
        inputParser.clearConsole();
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
        inputParser.printMessage("MINI RPG LITE 3000");
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
        inputParser.pause();
    }

    //Gets Username
    private String getUsername() {
        String username;
        //Runs infinitely until user does not verify his/her name
        do {
            inputParser.clearConsole();
            inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
            inputParser.printMessage("What is your name?");
            username = inputParser.readInput();
            inputParser.clearConsole();
            inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
            if (username.length() < 3) {
                inputParser.printMessage("Your name must contain more than 2 letters");
                inputParser.pause();
                continue;
            }
            if (inputParser.readInt("Your name is " + username + ". Is that correct?\n(1). YES,\n(2). NO,", 2) == 1) {
                break;
            }
            //Flushing console to ignore empty inputs (newline character)
            inputParser.flushConsole();
        } while (true);
        return username;
    }

    //Starts the fight in the battlefield
    private void beginFight() throws InterruptedException {
        int nHeroesAtTheBeginning = heroes.size();
        //Runs infinitely until all heroes do not die
        while (!heroes.isEmpty()) {
            //Runs when all the enemies are defeated
            if (enemies.isEmpty()) {
                inputParser.clearConsole();
                inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
                inputParser.printMessage("Congratulations! You have  defeated your enemies...");
                inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
                inputParser.printMessage("YOUR PLAYER STATS:");
                //prints survivor's stats
                playerStats();
                inputParser.flushConsole();
                inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
                inputParser.pause();
                inputParser.clearConsole();
                inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
                //Awards the survivors
                chooseYourRewards();
                inputParser.clearConsole();
                inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
                inputParser.printMessage("WELCOME TO THE NEXT ROUND");
                //Regenerates enemies for the next fight
                generateEnemies(nHeroesAtTheBeginning);
            }
            //A random hero gets selected against a random enemy
            int nthHero = (int) (Math.round(Math.random()*(heroes.size()-1)));
            int nthEnemy = (int) (Math.round(Math.random()*(enemies.size()-1)));
            Hero hero = heroes.get(nthHero);
            Enemy enemy = enemies.get(nthEnemy);
            //inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
            //Hero takes the first move then enemy responds against a random hero
            playMoves(hero, enemy);
            sleep(1000);
        }
        inputParser.clearConsole();
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
        inputParser.printMessage("Sorry! You were defeated...");
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
    }

    //Prints the statistics of the heroes
    private void playerStats() {
        for (Hero hero : heroes) {
            inputParser.printMessage(hero.toString());
        }
    }

    //Prints a menu of options to reward the enemies
    private void chooseYourRewards() {
        inputParser.printMessage("AWARD YOUR " + heroes.size() + " HEROES FOR THIS SUCCESS:");
        for (int n = 0; n<heroes.size(); n++) {
            Hero hero = heroes.get(n);
            inputParser.printMessage("PLAYER: " + hero);
            int nOptions = 7;
            String rewards = """
                    
                    (1). Increase LifePoints,\
                    
                    (2). Increase Armor,\
                    
                    (3). Increase WeaponDamage,\
                    
                    (4). Increase Potion Effectiveness,\
                    
                    (5). Increase Food Effectiveness,\
                    
                    (6). Increase Number Of Potions,\
                    
                    (7). Increase Number Of Food,""";
            if (hero instanceof Hunter) {
                rewards += "\n(8). Increase the number of Arrows,";
                nOptions++;
            } else if (hero instanceof SpellCaster) {
                rewards += "\n(8). Increase the effectiveness of his spell,";
                nOptions++;
            }
            int chosenOption = inputParser.readInt("CHOOSE THE REWARD:" + rewards, nOptions);
            switch (chosenOption) {
                case 1:
                    hero.lifePoints += 50;
                    break;
                case 2:
                    hero.armor += 25;
                    break;
                case 3:
                    hero.weaponDamage += 25;
                    break;
                case 4:
                    for (int m = 0; m<hero.potions.size(); m++) {
                        hero.potions.get(m).upgradePotion(15, 10, 5, 1);
                    }
                    break;
                case 5:
                    for (int m = 0; m<hero.lembas.size(); m++) {
                        hero.lembas.get(n).upgradeFood(10, 5);
                    }
                    break;
                case 6:
                    hero.potions.add(new Potion(25, 25, 15, 3));
                    break;
                case 7:
                    hero.lembas.add(new Food(15, 10));
                    break;
                case 8:
                    if (hero instanceof Hunter) {
                        ((Hunter) hero).nArrows += 10;
                    } else {
                        hero.useConsumable(new Potion(2, 2, 2, 2));
                    }
                    break;
            }
        }
    }

    //Hero takes a move against the enemy, but the enemy takes a move against a random hero
    public void playMoves(Hero hero, Enemy enemy) throws InterruptedException {
        //System.out.println(hero + " vs " + enemy);
        String heroName = hero instanceof Hunter ? "Hunter" : (hero instanceof Warrior ? "Warrior" : (hero instanceof Mage ? "Mage" : "Healer"));
        String enemyName = enemy instanceof BasicEnemy ? "BasicEnemy" : "Boss";
        String heroActs = heroName+" attacks on "+enemyName+". "+enemyName+" health drains from "+enemy.lifePoints + " to ";
        enemy.lifePoints -= Math.max((hero.attack() - enemy.defend()), 0); // enemy does not have an armor for now
        heroActs += (Math.max(enemy.lifePoints, 0)) + "\n";
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
        inputParser.printMessage(heroActs);
        sleep(inputParser.PAUSE_TIME);
        if(hero instanceof Healer) {
            String newHeroName;
            if(hero.lifePoints < 40){
                ((Healer)hero).heal(hero);
                heroActs = heroName+" heals his/her health to "+(hero.lifePoints)+".\n";
            }
            else {
                Hero newHero = heroes.get((int) (Math.round(Math.random() * (heroes.size() - 1))));
                newHeroName = (newHero instanceof Hunter ? "Hunter" : (newHero instanceof Warrior ? "Warrior" : (newHero instanceof Mage ? "Mage" : "Healer")));
                ((Healer) hero).heal(newHero);
                heroActs = heroName+" heals "+(newHero==hero ? "his" : newHeroName+ "'s")+" health to "+(newHero.lifePoints)+".\n";
            }
            inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
            inputParser.printMessage(heroActs);
            sleep(inputParser.PAUSE_TIME);
        }
        if (hero.lifePoints <= 60) {
            heroActs = heroName+" consumes ";
            if ((int)(Math.round(Math.random())) == 0) {
                heroActs += "Food. ";
            } else {
                heroActs += "Potion. ";
            }
            //Hero takes use of his/her possessions
            if (heroActs.contains(" Food") && !hero.lembas.isEmpty()) {
                hero.useConsumable(new Food(15, 10));
                heroActs += heroName+ "'s health and weaponDamage changes t0 " +hero.lifePoints+" and "+hero.weaponDamage+" respectively.\n";
            } else if (!hero.potions.isEmpty()) {
                hero.useConsumable(new Potion(25, 25, 15, 3));
                heroActs += heroName+ "'s health, armor" +(hero instanceof SpellCaster ? ", manaPoints " : "")+" and weaponDamage changes to "+hero.lifePoints+", "+hero.armor+" "+(hero instanceof SpellCaster ? ((SpellCaster)hero).manaPoints+" " : "")+"and "+hero.weaponDamage+" respectively.\n";
            }
            inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
            inputParser.printMessage(heroActs);
            sleep(inputParser.PAUSE_TIME);
        }
        //Removes the enemy from the list of enemies
        if (enemy.lifePoints <= 0) {
            enemies.remove(enemy);
            return;
        }
        //If the enemy is not dead, the enemy in turn attacks on a random hero
        hero = heroes.get((int)(Math.round(Math.random()*(heroes.size()-1))));
        heroName = (hero instanceof Hunter ? "Hunter" : (hero instanceof Warrior ? "Warrior" : (hero instanceof Mage ? "Mage" : "Healer")));
        heroActs = heroName + " tries defense against " + enemyName + ". ";
        int armorUpdate = hero.defend()-enemy.attack();
        if (armorUpdate <= 0) {
            if(hero.armor <= 0) {
                heroActs += enemyName+" damages armor completely and ";
            }
            hero.armor = 0;
            hero.lifePoints += armorUpdate;
            heroActs += heroName+ "'s health drains to " +(Math.max(hero.lifePoints, 0))+".\n";
            if(hero.lifePoints <= 0){
                heroes.remove(hero);
            }
        } else {
            hero.armor = armorUpdate;
            heroActs += enemyName+" gets "+heroName+ "'s armor health drain to " +hero.armor+".\n";
        }
        inputParser.printSeparator(inputParser.CONSOLE_WIDTH);
        inputParser.printMessage(heroActs);
        sleep(inputParser.PAUSE_TIME);
    }
}