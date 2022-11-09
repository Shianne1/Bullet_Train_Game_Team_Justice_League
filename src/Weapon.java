import java.util.ArrayList;
import java.util.Scanner;

public class Weapon extends Item implements equipItemInterface, itemInterface {
    private int durability; // the amount of uses the item has
    private int strength; // the amount of damage the weapon deals

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;
    Scanner input;
    // will access the player's inventory arraylist
    private ArrayList<Weapon> weaponsInventory;
    private ArrayList<Monster> enemy;

    Item itemObjectWeapon;
    Weapon weaponItem;

    /*--------------------------------------------Weapon Constructors-------------------------------------------------*/

    /**
     * @Function: This is a no - arg constructor that will access the item parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public Weapon() {
        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);

        weaponsInventory = new ArrayList<>();
        // an arraylist that will hold the monster's data
        enemy = new ArrayList();

        // putting the monster data into the monster arraylist
        game.readMonsters(enemy);
        input = new Scanner(System.in);
    }

    public Weapon(String name, int id, String itemDesc, String itemText, int durability, int strength) {
        super(id, name, itemDesc, itemText);
        this.durability = durability;
        this.strength = strength;
    }


    /*------------------------------------Getters & Setters for Weapons-----------------------------------------------*/
    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) { this.strength = strength; }

    /*---------------------------------Weapon Methods for implementing the game---------------------------------------*/

    /**
     * @param //item
     * @Method: useItem()
     * @Function: This method will allow the player to use an item to fight a monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public void use(Player player, String item) {
        //String item = input.nextLine();
        String[] parts = item.split(" ");
        for (Item item1 : items) {
            itemObjectWeapon = item1;
            if (parts[1].equalsIgnoreCase(super.getItemName())) {
                weaponItem.setDurability(getDurability() - 1);
                System.out.println("You inflicted " + weaponItem.getStrength() + " damage onto the enemy.");
                System.out.println("You have " + weaponItem.getDurability() + " uses left.");
                if (weaponItem.getDurability() == 0) { // if the weapons uses get to 0
                    System.out.println("You can no longer use this weapon");
                    player.inventoryRemove(itemObjectWeapon);
                    //inventory.remove(itemObjectWeapon); // remove item from player's inventory
                    break;
                }
            }
        }
    }

    //SHIANNE LESURE 11/7/2022
    public void addingWeapons(Player player){
            for (Item item : player.getInventory()) {
                /*
                if (item.getItemName().equalsIgnoreCase(weaponItem.getItemName())) {
                    if((Healing) item){
                        continue;
                    }else {
                        weaponsInventory.add((Weapon)item);
                    }
                   // weaponsInventory.add((Weapon)item);
                }

                 */
                if (item.getItemName().equalsIgnoreCase("knife")) {
                    weaponsInventory.add((Weapon) item);
                }
                if (item.getItemName().equalsIgnoreCase("katana")) {
                    weaponsInventory.add((Weapon) item);
                }
                if (item.getItemName().equalsIgnoreCase("pistol")) {
                    weaponsInventory.add((Weapon) item);
                }
                if (item.getItemName().equalsIgnoreCase("rifle")) {
                    weaponsInventory.add((Weapon) item);
                }
            }
    }

    //SHIANNE LESURE 11/8/2022
    public void useWeapon1(Player player, Weapon weapon, String item){
        String[] parts = item.split(" ");
        if (parts[1].equalsIgnoreCase(weapon.getItemName())) {
            weapon.setDurability(weapon.getDurability() - 1);
            System.out.println("You inflicted " + weapon.getStrength() + " point damage onto the enemy.");
            System.out.println("You have " + weapon.getDurability() + " uses left.");
            if (weapon.getDurability() == 0) {
                System.out.println("You can no longer use this weapon");
                player.inventoryRemove(weapon);
            }
        }
    }

    // SHIANNE LESURE 11/7/2022
    public void useWeapon(Player player, String item, int monsterLocation, ArrayList<Item> itemArrayList){
        addingWeapons(player);
        String[] parts = item.split(" ");
            for (Weapon weapon : weaponsInventory) {
                for(Monster monster : enemy) {
                    if(monsterLocation == monster.getMonsterId()) {
                        if (parts[1].equalsIgnoreCase(weapon.getItemName())) {
                            weapon.setDurability(weapon.getDurability() - 1);
                            System.out.println("You inflicted " + weapon.getStrength() + " point damage onto the enemy.");
                            System.out.println("You have " + weapon.getDurability() + " uses left.");
                            if (weapon.getDurability() == 0) {
                                System.out.println("You can no longer use this weapon");
                                player.inventoryRemove(weapon);
                                break;
                            }
                            break;
                        }
                    }
                }
                break;
            }

    }

    // SHIANNE LESURE 11/7/2022
    public void equipWeapon(Player player, String item, int monsterLocation){
        player.getInventory();
        addingWeapons(player);
        String[] parts = item.split(" ");
        for(Weapon weapon : weaponsInventory){
            if(parts[1].equalsIgnoreCase(weapon.getItemName())){
                player.setEquippedWeapon(weapon);
                System.out.println("You have equipped " + weapon.getItemName());
                /*
                System.out.println("If you would like to use the " + weapon.getItemName() + " type: [use " + weapon.getItemName() + "]");
                String playerAnswer = input.nextLine();
                if(playerAnswer.equalsIgnoreCase("use " + weapon.getItemName())){
                    useWeapon(player, playerAnswer, monsterLocation);
                }

                 */

               // player.inventoryRemove(weapon);
            }
        }
    }

    // SHIANNE LESURE 11/7/2022
    public void unequipWeapon(Player player, String item){
        String[] parts = item.split(" ");
        for(Weapon weapon: weaponsInventory){
            if(parts[1].equalsIgnoreCase(weapon.getItemName())){
                player.removeEquippedWeapon();
                System.out.println("You have unequipped " + weapon.getItemName());
            }
        }
    }

    /**
     * @param //Player
     * @Method: equip()
     * @Function: This method will equip the item calling the method to the player, while unequipping the player's current item
     * @author(s): Carlton Napier
     * @added: 10/31/2022
     */
    @Override
    public void equip(Player player) {


        /*
        if the player has an equipped weapon, the equipped weapon is added to the inventory,
        the weapon trying to be equipped is then set to the player
        the weapon trying to be equipped is then removed from the player's inventory
         */

        if (player.getEquippedWeapon() != null) {
            player.removeEquippedWeapon();
        }

            player.setEquippedWeapon(this);

    }
}
