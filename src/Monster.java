import java.util.ArrayList;

public class Monster extends Room{
    /*
    while you are implementing the game, just ask us if you need any help
    Look at pg. 4, 6, 7, 18, 19, 20, 21
     */

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Monster> enemy;

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
     * @Method: inspect()
     * @param monster
     * @return monsterDescription
     * @Function: This method will allow for the player to see the descriptions of the monster
     * @author(s): Shianne Lesure
     * @added: 10/29/2022
     */
    @Override
    public String inspect(String monster){
        String monsterDescription = "";
        for(int i = 0; i < enemy.size(); i++){
            if(monster.contains(enemy.get(i).name)){ // if player's input contains monster's name
                //System.out.println(enemy.get(i).getMonsterDesc());
                monsterDescription = enemy.get(i).getMonsterDesc(); // add the monster's description to the string
                break;
            }
        }
        return monsterDescription; // return the string
    }
}
