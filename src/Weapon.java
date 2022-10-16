
interface weaponInterface extends itemInterface {

    void durabilityChange(int durModifier);

}


public class Weapon extends Item implements weaponInterface {

    private int durability; // the amount of uses the item has
    private final int strength; // the amount of damage the weapon deals

    public Weapon(String name, int durability, int strength) {
        this.name = name;
        this.durability = durability;
        this.strength = strength;
    }

    @Override
    public void durabilityChange(int durModifier) {
        this.durability += durModifier;
    }

}
