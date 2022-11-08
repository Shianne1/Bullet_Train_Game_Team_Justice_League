import java.util.ArrayList;

public class Armor extends Item implements equipItemInterface {
    private int armorMod;
    private ArrayList<Armor> armorInventory;

    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;



    /*---------------------------------------------Armor Constructor--------------------------------------------------*/
    // SHIANNE LESURE 11/7/2022
    public Armor(){
       armorInventory = new ArrayList<>();

        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
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


    /*------------------------------------------Getter & Setters for Armor--------------------------------------------*/
    public int getArmorMod() { return armorMod; }


    /*-----------------------------------Armor Methods for implementing the game--------------------------------------*/
    // SHIANNE LESURE 11/7/2022
    public void addingArmor(Player player){
        for(Item armorItem : items) {
            for (Item item : player.getInventory()) {
                if (item.getItemName().equalsIgnoreCase(armorItem.getItemName())) {
                    armorInventory.add((Armor) item);
                }
                /*
                if (item.getItemName().equalsIgnoreCase("medium-armor")) {
                    armorInventory.add((Armor) item);
                }
                if (item.getItemName().equalsIgnoreCase("heavy-armor")) {
                    armorInventory.add((Armor) item);
                }

                 */
            }
        }
    }




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
