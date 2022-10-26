/**
 * @Object: Folder()
 * @Function: This OOP class will help set up the healing objects that is needed from the item.txt
 * when parsing information.
 * This class supers back to the item class and then also stores health and stack amount
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */

public class Healing extends Item
{
    private int healAmount;
    private int stackAmount;

    /**
     * @param id
     * @param name
     * @param desc
     * @param text
     * @param heal
     * @param stack
     * @Function: constructor for pre existing data from the item text file
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public Healing(int id, String name, String desc, String text, int heal, int stack)
    {
        super(id, name, desc, text);
        this.healAmount = heal;
        this.stackAmount = stack;
    }
}
