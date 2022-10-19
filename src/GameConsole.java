import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameConsole {

    // Our controller class can be in here or the player class
    // We will be parsing all of our text files within this class

    /*
    Final game Due nov. 6th
    Final source code will weigh the most for grading.

    don't collaborate on the main branch
    Make your own branch & communicate with the your team before you merge branches
    "git clone" = "download" | "pull" = "update"
    before pulling, save elsewhere in case of overwriting something important

    If you feel like there is something you need to change when implementing the game.
    Put within discord under Questions channel, and don't do it before you get everyone approval or at least my approval.
     */

    /*----------------------------------------Where the main game is played-------------------------------------------*/
    public static void main(String[] args) {
        GameConsole game = new GameConsole();
        game.readMapTxt();
        System.out.println();
        Puzzle puzzle = new Puzzle();
        puzzle.practiceRun(); // test run

    }

    /*-------------------------Read the text files and add them to arraylist or to string-----------------------------*/
    /**
     * Method: readPuzzleTxt
     * @param puzzleInfo
     * @Function: Will take the Puzzle.txt, and will parse the data into the puzzle class using an arraylist
     * @author(s) Shianne Lesure
     * @added 10/17/2022
     */
    public void readPuzzleTxt(ArrayList<Puzzle> puzzleInfo){
        try{
            File readPuzzleData = new File("src/Puzzle.txt");

            // scans Puzzle file
            Scanner inputPuzzle = new Scanner(readPuzzleData);

            while (inputPuzzle.hasNext()){

                // converts the puzzleID into a integer
                int puzzleIDTxt = Integer.parseInt(inputPuzzle.nextLine());

                String puzzleNameTxt = inputPuzzle.nextLine();
                String puzzleQuestionTxt = inputPuzzle.nextLine();
                String hintTxt = inputPuzzle.nextLine();
                String answerTxt = inputPuzzle.nextLine();

                // converts the puzzle attempts into an integer
                int attemptsTxt = Integer.parseInt(inputPuzzle.nextLine());

                String rewardsTxt = inputPuzzle.nextLine();

                // reading the empty string
                inputPuzzle.nextLine();

                // add inputs into puzzle objects which is added into the puzzleInfo arraylist
                puzzleInfo.add(new Puzzle(puzzleIDTxt,puzzleNameTxt,puzzleQuestionTxt,hintTxt,answerTxt,attemptsTxt,rewardsTxt));
            }

            // close file
            inputPuzzle.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *Method:readMapTxt
     * @Function: Will take the Map.txt, and will print out the map.
     * @author(s) Shianne Lesure
     * @added 10/17/2022
     */
    public static void readMapTxt(){
        try{
            File readMapData = new File("src/Map.txt");

            // scans Map file
            Scanner inputMap = new Scanner(readMapData);

            while(inputMap.hasNext()){

                // will print out the map
                System.out.println(inputMap.nextLine());
            }

            // close file
            inputMap.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
