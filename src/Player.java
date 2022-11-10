import java.io.Serializable;
import java.util.ArrayList;

/**
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
    String checkInventory(Player player, ArrayList<Item> items);

    /**
     * @Object: checkCodeInventory
     * @Function: This method return a the relays the inventory of codes to the player
     * @author(s): Shianne Lesure
     * @added 11/3/2022
     */
    String checkCodeInventory();
}

public class Player implements playerInterface, EntityInterface, Serializable {
    private int numOfMonstersKilled;
    private int maxHealth;
    private int currentHealth;
    private String name;
    private Room location;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    private ArrayList<Item> inventory;
    private ArrayList<String> codeInventory;

    private GameState checkpoint;

    boolean hasCheckPoint;

    Weapon defaultWeapon = new Weapon("Fist", 0, "Your fists", "lets you punch enemies", -1, 5);


    /*---------------------------------------------Player Constructors------------------------------------------------*/
    /**
     * @Function: empty constructor of player with default values
     * @author(s): Carlton Napier
     * @added: 10/16/2022
     */
    public Player() {
        this.name = "PlayerName";
        this.numOfMonstersKilled = 0;
        this.maxHealth = 300;
        this.currentHealth = 100;
        this.equippedWeapon = defaultWeapon; // durability/uses set to -1 since a fist will never break
        this.equippedArmor = new Armor("Clothes", 0, "Your clothes", "basic clothes that provide no protection", 0);
        this.inventory = new ArrayList<>();
        this.codeInventory = new ArrayList<>();

    }

    /**
     *
     * @param maxHealth
     * @param currentHealth
     * @param numOfMonstersKilled
     * @param name
     * @param location
     * @param equippedWeapon
     * @param equippedArmor
     * @param inventory
     * @param codeInventory
     * @param checkpoint
     * @Function: constructor for preexisting data
     * @author(s): Carlton Napier
     * @added: 10/16/2022
     */
    public Player(int maxHealth, int currentHealth, int numOfMonstersKilled, String name, Room location, Weapon equippedWeapon,
                  Armor equippedArmor, ArrayList<Item> inventory, ArrayList<String> codeInventory, GameState checkpoint) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.numOfMonstersKilled = numOfMonstersKilled;
        this.name = name;
        this.location = location;
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        this.inventory = inventory;
        this.codeInventory = codeInventory;
        this.checkpoint = checkpoint;

    }

    /**
     *
     * @param name
     * @param defaultCheckpoint
     * @function: constructor of player with starting stats, user inserted name, and a checkpoint of the start of the game, if they die before reaching one
     * @author(s): Carlton Napier
     * @added: 10/16/2022
     */
    public Player(String name, GameState defaultCheckpoint) {
        this.name = name;
        this.numOfMonstersKilled = 0;
        this.maxHealth = 300;
        this.currentHealth = 100;
        this.equippedWeapon = defaultWeapon; // durability/uses set to -1 since a fist will never break
        this.equippedArmor = new Armor("Clothes", 0, "Your clothes", "basic clothes that provide no protection", 0);
        this.inventory = new ArrayList<>();
        this.codeInventory = new ArrayList<>();
        this.checkpoint = defaultCheckpoint;
    }


    /*-----------------------------------Getters & Setters for Player variables---------------------------------------*/
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Room getLocation() { return location; }

    public void setLocation(Room location) { this.location = location; }

    public Weapon getEquippedWeapon() { return equippedWeapon; }


    /**
     * @Method: setEquippedArmor()
     * @Function: this code removes the equipped weapon from the player's inventory and puts it into the player's weapon slot
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * @Method: removeEquippedWeapon()
     * @Function: this code removes the equipped weapon from the player's weapon slot  and puts it into the player's inventory
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void removeEquippedWeapon() {
        this.equippedWeapon = defaultWeapon; // I had to change this from null because I was getting an nuller exception
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
        this.equippedArmor = equippedArmor;
        this.currentHealth = this.currentHealth + this.equippedArmor.getArmorMod();
    }

    /**
     * @Method: removeEquippedArmor()
     * @Function: this code removes the equipped armor from the player's armor slot  and puts it into the player's inventory
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    public void removeEquippedArmor() {
        this.currentHealth = this.currentHealth - this.equippedArmor.getArmorMod();
        this.equippedArmor = null;
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
        if(currentHealth > 300){
            this.currentHealth = 300; // I added this because the player can't go past 300
        }
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() { return maxHealth; }

    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    public int getNumOfMonstersKilled() {
        return numOfMonstersKilled;
    }

    public void setNumOfMonstersKilled(int numOfMonstersKilled) {
        this.numOfMonstersKilled = numOfMonstersKilled;
    }

    /*--------------------------------Interface Methods for implementing the game-------------------------------------*/
    @Override
    public void move(Room nextRoom) { this.location = nextRoom; }

    /**
     * @Method: checkInventory()
     * @Function: this code returns a formatted string of the player's inventory that will be printed by the view
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    @Override
    public String checkInventory(Player player, ArrayList<Item> items ) {
        if (!inventory.isEmpty()) {
            String inventoryList = "The current items in your inventory are: ";
            for (Item item : inventory) {
                if(!inventory.contains("bandage")){
                    inventoryList += "[" + item.getItemName() + "] ";
                }
            }
            return inventoryList;
        }
        else {
            for(Item healers : items){
                if(!inventory.contains("bandage")){
                    if(healers.getItemName().equalsIgnoreCase("bandage")){
                        player.inventoryAdd(healers);
                        break;
                    }
                }
            }
            return "You only have a bandage within your inventory";
        }
    }

    /**
     * @Method: checkCodeInventory()
     * @return codeInventoryList
     * @Function: this method will print out the codes the player gather throughout the game
     * @author(s): Shianne Lesure
     * @added: 11/3/2022
     */
    @Override
    public String checkCodeInventory() {
        if(!codeInventory.isEmpty()){ // if inventory is not empty
            String codeInventoryList = "The codes in your inventory are: ";
            for(String codes: codeInventory){
                codeInventoryList += "[" + codes + "] "; // will add the codes to the string
            }
            return codeInventoryList;
        } else {
            return "There are no codes within the inventory"; // if player doesn't have any codes
        }
    }


    /*----------------------------------Player Methods for implementing the game--------------------------------------*/
    /**
     * @Method: viewCode()
     * @Function: this method will print out the most recent code the player gathered
     * @author(s): Shianne Lesure
     * @added: 11/5/2022
     */
    public void viewCode(){
        if(codeInventory.size() == 1){ // if inventory has only one code
            System.out.println("Recent code: [" + codeInventory.get(0) + "]");
        }
        else if(!codeInventory.isEmpty()){ // if inventory is not empty
            String recentCode = codeInventory.get(codeInventory.size() - 1);
            System.out.println("Recent code: [" + recentCode + "]"); // print out the last element
        }
        else{
            System.out.println("There is no code to view.");
        }
    }

    /**
     * @Method: healHealth()
     * @Function: this code increases the player by the specified amount, capping at the player's max health
     * @author(s) Carlton Napier
     * @added 10/18/2022
     */
    @Override
    public void healHealth(int healthModifier) {
        setCurrentHealth(currentHealth + healthModifier);
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;
    }

    /**
     * @Method: checkStats()
     * @Function: this code returns a formated string of the player's current stats
     * @author(s) Carlton Napier, Shianne Lesure
     * @added 10/18/2022
     */
    public String checkStatsPlayer(Player player){
        player.getLocation().getRoomName();
        return "Name: " + player.getName() + "\n" +
                "Health [current / max]: " + player.getCurrentHealth() + " / " + player.getMaxHealth() + "\n" +
                "Location: " + player.getLocation().getRoomName() + "\n" +
                "Equipped Weapon: " + player.getEquippedWeapon().getItemName() + "\n" +
                "Equipped Armor: " + player.getEquippedArmor().getItemName() + "\n" +
                "Monster Killed: " + player.getNumOfMonstersKilled() + "\n";
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

    /**
     * @Method: inventoryAdd()
     * @param item
     * @Function: this method will add the items to the inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void inventoryAdd(Item item){ inventory.add(item); }

    /**
     * @Method: inventoryRemove()
     * @param item
     * @Function: this method will remove the items from the inventory
     * @author(s): Shianne Lesure
     */
    public void inventoryRemove(Item item){ inventory.remove(item); }

    /**
     * @Method: codeInventoryAdd()
     * @param codes
     * @Function: this method will add the codes to the inventory
     * @author(s): Shianne Lesure
     * @added: 11/3/2022
     */
    public void codeInventoryAdd(String codes){ codeInventory.add(codes); }
}
