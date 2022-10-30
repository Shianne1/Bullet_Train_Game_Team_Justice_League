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

    // will access the player's inventory arraylist
    private ArrayList<Item> inventory;

    //Player player = new Player();

    // will access the room's inventory arraylist
    Room currentRoom;
    Item itemObject;

    Scanner input;


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
        //player.checkInventory();
        inventory = new ArrayList<>();
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
    public Item(int id, String name, String desc, String text)
    {
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
    public String inspect() {
        String item = input.nextLine();
        String itemDescription = "";
        for(int i = 0; i < items.size(); i++){
            if(item.contains(items.get(i).getItemName())){ // if player's input contains item's name
                System.out.println(items.get(i).getItemText()); // print out the textual description of the item
                itemDescription = items.get(i).getItemDesc(); // add the description of the item to the string
                break;
            }
        }
        return itemDescription; // return the string
    }

    @Override
    public void use() {

    }

    /**
     * @Method: discardItem()
     * @Function: This method will allow for the player to remove the item from their inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public void discard() {
        String item = input.nextLine();
        for(Item item1: items){
            itemObject = item1;
            if(item.contains(this.itemName)){
                inventory.remove(itemObject);
                currentRoom.roomItemAdd(itemObject);
            }
        }
    }

    /**
     * @Method: storeItem()
     * @param item
     * @Function: This method will allow the player to take the item and store it within their inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void storeItem(String item){ // store item is the same as pick up item
        for(Item item1: items){
            itemObject = item1;
            if(item.contains(this.itemName)){
                if(item.contains("Katana")){ // if player adds katana to their inventory
                    discard(); // remove knife from inventory
                }
                else if(item.contains("medium armor")){ // if player adds medium armor to their inventory
                    discard(); // remove light armor from inventory
                }
                else if(item.contains("heavy armor")){ // if player add heavy armor to their inventory
                    discard(); // remove medium armor from inventory
                }
                inventory.add(itemObject);
                currentRoom.roomItemRemove(itemObject);
            }
        }
    }
}
