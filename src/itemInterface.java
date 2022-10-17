/*
     @Interface: itemInterface()
     @Function: basic  interface used as a template for any similarities between actions that affect items
     @implementedBy: Item, Armor, Weapon
     @author(s) Carlton Napier
     @added 10/16/2022
  */
interface itemInterface {

    String inspect(); // returns the item's description/item's text

    void use(); // uses the item based on its function

    void discard(); // discards the item
}
