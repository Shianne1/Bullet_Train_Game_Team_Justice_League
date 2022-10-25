import java.util.ArrayList;
import java.util.Random;


/**
 * @Object: Monster()
 * @Function: This OOP class will help set up the Monster objects that is needed from the monster.txt
 * when parsing information.
 * This class will interact and solve the puzzles to receive items. The puzzle will track variables such as puzzle names,
 * puzzle ID, question, answer, hint, attempts, rewards, and the command methods for the puzzles as the player interact with them.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Monster
{
    private int monsterId;
    private String monsterName;
    private String monsterDesc;
    private int health;
    private int damage;
    private String itemDrop1;
    private String itemDrop2;
    private double dropRate1;
    private double dropRate2;


    /**
     * @param id
     * @param name
     * @param desc
     * @param HP
     * @param dam
     * @param item1
     * @param item2
     * @param prob1
     * @param prob2
     * @Function: constructor for pre existing data from the Monster text file
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public Monster(int id, String name, String desc, int HP, int dam, String item1, String item2,
                   int prob1, int prob2)
    {
        this.monsterId = id;
        this.monsterName = name;
        this.monsterDesc = desc;
        this.health = HP;
        this.damage = dam;
        this.itemDrop1 = item1;
        this.itemDrop2 = item2;
        dropRate1 = prob1/100;
        dropRate2 = prob2/100;
    }

    /**
     * GetterSetters
     */
    public int getMonsterId() {
        return monsterId;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public String getMonsterDesc() {
        return monsterDesc;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public double getDropRate1() {
        return dropRate1;
    }

    public double getDropRate2() {
        return dropRate2;
    }

    public String getItemDrop1() {
        return itemDrop1;
    }

    public String getItemDrop2() {
        return itemDrop2;
    }

    /**
     * @Method: attackMonster()
     * @param player
     * @Function: when called on, deals damage to player, updates player health and
     * displays damage dealt and current player health
     * @author: Dakota Smith
     * 10/19/2022
     */
    public void attackMonster(Player player)
    {
        int dHealth = player.getCurrentHealth();
        dHealth = dHealth - damage;
        player.setCurrentHealth(dHealth);
        System.out.println("Monster Dealt: " + damage + " Damage.");
        System.out.println("Current HP: " + player.getCurrentHealth());
    }

    /**
     * @Method: parryMonster()
     * @Function: when called on, determines whether player parries monster or not
     * @author: Dakota Smith
     * 10/19/2022
     */
    public boolean parryMonster()
    {
        boolean parry = false;
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        if(randomInt > 50)
        {
            parry = true;
        }
        return parry;
    }

    /**
     * @Method: monsterDrop()
     * @param currentRoom
     * @param items
     * @Function: when called on, determines what item, if any, a monster drops into the room
     * @author: Dakota Smith
     * 10/25/2022
     */
    public void monsterDrop(Room currentRoom, ArrayList<Item> items)
    {
        Item temp;
        Random r = new Random();
        double probability = r.nextDouble();
        if(probability <= this.dropRate1)
        {
            for(Item a : items)
            {
                temp = a;
                String itemName = a.getItemName();
                if(itemName.equals(this.itemDrop1))
                {
                    currentRoom.roomItemAdd(temp);
                }
            }
        }
        else if(probability > this.dropRate1 && probability <= this.dropRate2)
        {
            for(Item a : items)
            {
                temp = a;
                String itemName = a.getItemName();
                if(itemName.equals(this.itemDrop2))
                {
                    currentRoom.roomItemAdd(temp);
                }
            }
        }
    }
}