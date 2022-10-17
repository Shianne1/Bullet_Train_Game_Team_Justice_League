

import java.io.*;
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

    public static void main(String[] args) {


        // view handles the system outputs
        View view = new View();
        // player data is a model that handles general data for the game being played
        GameState gameState = new GameState();

        // loop to start the game | while the gameState isn't running
        while (!gameState.isRunning)
            startGame(gameState, view);

        // loop for playing the game | while the gameState is running
        while (gameState.isRunning()) {
            // at the start of the loop, if the player's health is zero or less, the playerDeath() method is run
            if (gameState.player.currentHealth <= 0) {
                playerDeath(gameState, view);
            }

            // NEEDS A PROPER CODE FOR LOOPING THE GAME
            // - WHAT GOES ON IN THE ROOM
            // - ANY STORY DETAILS
            // - ETC
            // THIS IS A TEMP LOOP FOR TESTING

            view.printGameLoop(gameState);
            parseCommand(gameState, view);

        }
    }


    /*
    @Method: parseCommand()
    @Function: this code is to parse commands, and run methods based on the command received by the view
    @author(s) Carlton Napier
    @added 10/16/2022
 */
    private static void parseCommand(GameState gameState, View view) {
        String inputCommand = view.inputCommand();

        if (inputCommand.equals("save game")) {
            saveGame(gameState, view);
        }

        if (inputCommand.equals("load game")) {
            loadGame(gameState, view.loadingGameText());
        }

        if (inputCommand.equals("exit")) {
            endGame(gameState, view);
        }

        if (inputCommand.equals("check stats")) {
            view.printStatText(gameState.getPlayer());
        }

        if (inputCommand.equals("help")) {
            view.printBasicText(parseTextFile("Help.txt"));
        }

        //Player will be able to see the map of the train wagon and the stations linked to each wagon including the spot
        //they are currently at.
        if (inputCommand.equals("check map")) {
            view.printBasicText(parseTextFile("Map.txt"));
            view.printBasicText(gameState.getPlayer().getLocation().toString());
        }


        if (inputCommand.equals("check inventory")) {
            view.printInventory(gameState.getPlayer());
        }

    }

    /*
        @Method: startGame()
        @Function: this code is meant to run at the start of the game
        @author(s) Carlton Napier
        @added 10/16/2022
     */
    public static void startGame(GameState gameState, View view) { // code to run at the start of the game to determine starting a new file or loading
        /*
        the view - presents the title of the game and asks the player for their input to start a new game, load an old game,
        or exit out of the game entirely (using the view)sends the user input until one of three options is selected
         */
        String inputCommand = view.startOfGameText();

        // saying ["start game"] or just pressing enter gets the view to ask the player for a name (this name will be used for a new save file)
        if (inputCommand.equals("start game") || inputCommand.isBlank()) {
            /*
            newGame() method is called, the view sending the name the player will have ---
             - upon getting the name, the newGame() function  is called using the given name, and data from the files are used
               to make a new instance of the game
            */
            newGame(gameState, view.setSaveNameText());
        }
        /*
        saying ["load game"] gets the view to ask the player for a name (this name is used to load an existing save file, if it exists)
        - upon getting the name, the loadGame() function  is called using the given name, and the file with that name is loaded
        if it exists
        */
        else if (inputCommand.equals("load game")) {
            // loadGame() method is called, the view sending the name of the data being looked for
            loadGame(gameState, view.loadingGameText());
        }
        //saying ["exit"] or even ["exit game"] will run the ["end game"] function, ending the game
        else if (inputCommand.startsWith("exit")) {
            // endGame() method is called, with the view sending confirmation that the game is being closed down,
            endGame(gameState, view);
        }
    }

    /*
         @Method: newGame()
         @Function: this code is meant to run when a new game is created
         @author Carlton Napier
         @added 10/16/2022
      */
    public static void newGame(GameState gameState, String playerName) {
        // temp way to create new game for testing purposes (all the parameters are empty except for the instance of new player)

        //   -- objects from other methods used to parse the data files (currently empty but proper implementation in comments)
        ArrayList<Item> emptyItemList = new ArrayList<>();
        ArrayList<Room> emptyRoomList = new ArrayList<>();
        ArrayList<Puzzle> emptyPuzzleList = new ArrayList<>();
        ArrayList<Monster> emptyMonsterList = new ArrayList<>();

        gameState.setItemsInGame(emptyItemList);
        gameState.setRoomsInGame(emptyRoomList);
        gameState.setPuzzlesInGame(emptyPuzzleList);
        gameState.setMonstersInGame(emptyMonsterList);

        //     -- after objects are added to [gameState] a new instance of player is created using the name the player inputted and
        //     an instance of newGame (now filled with data) as a default checkpoint for the player to return to on death
        gameState.setPlayer(new Player(playerName, gameState));

        /*
        When the functions that can parse the files used to add the items/rooms/puzzle/monster data for the game are properly implemented:

        //   -- objects from other methods used to parse the data files (currently empty but proper implementation in comments)
        gameState.setItemsInGame(parseItemData(),);
        gameState.setRoomsInGame(parseRoomData());
        gameState.setPuzzlesInGame(parsePuzzleData());
        gameState.setMonstersInGame(parseMonsterData(););

        //     -- after objects are added to [gameState] a new instance of player is created using the name the player inputted and
        //     an instance of newGame (now filled with data) as a default checkpoint for the player to return to on death
        gameState.setPlayer(new Player(playerName, gameState));

        //  Player starts in train wagon 1 (assumed to be the first room in the arrayList)
        gameState.getPlayer().setLocation(gameState.getRoomsInGame().get(0));
        */

        //    -- after newGame is properly filled with data, game is set to running to allow for a game loop to be maintained
        gameState.setRunning(true);


    }

    /*
       @Method: loadGame()
       @Function: this code is meant to run when pre-existing save data is being asked to be loaded in
       @author Carlton Napier
       @added 10/16/2022
    */
    public static void loadGame(GameState gameState, String playerName) {

        try {
            //  the file that references the gameState object saved under the name of the player is used to create a File variable
            File gameStateData = new File("src/savedata/" + playerName + "_data.bin");

            FileInputStream dataFile = new FileInputStream(gameStateData);
            ObjectInputStream dataInput = new ObjectInputStream(dataFile);

            // the current gameState is set to the loaded gameState, and set to run
            gameState.setGameState((GameState) dataInput.readObject());
            gameState.setRunning(true);
        }
        //   -- if this file  doesn't exist, or the file searched for doesn't have the proper format, an exception is thrown
        catch (FileNotFoundException e) {
            System.out.println("No File under that name was found, please try again");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("The data in this file is invalid, please try again");
        }


    }

    /*
       @Method: saveGame()
       @Function: this code is meant to run when pre-existing game is being saved
       @author Carlton Napier
       @added 10/16/2022
    */
    public static void saveGame(GameState gameState, View view) {
        // Player is asked to confirm that they want to save the game
        String savingGame = view.savingGameText();
        if (savingGame.equalsIgnoreCase("yes")) {
            try {
                // a new file is created, based on the player's name as a bin file, in the savedata folder
                //if the file exists, it is overwritten
                File gameStateData = new File("src/savedata/" + gameState.player.getName() + "_data.bin");

                FileOutputStream dataFile = new FileOutputStream(gameStateData);
                ObjectOutputStream dataOutput = new ObjectOutputStream(dataFile);

                //writes the gameState object to the file
                dataOutput.writeObject(gameState);
            } catch (FileNotFoundException e) {
                System.out.println("File failed to be created");
            } catch (IOException e) {
                System.out.println("Data failed to be written");
            }
        }
    }

    /*
        @Method: endGame()
        @Function: this code is meant to run when the player wants to exit the game
        @author Carlton Napier
        @added 10/16/2022
    */
    public static void endGame(GameState gameState, View view) {
        // prints an exit message
        view.printExitMessage();

        //if the game is ending while the game is running
        if(gameState.isRunning) { //prompts the player to save
            saveGame(gameState, view);
        }

        //exits out of the program
        System.exit(0);
    }


    /*
        @Method: playerDeath()
        @Function: this code is meant to run when the player's death is noticed by the controller
        @author Carlton Napier
        @added 10/16/2022
    */
    public static void playerDeath(GameState gameState, View view) {
        // a message alerting that the player has died is sent by the view
        view.printDeathMessage();
        // if the player has a valid checkpoint, their game is set back to that point
        if (gameState.getPlayer().hasCheckPoint) {
            gameState.setGameState(gameState.getPlayer().getCheckpoint());
        }
        // if the player somehow doesn't have a checkpoint, they're put into a new game using the same player name
        else {
            newGame(gameState, gameState.player.getName());
        }
    }

    /*
        @Method: parseTextFile()
        @Function: this is a backend method that reads in a text file and returns it as a string
        - is only really useful for Map.txt and Help.txt, as they are not transferred into objects
        @author Carlton Napier
        @added 10/16/2022
    */
    public static String parseTextFile(String textFile) {
        try {
            // a scanner that reads the given text file being searched for
            Scanner textFileIn = new Scanner(new File(textFile));
            //blank string created so scanned text can be added
            String textData = "";


            //adds to the string with the text from the file
            while (textFileIn.hasNext()) {
                textData = textData.concat(textFileIn.nextLine() + "\n");
            }


            //returns the read text
            return textData;
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect File");
            return "Data not Found";
        }
    }

}
