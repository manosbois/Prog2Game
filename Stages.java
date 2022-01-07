
public class Stages {

	private static final int MY_HERO_ENERGY = 10;
	public static Hero myHero;//Creating the object for the user;


	private static int tempHP = 0; //We temporarily save HP for checkpoints
	private static int tempAttack = 0; //We temporarily save attack for checkpoints
	private static int tempArmor = 0; //We temporarily save armor for checkpoints

	private static int apHp; //Attribute Points for HP
	private static int apAttack; //Attribute Points for Attack
	private static int apArmour; //Attribute Points for Armor
	private static int attributePoints;

	public static int getApHp() { return apHp;	}

	public static void setApHp(int apHp) {
		Stages.apHp += apHp;
		attributePoints -= apHp;
	}

	public static int getApAttack() { return apAttack;	}

	public static void setApAttack(int apAttack) {
		Stages.apAttack += apAttack;
		attributePoints -= apAttack;
	}

	public static int getApArmour() { return apArmour;	}

	public static void setApArmour(int apArmour) {
		Stages.apArmour += apArmour;
		attributePoints -= apArmour;
	}
	public static void setAttackZero() {
		attributePoints += apAttack;
		apAttack = 0;
	}
	public static void setArmorZero() {
		attributePoints += apArmour;
		apArmour = 0;
	}
	public static void setHpZero() {
		attributePoints += apHp;
		apHp = 0;
	}
	public static void setApStatsToZero() {
		apHp = 0;
		apAttack = 0;
		apArmour = 0;
	}
	public static void setAttributePoints(int attributePoints) { Stages.attributePoints = attributePoints; }
	public static int getAttributePoints() { return attributePoints; }

	private static final int ZEUS_BATTLE = 12;
	private static final int END_OF_GAME = 999;
	private static final int FIRST_CHECKPOINT = 6;
	private static final int SECOND_CHECKPOINT = 11;
	private static int i; //Declaring i var here
	//public Stages() {

	public static void stageControl() throws InterruptedException  {
		myHero= new Hero(100 , 100 , 100 , MY_HERO_ENERGY);
		myHero.setName(Game.graph.getTempHeroName());
		
		
		boolean hasDied = false;
		//Creating the object for the user

		i = 1;
		while (i <= 12) { //We define the number of attribute points
			
			setAttributePoints(Battle.battleMethod(myHero, i));
			
			if (attributePoints > 0 & i == ZEUS_BATTLE) { // The user has won the entire game
				Game.graph.createWinWindow();
			} else if (attributePoints > 0 & i != ZEUS_BATTLE) { // Give attribute points

				Game.graph.createBattleWinWindow(Battle.god.getName());

				synchronized (Battle.getLock()) {
					try {
						Battle.getLock().wait(); //Notified in Graph.734
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (i == FIRST_CHECKPOINT || i == SECOND_CHECKPOINT) { //We use temps for the checkpoints
					tempHP = myHero.getHp();
					tempAttack = myHero.getAttack();
					tempArmor = myHero.getArmour();
				}
				i++; //i will be increased only when the user wins.
			} else { //After the user's player is dead we check for the checkpoints
				if (!hasDied) {
					findCheckpoint(myHero);
					hasDied = true;
				} else {
					System.out.println("You have already used your checkpoint.");
					System.out.println("Game over!");
					Game.graph.createLoseWindow();
					i = END_OF_GAME;
				}
			}

		}
	}

	public static void findCheckpoint(Hero myHero) { //A method that finds the checkpoint of the player
		try {
			if (i <= FIRST_CHECKPOINT) { //Before the battle with the sixth god
				System.out.println("Game Over!");
				Game.graph.createLoseWindow();
				i = END_OF_GAME;
			} else if (/* i > FIRST_CHECKPOINT && */ i < ZEUS_BATTLE) { //Before the battle with the twelfth god
				myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
				i = FIRST_CHECKPOINT;
				Game.graph.createCheckpointWindow();
				synchronized (Battle.getLock()) {
					Battle.getLock().wait();
				}
			} else if (i == ZEUS_BATTLE) { //After the player has lost by the last god
				myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
				i = ZEUS_BATTLE;
				Game.graph.createCheckpointWindow();
				synchronized (Battle.getLock()) {
					Battle.getLock().wait();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	//A method that distributes the attribute points
	public static void giveAttributesPoints() {
		myHero.setStats(apHp + myHero.getHp(), apAttack + myHero.getAttack(),
				apArmour + myHero.getArmour(), MY_HERO_ENERGY);
	}

}
