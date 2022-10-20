
    // Our controller class can be in here or the player class
    // We will be parsing all of our text files within this class

    /*
    Final game Due nov. 6th
    Final source code will weigh the most for grading.

    don't collaborate on the 
    
    
    branch
    Make your own branch & communicate with the your team before you merge branches
    "git clone" = "download" | "pull" = "update"
    before pulling, save elsewhere in case of overwriting something important

    If you feel like there is something you need to change when implementing the game.
    Put within discord under Questions channel, and don't do it before you get everyone approval or at least my approval.
     */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class  GameConsole {
    public static void main(String[] args) {
        GameConsole item = new GameConsole();
        item.readItemTxt();
        System.out.println();
    }

    public void readItemTxt(ArrayList<Item> itemInfo) {
        try {
            File readItemData = new File("item.txt");
            Scanner inputItem = new Scanner(readItemData);

            while (inputItem.hasNext()) {

                int itemID = Integer.parseInt(inputItem.nextLine());
                String name = inputItem.nextLine();
                String itemDesc = inputItem.nextLine();
                String itemText = inputItem.nextLine();

                inputItem.nextLine();
                itemInfo.add(new Item(itemID, name, itemDesc, itemText));

            }
            inputItem.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readItemTxt() {
        try {
            File readItemData = new File("item.txt");
            Scanner inputItem = new Scanner(readItemData);

            while (inputItem.hasNext()) {
                System.out.println(inputItem.nextLine());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
