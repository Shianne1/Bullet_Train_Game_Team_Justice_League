/**
     @Interface: itemInterface()
     @Function: basic interface used as a template for any similarities between actions that affect items
     @implementedBy: Item, Armor, Weapon
     @author(s) Carlton Napier, Shianne Lesure
     @added 10/16/2022
  */
interface itemInterface {
    String inspect(String item); // returns the item's description/item's text
    void use(Player player, String item); // uses the item based on its function
    void discard(String item, Room current, Player inventory); // discards the item
}
