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


    /*----------------------------------------Where the main game is played-------------------------------------------*/
    public static void main(String[] args) {
        GameConsole game = new GameConsole();
        // view handles the system outputs
        View view = new View();
        // player data is a model that handles general data for the game being played
        GameState gameState = new GameState();
        Item item = new Item();
        Healing healing = new Healing();
        Weapon weapon = new Weapon();
        Armor armor = new Armor();
        //Folder folder = new Folder();
        Crate crate = new Crate();
        Puzzle puzzle = new Puzzle();
        Monster monster = new Monster();
        Room room = new Room();
        Player player = new Player();

        /*
        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<Monster> bestiary = new ArrayList<>();
        ArrayList<Crate> crateList = new ArrayList<>();
        ArrayList<Item> itemList = new ArrayList<>();


        game.readRooms(roomList);
        game.readMonsters(bestiary);
        game.readCrates(crateList);
        game.readItems(itemList, roomList);

         */
        //puzzle.practiceRun(); // test run

        // loop to start the game | while the gameState isn't running
        while (!gameState.isRunning)
            startGame(gameState, view);

        // loop for playing the game | while the gameState is running
        while (gameState.isRunning()) {
            // at the start of the loop, if the player's health is zero or less, the playerDeath() method is run
            if (gameState.getPlayer().getCurrentHealth() <= 0) {
                playerDeath(gameState, view);
            }

            // NEEDS A PROPER CODE FOR LOOPING THE GAME
            // - WHAT GOES ON IN THE ROOM
            // - ANY STORY DETAILS
            // - ETC
            // THIS IS A TEMP LOOP FOR TESTING

            view.printGameLoop(gameState);
            parseCommand(game, gameState, view, puzzle, item, healing, weapon, armor, crate, monster, room, player);

        }
    }

    /*--------------------------------GameConsole methods for implementing the game-----------------------------------*/
    /**
     * @Method: parseCommand()
     * @Function: this code is to parse commands, and run methods based on the command received by the view
     * @author(s) Carlton Napier, Shianne Lesure 
     * @added 10/16/2022
     */
    private static void parseCommand(GameConsole game, GameState gameState, View view, Puzzle puzzle, Item item, Healing healing,
                                     Weapon weapon, Armor armor, Crate crate, Monster monster, Room room, Player player) {
        String inputCommand = view.inputCommand();
        int IDofPuzzleInRoom = gameState.getPlayer().getLocation().getRoomPuzzle();
        int IDofMonsterInRoom = gameState.getPlayer().getLocation().getRoomMonster();
        Room playerLocation = gameState.getPlayer().getLocation();
        Player currentPlayer = gameState.getPlayer();
        Armor currentArmor = gameState.getPlayer().getEquippedArmor();
        Weapon currentWeapon = gameState.getPlayer().getEquippedWeapon();
        monster.getHealth();

        if(inputCommand.equals("save game")) {
            saveGame(gameState, view);
        }
        else if(inputCommand.equals("load game")) {
            loadGame(gameState, view.loadingGameText());
        }
        else if(inputCommand.equals("exit game")) { // THIS FEATURE IS WORKING
            endGame(gameState, view);
        }
        else if(inputCommand.equals("check stats")) { // THIS FEATURE IS WORKING
            view.printBasicText(gameState.getPlayer().checkStatsPlayer(currentPlayer));
        }
        else if(inputCommand.equals("help")) { // THIS FEATURE IS WORKING
            view.printBasicText(parseHelpText());
        }

        //Player will be able to see the map of the train wagon and the stations linked to each wagon including the spot
        //they are currently at.
        else if(inputCommand.equals("check map")) { // THIS FEATURE IS WORKING
            game.readMapTxt();
            view.printBasicText("\nYou are in " + gameState.getPlayer().getLocation().getRoomName());
        }
        else if(inputCommand.equals("check inventory")) { // THIS FEATURE IS WORKING
            view.printInventory(gameState.getPlayer());
        }
        else if(inputCommand.equals("north") || inputCommand.equals("n") || inputCommand.equals("east") || inputCommand.equals("e") || // THIS FEATURE IS WORKING
                inputCommand.equals("south") || inputCommand.equals("s") ||  inputCommand.equals("west") || inputCommand.equals("w")){
            room.Direction(currentPlayer, gameState.getRoomsInGame(), inputCommand, gameState.getItemsInGame());
        }
        else if(inputCommand.equals("checkout room")){ // THIS FEATURE IS WORKING
            view.printBasicText(gameState.getPlayer().getLocation().inspectRoom(gameState.getMonstersInGame(), gameState.getPuzzlesInGame(), playerLocation));
        }
        else if(inputCommand.contains("inspect")){ // THIS FEATURE IS WORKING
            view.printBasicText(item.inspect(inputCommand));
            item.getFolderCode(playerLocation, currentPlayer);
        }
        else if(inputCommand.equals("view code")){ // THIS FEATURE IS WORKING
            gameState.getPlayer().viewCode();
        }
        else if(inputCommand.equals("use code")){ // THIS FEATURE IS WORKING
            room.lockRoom(currentPlayer);
        }
        else if(inputCommand.contains("store")){ // THIS FEATURE IS WORKING
            item.storeItem(inputCommand, playerLocation, currentPlayer);
            puzzle.removeRewardsItem(playerLocation, inputCommand);
            crate.removeItemFromCrate(playerLocation,inputCommand);
            room.removeMysteryFolder(playerLocation, inputCommand);
        }
        else if(inputCommand.contains("discard")){ // THIS FEATURE IS WORKING
            item.discard(inputCommand, playerLocation, currentPlayer);
        }
        else if(inputCommand.contains("use")){ // THIS FEATURE IS WORKING
        }
        else if(inputCommand.contains("examine")){ // THIS FEATURE IS WORKING
            view.printBasicText(crate.examineCrate(inputCommand));
        }
        else if(inputCommand.contains("unequip")){ // THIS FEATURE IS WORKING
            weapon.unequipWeapon(currentPlayer, inputCommand);
            armor.unequipArmor(currentPlayer, inputCommand);
        }
        else if(inputCommand.contains("equip")){ // THIS FEATURE IS WORKING FOR WEAPONS
            weapon.equipWeapon(currentPlayer, inputCommand);
            armor.equipArmor(currentPlayer, inputCommand);
        }
        else if(inputCommand.equalsIgnoreCase("checkout puzzle")){ // THIS FEATURE IS WORKING
            puzzle.inspectPuzzle(IDofPuzzleInRoom, playerLocation, currentPlayer);
        }
        else if(inputCommand.contains("solve puzzle")){ // THIS FEATURE IS WORKING
            puzzle.solvePuzzle(IDofPuzzleInRoom, playerLocation, currentPlayer);
        }
        else if(inputCommand.contains("get hint")){ // THIS FEATURE IS WORKING
            puzzle.hint(IDofPuzzleInRoom);
        }
        else if(inputCommand.contains("retry puzzle")){ // THIS FEATURE IS WORKING
           puzzle.retryPuzzle(IDofPuzzleInRoom, playerLocation, currentPlayer);
        }
        else if(inputCommand.contains("exit puzzle")){ // THIS FEATURE IS WORKING
        }
        else if(inputCommand.equalsIgnoreCase("checkout monster")){ // THIS FEATURE IS WORKING
            view.printBasicText(monster.inspectMonster(IDofMonsterInRoom));
        }
        else if(inputCommand.contains("attack monster")){ // THIS FEATURE IS WORKING
            monster.attackMonster(currentPlayer, IDofMonsterInRoom,currentArmor, currentWeapon, playerLocation, gameState.getRoomsInGame());
        }
        else if(inputCommand.contains("parry monster")){ // THIS FEATURE IS WORKING
            monster.parryMonster(IDofMonsterInRoom, currentWeapon);
        }
        else if(inputCommand.equals("heal")){
            healing.useHealing(currentPlayer, inputCommand);
        }
        else if(inputCommand.equals("run")){ // THIS FEATURE IS WORKING
        }
        else{
            System.out.println("Invalid command. Try again");
        }

    }

    /**
     * @Method: startGame()
     * @Function: this code is meant to run at the start of the game
     * @author(s) Carlton Napier
     * @added 10/16/2022
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
            view.printBasicText("\nYou are in " + gameState.getPlayer().getLocation().getRoomName() + "\n" + gameState.getPlayer().getLocation().getRoomDesc());

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

    /**
     * @Method: newGame()
     * @Function: this code is meant to run when a new game is created
     * @author(s)  Carlton Napier
     * @added 10/16/2022
     */
    public static void newGame(GameState gameState, String playerName) {
        // temp way to create new game for testing purposes (all the parameters are empty except for the instance of new player)

        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<Monster> bestiary = new ArrayList<>();
        ArrayList<Crate> crateList = new ArrayList<>();
        ArrayList<Item> itemList = new ArrayList<>();
        ArrayList<Puzzle> puzzleList = new ArrayList<>();
        readRooms(roomList);
        readMonsters(bestiary);
        readCrates(crateList);
        readItems(itemList, roomList);
        readPuzzleTxt(puzzleList);

        gameState.setRoomsInGame(roomList);
        gameState.setItemsInGame(itemList);
        gameState.setPuzzlesInGame(puzzleList);
        gameState.setMonstersInGame(bestiary);

        gameState.setPlayer(new Player(playerName, gameState));
        gameState.getPlayer().setLocation(gameState.getRoomsInGame().get(0));

        //   -- objects from other methods used to parse the data files (currently empty but proper implementation in comments)
        /*
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
        gameState.setItemsInGame(parseItemData());
        gameState.setRoomsInGame(parseRoomData());
        gameState.setPuzzlesInGame(readPuzzleTxt(emptyPuzzleList));
        gameState.setMonstersInGame(parseMonsterData());
        gameState.setPlayer(new Player(playerName, gameState));
        gameState.getPlayer().setLocation(gameState.getRoomsInGame().get(0));

        /*
        When the functions that can parse the files used to add the items/rooms/puzzle/monster data for the game are properly implemented:

        //   -- objects from other methods used to parse the data files (currently empty but proper implementation in comments)
        gameState.setItemsInGame(parseItemData(),);
        gameState.setRoomsInGame(parseRoomData());
        gameState.setPuzzlesInGame(parsePuzzleData());
        gameState.setMonstersInGame(parseMonsterData());

        //     -- after objects are added to [gameState] a new instance of player is created using the name the player inputted and
        //     an instance of newGame (now filled with data) as a default checkpoint for the player to return to on death
        gameState.setPlayer(new Player(playerName, gameState));

        //  Player starts in train wagon 1 (assumed to be the first room in the arrayList)
        gameState.getPlayer().setLocation(gameState.getRoomsInGame().get(0));
        */

        //    -- after newGame is properly filled with data, game is set to running to allow for a game loop to be maintained
        gameState.setRunning(true);
    }

    /**
     * @Method: loadGame()
     * @Function: this code is meant to run when pre-existing save data is being asked to be loaded in
     * @author(s)  Carlton Napier
     * @added 10/16/2022
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

    /**
     * @Method: saveGame()
     * @Function: this code is meant to run when pre-existing game is being saved
     * @author(s)  Carlton Napier
     * @added 10/16/2022
     */
    public static void saveGame(GameState gameState, View view) {
        // Player is asked to confirm that they want to save the game
        String savingGame = view.savingGameText();
        if (savingGame.equalsIgnoreCase("yes")) {
            try {
                // a new file is created, based on the player's name as a bin file, in the savedata folder
                //if the file exists, it is overwritten
                File gameStateData = new File("src/savedata/" + gameState.getPlayer().getName() + "_data.bin");

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

    /**
     * @Method: endGame()
     * @Function: this code is meant to run when the player wants to exit the game
     * @author(s)  Carlton Napier
     * @added 10/16/2022
     */
    public static void endGame(GameState gameState, View view) {
        // prints an exit message
        view.printExitMessage();

        //if the game is ending while the game is running
        if (gameState.isRunning) { //prompts the player to save
            saveGame(gameState, view);
        }

        //exits out of the program
        System.exit(0);
    }


    /*----------------------------Player Controller methods for implementing the game---------------------------------*/
    /**
     * @Method: playerDeath()
     * @Function: this code is meant to run when the player's death is noticed by the controller
     * @author(s) Carlton Napier
     * @added 10/16/2022
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
            newGame(gameState, gameState.getPlayer().getName());
        }
    }


    /*-------------------------Read the text files and add them to arraylist or to string-----------------------------*/
    /**
     * Method: readPuzzleTxt
     * @param puzzleInfo
     * @Function: Will take the Puzzle.txt, and will parse the data into the puzzle class using an arraylist
     * @author(s) Shianne Lesure
     * @added 10/17/2022
     */
    public static void readPuzzleTxt(ArrayList<Puzzle> puzzleInfo){
        try{
            File readPuzzleData = new File("src/Puzzle.txt");
            Scanner inputPuzzle = new Scanner(readPuzzleData); // scans Puzzle file
            while (inputPuzzle.hasNext()){
                int puzzleIDTxt = Integer.parseInt(inputPuzzle.nextLine());// converts the puzzleID into a integer
                String puzzleNameTxt = inputPuzzle.nextLine();
                String puzzleQuestionTxt = inputPuzzle.nextLine();
                String hintTxt = inputPuzzle.nextLine();
                String answerTxt = inputPuzzle.nextLine();
                int attemptsTxt = Integer.parseInt(inputPuzzle.nextLine()); // converts the puzzle attempts into an integer

                //REWARDS 1 & 2 NEEDS TO BE CONVERTED OVER TO ITEMS
                String reward1Txt = inputPuzzle.nextLine();
                String reward2Txt = inputPuzzle.nextLine();
                String puzzleCodes = inputPuzzle.nextLine();
                //int puzzleLocationTxt = Integer.parseInt(inputPuzzle.nextLine());

                inputPuzzle.nextLine(); // reading the empty string

                // add inputs into puzzle objects which is added into the puzzleInfo arraylist
                puzzleInfo.add(new Puzzle(puzzleIDTxt,puzzleNameTxt,puzzleQuestionTxt,hintTxt,answerTxt,attemptsTxt,reward1Txt,reward2Txt,puzzleCodes));
            }
            inputPuzzle.close(); // close file
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
            Scanner inputMap = new Scanner(readMapData); // scans Map file
            while(inputMap.hasNext()){
                System.out.println(inputMap.nextLine()); // will print out the map
            }
            inputMap.close();  // close file
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: readGameIntro()
     * @Function: Will print out the game intro and instructions for the player
     * @author(s): Shianne Lesure
     * @added: 10/30/22
     */
    public void readGameIntro(){
        try{
            File readIntroData = new File("src/GameInstructions.txt");
            Scanner inputIntro = new Scanner(readIntroData); // scans instructions file
            while (inputIntro.hasNext()){
                System.out.println(inputIntro.nextLine()); // print out the game instructions
            }
            inputIntro.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Method: parseTextFile()
     * @Function: this is a backend method that reads in a text file and returns it as a string
     * - is only really useful for Map.txt and Help.txt, as they are not transferred into objects
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public static String parseHelpText() {
        try {
            // a scanner that reads the given text file being searched for
            Scanner textFileIn = new Scanner(new File("src/Help.txt"));
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

    /**
     * @param items
     * @param rooms
     * @Function: reads in data from file, creates item/armor/weapon/folder/healing objects,
     * puts them into general arraylist
     * @author(s) Dakota Smith
     * 10/26/2022
     */
    public static void readItems(ArrayList<Item> items, ArrayList<Room> rooms) {
        File fileIn = new File("src/Item.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while(reader.hasNext()) {
            //takes in all variables that are shared by all items
            int itemId = Integer.parseInt(reader.nextLine());
            String itemName = reader.nextLine();
            String itemDesc = reader.nextLine();
            String textDesc = reader.nextLine();
            //checks if item is a type of armor, if so, takes in relevant armor variables
            //creates armor item, adds to item arraylist
            if(itemName.contains("Armor")) {
                int AC = Integer.parseInt(reader.nextLine());
                Armor temp = new Armor(itemName, itemId, itemDesc, textDesc, AC);
                items.add(temp);
            }
            //checks if healing item, if so takes in relevant variable, creates healing item, adds to arraylist
            else if(itemName.contains("Bandage") || itemName.contains("Syringe") ||
                    itemName.contains("Med")) {
                int healAmount = Integer.parseInt(reader.nextLine());
                int stack = Integer.parseInt(reader.nextLine());
                Healing temp = new Healing(itemId, itemName, itemDesc, textDesc, healAmount, stack);
                items.add(temp);
            }
            //checks if folder item, creates folder item, adds to item arraylist
            else if(itemName.contains("Folder")) {
                Folder temp = new Folder(itemId, itemName, itemDesc, textDesc);
                items.add(temp);
                //adds folder item to relevant room item arraylist
                for(Room a: rooms) {
                    String checkFold = a.getCrates();
                    if(checkFold.equals(itemName)) {
                        a.roomItemAdd(temp);
                    }
                }
            }
            //creates weapon type objects
            else {
                int damage = Integer.parseInt(reader.nextLine());
                int uses = Integer.parseInt(reader.nextLine());
                Weapon temp = new Weapon(itemName, itemId, itemDesc, textDesc, damage, uses);
                items.add(temp);
            }
        }
    }

    /**
     * @param rooms
     * @Function: reads in data from file, creates room objects, puts them into general arraylist
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public static void readRooms(ArrayList<Room> rooms) {
        File fileIn = new File("src/Room.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (reader.hasNext()) {
            int roomId = Integer.parseInt(reader.nextLine());
            String roomName = reader.nextLine();
            String roomDesc = reader.nextLine();
            String connect = reader.nextLine();
            boolean lock = Boolean.parseBoolean(reader.nextLine());
            String crates = reader.nextLine();
            int roomPuzz = Integer.parseInt(reader.nextLine());
            int roomMon = Integer.parseInt(reader.nextLine());
            String codes = reader.nextLine();
            Room temp = new Room(roomId, roomName, roomDesc, connect, lock, crates, roomPuzz, roomMon,codes);
            rooms.add(temp);
        }
        reader.close();
    }

    /**
     * @param monsters
     * @Function: reads in data from file, creates monster objects, puts them into general arraylist
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public static void readMonsters(ArrayList<Monster> monsters) {
        File monsterIn = new File("src/Monster.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(monsterIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (reader.hasNext()) {
            int monId = Integer.parseInt(reader.nextLine());
            String monName = reader.nextLine();
            String monDesc = reader.nextLine();
            int monHP = Integer.parseInt(reader.nextLine());
            int monDam = Integer.parseInt(reader.nextLine());
            String item1 = reader.nextLine();
            String item2 = reader.nextLine();
            int prob1 = Integer.parseInt(reader.nextLine());
            int prob2 = Integer.parseInt(reader.nextLine());
            Monster temp = new Monster(monId, monName, monDesc, monHP, monDam, item1, item2, prob1, prob2);
            monsters.add(temp);
        }
        reader.close();
    }

    /**
     * @param crates
     * @Function: reads in data from file, creates crate objects, puts them into general arraylist
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public static void readCrates(ArrayList<Crate> crates) {
        File monsterIn = new File("src/Crate.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(monsterIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (reader.hasNext()) {
            String crateName = reader.nextLine();
            String crateItem = reader.nextLine();
            int roomID = Integer.parseInt(reader.nextLine());
            Crate temp = new Crate(crateName, crateItem, roomID);
            crates.add(temp);
        }
        reader.close();
    }
}
