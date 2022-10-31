import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Object: Puzzle()
 * @Function: This OOP class will help set up the puzzle objects that is needed from the puzzle.txt when parsing information.
 * This class will interact and solve the puzzles to receive items. The puzzle will track variables such as puzzle names,
 * puzzle ID, question, answer, hint, attempts, rewards, and the command methods for the puzzles as the player interact with them.
 * @author(s) Shianne Lesure
 * @added 10/17/2022
 */
public class Puzzle implements Serializable{
    /*
    Look at pg. 7, 8, 9, 17, 18, 22
     */

    private int puzzleID;
    private String puzzleName;
    private String puzzleQuestion;
    private String hint;
    private String answer;
    private int attempts;
    private String reward1;
    private String reward2;
    private int puzzleLocation;

    // accessing the game console class
    GameConsole game = new GameConsole();
    GameState gameState = new GameState();

    private ArrayList<Puzzle> puzzles;
    private ArrayList<String> puzzleReward1;
    private ArrayList<String> puzzleReward2;

    // will allow for the puzzle class to access the current room the player is in
    Room currentRoom;

    Scanner input;

    // NEED TO ACCESS THE ITEMS ARRAYLIST
    // NEED TO ACCESS ROOMS ARRAYLIST


    /*-----------------------------------------------Puzzle Constructors----------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the puzzle, room, and item's parse methods and add their data into
     * their object arraylist.
     * @author(s) Shianne Lesure
     * @added 10/18/2022
     */
    public Puzzle(){
        // will take the player's input
        input = new Scanner(System.in);

        // an arraylist that will hold the puzzle's data
        puzzles = new ArrayList<>();

        // putting the puzzle data into the puzzles arraylist
        game.readPuzzleTxt(puzzles);

        // are arraylists that will hold the items the puzzle will drop if player solve them.
        puzzleReward1 = new ArrayList<>();
        puzzleReward2 = new ArrayList<>();

        itemInventoryForPuzzles(puzzleReward1,puzzleReward2);
    }

    /**
     * @param puzzleID
     * @param puzzleName
     * @param puzzleQuestion
     * @param hint
     * @param answer
     * @param attempts
     * @param reward1
     * @param reward2
     * @param puzzleLocation
     * @Function: constructor for pre existing data from the puzzle text file
     * @author(s) Shianne Lesure
     * 10/17/2022
     */
    public Puzzle(int puzzleID, String puzzleName, String puzzleQuestion, String hint, String answer, int attempts,String reward1, String reward2, int puzzleLocation) {
        this.puzzleID = puzzleID;
        this.puzzleName = puzzleName;
        this.puzzleQuestion = puzzleQuestion;
        this.hint = hint;
        this.answer = answer;
        this.attempts = attempts;
        this.reward1 = reward1;
        this.reward2 = reward2;
        this.puzzleLocation = puzzleLocation;
    }

    /*------------------------------------Getters & Setters for Puzzle variables--------------------------------------*/
    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public String getPuzzleQuestion() {
        return puzzleQuestion;
    }

    public void setPuzzleQuestion(String puzzleQuestion) {
        this.puzzleQuestion = puzzleQuestion;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getReward1() { return reward1; }

    public void setReward1(String reward1) { this.reward1 = reward1; }

    public String getReward2() { return reward2; }

    public void setReward2(String reward2) { this.reward2 = reward2; }

    public int getPuzzleLocation() { return puzzleLocation; }

    public void setPuzzleLocation(int puzzleLocation) { this.puzzleLocation = puzzleLocation; }

    @Override
    public String toString() {
        return puzzleID + "\n" + puzzleName + "\n" + puzzleQuestion + "\n" + hint +  "\n" + answer +
                "\n" + attempts + "\n" + reward1 + "\n" + reward2 + "\n" + puzzleLocation;
    }


    /*-------------------------------Puzzle Methods for implementing the game-----------------------------------------*/
    // THIS IS JUST A PRACTICE RUN TO TEST IF MY ARRAYLIST IS BEING READ
    public void practiceRun (){

        for(int i = 0; i < puzzles.size(); i++){
            System.out.println(puzzles.get(i));
            System.out.println();
        }


        System.out.println("-----------------------------------------------------------------------------------------");
        //System.out.print(puzzles);
    }

    /**
     * @Method: itemInventoryForPuzzles()
     * @param puzzleReward1
     * @param puzzleReward2
     * @Function: This method will take the variables reward1 & reward2 from the puzzle arraylist and added to 2 separate arraylist.
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void itemInventoryForPuzzles(ArrayList<String> puzzleReward1, ArrayList<String> puzzleReward2){
        for(Puzzle prize1: puzzles){
            String reward1Name = prize1.getReward1();
            if(reward1Name.equals(reward1)){
                puzzleReward1.add(reward1Name); // adding the reward1 item into the puzzleReward1
            }
        }
        for(Puzzle prize2: puzzles){
            String reward2Name = prize2.getReward2();
            if(reward2Name.equals(reward2)){
                puzzleReward2.add(reward2Name); // adding the reward2 item into the puzzleReward2
            }
        }
    }

    /**
     * @Method: dropRewardsItem()
     * @param rewardItems
     * @Function: This method will drop the reward items into the current room the player is in.
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void dropRewardsItem(String rewardItems){
        for(int p = 0; p < puzzleReward1.size(); p++){
            if(puzzleReward1.contains(puzzleReward1.get(p))){
                puzzleReward1.remove(puzzleReward1.get(p)); // remove the reward 1 item from the arraylist
                //currentRoom.roomItemAdd(puzzleReward1.get(p)); // add the reward 1 item to the current room arraylist
                break;
            }
        }
        for(int i = 0; i < puzzleReward2.size(); i++){
            if(puzzleReward2.contains(puzzleReward2.get(i))){
                puzzleReward2.remove(puzzleReward2.get(i)); // remove the reward 2 item from the arraylist
                //currentRoom.roomItemAdd(puzzleReward2.get(i)); // add the reward 2 item to the current room arraylist
                break;
            }
        }
    }

    /**
     * @Mehtod: inspectPuzzle()
     * @param answer
     * @Function: This method will allow the player to see the question of the puzzle as well if they would like to solve the puzzle
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void inspectPuzzle(String answer){
        /*
        for(int i = 0; i < puzzles.size(); i++){
            if(answer.contains(puzzles.get(i).getPuzzleName())){ // if player's input contains the puzzle's name
                System.out.println(puzzles.get(i).getPuzzleQuestion());
                System.out.println("If you would like to solve the puzzle type: (solve puzzle)");
                answer = input.nextLine();
            }
        }

         */

        /*
        for(int i = 0; i < gameState.getPuzzlesInGame().size(); i++){
            if(answer.contains(gameState.getPuzzlesInGame().get(i).getPuzzleName())){ // if player's input contains the puzzle's name
                System.out.println(gameState.getPuzzlesInGame().get(i).getPuzzleQuestion());
                System.out.println("If you would like to solve the puzzle type: (solve puzzle)");
                answer = input.nextLine();
            }
        }

         */

    }

    /**
     * @Mthod: solvePuzzle()
     * @param playerAnswer
     * @Function: This method will allow for the player to answer the current puzzle to reviews prizes
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void solvePuzzle(String playerAnswer){
        for(int i = 0; i < puzzles.size(); i++){
            if(playerAnswer.contains(puzzles.get(i).getPuzzleName())) { // if player's input contains the puzzle's name
                int countAttempts = 1;
                while (puzzles.get(i).getAttempts() != 0) {
                    if (playerAnswer.equalsIgnoreCase(puzzles.get(i).getAnswer()) && puzzles.get(i).getAttempts() == 3) { // if player solve the puzzle on their 1st try
                        System.out.println("You solve the puzzle correctly! You can now claim your prizes! \n" + puzzles.get(i).reward1 + "\n" + puzzles.get(i).reward2);

                        // will drop the reward item as well as bandages
                        dropRewardsItem(puzzles.get(i).reward1);
                        dropRewardsItem(puzzles.get(i).reward2);
                        break;
                    }
                    else if (playerAnswer.equalsIgnoreCase(puzzles.get(i).getAnswer()) && puzzles.get(i).getAttempts() < 3) { // if player solve the puzzle on their 2nd & 3rd try
                        System.out.println("You solve the puzzle correctly! You can now claim your prize!\n" + puzzles.get(i).reward2);

                        //will only drop the bandages
                        dropRewardsItem(puzzles.get(i).reward2);
                        break;
                    }
                    else if (!playerAnswer.equalsIgnoreCase(puzzles.get(i).getAnswer())) { // if player doesn't solve the puzzle correctly
                        puzzles.get(i).setAttempts(puzzles.get(i).getAttempts() - 1); // remove their 1 attempt from player
                        if (puzzles.get(i).getAttempts() == 0) { // if player runs out of attempts
                            System.out.println("Failed tp solve this puzzle.");
                            break;
                        }
                        System.out.println("The answer you provided is wrong. You still have " + puzzles.get(i).getAttempts() + " attempts left.");
                        playerAnswer = input.nextLine();
                        countAttempts++;
                    }
                }
                puzzles.get(i).setAttempts(countAttempts); // set the number of attempts back to 3
            }
        }

    }

    /**
     * @Method: hint()
     * @param player
     * @Function: This method will give the player the current hint attach to the current puzzle
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void hint(String player){
        for(int i = 0; i < puzzles.size(); i++){
            if(player.contains(puzzles.get(i).getPuzzleName())) { // if player's input contains the puzzle's name
                System.out.println(puzzles.get(i).getHint()); // will print out the hint of the current puzzle
            }
        }
    }

    /**
     * @Method: retryPuzzle()
     * @param player
     * @Function: This method will allow for the player to retry the puzzle if they have failed
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void retryPuzzle(String player){
        for(int i = 0; i < puzzles.size(); i++){
            if(player.contains(puzzles.get(i).getPuzzleName())) { // if player's input contains the puzzle's name

                // the player will get the question again as well as a chance to solve the puzzle
                inspectPuzzle(player);
                solvePuzzle(player);
            }
        }
    }

    /**
     * @Method: exitPuzzle()
     * @param answer
     * @Functions: This method will allow the player to break out the puzzle so they can interact with other things within the train
     * @author(s): Shianne Lesure
     * @added: 10/27/2022
     */
    public void exitPuzzle(String answer){
        for(int i = 0; i < puzzles.size(); i++){
            if(answer.contains(puzzles.get(i).getPuzzleName())) { // if player's input contains the puzzle's name
                break; // will break out of the puzzle
            }
        }
    }
}
