import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Object: Room()
 * @Function: This OOP class will help set up the Room objects that is needed from the room.txt
 * when parsing information.
 * This class will store other objects . The room will track variables such as room names,
 * room ID, description, if it's visited or locked, what crates, puzzles, items or monsters are in the room,
 * what direction the player can move and the command methods for inspecting the room and adding items
 * to the room item arraylist.
 * @author(s) Dakota Smith
 * @added 10/17/2022
 */
public class Room implements Serializable {
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


    /*----------------------------------------------Room Constructors-------------------------------------------------*/
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
                int puzzle, int monster) {
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

    /*-------------------------------------Getters & Setters for Room variables---------------------------------------*/
    public int getRoomId() { return roomId; }

    public String getRoomName() { return roomName; }

    public int getRoomMonster() { return roomMonster; }

    public int getRoomPuzzle() { return roomPuzzle; }

    public int getNorth() { return north; }

    public int getEast() { return east; }

    public int getSouth() { return south; }

    public int getWest() { return west; }

    public String getCrates() { return crates; }

    public boolean isLocked() { return isLocked; }

    public boolean isVisited() { return isVisited; }


    /*-----------------------------------Room Methods for implementing the game---------------------------------------*/
    public void directions(String[] dir) {
        this.north = Integer.parseInt(dir[0]);
        this.east = Integer.parseInt(dir[1]);
        this.south = Integer.parseInt(dir[2]);
        this.west = Integer.parseInt(dir[3]);
    }

    /**
     * @Method: inspectRoom()
     * @param monsters
     * @param puzzles
     * @return fullDesc
     * @Function: when called on, prints out all relevant information about room
     * and what it contains
     * @author: Dakota Smith
     * 10/19/2022
     */
    public String inspectRoom(ArrayList<Monster> monsters, ArrayList<Puzzle> puzzles) {
        String fullDesc;
        Puzzle tempPuzz;
        Monster tempMon;
        fullDesc = roomDesc;
        //Checks if the room has a puzzle, if so adds puzzle name to the room inspect variable
        if(this.roomPuzzle > -1) {
            tempPuzz = puzzles.get(roomPuzzle);
            fullDesc += "\nThere is Puzzle: " + tempPuzz.getPuzzleName() + ".\n ";
        }
        //checks if the room has a monster, if so adds monster name to room inspect string
        if(this.roomMonster > -1) {
            tempMon = monsters.get(roomMonster);
            fullDesc += "\nThere is Monster: " + tempMon.getMonsterName() + ".\n ";
        }
        //adds if there are any item crates in room
        fullDesc += this.crates;
        //checks if there are No items
        if(roomItems.isEmpty()) {
            fullDesc += "\nThere are no items visible in this room.";
        }
        else {
            String temp = "\nThe Items in this room are: ";
            for(Item a : roomItems) {
                temp = temp + a.getItemName() + " ";
            }
            fullDesc += temp;
        }
        return fullDesc;
    }

    /**
     * @Method: roomItemAdd()
     * @param item
     * @Function: when called on, adds passed item to room item arraylist
     * @author: Dakota Smith
     * 10/25/2022
     */
    public void roomItemAdd(Item item)
    {
        roomItems.add(item);
    }

    /**
     * @Method: roomItemRemove()
     * @param item
     * @Function: will remove item from roomitem arraylist
     * @author: Shianne Lesure
     * 10/29/2022
     */
    public void roomItemRemove(Item item) { roomItems.remove(item); }
}
