import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @Object: Monster()
 * @Function: This OOP class will help set up the Monster objects that is needed from the monster.txt
 * when parsing information.
 * This class will interact with the player to do combat and gain items. The objects will track variables
 * monster ID, names, descriptions, health, damage, items and drop rates, and the
 * command methods for the monsters as the player interact with them.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Monster implements EntityInterface, Serializable {
    private int monsterId;
    private String monsterName;
    private String monsterDesc;
    private int health;
    private int damage;
    private String itemDrop1;
    private String itemDrop2;
    private double dropRate1;
    private double dropRate2;

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Monster> enemy;


    /*---------------------------------------------Monster Constructors-----------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the monster's parse method and add the data into
     * their object arraylist
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public Monster(){
        // an arraylist that will hold the monster's data
        enemy = new ArrayList();

        // putting the monster data into the monster arraylist
        game.readMonsters(enemy);
    }

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


    /*-----------------------------------Getters & Setters for Monster variables--------------------------------------*/
    public int getMonsterId() { return monsterId; }

    public String getMonsterName() {
        return monsterName;
    }

    public String getMonsterDesc() {
        return monsterDesc;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() { return damage; }

    public double getDropRate1() { return dropRate1; }

    public double getDropRate2() { return dropRate2; }

    public String getItemDrop1() {
        return itemDrop1;
    }

    public String getItemDrop2() {
        return itemDrop2;
    }


    /*----------------------------------Monster Methods for implementing the game-------------------------------------*/
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
        //creates delta health variable, sets to current player health, decreases variable by
        //monster damage, then sets player health to the difference
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
        //generate random number 1-100
        //if number is greater than 50, the monster is parried
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
        //creates empty temporary item, and generates random double 0.0 - 1.0
        Item temp;
        Random r = new Random();
        double probability = r.nextDouble();
        //if probability variable falls within 0 to the first drop rate of item, monster drops first possible item
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
        //if probability falls within bounds defined by the first drop rate number, and the sum of both drop rate
        //variables, second item is dropped, otherwise no item is dropped
        else if(probability > this.dropRate1 && probability <= (this.dropRate1 + this.dropRate2))
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

    @Override
    public void healHealth(int healthModifier) {

    }

    @Override
    public void takeDamage(int healthModifier) {

    }

    /**
     * @Method: inspect()
     * @param monster
     * @return monsterDescription
     * @Function: This method will allow for the player to see the descriptions of the monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    public String inspectMonster(String monster){
        String monsterDescription = "";
        for(int i = 0; i < enemy.size(); i++){
            if(monster.contains(enemy.get(i).getMonsterName())){ // if player's input contains monster's name
                //System.out.println(enemy.get(i).getMonsterDesc());
                monsterDescription = enemy.get(i).getMonsterDesc(); // add the monster's description to the string
                break;
            }
        }
        return monsterDescription; // return the string
    }
}

