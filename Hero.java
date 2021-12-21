
public class Hero extends Character {

	public Hero(int tempHP, int tempAttack, int tempArmour,
				int tempEnergy) {

		super(tempHP, tempAttack, tempArmour, tempEnergy);
		this.attack = tempAttack;
		this.armour = tempArmour;
		this.energy = tempEnergy;
	}

	// Stats
	private int attack;
	private int armour;
	private int energy;


	// Getters & Setters for Stats
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) { this.energy = energy; }

	// Useful methods
	public void setStats(int hp, int attack, int armour, int energy) {
		super.setHp(hp);
		setAttack(attack);
		setArmour(armour);
		setEnergy(energy);
	}

}
