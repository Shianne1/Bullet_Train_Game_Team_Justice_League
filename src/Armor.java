public class Armor extends Item implements equipItemInterface{
    public  int armorMod;

    public Armor(String name, int id, String itemDesc, String itemText, int armorMod) {
        this.name = name;
        this.id = id;
        this.itemDesc = itemDesc;
        this.itemText = itemText;
        this.armorMod = armorMod;
    }
}
