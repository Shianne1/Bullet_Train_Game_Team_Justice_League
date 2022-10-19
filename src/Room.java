public class Room extends Player
{
    /**
     * tried an approach, found it too intensive on the side of writing the text file
     * will likely have to come back to make minor changes depending on how we want
     * item and puzzle ids to operate.
     *
     * New approach doesn't read in boolean of if room has puzzle/item/monster, but
     * uses the value of the Id to see if one is present or not.
     */

    private int roomId;
    private String roomName;
    private String roomDesc;
    //private String roomCon;
    private boolean isVisited;
    private boolean isLocked;
    private boolean hasItem;
    private boolean hasPuzzle;
    private boolean hasMonster;
    private int roomItem;
    private int roomPuzzle;
    private int roomMonster;
    private String[] connections;
    private int north;
    private int east;
    private int south;
    private int west;

    public Room(int id, String name, String desc, String connection, boolean lock, int item, int puzzle, int monster)
    {
        this.roomId = id;
        this.roomName = name;
        this.roomDesc = desc;
        //this.roomCon = connection;
        this.isVisited = false;
        this.isLocked = lock;
        /**
        this.hasItem = hasIt;
        this.hasPuzzle = hasPuz;
        this.hasMonster = hasMon;
        */
        this.roomItem = item-1;
        this.roomPuzzle = puzzle-1;
        this.roomMonster = monster-1;
        if(item > 0)
            this.hasItem = true;
        else
            this.hasItem = false;
        if(puzzle > 0)
            this.hasPuzzle = true;
        else
            this.hasPuzzle = false;
        if(monster > 0)
            this.hasMonster = true;
        else
            this.hasMonster = false;

        connections = connection.split(",");
        this.directions(connections);
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomItem() {
        return roomItem;
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

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void directions(String[] dir) {
        this.north = Integer.parseInt(dir[0]) -1;
        this.east = Integer.parseInt(dir[1]) -1;
        this.south = Integer.parseInt(dir[2]) -1;
        this.west = Integer.parseInt(dir[3]) -1;
    }

    //will need to pass in item arraylist and room arraylist
    public String inspectRoom()
    {
        String fullDesc;
        fullDesc = roomDesc;

        return fullDesc;
    }
}
