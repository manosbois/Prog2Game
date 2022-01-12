
/** Class to help implement the stats of the Hero */
public class Hero extends Character {

	/** Class Constructor */
	public Hero(int tempHP, int tempAttack, int tempArmour,
				int tempEnergy) {

		super(tempHP, tempAttack, tempArmour, tempEnergy);
		this.attack = tempAttack;
		this.armour = tempArmour;
		this.energy = tempEnergy;
	}

	
	/** The attack of the Hero */
	private int attack;
	/** The armour of the Hero */
	private int armour;
	/** The energy of the Hero */
	private int energy;


	
	/** @return the Attack of the Hero */
	public int getAttack() {
		return attack;
	}

	/** @param attack, set the attack of the Hero */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/** @return the Armour of the Hero */
	public int getArmour() {
		return armour;
	}

	/** @param armour, set the armour of the Hero */
	public void setArmour(int armour) {
		this.armour = armour;
	}

	/** @return the Energy of the Hero */
	public int getEnergy() {
		return energy;
	}

	/** @param energy, set the energy of the Hero */
	public void setEnergy(int energy) { 
		this.energy = energy; 
	}

	
	/** Method that sets the Statistics of the Hero */
	public void setStats(int hp, int attack, int armour, int energy) {
		super.setHp(hp);
		setAttack(attack);
		setArmour(armour);
		setEnergy(energy);
	}

}
