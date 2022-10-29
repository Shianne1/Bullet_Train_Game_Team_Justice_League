import java.util.ArrayList;
import java.util.Scanner;

public class Item extends Room{
    /*
        Light armor found in Station 1 loot room
        Medium armor found in Station 2 monster room
        Heavy armor found in Station 5 monster room
        Katana found in Station 3 puzzle room

        Pistol found in station 2 loot room within a crate
        Rifle found in station 4 loot & puzzle room within a crate
        Folder 1 found in station 1 Mystery room
        Folder 2 found in station 2 Mystery room
        Folder 3 found in station 3 Mystery room
        Folder 4 found in station 4 Mystery room

        while you are implementing the game, just ask us if you need any help
        Look at pg. 4, 5, 9, 10, 11, 12, 13, 17, 22
     */

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;

    // will access the player's inventory arraylist
    Player inventory;

    // will access the room's inventory arraylist
    Room currentRoom;

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
     * @Method: storeItem()
     * @param item
     * @Function: This method will allow the player to take the item and store it within their inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void storeItem(String item){ // store item is the same as pick up item
        for(int i = 0; i < items.size(); i++){
            if(item.contains(items.get(i).name)){ // if player's input contains the item's name
                if(item.contains("Katana")){ // if player adds katana to their inventory
                    discardItem("knife"); // remove knife from inventory
                }
                else if(item.contains("medium armor")){ // if player adds medium armor to their inventory
                    discardItem("light armor"); // remove light armor from inventory
                }
                else if(item.contains("heavy armor")){ // if player add heavy armor to their inventory
                    discardItem("medium armor"); // remove medium armor from inventory
                }
                inventory.add(item.get(i).name); // add item to player's inventory
                roomItems.remove(item.get(i).name); // remove item from current room
                break;
            }
        }
    }

    /**
     * @Method: inspect()
     * @param item
     * @return itemDescription
     * @Function: This method will allow for the player to see the description of the item
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public String inspect(String item){
        String itemDescription = "";
        for(int i = 0; i < items.size(); i++){
            if(item.contains(items.get(i).name)){ // if player's input contains item's name
                System.out.println(items.get(i).getItemText()); // print out the textual description of the item
                itemDescription = items.get(i).itemDesc; // add the description of the item to the string
                break;
            }
        }
        return itemDescription; // return the string
    }

    /**
     * @Method: discardItem()
     * @param item
     * @Function: This method will allow for the player to remove the item from their inventory
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void discardItem(String item){
        for(int i = 0; i < items.size(); i++){
            if(item.contains(items.get(i).name)){ // if player's input contains item's name
                inventory.remove(item.get(i).name); // remove item from player's inventory
                currentRoom.roomItemAdd(item.get(i).name); // add item  to current room
                break;
            }
        }
    }




}
