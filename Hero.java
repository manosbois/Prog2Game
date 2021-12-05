package SuperCharacter.src;

public class Hero extends Character {

	public Hero(int tempHP, int tempAttack, int tempArmour,
				int tempEnergy, String name) {

		super(tempHP, tempAttack, tempArmour, tempEnergy, name);
		this.hp = tempHP;
		this.attack = tempAttack;
		this.armour = tempArmour;
		this.energy = tempEnergy;
	}

	// Stats
	private int hp;
	private int attack;
	private int armour;
	private int energy;

	// Getters & Setters for Stats
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

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
		setHp(hp);
		setAttack(attack);
		setArmour(armour);
		setEnergy(energy);
	}

}
