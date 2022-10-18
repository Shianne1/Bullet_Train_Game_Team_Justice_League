public class Armor extends Item implements equipItemInterface{
    public  int armorMod;

    public Armor(String name, int id, String itemDesc, String itemText, int armorMod) {
        this.setName(name);
        this.id = id;
        this.setItemDesc(itemDesc);
        this.setItemText(itemText);
        this.armorMod = armorMod;
    }

    @Override
    public void equip() {

    }
}
