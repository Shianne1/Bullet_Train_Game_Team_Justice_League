import java.io.Serializable;
import java.util.ArrayList;


// Model that tracks Game Data
public  class  GameData implements Serializable {
    Player player;
    ArrayList<Item> itemsInGame;
    ArrayList<Room> roomsInGame;
    ArrayList<Puzzle> puzzlesInGame;
    ArrayList<Monster> monstersInGame;
    String helpText;
    String map;



    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    boolean isRunning;

    public GameData(Player player, ArrayList<Item> itemsInGame, ArrayList<Room> roomsInGame, ArrayList<Puzzle> puzzlesInGame, ArrayList<Monster> monstersInGame, String helpText, String map) {
        this.player = player;
        this.itemsInGame = itemsInGame;
        this.roomsInGame = roomsInGame;
        this.puzzlesInGame = puzzlesInGame;
        this.monstersInGame = monstersInGame;
        this.helpText = helpText;
        this.map = map;
    }

    public GameData() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Item> getItemsInGame() {
        return itemsInGame;
    }

    public void setItemsInGame(ArrayList<Item> itemsInGame) {
        this.itemsInGame = itemsInGame;
    }

    public ArrayList<Room> getRoomsInGame() {
        return roomsInGame;
    }

    public void setRoomsInGame(ArrayList<Room> roomsInGame) {
        this.roomsInGame = roomsInGame;
    }

    public ArrayList<Puzzle> getPuzzlesInGame() {
        return puzzlesInGame;
    }

    public void setPuzzlesInGame(ArrayList<Puzzle> puzzlesInGame) {
        this.puzzlesInGame = puzzlesInGame;
    }

    public ArrayList<Monster> getMonstersInGame() {
        return monstersInGame;
    }

    public void setMonstersInGame(ArrayList<Monster> monstersInGame) {
        this.monstersInGame = monstersInGame;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setGameData(GameData loadedGame) {
        this.player = loadedGame.player;
        this.itemsInGame =  loadedGame.itemsInGame;
        this.roomsInGame = loadedGame.roomsInGame ;
        this.puzzlesInGame = loadedGame.puzzlesInGame ;
        this.monstersInGame = loadedGame.monstersInGame ;
        this.helpText =  loadedGame.helpText;
        this.map = loadedGame.map ;
    }


}
