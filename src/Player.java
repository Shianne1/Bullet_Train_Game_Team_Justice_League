import java.io.Serializable;
import java.util.ArrayList;


/*
     @Object: Player()
     @Function: this class is in charge of the variables and methods used in reference to the player
     @author(s) Carlton Napier
     @added 10/16/2022
  */
interface playerInterface {

    int maxHealth = 100;

    /**
     * @Object: move()
     * @Function: this class moves the player into the room of the direction sent
     * by the controller
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    void move(Room nextRoom);

    /**
     * @Object: checkInventory()
     * @Function: this method returns a string that relays the inventory of the player
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    String checkInventory();

}

public class Player implements playerInterface, EntityInterface, Serializable {


    private int numOfMonstersKilled;
    private int maxHealth;
    private int currentHealth;
    private String name;
    private Room location;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private ArrayList<Item> inventory;

    private GameState checkpoint;


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
        this.name = "PlayerName";
        this.numOfMonstersKilled = 0;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.equippedWeapon = new Weapon("Fist", 0, "Your fists", "lets you punch enemies", -1, 5); // durability/uses set to -1 since a fist will never break
        this.equippedArmor = new Armor("Clothes", 0, "Your clothes", "basic clothes that provide no protection", 0);
        this.inventory = new ArrayList<>();

    }


    // constructor for preexisting data
    public Player(int maxHealth, int currentHealth, int numOfMonstersKilled, String name, Room location, Weapon equippedWeapon, Armor equippedArmor, ArrayList<Item> inventory, GameState checkpoint) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.numOfMonstersKilled = numOfMonstersKilled;
        this.name = name;
        this.location = location;
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        this.inventory = inventory;
        this.checkpoint = checkpoint;

    }

    // constructor of player with starting stats, user inserted name, and a checkpoint of the start of the game, if they die before reaching one
    public Player(String name, GameState defaultCheckpoint) {
        this.name = name;
        this.numOfMonstersKilled = 0;
        this.maxHealth = 100;
        this.currentHealth = this.maxHealth;
        this.equippedWeapon = new Weapon("Fist", 0, "Your fists", "lets you punch enemies", -1, 5); // durability/uses set to -1 since a fist will never break
        this.equippedArmor = new Armor("Clothes", 0, "Your clothes", "basic clothes that provide no protection", 0);
        this.inventory = new ArrayList<>();
        this.checkpoint = defaultCheckpoint;
    }

    @Override
    public void move(Room nextRoom) {
        this.location = nextRoom;
    }


    /**
     * @Method: checkInventory()
     * @Function: this code returns a formatted string of the player's inventory that will be printed by the view
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    @Override
    public String checkInventory() {
        if (!inventory.isEmpty()) {
            String inventoryList = "The current items in your inventory are: ";
            for (Item item : inventory) {
                inventoryList.concat("[" + item.toString() + "] ");
            }
            return inventoryList;
        } else {
            return "There are no items in your inventory";
        }
    }

    //------------------Player Methods---------------------//

    /**
     * @Method: healHealth()
     * @Function: this code increases the player by the specified amount, capping at the player's max health
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */

    /**
     * @Method: checkStats()
     * @Function: this code returns a formated string of the player's current stats
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public String checkStats() {
        return "Name: " + this.name + "\n" +
                "Health (current/max): " + this.currentHealth + "/" + this.maxHealth + "\n" +
                "Location: " + this.location + "\n" +
                "Equipped Weapon: " + this.equippedWeapon + "\n" +
                "Equipped Armor: " + this.equippedArmor + "\n" +
                "Monsters Killed: " + this.numOfMonstersKilled + "\n";
    }

    @Override
    public void healHealth(int healthModifier) {
        setCurrentHealth(currentHealth + healthModifier);
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;
    }

    /**
     * @Method: takeDamage()
     * @Function: this code decreases the player by the specified amount, flooring at zero
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    @Override
    public void takeDamage(int healthModifier) {
        setCurrentHealth(currentHealth - healthModifier);
        if (currentHealth < 0)
            currentHealth = 0;
    }

    //------------------Getters and Setters---------------------//

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

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }


    /**
     * @Method: setEquippedArmor()
     * @Function: this code removes the equipped weapon from the player's inventory and puts it into the player's weapon slot
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.inventory.remove(equippedWeapon);
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * @Method: removeEquippedWeapon()
     * @Function: this code removes the equipped weapon from the player's weapon slot  and puts it into the player's inventory
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void removeEquippedWeapon() {
        this.inventory.add(this.equippedWeapon);
        this.equippedWeapon = null;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    /**
     * @Method: setEquippedArmor()
     * @Function: this code removes the equipped armor from the player's inventory and puts it into the player's armor slot
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void setEquippedArmor(Armor equippedArmor) {
        this.inventory.remove(equippedArmor);
        this.equippedArmor = equippedArmor;
        this.maxHealth = playerInterface.maxHealth + this.equippedArmor.armorMod;
    }

    /**
     * @Method: removeEquippedArmor()
     * @Function: this code removes the equipped armor from the player's armor slot  and puts it into the player's inventory
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void removeEquippedArmor() {
        this.inventory.add(this.equippedArmor);
        this.equippedArmor = null;
        this.maxHealth = playerInterface.maxHealth - equippedArmor.armorMod;
    }

    public GameState getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(GameState checkpoint) {
        this.checkpoint = checkpoint;
    }


    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }



}
