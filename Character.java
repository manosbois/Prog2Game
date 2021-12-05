package SuperCharacter.src;

public abstract class Character {

    public Character() { }

    public Character(int tempHP, int tempAttack,
                     int tempArmour, int tempEnergy, String name) {
        this.tempHP = tempHP;
        this.tempAttack = tempAttack;
        this.tempArmour = tempArmour;
        this.tempEnergy = tempEnergy;
        this.name = name;
    }

    private int tempHP;
    private int tempAttack;
    private int tempArmour;
    private int tempEnergy;
    private String name;
    // Moves
    private final DamageMove damagingMove1 = new DamageMove(6, "Sword", 90);
    private final DamageMove damagingMove2 = new DamageMove(4, "Spear", 70);
    private final BuffMove buffMove = new BuffMove(10, "Meditate");
    private final ProtectiveMove protectiveMove = new ProtectiveMove(5, "Shield");
    private final Move noMove = new Move(0, "No Move");

    public int getTempHP() {
        return tempHP;
    }

    public void setTempHP(int tempHP) {
        this.tempHP = Math.max(tempHP, 0);
    }

    public int getTempAttack() {
        return tempAttack;
    }

    public void setTempAttack(int tempAttack) {
        this.tempAttack = tempAttack;
    }

    public int getTempArmour() {
        return tempArmour;
    }

    public void setTempArmour(int tempArmour) {
        this.tempArmour = tempArmour;
    }

    public int getTempEnergy() {
        return tempEnergy;
    }

    public void setTempEnergy(int tempEnergy) {
        this.tempEnergy = tempEnergy;
    }

    // Getter for name
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    // Getters for the Hero's Moves
    public DamageMove getDamagingMove1() {
        return damagingMove1;
    }

    public DamageMove getDamagingMove2() {
        return damagingMove2;
    }

    public BuffMove getBuffMove() {
        return buffMove;
    }

    public ProtectiveMove getProtectiveMove() {return protectiveMove;}

    public Move getNoMove() {
        return noMove;
    }

    // Useful methods

    public void setTempStats(int tempHP, int tempAttack,
                             int tempArmour, int tempEnergy) {
        this.tempHP = tempHP;
        this.tempAttack = tempAttack;
        this.tempArmour = tempArmour;
        this.tempEnergy = tempEnergy;
    }
}
