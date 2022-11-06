/**
     @Interface: itemInterface()
     @Function: basic  interface used as a template for any similarities between actions that affect equip items
     @implementedBy: Item, Armor, Weapon
     @author(s) Carlton Napier
     @added 10/16/2022
  */
interface equipItemInterface extends itemInterface{
    void equip(Player player); // equips the item
}
