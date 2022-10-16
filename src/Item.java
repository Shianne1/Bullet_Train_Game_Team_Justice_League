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

import java.io.Serializable;

interface itemInterface {

    String inspect(); // returns the item's description/item's text

    void use(); // uses the item based on its function

    void equip();

    void discard();
}

public class Item implements itemInterface, Serializable {

    int ItemID;
    String name;
    String itemDesc; //  description of the item's function
    String itemText; //  flavor text for the item

    public Item() {
    }

    public Item(String name) {

        this.name = name;
    }

    public Item(int itemID, String name, String itemDesc, String itemText) {
        this.ItemID = itemID;
        this.name = name;
        this.itemDesc = itemDesc;
        this.itemText = itemText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }


    @Override
    public String inspect() {
        return null;
    }

    @Override
    public void use() {

    }

    @Override
    public void equip() {

    }

    @Override
    public void discard() {

    }


}
