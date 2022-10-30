public class Armor extends Item implements equipItemInterface {
    public int armorMod;


    /*---------------------------------------------Armor Constructor--------------------------------------------------*/

    /**
     * @param name
     * @param id
     * @param itemDesc
     * @param itemText
     * @param armorMod
     * @Function constructor for pre existing data from the item text file
     * @author(s): ?
     * @added: ?
     */
    public Armor(String name, int id, String itemDesc, String itemText, int armorMod) {
        super(id, name, itemDesc, itemText);
        this.armorMod = armorMod;
    }


    /*-----------------------------------Armor Methods for implementing the game--------------------------------------*/
    @Override
    public void equip() {

    }
}
