import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    ArrayList<Item> itemsInRoom = new ArrayList<>();

    public Room() {
    }

    public ArrayList<Item> getItemsInRoom() {
        return itemsInRoom;
    }



    /*
    while you are implementing the game, just ask us if you need any help
    Look at pg. 4, 5, 6, 21
     */
}
