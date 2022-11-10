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
