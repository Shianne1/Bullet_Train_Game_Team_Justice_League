import java.util.ArrayList;

/**
 * @Object: Folder()
 * @Function: This OOP class will help set up the folder objects that is needed from the item.txt
 * when parsing information.
 * This class supers back to the item class
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Folder extends Item {
    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;


    /*---------------------------------------------Folder Constructors------------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the item parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public Folder(){
        // an arraylist that will hold the item's data
        items = new ArrayList();
        roomItems = new ArrayList();

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
     * @added: 10/17/2022
     */
    public Folder(int id, String name, String desc, String text) {
        super(id, name, desc, text);
    }


    /*----------------------------------Folder Methods for implementing the game--------------------------------------*/
    /**
     * @Method: viewMysteryItem()
     * @param item
     * @Function: This method will allow the player to see the message within the folders
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void viewMysteryItem(String item){
        for(int i = 0; i < items.size(); i++){
            if(item.contains(items.get(i).getItemName())){// if player's input contains the folder's name
                System.out.println(items.get(i).getItemText()); // print out the message within the folder
                break;
            }
        }
    }
}
