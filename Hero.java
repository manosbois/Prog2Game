
public class Hero {

	// pedia
	private int hp;
	private int attack;
	private int armour;
	private int energy;
	private int tempHP;
	private int tempAttack;
	private int tempArmour;
	private int tempEnergy;

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

	public int getTempHP() {
		return tempHP;
	}

	public void setTempHP(int tempHP) {
		this.tempHP = tempHP;
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

	public Hero(int h, int at, int ar, int en) {
		hp = h;
		attack = at;
		armour = ar;
		energy = en;
		
	}

	public Hero (int numOfBattle) {
		switch (numOfBattle) {
		// Hestia 
		// An xreiazomaste to onoma twn thewn yparxoun 2 lyseis
		// 1: Ws 2o pedio ston constructor
		// 2: Ws munhma ston xrhsth
		case 1:
			hp = 100;
			attack = 100;
			armour = 100;
			energy = 10;
			break;
		//Hefestus
		case 2:
			hp = 110;
			attack = 110;
			armour = 110;
			energy = 10;
			break;
		//Hermes
		case 3:
			hp = 120;
			attack = 120;
			armour = 120;
			energy = 10;
			break;
		//Demetra
		case 4:
			hp = 130;
			attack = 130;
			armour = 130;
			energy = 10;
			break;
		//Aphrodite
		case 5:
			hp = 140;
			attack = 140;
			armour = 140;
			energy = 10;
			break;
		//Apollo
		case 6:
			hp = 150;
			attack = 150;
			armour = 150;
			energy = 10;
			break;
		//Artemis
		case 7:
			hp = 160;
			attack = 160;
			armour = 160;
			energy = 10;
			break;
		//Poseidon
		case 8:
			hp = 170;
			attack = 170;
			armour = 170;
			energy = 10;
			break;
		//Ares
		case 9:
			hp = 180;
			attack = 180;
			armour = 180;
			energy = 10;
			break;
		//Hera
		case 10:
			hp = 190;
			attack = 190;
			armour = 190;
			energy = 10;
			break;
		//Athena
		case 11:
			hp = 200;
			attack = 200;
			armour = 200;
			energy = 10;
			break;
		//Zeus
		case 12:
			hp = 210;
			attack = 210;
			armour = 210;
			energy = 10;
			break;
		}
	}
	

}
