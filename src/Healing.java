import java.util.ArrayList;

public class Healing extends Item{

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Item> items;
    private ArrayList<Room> roomItems;

    // will access the player's inventory arraylist
    Player inventory;

    // will access the player's current health
    Player currentHealth;

    /**
     * @Function: This is a no - arg constructor that will access the item parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public Healing(){
        // an arraylist that will hold the item's data
        roomItems = new ArrayList();
        items = new ArrayList();

        // putting the items data into the item & room arraylist
        game.readItems(items, roomItems);
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    /**
     * @Method: useItem()
     * @param item
     * @Function: This method will allow the player use an item to heal their wounds and increase their health
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public void useItem(String item) {
        for (int i = 0; i < items.size(); i++) {
            if (item.contains(items.get(i).getItemName)) { // if input contains the item name
                items.get(i).setStack(getStack() - 1); // subtract 1 from the healing item stack
                currentHealth = items.get(i).getHealAmount() + currentHealth; // add the healing points to the player's current health

                // print out to the player how much their current health has went up
                System.out.println("You health has jumped up by " + items.get(i).getHealAmount());
                break;
            }

        }
    }
}
