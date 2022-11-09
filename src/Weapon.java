import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Weapon extends Item implements itemInterface, Serializable {
    private int durability; // the amount of uses the item has
    private int strength; // the amount of damage the weapon deals

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;
    transient Scanner input;
    // will access the player's inventory arraylist
    private ArrayList<Weapon> weaponsInventory;
    private ArrayList<Monster> enemy;


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

    //SHIANNE LESURE 11/7/2022
    public void addingWeapons(Player player){
            for (Item item : player.getInventory()) {
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
    public void equipWeapon(Player player, String item){
        player.getInventory();
        addingWeapons(player);
        String[] parts = item.split(" ");
        for(Weapon weapon : weaponsInventory){
            if(parts[1].equalsIgnoreCase(weapon.getItemName())){
                player.setEquippedWeapon(weapon);
                System.out.println("You have equipped " + weapon.getItemName());
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
                weaponsInventory.remove(weapon);
                break;
            }
        }
    }
}
