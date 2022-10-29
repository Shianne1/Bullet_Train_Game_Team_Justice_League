import java.util.ArrayList;

/**
 * @Object: Crate()
 * @Function: This OOP class will help set up the crate objects that is needed from the crate.txt
 * when parsing information.
 * This class will "Store" items. The item will track variables
 * Crate names, "stored" items and crate location.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */

public class Crate {
    private String crateName;
    private String itemName;
    private int crateLocation;


    /*----------------------------------------------Crate Constructors------------------------------------------------*/
    /**
     * @param name
     * @param item
     * @param location
     * @Function: constructor for pre existing data from the Crate text file
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public Crate(String name, String item, int location) {
        this.crateName = name;
        this.itemName = item;
        this.crateLocation = location;
    }


    /*-------------------------------------Getters & Setters for Crate variables--------------------------------------*/
    public String getCrateName() {
        return crateName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCrateLocation() {
        return crateLocation;
    }


    /*-----------------------------------Crate Methods for implementing the game--------------------------------------*/
    /**
     * @Method: examineCrate()
     * @param item
     * @param crates
     * @Function: returns the item that is within the crate being examined
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public String examineCrate(String item, ArrayList<Crate> crates) {
        //creates null string
        String check = null;

        //goes through crate arraylist, finds relevant crate, outputs contents of crate
        for(int i = 0; i < crates.size(); i++) {
            Crate temp = crates.get(i);
            if(item.contains(temp.getItemName())) {
                check = this.getItemName();
            }
        }
        return check;
    }
}
