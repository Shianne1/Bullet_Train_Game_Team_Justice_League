import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Object: item()
 * @Function: This OOP class will help set up the item objects that is needed from the item.txt
 * when parsing information.
 * This class will interact and solve the puzzles to receive items. The puzzle will track
 * variables such as item names, item ID, description, text, and the
 * command methods for the items as the player interact with them.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Item implements itemInterface, Serializable {
    private int itemId;
    private String itemName;
    private String itemDesc;
    private String itemText;

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;

    Item itemObject;

    Scanner input = new Scanner(System.in);


    /*--------------------------------------------Item Constructors---------------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the item parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public Item(){
        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
    }

    /**
     * @param id
     * @param name
     * @param desc
     * @param text
     * @Function: constructor for pre existing data from the item text file
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public Item(int id, String name, String desc, String text) {
        this.itemId = id;
        this.itemName = name;
        this.itemDesc = desc;
        this.itemText = text;
    }


    /*------------------------------------Getters & Setters for Item variables----------------------------------------*/
    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public String getItemText() {
        return itemText;
    }


    /*----------------------------------Item Methods for implementing the game----------------------------------------*/
    /**
     * @Method: inspect()
     * @return itemDescription
     * @Function: This method will allow for the player to see the description of the item
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public String inspect(String item) {
        String[] parts = item.split(" "); // will split the player's input to get the item's name
        String itemDescription = "";
        for(Item inspectItem: items){
            if(parts[1].equalsIgnoreCase(inspectItem.getItemName())){
                itemDescription = inspectItem.getItemDesc(); // will add the description to the string
                break;
            }
        }
        return itemDescription; // return the string
    }

    // SHIANNE LESURE 11/6/2022
    public void getFolderCode(Room current, Player player){
        current.getRoomCode();
        for(Item folderItem: items){
            folderItem.getItemDesc();
            if(folderItem.getItemDesc().contains(current.getRoomCode())){
                player.codeInventoryAdd(current.getRoomCode());
            }
            /*
            if(folderItem.getItemDesc().contains("MONEY")){
                player.codeInventoryAdd("MONEY");
            }

            if(folderItem.getItemDesc().contains("CHEMICALS")){
                player.codeInventoryAdd("CHEMICALS");
            }

            if(folderItem.getItemDesc().contains("BOMBS")){
                player.codeInventoryAdd("BOMBS");
            }

            if(folderItem.getItemDesc().contains("WEAPONS")){
                player.codeInventoryAdd("WEAPONS");
            }

             */
        }
    }


    @Override
    public void use(Player player) {

    }


    /**
     * @Method: discardItem()
     * @param current
     * @param inventory
     * @param item
     * @Function: This method will allow for the player to remove the item from their inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public void discard(String item, Room current, Player inventory) {
        String[] parts = item.split(" "); // will split the player's input to get the item's name
        for(Item item1: items){
            itemObject = item1;
            if(parts[1].equalsIgnoreCase(item1.getItemName())){
                inventory.inventoryRemove(itemObject); // will remove item from player's inventory
                current.roomItemAdd(itemObject); // will drop item into current room
                System.out.println(item1.getItemName() + " has been remove from the inventory.");
                break;
            }
        }
    }

    /**
     * @Method: storeItem()
     * @param item
     * @param current
     * @param inventory
     * @Function: This method will allow the player to take the item and store it within their inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void storeItem(String item, Room current, Player inventory){ // store item is the same as pick up item
        current.roomItemRemove(itemObject);
        String[] parts = item.split(" ");
        for(Item item1: items){
            itemObject = item1;
            if(parts[1].equalsIgnoreCase(itemObject.getItemName())){
                inventory.inventoryAdd(itemObject);
                current.roomItemRemove(itemObject);
                if(item.contains("Katana")){ // if player adds katana to their inventory
                    discard("Knife", current, inventory); // remove knife from inventory
                }
                else if(item.contains("medium armor")){ // if player adds medium armor to their inventory
                    discard("Light Armor", current, inventory); // remove light armor from inventory
                }
                else if(item.contains("heavy armor")){ // if player add heavy armor to their inventory
                    discard("Medium Armor",current, inventory ); // remove medium armor from inventory
                }
                System.out.println(item1.getItemName() + " has been added to the inventory.");
                break;
            }
        }
    }
}
