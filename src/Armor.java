public class Armor extends Item implements equipItemInterface {
    public int armorMod;


    /*---------------------------------------------Armor Constructor--------------------------------------------------*/
    public Armor(){

    }

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
    /**
     * @Method: equip()
     * @param //Player
     * @Function: This method will equip the item calling the method to the player, while unequipping the player's current item
     * @author(s): Carlton Napier
     * @added: 10/31/2022
     */
    @Override
    public void equip(Player player) {


        /*
        if the player has equipped armor, the equipped armor is added to the inventory,
        the armor trying to be equipped is then set to the player
        the armor trying to be equipped is then removed from the player's inventory
         */

        if (player.getEquippedArmor() != null)
        {
            player.removeEquippedArmor();
        }
            player.setEquippedArmor(this);
    }
}
