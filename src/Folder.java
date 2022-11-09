import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Object: Folder()
 * @Function: This OOP class will help set up the folder objects that is needed from the item.txt
 * when parsing information.
 * This class supers back to the item class
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Folder extends Item implements Serializable {
    // accessing the game console class
    GameConsole game = new GameConsole();


    /*---------------------------------------------Folder Constructors------------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the item parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     *
    public Folder(){
        // an arraylist that will hold the item's data
        items = new ArrayList();
        roomItems = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
    }
    */

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

}
