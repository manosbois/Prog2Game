package gr.aueb.dmst.GodsNemesis;

/** Class to help implement the Characters of the Game 
 * This class is used by God and Hero classes
 * Every Character has his individual statistics.
 * The Temp statistics are used by every Character during a fight.
 * These stats are the ones that during the battle.
 */
public abstract class Character {

    /** Class Constructor. Used only in God */
    public Character() { }

    /** Class Constructor */
    public Character(int tempHP, int tempAttack,
                     int tempArmour, int tempEnergy) {
        this.hp = tempHP;
        this.tempHP = tempHP;
        this.tempAttack = tempAttack;
        this.tempArmour = tempArmour;
        this.tempEnergy = tempEnergy;
    }
    /** The HP of the Character */
    private int hp;
    /** The tempHP of the Character */
    private int tempHP;
    /** The tempAttack of the Character */
    private int tempAttack;
    /** The tempArmour of the Character */
    private int tempArmour;
    /** The tempEnergy of the Character */
    private int tempEnergy;
    /** The name of the Character */
    private String name;

    // Moves
    /** The first damaging Move (Sword) */
    private final DamageMove damagingMove1 = new DamageMove(
            8, "Sword", 90, Graph.getLanguage() + "-DamageMove.txt",
            this.getClass().getResource("Resources/SwordSound.mp3"));
    /** The second damaging Move (Spear) */
    private final DamageMove damagingMove2 = new DamageMove(
            6, "Spear", 70, Graph.getLanguage() + "-DamageMove.txt",
            this.getClass().getResource("Resources/SpearSound.mp3"));
    /** The Buff Move (Meditate) */
    private final BuffMove buffMove = new BuffMove(
            10, "Meditate", Graph.getLanguage() + "-BuffMove.txt",
            this.getClass().getResource("Resources/Meditate.mp3"));
    /** The Protective Move (Shield) */
    private final ProtectiveMove protectiveMove = new ProtectiveMove(
            5, "Shield", Graph.getLanguage() + "-ProtectiveMove.txt",
            this.getClass().getResource("Resources/Shield.mp3"));
    /** The No Move (NoMove) */
    private final Move noMove = new Move(
            0, "No Move", Graph.getLanguage() + "-Move.txt",
            this.getClass().getResource("Resources/NoMove.mp3"));

    /** @return HP of the Character */
    public int getHp() {
        return hp;
    }

    /** @param hp, set the HP of the Character */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /** @return the tempHP */
    public int getTempHP() {
        return tempHP;
    }

    /** @param tempHP, set the TempHP of the Character */
    public void setTempHP(int tempHP) {
        this.tempHP = Math.max(tempHP, 0);
    }

    /** @return the tempAttack of the Character */
    public int getTempAttack() {
        return tempAttack;
    }

    /** @param tempAttack, set the TempAttack of the Character */
    public void setTempAttack(int tempAttack) {
        this.tempAttack = tempAttack;
    }

    /** @return the TempArmour of the Character */
    public int getTempArmour() {
        return tempArmour;
    }

    /** @param tempArmour, set the TempArmour of the Character */
    public void setTempArmour(int tempArmour) {
        this.tempArmour = tempArmour;
    }

    /** @return the TempEnergy of the Character */
    public int getTempEnergy() {
        return tempEnergy;
    }

    /** @param tempEnergy, set the TempEnergy of the Character */
    public void setTempEnergy(int tempEnergy) {
        this.tempEnergy = tempEnergy;
    }

    /** @return the Name of the Character */
    public String getName() {
        return name;
    }

    /** @param name, set the Name of the Character */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the DamagingMove1 */
    public DamageMove getDamagingMove1() {
        return damagingMove1;
    }

    /** @return the DamagingMove2 */
    public DamageMove getDamagingMove2() {
        return damagingMove2;
    }

    /** @return the BuffMove */
    public BuffMove getBuffMove() {
        return buffMove;
    }

    /** @return the ProtectiveMove */
    public ProtectiveMove getProtectiveMove() {
        return protectiveMove;
    }

    /** @return the NoMove */
    public Move getNoMove() {
        return noMove;
    }

    /** Method that sets the Temp variables */
    public void setTempStats(int hp, int attack,
                             int armour, int energy) {
        this.tempHP = hp;
        this.tempAttack = attack;
        this.tempArmour = armour;
        this.tempEnergy = energy;
        this.hp = hp;
    }
}
