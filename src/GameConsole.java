import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
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
        View view = new View();
        GameData gameData = new GameData();


        while(!gameData.isRunning)
        startGame(gameData, view);

        while (gameData.isRunning()) //game loop
        {
            if (gameData.player.health <= 0) {
                playerDeath(gameData, view);
            }

            System.out.println("Testing game, input command");
            parseCommand(gameData, view);

        }
    }

    private static void parseCommand(GameData gameData, View view) {
        String inputCommand = view.inputCommand();

        if (inputCommand.equals("save game")) {
            saveGame(gameData);
        }

        if (inputCommand.equals("load game")) {
            gameData.setGameData(loadGame(view.loadingGame()));
        }

        if (inputCommand.equals("set checkpoint")) {
            gameData.getPlayer().setCheckpoint(gameData);
        }

        if (inputCommand.equals("get checkpoint")) {
            gameData.setGameData(gameData.getPlayer().getCheckpoint());
        }

        if (inputCommand.equals("exit")) {
            endGame(gameData,  view);
        }

        if (inputCommand.equals("stats")) {
            view.printStats(gameData.getPlayer());
        }
        if (inputCommand.equals("help")) {
            view.printBasicText(gameData.getHelpText());
        }

        if (inputCommand.equals("map")) {
            view.printBasicText(gameData.getMap());
        }

        if (inputCommand.equals("damage")) {
            Random rand = new Random();

            int randomDamage = rand.nextInt(2, 50);
            gameData.getPlayer().takeDamage(randomDamage);
        }

        if (inputCommand.equals("heal")) {
            Random rand = new Random();

            int randomDamage = rand.nextInt(2, 50);
            gameData.getPlayer().healHealth(randomDamage);
        }

        if (inputCommand.equals("inventory")) {
            view.printItems(gameData.getPlayer());
        }

        if (inputCommand.equals("tostring")) {
            view.printBasicText(gameData.toString());
        }

        if (inputCommand.equals("death")) {
            gameData.getPlayer().health = 0;
        }

    }

    public static void startGame(GameData gameData, View view) { // code to run at the start of the game to determine starting a new file or loading
        String inputCommand = view.startGame();

        if (inputCommand.equals("start game")) // if the player wants to start a new game
        {
            String playerName = view.setSaveName();
            gameData.setGameData(newGame(playerName));
            gameData.setRunning(true);
        } else if (inputCommand.equals("load game")) {
            gameData.setGameData(loadGame(view.loadingGame()));
            gameData.setRunning(true);

        } else if (inputCommand.equals("exit")) {
            endGame(gameData,  view);

        }
    }


    public static GameData newGame(String playerName) {

        GameData newGame = new GameData();

        newGame.setPlayer(new Player(playerName));

           /* gameData.setItemsInGame(parseItemData());
            gameData.setRoomsInGame(parseRoomData());
            gameData.setPuzzlesInGame(parsePuzzleData());
            gameData.setMonstersInGame(parseMonsterData());
            gameData.setHelpText(parseTextFile(new File("Help.txt")));
            gameData.setMap(parseTextFile(new File("Map.txt"))); */


        ArrayList<Item> emptyItemList = new ArrayList<>();
        ArrayList<Room> emptyRoomList = new ArrayList<>();
        ArrayList<Puzzle> emptyPuzzleList = new ArrayList<>();
        ArrayList<Monster> emptyMonsterList = new ArrayList<>();

        newGame.setItemsInGame(emptyItemList);
        newGame.setRoomsInGame(emptyRoomList);
        newGame.setPuzzlesInGame(emptyPuzzleList);
        newGame.setMonstersInGame(emptyMonsterList);
        newGame.setHelpText(parseTextFile(new File("src/Help.txt")));
        newGame.setMap(parseTextFile(new File("src/Map.txt")));

        newGame.setRunning(true);


        return newGame;
    }

    public static GameData loadGame(String playerName) {
        try {
            File gameStateData = new File("src/saveData/" + playerName + "data.bin");

            System.out.println(playerName);

            FileInputStream dataFile = new FileInputStream(gameStateData);
            ObjectInputStream dataInput = new ObjectInputStream(dataFile);

            GameData loadedGame = (GameData) dataInput.readObject();
            return loadedGame;


        } catch (FileNotFoundException e) {
            System.out.println("There is no file");
            return null;
        } catch (IOException e) {
            System.out.println("Object not found");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            return null;
        }


    }

    public static void saveGame(GameData gameState) {
        try {
            File gameStateData = new File("src/saveData/" + gameState.player.getName() + "data.bin");

            FileOutputStream dataFile = new FileOutputStream(gameStateData);
            ObjectOutputStream dataOutput = new ObjectOutputStream(dataFile);

            dataOutput.writeObject(gameState);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endGame(GameData gameState, View view) {
        view.printExitMessage();
        gameState.setRunning(false);
        System.exit(0);
    }

    public static void playerDeath(GameData gameState, View view) {
        view.printDeathMessage();
        if (gameState.getPlayer().hasCheckPoint) {
            gameState.setGameData(newGame(gameState.getPlayer().getName()));
        } else {
            gameState.setGameData(gameState.getPlayer().getCheckpoint());
        }
    }

    public static String parseTextFile(File textFile) { //generic text file method for flexibility (used for Help, can be used for Map)
        try {
            Scanner textFileIn = new Scanner(textFile);
            String textData = "";


            //creates a string with the map data
            while (textFileIn.hasNext()) {
                textData = textData.concat(textFileIn.nextLine() + "\n");
            }

            return textData;
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect File");
            return "Data not Found";
        }
    }

    public static ArrayList<Item> parseItemData() {
        try {
            File itemData = new File("src/Item.txt");
            ArrayList<Item> itemListData = new ArrayList<>();

            Scanner itemDataIn = new Scanner(itemData); //scans the file for the item data


            //creates item objects based off the data file
            while (itemDataIn.hasNext()) {
                itemListData.add(
                        new Item(Integer.parseInt(itemDataIn.nextLine()),
                                itemDataIn.nextLine(),
                                itemDataIn.nextLine(),
                                itemDataIn.nextLine()
                        ));
            }

            return itemListData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<Room> parseRoomData() {
        try {
            File roomData = new File("src/Room.txt");
            ArrayList<Room> roomListData = new ArrayList<>();

            Scanner roomDataIn = new Scanner(roomData); //scans the file for the item data


            //creates item objects based off the data file
            while (roomDataIn.hasNext()) {
                roomListData.add(
                        new Room(
                        ));
            }

            return roomListData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<Puzzle> parsePuzzleData() {
        try {
            File puzzleData = new File("src/Room.txt");
            ArrayList<Puzzle> puzzleListData = new ArrayList<>();

            Scanner puzzleDataIn = new Scanner(puzzleData); //scans the file for the item data


            //creates item objects based off the data file
            while (puzzleDataIn.hasNext()) {
                puzzleListData.add(
                        new Puzzle(
                        ));
            }

            return puzzleListData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<Monster> parseMonsterData() {
        try {
            File monsterData = new File("src/Monster.txt");
            ArrayList<Monster> monsterListData = new ArrayList<>();

            Scanner monsterDataIn = new Scanner(monsterData); //scans the file for the item data

            //creates item objects based off the data file
            while (monsterDataIn.hasNext()) {
                monsterListData.add(
                        new Monster(
                        ));
            }

            return monsterListData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
