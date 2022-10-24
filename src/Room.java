import java.util.ArrayList;

/**
 * @Object: Room()
 * @Function: This OOP class will help set up the Monster objects that is needed from the monster.txt
 * when parsing information.
 * This class will interact and solve the puzzles to receive items. The puzzle will track variables such as puzzle names,
 * puzzle ID, question, answer, hint, attempts, rewards, and the command methods for the puzzles as the player interact with them.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Room
{

    private int roomId;
    private String roomName;
    private String roomDesc;
    private boolean isVisited;
    private boolean isLocked;
    private String crates;
    private int roomPuzzle;
    private int roomMonster;
    private String[] connections;
    private int north;
    private int east;
    private int south;
    private int west;
    private ArrayList<Item> roomItems;


    /**
     * @param id
     * @param name
     * @param desc
     * @param connection
     * @param lock
     * @param crates
     * @param puzzle
     * @param monster
     * @Function: constructor for pre existing data from the Room text file
     * @author(s) Dakota Smith
     * 10/17/2022
     */
    public Room(int id, String name, String desc, String connection, boolean lock, String crates,
                int puzzle, int monster)
    {
        this.roomId = id;
        this.roomName = name;
        this.roomDesc = desc;
        this.isVisited = false;
        this.isLocked = lock;
        this.crates = crates;
        this.roomPuzzle = puzzle;
        this.roomMonster = monster;
        this.roomItems = new ArrayList<>();
        connections = connection.split(",");
        this.directions(connections);
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomMonster() {
        return roomMonster;
    }

    public int getRoomPuzzle() {
        return roomPuzzle;
    }

    public int getNorth() {
        return north;
    }

    public int getEast() {
        return east;
    }

    public int getSouth() {
        return south;
    }

    public int getWest() {
        return west;
    }

    public String getCrates() {
        return crates;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void directions(String[] dir) {
        this.north = Integer.parseInt(dir[0]);
        this.east = Integer.parseInt(dir[1]);
        this.south = Integer.parseInt(dir[2]);
        this.west = Integer.parseInt(dir[3]);
    }

    //will need to pass in item arraylist and room arraylist
    public String inspectRoom(ArrayList<Monster> monsters, ArrayList<Puzzle> puzzles)
    {
        String fullDesc;
        Puzzle tempPuzz;
        Monster tempMon;
        fullDesc = roomDesc;
        if(this.roomPuzzle > -1)
        {
            tempPuzz = puzzles.get(roomPuzzle);
            fullDesc += "\nThere is Puzzle: " + tempPuzz.getPuzzleName() + ".\n ";
        }
        if(this.roomMonster > -1)
        {
            tempMon = monsters.get(roomMonster);
            fullDesc += "\nThere is Monster: " + tempMon.getMonsterName() + ".\n ";
        }
        fullDesc += this.crates;
        if(roomItems.isEmpty())
        {
            fullDesc += "\nThere are no items visible in this room.";
        }
        else
        {
            String temp = "\nThe Items in this room are: ";
            for(Item a : roomItems)
            {
                temp = temp + a.getItemName() + " ";
            }
            fullDesc += temp;
        }
        return fullDesc;
    }
}
