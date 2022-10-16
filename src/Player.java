import java.io.Serializable;
import java.util.ArrayList;


interface playerInterface {

    void move(String dir);

    void pickUp(Item item);

    void dropItem(Item item);

    String checkInventory();

}

public class Player implements playerInterface, EntityInterface, Serializable {

    int health;
    String name;
    Room location;
    Item equippedWeapon;
    Item equippedArmor;
    ArrayList<Item> inventory;
    GameData checkpoint;


    boolean hasCheckPoint;

      /*
    Starting Stats:
    HP – 100
    Damage – (Fists) Deals 5 HP a hit
    Empty Inventory


        Make sure to look at pg 4 & 5 for inventory & death
        Look at pg 4, 5, 13, 14, 15, 16, 17
        while you are implementing the game, just ask us if you need any help

     */

    // empty constructor of player with default values
    public Player() {
        this.name = "Player Name";
        this.health = 100;
        this.equippedWeapon = new Weapon("Fist", -1, 5); // durability/uses set to -1 since a fist will never break
        this.inventory = new ArrayList<>();
    }

    // constructor for preexisting data
    public Player(int health, String name, Room location, Item equippedWeapon, Item equippedArmor, ArrayList<Item> inventory, GameData checkpoint) {
        this.health = health;
        this.name = name;
        this.location = location;
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        this.inventory = inventory;
        this.checkpoint = checkpoint;

    }

    // constructor of player with starting stats, user inserted name, and a checkpoint of the start of the game, if they die before reaching one
    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.equippedWeapon = new Weapon("Fist", -1, 5); // durability/uses set to -1 since a fist will never break
        this.inventory = new ArrayList<>();
    }

    @Override
    public void move(String dir) {

    }

    @Override
    public void pickUp(Item item) {
        try {
            this.getLocation().getItemsInRoom().remove(item);
            inventory.add(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropItem(Item item) {
        try {
            inventory.remove(item);
            this.getLocation().getItemsInRoom().add(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String checkInventory() {
        if (!inventory.isEmpty()) {
            String inventoryList = "The current items in your inventory are: ";
            for (Item item : inventory) {
                inventoryList.concat("[" + item.name + "] ");
            }
            return inventoryList;
        } else {
            return "There are no items in your inventory";
        }
    }

    @Override
    public void healHealth(int healthModifier) {
        setHealth(health + healthModifier);

        if(health > 100)
            health = 100;

    }

    @Override
    public void takeDamage(int healthModifier) {
        setHealth(health - healthModifier);


        if(health < 0)
            health = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Item equippedItem) {
        this.equippedArmor = equippedItem;
    }

    public GameData getCheckpoint() {
        if (hasCheckPoint)
            return checkpoint;

        else
            return null;
    }

    public void setCheckpoint(GameData checkpoint) {
        this.checkpoint = checkpoint;
        setHasCheckPoint(true);
    }

    public boolean HasCheckPoint() {
        return hasCheckPoint;
    }

    public void setHasCheckPoint(boolean hasCheckPoint) {
        this.hasCheckPoint = hasCheckPoint;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
