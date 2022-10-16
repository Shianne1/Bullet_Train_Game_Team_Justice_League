import java.util.ArrayList;
import java.util.Scanner;

public class View  {

    Scanner userInput = new Scanner(System.in);

    public void printItems(Object object) {
        /*

        if (object.getClass() == Room.class) {
            if (((Room) object).itemsInRoom != null && ((Room) object).itemsInRoom != null) {
                System.out.println("The items currently in the room are: ");

                for (Item item : ((Room) object).itemsInRoom) {
                    System.out.print("[" + item.getName() + "] ");
                }
            } else {
                System.out.print("There are no items in the room");

            }


        }*/
        if (object.getClass() == Player.class) {
            System.out.println(((Player) object).checkInventory());
        }

    }

    public String setSaveName()
    {
        System.out.println("What name would you like for your save file?");
        return inputCommand();
    }

    public void printFile(String fileText) {
        System.out.println(fileText);
    }

    public String loadingGame()
    {
        System.out.println("What is the name of the save you want to load?");
        return inputCommand();
    }

    public String startGame() {
        System.out.println("Welcome to Bullet Train!");
        System.out.println("[Start Game] - [Load Game] - [Exit]");

       return inputCommand();
    }

    public void printStats(Player player)
    {
        System.out.println("Name: " + player.name);
        System.out.println("Health: " +  player.health);
        System.out.println("Location: " +  player.location);
        System.out.println("Equipped Weapon: " +  player.equippedWeapon);
        System.out.println("Equipped Armor: " +  player.equippedArmor);
    }

    public void printBasicText(String string)
    {
        System.out.println(string);
    }

    public void printExitMessage()
    {
        System.out.println("The game will now exit, thanks for playing!");
    }

    public void printDeathMessage()
    {
        System.out.println("It seems you have died, you will now be returned to your last check point");

    }


    public String inputCommand() {
        System.out.print("Input a command: ");
        return userInput.nextLine().toLowerCase().trim();
    }
}
