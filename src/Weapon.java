public class Weapon extends Item implements equipItemInterface {
    private int durability; // the amount of uses the item has
    private final int strength; // the amount of damage the weapon deals
    public Weapon(String name, int id, String itemDesc, String itemText, int durability, int strength) {
        this.setName(name);
        this.id = id;
        this.setItemDesc(itemDesc);
        this.setItemText(itemText);
        this.durability = durability;
        this.strength = strength;
    }

    @Override
    public void equip() {

    }
}
