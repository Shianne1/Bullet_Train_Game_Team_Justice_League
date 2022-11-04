
import java.util.ArrayList;
import java.util.Scanner;

public class GameCodes {
    private int codeLocation;
    private String code;
    private ArrayList<GameCodes> codesInGame;

    GameConsole game = new GameConsole();
    Scanner input;

    public GameCodes(){
        codesInGame = new ArrayList<>();
        game.readCodes(codesInGame);
        input = new Scanner(System.in);
    }

    public GameCodes(int codeLocation, String code) {
        this.codeLocation = codeLocation;
        this.code = code;
    }

    public int getCodeLocation() {
        return codeLocation;
    }

    public void setCodeLocation(int codeLocation) {
        this.codeLocation = codeLocation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "GameCodes{" +
                "codeLocation=" + codeLocation +
                ", code='" + code + '\'' +
                '}';
    }

    //SHIANNE LESURE
    public void useCode(Player playerCodes, Room currentRoom, int playerLocation){
        playerCodes.checkCodeInventory();
        String currentCode;
        //String[] parts = currentCode.split(" ");
        for(GameCodes codesForGames: codesInGame) {
            System.out.print("PASSWORD: ");
            currentCode = input.nextLine();
            if (playerLocation == codeLocation) {
                if(currentCode.equalsIgnoreCase(codesForGames.code)){
                    currentRoom.setLocked(false);
                    break;
                }
                else {
                    currentCode = input.nextLine();
                }
            }
            else if (playerLocation != codeLocation){
                System.out.println("Wrong password");
            }
        }
    }
}
