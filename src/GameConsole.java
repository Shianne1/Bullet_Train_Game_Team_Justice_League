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

    public static void main(String[] args) {
        GameConsole game = new GameConsole();
        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<Monster> bestiary = new ArrayList<>();
        ArrayList<Crate> crateList = new ArrayList<>();
        ArrayList<Item> itemList = new ArrayList<>();
        game.readRooms(roomList);
        game.readMonsters(bestiary);
        game.readCrates(crateList);
        game.readItems(itemList, roomList);
    }

    /**
     * @param items
     * @param rooms
     * @Function: reads in data from file, creates item/armor/weapon/folder/healing objects,
     * puts them into general arraylist
     * @author(s) Dakota Smith
     * 10/26/2022
     */
    public void readItems(ArrayList<Item> items, ArrayList<Room> rooms)
    {
        File fileIn = new File("src/Item.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while(reader.hasNext())
        {
            //takes in all variables that are shared by all items
            int itemId = Integer.parseInt(reader.nextLine());
            String itemName = reader.nextLine();
            String itemDesc = reader.nextLine();
            String textDesc = reader.nextLine();
            //checks if item is a type of armor, if so, takes in relevant armor variables
            //creates armor item, adds to item arraylist
            if(itemName.contains("Armor"))
            {
                int AC = Integer.parseInt(reader.nextLine());
                Armor temp = new Armor(itemId, itemName, itemDesc, textDesc, AC);
                items.add(temp);
            }
            //checks if healing item, if so takes in relevant variable, creates healing item, adds to arraylist
            else if(itemName.contains("Bandage") || itemName.contains("Syringe") ||
                    itemName.contains("Med"))
            {
                int healAmount = Integer.parseInt(reader.nextLine());
                int stack = Integer.parseInt(reader.nextLine());
                Healing temp = new Healing(itemId, itemName, itemDesc, textDesc, healAmount, stack);
                items.add(temp);
            }
            //checks if folder item, creates folder item, adds to item arraylist
            else if(itemName.contains("Folder"))
            {
                Folder temp = new Folder(itemId, itemName, itemDesc, textDesc);
                items.add(temp);
                //adds folder item to relevant room item arraylist
                for(Room a: rooms)
                {
                    String checkFold = a.getCrates();
                    if(checkFold.equals(itemName))
                    {
                        a.roomItemAdd(temp);
                    }
                }
            }
            //creates weapon type objects
            else
            {
                int damage = Integer.parseInt(reader.nextLine());
                int uses = Integer.parseInt(reader.nextLine());
                Weapon temp = new Weapon(itemId, itemName, itemDesc, textDesc, damage, uses);
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
    public void readRooms(ArrayList<Room> rooms) {
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
            Room temp = new Room(roomId, roomName, roomDesc, connect, lock, crates, roomPuzz, roomMon);
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
    public void readMonsters(ArrayList<Monster> monsters) {
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
    public void readCrates(ArrayList<Crate> crates) {
        File monsterIn = new File("src/Crate.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(monsterIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (reader.hasNext())
        {
            String crateName = reader.nextLine();
            String crateItem = reader.nextLine();
            int roomID = Integer.parseInt(reader.nextLine());
            Crate temp = new Crate(crateName, crateItem, roomID);
            crates.add(temp);
        }
        reader.close();
    }
}
