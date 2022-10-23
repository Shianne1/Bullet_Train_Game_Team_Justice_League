import java.util.Random;

public class Monster
{
    /*
    while you are implementing the game, just ask us if you need any help
    Look at pg. 4, 6, 7, 18, 19, 20, 21
     */

    private int monsterId;
    private String monsterName;
    private String monsterDesc;
    private int health;
    private int damage;
    private String itemDrop1;
    private String itemDrop2;
    private double dropRate1;
    private double dropRate2;

    public Monster(int id, String name, String desc, int HP, int dam, String item1, String item2,
                   int prob1, int prob2)
    {
        this.monsterId = id-1;
        this.monsterName = name;
        this.monsterDesc = desc;
        this.health = HP;
        this.damage = dam;
        this.itemDrop1 = item1;
        this.itemDrop2 = item2;
        dropRate1 = prob1/100;
        dropRate2 = prob2/100;
    }

    public void inspectMonster()
    {
        System.out.println(monsterName + " " + monsterDesc);
        System.out.println("Damage: " + damage + " Health: " + health);
    }

    public void attackMonster(Player player)
    {
        int dHealth = player.getCurrentHealth();
        dHealth = dHealth - damage;
        player.setCurrentHealth(dHealth);
        System.out.println("Monster Dealt: " + damage + " Damage.");
        System.out.println("Current HP: " + player.getCurrentHealth());
    }

    public void parryMonster()
    {
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
    }

    public void heal()
    {}


    public void run(Room currentRoom, Player player)
    {

    }

    public void flee(Room currentRoom, Player player)
    {

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
}
