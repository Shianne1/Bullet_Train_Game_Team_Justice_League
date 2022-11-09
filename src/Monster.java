import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @Object: Monster()
 * @Function: This OOP class will help set up the Monster objects that is needed from the monster.txt
 * when parsing information.
 * This class will interact with the player to do combat and gain items. The objects will track variables
 * monster ID, names, descriptions, health, damage, items and drop rates, and the
 * command methods for the monsters as the player interact with them.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Monster implements EntityInterface, Serializable {
    private int monsterId;
    private String monsterName;
    private String monsterDesc;
    private int health;
    private int damage;
    private String itemDrop1;
    private String itemDrop2;
    private double dropRate1;
    private double dropRate2;

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Monster> enemy;
    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;

    Scanner input;


    /*---------------------------------------------Monster Constructors-----------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the monster's parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public Monster(){
        // an arraylist that will hold the monster's data
        enemy = new ArrayList();

        // putting the monster data into the monster arraylist
        game.readMonsters(enemy);

        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
        input = new Scanner(System.in);
    }

    /**
     * @param id
     * @param name
     * @param desc
     * @param HP
     * @param dam
     * @param item1
     * @param item2
     * @param prob1
     * @param prob2
     * @Function: constructor for pre existing data from the Monster text file
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public Monster(int id, String name, String desc, int HP, int dam, String item1, String item2,
                   int prob1, int prob2) {
        this.monsterId = id;
        this.monsterName = name;
        this.monsterDesc = desc;
        this.health = HP;
        this.damage = dam;
        this.itemDrop1 = item1;
        this.itemDrop2 = item2;
        dropRate1 = prob1/100;
        dropRate2 = prob2/100;
    }


    /*-----------------------------------Getters & Setters for Monster variables--------------------------------------*/
    public int getMonsterId() { return monsterId; }

    public String getMonsterName() {
        return monsterName;
    }

    public String getMonsterDesc() {
        return monsterDesc;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) { this.health = health; }

    public int getDamage() { return damage; }

    public double getDropRate1() { return dropRate1; }

    public double getDropRate2() { return dropRate2; }

    public String getItemDrop1() {
        return itemDrop1;
    }

    public String getItemDrop2() {
        return itemDrop2;
    }


    /*----------------------------------Monster Methods for implementing the game-------------------------------------*/
    /**
     * @Method: attackMonster()
     * @param player
     * @Function: when called on, deals damage to player, updates player health and
     * displays damage dealt and current player health
     * @author: Dakota Smith
     * 10/19/2022
     */
    public void attackMonster(Player player, int monsterLocation, Armor armor) {
        //creates delta health variable, sets to current player health, decreases variable by
        //monster damage, then sets player health to the difference

        /*
        int dHealth = player.getCurrentHealth();
        dHealth = dHealth - damage;
        player.setCurrentHealth(dHealth);
        System.out.println("Monster Dealt: " + damage + " Damage.");
        System.out.println("Current HP: " + player.getCurrentHealth());

         */
            for (Monster villains : enemy) {
                if (monsterLocation == this.monsterId)
                {
                    /*
                    System.out.println("If you would like to use your weapon type: [use " + player.getEquippedWeapon().getItemName() + "]");
                    String useItem = input.nextLine();
                    if (useItem.equalsIgnoreCase("use " + player.getEquippedWeapon().getItemName())) {
                        weapon.useWeapon(player, useItem, monsterLocation);
                    }

                    */
                    /*
                    System.out.println("Monster HP Life: " + villains.getHealth());
                    System.out.println(villains.getMonsterName() + " inflicted " + villains.getDamage() + " damage points onto you.");
                    if (player.getEquippedArmor() != null) {
                        int damageAfterArmor = villains.getDamage() - armor.getArmorMod();
                        player.setCurrentHealth(player.getCurrentHealth() - damageAfterArmor);
                    } else {
                        player.setCurrentHealth(player.getCurrentHealth() - villains.getDamage());
                    }
                    System.out.println("Your current HP: " + player.getCurrentHealth());

                    if (villains.getHealth() <= 0) {
                        System.out.println("You have killed the " + villains.getMonsterName());
                        player.setNumOfMonstersKilled(player.getNumOfMonstersKilled() + 1);
                    }
                    */



                    System.out.println("Monster HP Life: " + villains.getHealth());
                    System.out.println(villains.getMonsterName() + " inflicted " + villains.getDamage() + " damage points onto you.");
                    if (player.getEquippedArmor() != null) {
                        int damageAfterArmor = villains.getDamage() - armor.getArmorMod();
                        player.setCurrentHealth(player.getCurrentHealth() - damageAfterArmor);
                    } else {
                        player.setCurrentHealth(player.getCurrentHealth() - villains.getDamage());
                    }
                    System.out.println("Your current HP: " + player.getCurrentHealth());

                    if (villains.getHealth() <= 0) {
                        System.out.println("You have killed the " + villains.getMonsterName());
                        player.setNumOfMonstersKilled(player.getNumOfMonstersKilled() + 1);
                    }

                    break;
                }
            }

    }

    /**
     * @Method: parryMonster()
     * @Function: when called on, determines whether player parries monster or not
     * @author: Dakota Smith
     * 10/19/2022
     */
    public boolean parryMonster(int monsterLocation, Weapon weapon) {
        //generate random number 1-100
        //if number is greater than 50, the monster is parried
        boolean parry = false;
        for(Monster villan : enemy) {
            if(monsterLocation == this.monsterId) {
                Random r = new Random();
                int randomInt = r.nextInt(100) + 1;
                if (randomInt > 50) {
                    System.out.println("You have double your damage towards " + villan.getMonsterName());
                    weapon.setStrength(weapon.getStrength() * 2);
                    villan.setHealth(villan.getHealth() - weapon.getStrength());
                    parry = true;
                }
                else {
                    System.out.println("You inflicted no damage.");
                }
            }
        }
        return parry;
    }

    /**
     * @Method: monsterDrop()
     * @param currentRoom
     * @param
     * @Function: when called on, determines what item, if any, a monster drops into the room
     * @author: Dakota Smith
     * 10/25/2022
     */
    public void monsterDrop(Room currentRoom) {
        //creates empty temporary item, and generates random double 0.0 - 1.0
        Item temp;
        Random r = new Random();
        double probability = r.nextDouble();
        //if probability variable falls within 0 to the first drop rate of item, monster drops first possible item
        if(probability <= this.dropRate1) {
            for(Item a : items) {
                temp = a;
                String itemName = a.getItemName();
                if(itemName.equals(this.itemDrop1)) {
                    currentRoom.roomItemAdd(temp);
                }
            }
        }
        //if probability falls within bounds defined by the first drop rate number, and the sum of both drop rate
        //variables, second item is dropped, otherwise no item is dropped
        else if(probability > this.dropRate1 && probability <= (this.dropRate1 + this.dropRate2)) {
            for(Item a : items) {
                temp = a;
                String itemName = a.getItemName();
                if(itemName.equals(this.itemDrop2)) {
                    currentRoom.roomItemAdd(temp);
                }
            }
        }
    }

    /**
     * @Method: flee()
     * @param player
     * @param roomList
     * @Function: when called on, moves player to previous room.
     * @author: Dakota Smith
     * 11/1/2022
     */
    public void flee(Player player, ArrayList<Room> roomList)
    {
        //creates holder variables
        Room temp = player.getLocation();
        int newLocation;

        //checks if there is any room that player can move to
        //There should only be one room that a monster room connects to.
        if(temp.getNorth() >= 0)
        {
            newLocation = temp.getNorth();
            temp = roomList.get(newLocation);
            player.move(temp);
            System.out.println("You have fallen to or below 10% health, you've run from the monster");
        }
        else if(temp.getEast() >= 0)
        {
            newLocation = temp.getEast();
            temp = roomList.get(newLocation);
            player.move(temp);
            System.out.println("You have fallen to or below 10% health, you've run from the monster");
        }
        else if(temp.getSouth() >= 0)
        {
            newLocation = temp.getSouth();
            temp = roomList.get(newLocation);
            player.move(temp);
            System.out.println("You have fallen to or below 10% health, you've run from the monster");
        }
        else
        {
            newLocation = temp.getWest();
            temp = roomList.get(newLocation);
            player.move(temp);
            System.out.println("You have fallen to or below 10% health, you've run from the monster");
        }
    }

    @Override
    public void healHealth(int healthModifier) {

    }

    @Override
    public void takeDamage(int healthModifier) {

    }

    /**
     * @Method: inspectMonster()
     * @param monsterLocationID
     * @return monsterDescription
     * @Function: This method will allow for the player to see the descriptions of the monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public String inspectMonster(int monsterLocationID ){
        String monsterDescription = "";
        for(Monster monster1: enemy){
            if(monsterLocationID == this.monsterId){
                monsterDescription = monster1.getMonsterDesc(); // will add description to the string
                break;
            }
        }
        return monsterDescription; // return the string
    }
}

