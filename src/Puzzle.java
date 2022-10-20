import java.util.ArrayList;
import java.io.Serializable;
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

    // The rewards are the items within the game
    private String rewards;

    // accessing the game console class
    GameConsole game = new GameConsole();

    private ArrayList<Puzzle> puzzles;
    // NEED TO ACCESS THE ITEMS ARRAYLIST
    // NEED TO ACCESS THE PLAYER'S INVENTORY MAYBE ?
    // NEED TO ACCESS THE PLAYER'S LOCATION
    // NEED TO ACCESS ROOMS ARRAYLIST


    /*-----------------------------------------------Puzzle Constructors----------------------------------------------*/
    /**
     * @Function: This is a no - arg constructor that will access the puzzle, room, and item's parse methods and add their data into
     * their object arraylist.
     * @author(s) Shianne Lesure
     * @added 10/18/2022
     */
    public Puzzle(){
        // an arraylist that will hold the puzzle's data
        puzzles = new ArrayList<>();

        // putting the puzzle data into the puzzles arraylist
        game.readPuzzleTxt(puzzles);
    }

    /**
     * @param puzzleID
     * @param puzzleName
     * @param puzzleQuestion
     * @param hint
     * @param answer
     * @param attempts
     * @param rewards
     * @Function: constructor for pre existing data from the puzzle text file
     * @author(s) Shianne Lesure
     * 10/17/2022
     */
    public Puzzle(int puzzleID, String puzzleName, String puzzleQuestion, String hint, String answer, int attempts, String rewards) {
        this.puzzleID = puzzleID;
        this.puzzleName = puzzleName;
        this.puzzleQuestion = puzzleQuestion;
        this.hint = hint;
        this.answer = answer;
        this.attempts = attempts;
        this.rewards = rewards;
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

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    @Override
    public String toString() {
        return puzzleID + "\n" + puzzleName + "\n" + puzzleQuestion + "\n" + hint +  "\n" + answer + "\n" + attempts + "\n" + rewards + "\n" ;
    }


    /*-------------------------------Puzzle Methods for implementing the game-----------------------------------------*/
    // THIS IS JUST A PRACTICE RUN TO TEST IF MY ARRAYLIST IS BEING READ
    public void practiceRun (){
        for(int i = 0; i < puzzles.size(); i++){
            System.out.println(puzzles.get(i));
        }
    }

    public void itemInventoryForPuzzles() {
        // HAVE A FOR LOOP OF ITEMS INVENTORY FROM ITEMS CLASS
        /*
        if(items){

        }

         */

    }
    public void inspectPuzzle(Scanner input){
        for(int i = 0; i < puzzles.size(); i++){
            //IF PLAYERS LOCATION MATCHES WITH THE PUZZLES LOCATION
            System.out.println(puzzles.get(i).getPuzzleName());
            System.out.println(puzzles.get(i).getPuzzleQuestion());
            System.out.println("If you would like to solve the puzzle type: (solve puzzle)");
            String playerAnswer = input.nextLine();
            if(playerAnswer.equalsIgnoreCase("solve puzzle")){
                solvePuzzle(input);
            }
        }

    }

    public void solvePuzzle(Scanner input){
        for(int i = 0; i < puzzles.size(); i++){
            //IF PLAYERS LOCATION MATCHES WITH THE PUZZLES LOCATION
            String playerAnswer = input.nextLine();
            int count = 1;
            while (puzzles.get(i).getAttempts() != 0){
                if(playerAnswer.equalsIgnoreCase("Get hint")){
                    hint();
                }
                if(playerAnswer.equalsIgnoreCase(puzzles.get(i).getAnswer())){
                    System.out.println("You solve the puzzle correctly! You can now pick up your rewards " + puzzles.get(i).getRewards());
                    puzzles.get(i).setRewards("No rewards to receive");
                    // SET LOCATION TO 0

                    //puzzle will have its own inventory of items that will need to be drop if puzzle is solve correctly.
                    break;
                }
                else if(!playerAnswer.equalsIgnoreCase(puzzles.get(i).getAnswer())){
                    puzzles.get(i).setAttempts(puzzles.get(i).getAttempts() - 1);
                    if(puzzles.get(i).getAttempts() == 0){
                        System.out.println("Failed tp solve this puzzle. If you would like to try the puzzle again type: (retry puzzle)");
                        playerAnswer = input.nextLine();
                        if(playerAnswer.equalsIgnoreCase("retry puzzle")){
                            retryPuzzle();
                        }

                        break;
                    }
                    System.out.println("The answer you provided is wrong. You still have " + puzzles.get(i).getAttempts() + " attempts left.");
                    playerAnswer = input.nextLine();
                    count ++;
                }
            }
            puzzles.get(i).setAttempts(count);
        }

    }

    public void hint(){
        for(int i = 0; i < puzzles.size(); i++){
            //IF PLAYERS LOCATION MATCHES WITH THE PUZZLES LOCATIONS
            puzzles.get(i).getHint();
        }
    }

    public void retryPuzzle(){

        // stay after class for help with the retry puzzle
    }

    public void exitPuzzle(){

    }
}
