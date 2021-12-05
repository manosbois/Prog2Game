package BattleConnection.src;

//A class for Moves that do damage
public class DamageMove extends Move {

    private static final int DENOMINATOR_MULTIPLIER = 4; //A number
    // that helps the calculation in effect method
    private final int damage; //This variable show how much damage this move does

	//Constructor
    public DamageMove(int energy, String name, int damage) {
        super(energy, name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void effect(Character hero1, Character hero2, double modifier) {
        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        //Calculating damage
        double tempHP = modifier * (getDamage() * hero1.getTempAttack())
                / (DENOMINATOR_MULTIPLIER * hero2.getTempArmour());
        //Removing HP from player taking damage
        hero2.setTempHP(hero2.getTempHP() - (int) Math.round(tempHP));
        //Removing Energy from player using the move
        hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
        System.out.printf("%s's HP is now %d. The damage was %d HP.%n%n",
               hero2.getName(), hero2.getTempHP(), (int) Math.round(tempHP));
    }

    @Override
    public String toString() {
        return String.format("%s and it's damage is %d",
                super.toString(), getDamage());
    }
}
