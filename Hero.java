
public class Hero {

	// Stats
	private int hp;
	private int attack;
	private int armour;
	private int energy;
	// Temp Stats
	private int tempHP;
	private int tempAttack;
	private int tempArmour;
	private int tempEnergy;
	private String name;
	// Moves
	private DamageMove damagingMove1 = new DamageMove(6, "Sword", 90);
	private DamageMove damagingMove2 = new DamageMove(4, "Spear", 70);
	private BuffMove buffMove = new BuffMove(10, "Meditate");
	private Move noMove = new Move(0, "No Move");

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

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	// Getters & Setters for Temp Stats
	public int getTempHP() {
		return tempHP;
	}

	public void setTempHP(int tempHP) {
		if (tempHP < 0) {
			this.tempHP = 0;
		} else {
			this.tempHP = tempHP;
		}
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
	public String getName() {
		return name;
	}

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

	public Move getNoMove() {
		return noMove;
	}

	// Useful methods
	public void setStats(int hp, int attack, int armour, int energy) {
		this.hp = hp;
		this.attack = attack;
		this.armour = armour;
		this.energy = energy;
	}

	public void setTempStats(int tempHP, int tempAttack, int tempArmour, int tempEnergy) {
		this.tempHP = tempHP;
		this.tempAttack = tempAttack;
		this.tempArmour = tempArmour;
		this.tempEnergy = tempEnergy;
	}

	// Constructors
	public Hero(int hp, int attack, int armour, int energy, String name) {
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.armour = armour;
		this.energy = energy;
		tempHP = hp;
		tempAttack = attack;
		tempArmour = armour;
		tempEnergy = energy;
		// Idea an xreiastei h dhmiourgia antikeimenou mesa ston constructor
		// BuffMove buffMove = new BuffMove(10, "Meditate");
	}

	public Hero(int numOfBattle) {
		switch (numOfBattle) {
		// Hestia
		case 1:
			this.name = "Hestia";
			setStats(100, 100, 100, 10);
			setTempStats(100, 100, 100, 10);
			break;
		// Hefestus
		case 2:
			this.name = "Hefestus";
			setStats(110, 110, 110, 10);
			setTempStats(110, 110, 110, 10);
			break;
		// Hermes
		case 3:
			this.name = "Hermes";
			setStats(120, 120, 120, 10);
			setTempStats(120, 120, 120, 10);
			break;
		// Demetra
		case 4:
			this.name = "Demetra";
			setStats(130, 130, 130, 10);
			setTempStats(130, 130, 130, 10);
			break;
		// Aphrodite
		case 5:
			this.name = "Aphrodite";
			setStats(140, 140, 140, 10);
			setTempStats(140, 140, 140, 10);
			break;
		// Apollo
		case 6:
			this.name = "Apollo";
			setStats(150, 150, 150, 10);
			setTempStats(150, 150, 150, 10);
			break;
		// Artemis
		case 7:
			this.name = "Artemis";
			setStats(160, 160, 160, 10);
			setTempStats(160, 160, 160, 10);
			break;
		// Poseidon
		case 8:
			this.name = "Poseidon";
			setStats(170, 170, 170, 10);
			setTempStats(170, 170, 170, 10);
			break;
		// Ares
		case 9:
			this.name = "Ares";
			setStats(180, 180, 180, 10);
			setTempStats(180, 180, 180, 10);
			break;
		// Hera
		case 10:
			this.name = "Hera";
			setStats(190, 190, 190, 10);
			setTempStats(190, 190, 190, 10);
			break;
		// Athena
		case 11:
			this.name = "Athena";
			setStats(200, 200, 200, 10);
			setTempStats(200, 200, 200, 10);
			break;
		// Zeus
		case 12:
			this.name = "Zeus";
			setStats(210, 210, 210, 10);
			setTempStats(210, 210, 210, 10);
			break;
		}
	}

}
