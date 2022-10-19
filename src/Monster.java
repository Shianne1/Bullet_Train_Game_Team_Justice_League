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

    public Monster(int id, String name, String desc, int HP, int dam)
    {
        this.monsterId = id;
        this.monsterName = name;
        this.monsterDesc = desc;
        this.health = HP;
        this.damage = dam;
    }

    public void inspectMonster()
    {}

    public void attackMonster()
    {}

    public void parryMonster()
    {}

    public void heal()
    {}

    public void monsterDamage()
    {}

    public void run()
    {}

    public void flee()
    {}
}
