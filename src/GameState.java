import java.io.Serializable;
import java.util.ArrayList;
/**
     @Object: GameState()
     @Function: this class is used as a backend for the program to keep track of all objects in a game, made serializable
     to allow for saving/loading as an object to a file
     @author(s) Carlton Napier
     @added 10/16/2022
  */
public  class GameState implements Serializable {
    private Player player;
    private ArrayList<Item> itemsInGame;
    private ArrayList<Room> roomsInGame;
    private  ArrayList<Puzzle> puzzlesInGame;
    private ArrayList<Monster> monstersInGame;

    /*
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

     */

    boolean isRunning;


    /*----------------------------------------------GameState Constructors--------------------------------------------*/
    public GameState(Player player, ArrayList<Item> itemsInGame, ArrayList<Room> roomsInGame, ArrayList<Puzzle> puzzlesInGame, ArrayList<Monster> monstersInGame) {
        this.player = player;
        this.itemsInGame = itemsInGame;
        this.roomsInGame = roomsInGame;
        this.puzzlesInGame = puzzlesInGame;
        this.monstersInGame = monstersInGame;

        this.isRunning = false;
    }


    /*----------------------------------Getters & Setters for GameState variables-------------------------------------*/
    public GameState() { this.isRunning = false; }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public ArrayList<Item> getItemsInGame() { return itemsInGame; }

    public void setItemsInGame(ArrayList<Item> itemsInGame) { this.itemsInGame = itemsInGame; }

    public ArrayList<Room> getRoomsInGame() { return roomsInGame; }

    public void setRoomsInGame(ArrayList<Room> roomsInGame) { this.roomsInGame = roomsInGame; }

    public ArrayList<Puzzle> getPuzzlesInGame() { return puzzlesInGame; }

    public void setPuzzlesInGame(ArrayList<Puzzle> puzzlesInGame) { this.puzzlesInGame = puzzlesInGame; }

    public ArrayList<Monster> getMonstersInGame() { return monstersInGame; }

    public void setMonstersInGame(ArrayList<Monster> monstersInGame) { this.monstersInGame = monstersInGame; }
    public boolean isRunning() { return isRunning; }

    public void setRunning(boolean running) { isRunning = running; }


    /*--------------------------------GameState Methods for implementing the game-------------------------------------*/
    /**
     * @Method: setGameState()
     * @param loadedGame
     * @Function: allows for loading data from a different GameState into another, used for loading the game
     * @author(s): Carlton Napier
     * @added: ?
     */
    public void setGameState(GameState loadedGame) {
        this.player = loadedGame.player;
        this.itemsInGame =  loadedGame.itemsInGame;
        this.roomsInGame = loadedGame.roomsInGame ;
        this.puzzlesInGame = loadedGame.puzzlesInGame ;
        this.monstersInGame = loadedGame.monstersInGame ;
        this.isRunning = loadedGame.isRunning;
    }

}
