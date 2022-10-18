import java.util.Scanner;


   /*
        Note: Any additions by Carlton Napier (me) are subject to change for better fits and changes to code
     */

public class View {

    // scanner that lets the user input through the view
    Scanner userInput = new Scanner(System.in);


    //------------------Player view methods---------------------//


    /**
     * @Method: printItemsText()
     * @Function: this code is for printing out the checkInventory method from the player model
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public void printInventory(Player player) {
        System.out.println((player.checkInventory()));
    }

    /**
     * @Method: printStatText()
     * @Function: this code prints out the stats of the player
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public void printStatText(Player player) {
        System.out.println(player.checkStats());
    }

    //------------------GameConsole view methods---------------------//

    /**
     * @Method: inputCommand()
     * @Function: basic call for the view to ask to input command
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public String inputCommand() {
        System.out.print("Input a command: ");
        return userInput.nextLine().toLowerCase().trim();
    }


    /**
     * @Method: printGameLoop()
     * @Function: this code is for printing out the start of the game loop, while asking the player for an input
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public void printGameLoop(GameState gameState) {
        System.out.println("Testing game, input command");
        // NEEDS A PROPER CODE FOR LOOPING THE GAME
        // - WHAT GOES ON IN THE ROOM/room description
        // - ANY STORY DETAILS
        // - ETC
        // THIS IS A TEMP LOOP FOR TESTING
        // example: System.out.println(gameSate.player.location.getDescription(); inputCommand();

    }


    /**
     * @Method: setSaveNameText()
     * @Function: this code  sets the text for the save game
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public String setSaveNameText() {
        System.out.println("What name would you like for your save file?");
        return inputCommand();
    }


    /**
     * @Method: loadingGameText()
     * @Function: this code prints the text asking to load, asking the player to input the name for the data
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public String loadingGameText() {
        System.out.println("What is the name of the save you want to load?");
        return inputCommand();
    }

    /**
     * @Method: savingGameText()
     * @Function: this code prints the text asking to save
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public String savingGameText() {
        System.out.println("Would you like to save the game?");
        System.out.println("[YES] [NO]");
        return inputCommand();

    }

    /**
     * @Method: startOfGameText()
     * @Function: this code prints at the very start of the game, before heading into the loop, and asks the player to choose
     * how they want to start the game
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public String startOfGameText() {
        System.out.println("Welcome to Bullet Train!");
        System.out.println("[Start Game] - [Load Game] - [Exit]");

        return inputCommand();
    }


    /**
     * @Method: printBasicText()
     * @Function: this code is just a basic printer, just to keep things tied to the view
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public void printBasicText(String string) {
        System.out.println(string);
    }

    /**
     * @Method: printExitMessage()
     * @Function: this code is ran to print out when the game is preparing to exit
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public void printExitMessage() {
        System.out.println("The game will now exit, thanks for playing!");
    }

    /**
     * @Method: printDeathMessage()
     * @Function: this code is ran to tell the player they've died and will be returned to the checkpoint
     * @author(s) Carlton Napier
     * @added 10/16/2022
     */
    public void printDeathMessage() {
        System.out.println("It seems you have died, you will now be returned to your last check point");

    }


}
