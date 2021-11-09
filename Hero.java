
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
	//Xreiazomaste allages stous constructors twn moves, gia na leitourgoun swsta ta parakatw antikeimena.
	private DamageMove damagingMove1 = new DamageMove(10, "Spear");
	private DamageMove damagingMove2 = new DamageMove(10, "Sword");
	private BuffMove buffMove = new BuffMove(10, "Meditate");
	private Move noMove = new Move(10, "No Move");
	
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
	
	public Hero(int h, int at, int ar, int en) {
		hp = h;
		attack = at;
		armour = ar;
		energy = en;
		tempHP = hp;
		tempAttack = attack;
		tempArmour = armour;
		tempEnergy = energy;
		//Idea an xreiastei h dhmiourgia antikeimenou mesa ston constructor
		//BuffMove buffMove = new BuffMove(10, "Meditate");
	}
	
	public Hero(int numOfBattle) {
		switch (numOfBattle) {
		// Hestia 
		// An xreiazomaste to onoma twn thewn yparxoun 2 lyseis
		// 1: Ws 2o pedio ston constructor
		// 2: Ws munhma ston xrhsth
		case 1:
			setStats(100,100,100,10);
			setTempStats(100,100,100,10);
			break;
		//Hefestus
		case 2:
			setStats(110,110,110,10);
			setTempStats(110,110,110,10);
			break;		
		// Hermes
		case 3:
			setStats(120,120,120,10);
			setTempStats(120,120,120,10);
			break;
		// Demetra
		case 4:
			setStats(130,130,130,10);
			setTempStats(130,130,130,10);
			break;
		// Aphrodite
		case 5:
			setStats(140,140,140,10);
			setTempStats(140,140,140,10);
			break;
		// Apollo
		case 6:
			setStats(150,150,150,10);
			setTempStats(150,150,150,10);
			break;
		// Artemis
		case 7:
			setStats(160,160,160,10);
			setTempStats(160,160,160,10);
			break;
		// Poseidon
		case 8:
			setStats(170,170,170,10);
			setTempStats(170,170,170,10);
			break;
		// Ares
		case 9:
			setStats(180,180,180,10);
			setTempStats(180,180,180,10);
			break;
		// Hera
		case 10:
			setStats(190,190,190,10);
			setTempStats(190,190,190,10);
			break;
		// Athena
		case 11:
			setStats(200,200,200,10);
			setTempStats(200,200,200,10);
			break;
		// Zeus
		case 12:
			setStats(210,210,210,10);
			setTempStats(210,210,210,10);
			break;
		}
	}
	

}
