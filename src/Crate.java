/**
 * @Object: Crate()
 * @Function: This OOP class will help set up the crate objects that is needed from the crate.txt
 * when parsing information.
 * This class will "Store" items. The item will track variables
 * Crate names, "stored" items and crate location.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */

public class Crate
{
    private String crateName;
    private String itemName;
    private int crateLocation;
    public Crate(String name, String item, int location)
    {
        this.crateName = name;
        this.itemName = item;
        this.crateLocation = location;
    }

    /**
     * GetterSetters
     */
    public String getCrateName() {
        return crateName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCrateLocation() {
        return crateLocation;
    }
}
