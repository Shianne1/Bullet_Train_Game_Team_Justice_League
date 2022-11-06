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
    private ArrayList<Item> inventory;

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

        inventory = new ArrayList<>();
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


    /*---------------------------------Weapon Methods for implementing the game---------------------------------------*/

    /**
     * @param //item
     * @Method: useItem()
     * @Function: This method will allow the player to use an item to fight a monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public void use() {
        String item = input.nextLine();
        for (Item item1 : items) {
            itemObjectWeapon = item1;
            if (item.contains(super.getItemName())) {
                weaponItem.setDurability(getDurability() - 1);
                System.out.println("You inflicted " + weaponItem.getStrength() + " damage onto the enemy.");
                System.out.println("You have " + weaponItem.getDurability() + " uses left.");
                if (weaponItem.getDurability() == 0) { // if the weapons uses get to 0
                    System.out.println("You can no longer use this weapon");
                    inventory.remove(itemObjectWeapon); // remove item from player's inventory
                    break;
                }
                break;
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
