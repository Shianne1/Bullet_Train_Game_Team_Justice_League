import java.io.Serializable;

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
public class Item implements itemInterface, Serializable
{
    private int itemId;
    private String itemName;
    private String itemDesc;
    private String itemText;

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

    /**
     * GetterSetters
     */
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
}
