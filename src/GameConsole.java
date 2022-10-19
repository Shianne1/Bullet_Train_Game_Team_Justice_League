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

    public static void main(String[] args)
    {
        GameConsole game = new GameConsole();
        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<Monster> bestiary = new ArrayList<>();
        game.readRooms(roomList);
        game.readMonsters(bestiary);

    }

    public void readRooms(ArrayList<Room> rooms)
    {
        File fileIn = new File("src/RoomIn.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while(reader.hasNext()) {
            int roomId = Integer.parseInt(reader.nextLine());
            String roomName = reader.nextLine();
            String roomDesc = reader.nextLine();
            String connect = reader.nextLine();
            boolean lock = Boolean.parseBoolean(reader.nextLine());
            int roomItem = Integer.parseInt(reader.nextLine());
            int roomPuzz = Integer.parseInt(reader.nextLine());
            int roomMon = Integer.parseInt(reader.nextLine());
            Room temp = new Room(roomId, roomName, roomDesc, connect, lock, roomItem, roomPuzz, roomMon);
            rooms.add(temp);
        }
        reader.close();
    }

    public void readMonsters(ArrayList<Monster> monsters)
    {
        File monsterIn = new File("src/MonsterIn.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(monsterIn);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while(reader.hasNext())
        {
            int monId = Integer.parseInt(reader.nextLine());
            String monName = reader.nextLine();
            String monDesc = reader.nextLine();
            int monHP = Integer.parseInt(reader.nextLine());
            int monDam = Integer.parseInt(reader.nextLine());
            Monster temp = new Monster(monId, monName, monDesc, monHP, monDam);
            monsters.add(temp);
        }
        reader.close();
    }
}
