import java.util.ArrayList;

public class Weapon extends Item implements equipItemInterface {
    private int durability; // the amount of uses the item has
    private final int strength; // the amount of damage the weapon deals

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;

    // will access the player's inventory arraylist
    Player inventory;


    /*--------------------------------------------Weapon Constructors-------------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the item parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     * @param strength
     */
    public Weapon(int strength){
        this.strength = strength;
        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
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
     * @Method: useItem()
     * @param item
     * @Function: This method will allow the player to use an item to fight a monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void useItem(String item){ // For weapons
        for(int i = 0; i < items.size(); i++){
            if(item.contains(items.get(i).getItemName())){ // if input contains the item name

                // subtract the amount of uses the player can use the weapon
                items.get(i).setDurability(items.get(i).getDurability() - 1);

                // print out to the player how many damage points they did to the enemy
                System.out.println("You inflicted " + items.get(i).getStrength() + " damage onto the enemy.");

                // print out how many uses left the player can use with that item
                System.out.println("You have " + items.get(i).getDurability() + " uses left.");
                if(items.get(i).getDurability() == 0){ // if the weapons uses get to 0
                    System.out.println("You can no longer use this weapon");
                    inventory.remove(items.get(i).getItemName()); // remove item from player's inventory
                    break;
                }
                break;
            }
        }

    }

    @Override
    public void equip() {

    }
}
