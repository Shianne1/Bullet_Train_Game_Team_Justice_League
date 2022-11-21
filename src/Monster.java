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
public class Monster implements  Serializable {
    private int monsterId;
    private String monsterName;
    private String monsterDesc;
    private int health;
    private int damage;
    private String itemDrop1;
    private String itemDrop2;
    private int dropRate1;
    private int dropRate2;

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Monster> enemy;
    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;

    transient Scanner input;

    public static final String ANSI_YELLOW = "\u001b[33;1m";
    public static final String ANSI_RESET= "\u001B[0m";


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
        this.dropRate1 = prob1;
        this.dropRate2 = prob2;
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

    public int getDropRate1() { return dropRate1; }

    public int getDropRate2() { return dropRate2; }

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
     * @param armor
     * @param current
     * @param monsterLocation
     * @param roomArrayList
     * @param weapon
     * @Function: when called on, deals damage to player, updates player health and
     * displays damage dealt and current player health
     * @author: Dakota Smith, Shianne Lesure
     * @added: 10/19/2022
     */
    public void attackMonster(Player player, int monsterLocation, Armor armor, Weapon weapon, Room current, ArrayList<Room> roomArrayList, GameState gameState, View view) {
        //creates delta health variable, sets to current player health, decreases variable by
        //monster damage, then sets player health to the difference
            for (Monster villains : enemy) {
                if (monsterLocation == villains.getMonsterId()) {
                    if(player.getCurrentHealth() <= 20){
                        System.out.println("If you would like to run type: [run]");
                        String running = input.nextLine();
                        if(running.equalsIgnoreCase("run")) {
                            System.out.println("You have ran away from the enemy.");
                            run(player, roomArrayList);
                            break;
                        }
                    }
                    if (villains.getHealth() <= 0) {
                        player.setNumOfMonstersKilled(player.getNumOfMonstersKilled() + 1);
                        if(villains.getMonsterName().equalsIgnoreCase("Mr.Big")){
                            System.out.println(ANSI_YELLOW + "CONGRATULATIONS ASSASSIN! YOU HAVE TO DEFEATED MR. BIG AND COLLECTED THE SUITCASE!");
                            System.out.println("GAME OVER" + ANSI_RESET);
                            GameConsole.endGame(gameState,view);
                            break;
                        }
                        System.out.println("You have killed the " + villains.getMonsterName());
                        monsterDrop(current, monsterLocation);
                        current.setRoomMonster(-1);
                        break;
                    }
                    System.out.println("If you would like to use the " + weapon.getItemName() + " type: [use " + weapon.getItemName() + "]");
                    String playerAnswer = input.nextLine();
                    if(playerAnswer.equalsIgnoreCase("use " + weapon.getItemName())){
                        weapon.useWeapon1(player, weapon, playerAnswer);
                        villains.setHealth(villains.getHealth() - weapon.getStrength());
                    }
                        int damageAfterArmor = villains.getDamage() - armor.getArmorMod();
                        if(damageAfterArmor > 0){
                            player.setCurrentHealth(player.getCurrentHealth() - damageAfterArmor);
                        }
                        else {
                            if(armor.getArmorMod() == 0) {
                                player.setCurrentHealth(player.getCurrentHealth() - villains.getDamage());
                            }
                        }
                        System.out.println("\nMonster HP Life: " + villains.getHealth());
                        System.out.println(villains.getMonsterName() + " inflicted " + villains.getDamage() + " damage points onto you.");
                        System.out.println("Your current HP: " + player.getCurrentHealth());
                    break;
                }
            }
    }

    /**
     * @Method: parryMonster()
     * @param monsterLocation
     * @param weapon
     * @Function: when called on, determines whether player parries monster or not
     * @author: Dakota Smith, Shianne Lesure
     * @added: 10/19/2022
     */
    public boolean parryMonster(int monsterLocation, Weapon weapon) {
        //generate random number 1-100
        //if number is greater than 50, the monster is parried
        boolean parry = false;
        for(Monster villan : enemy) {
            if(monsterLocation == villan.getMonsterId()) {
                Random r = new Random();
                int randomInt = r.nextInt(100) + 1;
                if (randomInt > 50) {
                    System.out.println("You have double your damage towards " + villan.getMonsterName());
                    weapon.setStrength(weapon.getStrength() * 2);
                    villan.setHealth(villan.getHealth() - weapon.getStrength());
                    parry = true;
                    break;
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
     * @param monsterLocation
     * @Function: when called on, determines what item, if any, a monster drops into the room
     * @author: Dakota Smith
     * @added: 10/25/2022
     */
    public void monsterDrop(Room currentRoom, int monsterLocation) {
        for(Monster monster : enemy){
            if(monsterLocation == monster.getMonsterId()){
                Random r = new Random();
                int probability = r.nextInt(100) + 1;
                if(probability <= monster.getDropRate1()){
                    for(Item a : items){
                        if(a.getItemName().equalsIgnoreCase(monster.getItemDrop1())){
                            System.out.println("The enemy had drop " + a.getItemName());
                            currentRoom.roomItemAdd(a);
                            break;
                        }
                    }
                }
                if(probability <= monster.getDropRate2()){
                    for(Item a : items){
                        if(a.getItemName().equalsIgnoreCase(monster.getItemDrop2())){
                            System.out.println("The enemy had drop " + a.getItemName());
                            currentRoom.roomItemAdd(a);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * @Method: removeMonsterItem()
     * @param current
     * @param item
     * @Function: will remove items from monster room
     * @author(s): Shianne Lesure
     * @added: 11/9/2022
     */
    public void removeMonsterItem(Room current, String item){
        String[] parts = item.split(" ");
        for(Item item1 : items){
            if(parts[1].equalsIgnoreCase(item1.getItemName())){
                current.roomItemRemove(item1);
                break;
            }
        }
    }

    /**
     * @Method: run()
     * @param player
     * @param roomList
     * @Function: when called on, moves player to previous room.
     * @author: Dakota Smith, Shianne Lesure
     * @added: 11/1/2022
     */
    public void run(Player player, ArrayList<Room> roomList){
        if(player.getCurrentHealth() <= 20){
            Room newRoom;
            for(Room safeSpot : roomList){
                if(player.getLocation().getRoomId() == safeSpot.getRoomId()) {
                    if (safeSpot.getNorth() >= 0) {
                        newRoom = roomList.get(safeSpot.getNorth());
                        player.move(newRoom);
                        System.out.println("You are in the " + newRoom.getRoomName());
                        break;
                    }

                    if (safeSpot.getEast() >= 0) {
                        newRoom = roomList.get(safeSpot.getEast());
                        player.move(newRoom);
                        System.out.println("You are in the " + newRoom.getRoomName());
                        break;
                    }

                    if (safeSpot.getSouth() >= 0) {
                        newRoom = roomList.get(safeSpot.getSouth());
                        player.move(newRoom);
                        System.out.println("You are in the " + newRoom.getRoomName());
                        break;
                    }

                    if (safeSpot.getWest() >= 0) {
                        newRoom = roomList.get(safeSpot.getWest());
                        player.move(newRoom);
                        System.out.println("You are in the " + newRoom.getRoomName());
                        break;
                    }
                }
            }
        }
    }

    /**
     * @Method: inspectMonster()
     * @param monsterLocationID
     * @return monsterDescription
     * @Function: This method will allow for the player to see the descriptions of the monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void inspectMonster(int monsterLocationID, Player player, Armor armor ){
        for(Monster monster1: enemy){
            if(monsterLocationID == monster1.getMonsterId()){
                System.out.println(monster1.getMonsterDesc() + "\n");// will add description to the string
                System.out.println("\nMonster HP Life: " + monster1.getHealth());
                System.out.println(monster1.getMonsterName() + " inflicted " + monster1.getDamage() + " damage points onto you.");
                if (player.getEquippedArmor() != null) {
                    int damageAfterArmor = monster1.getDamage() - armor.getArmorMod();
                    if(damageAfterArmor > 0){
                        player.setCurrentHealth(player.getCurrentHealth() - damageAfterArmor);
                    }
                } else {
                    player.setCurrentHealth(player.getCurrentHealth() - monster1.getDamage());
                }
                System.out.println("Your current HP: " + player.getCurrentHealth());
                break;
            }
        }
    }
}

