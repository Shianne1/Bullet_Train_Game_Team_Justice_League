import java.io.Serializable;
import java.util.ArrayList;

public class Armor extends Item implements Serializable {
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
            for (Item item : player.getInventory()) {
                if (item.getItemName().equalsIgnoreCase("light-armor")) {
                    armorInventory.add((Armor) item);
                }
                if (item.getItemName().equalsIgnoreCase("medium-armor")) {
                    armorInventory.add((Armor) item);
                }
                if (item.getItemName().equalsIgnoreCase("heavy-armor")) {
                    armorInventory.add((Armor) item);
                }

            }
    }

    // SHIANNE LESURE 11/7/2022
    public void equipArmor(Player player, String item){
        addingArmor(player);
        String[] parts = item.split(" ");
        for(Armor armor: armorInventory){
            if(parts[1].equalsIgnoreCase(armor.getItemName())){
                player.setEquippedArmor(armor);
                System.out.println("Your " + armor.getItemName() + " will defend you by " + armor.getArmorMod());
            }
        }
    }

    //SHIANNE LESURE 11/8/2022
    public void unequipArmor(Player player, String item){
        String[] parts = item.split(" ");
        for(Armor armor: armorInventory){
            if(parts[1].equalsIgnoreCase(armor.getItemName())){
                player.removeEquippedArmor();
                System.out.println("You have unequipped " + armor.getItemDesc());
                armorInventory.remove(armor);
                break;
            }
        }
    }

}
