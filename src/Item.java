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
public class Item implements itemInterface, Serializable {

    private String name;

    int id;
    private String itemDesc;
    private String itemText;

    @Override
    public String inspect() {
        return null;
    }

    @Override
    public void use() {

    }

    @Override
    public void discard() {

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
}
