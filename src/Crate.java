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

    GameConsole game = new GameConsole();
    private ArrayList<Crate> crates;

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;


    /*----------------------------------------------Crate Constructors------------------------------------------------*/
    public Crate(){
        crates = new ArrayList<>();
        game.readCrates(crates);

        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
    }

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

    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getCrateLocation() {
        return crateLocation;
    }


    /*-----------------------------------Crate Methods for implementing the game--------------------------------------*/
    /**
     * @Method: examineCrate()
     * @param item
     * @Function: returns the item that is within the crate being examined
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public String examineCrate(String item) {
        String[] parts = item.split(" ");
        String checkCrate = "";
            for(Crate temp : crates){
                if(parts[1].equalsIgnoreCase(temp.getCrateName())){
                    checkCrate = temp.getItemName();
                    break;
                }
            }
        return checkCrate;
            /*
        //goes through crate arraylist, finds relevant crate, outputs contents of crate
        for(int i = 0; i < crates.size(); i++) {
            Crate temp = crates.get(i);
            if(item.contains(temp.getItemName())) {
                check = this.getItemName();
            }
        }
        return check;

             */
    }

    // SHIANNE LESURE 11/8/2022
    public void removeItemFromCrate(Room current, String item, Crate crate){ // ADD THE - TO ALL THE 2 WORDED ITEMS WITHIN ROOM & CRATE TEXTILE
        String[] parts = item.split(" ");
            for (Crate temp : crates) {
                if(current.getRoomId() == temp.getCrateLocation()) {
                    if(current.getCrates().contains(temp.getCrateName())) {
                        if (parts[1].equalsIgnoreCase(temp.getItemName())) {
                            temp.setItemName("No item");
                            break;
                        }
                    }
                }
            }
    }
}
