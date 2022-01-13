
public final class Stages {

	private Stages() { }

	/** Object of Hero class that represents the user's character */
	static Hero myHero;

	/** The starting Stats of myHero variable */
	private static final int MY_HERO_ENERGY = 10, MY_HERO_HP = 100,
			MY_HERO_ATTACK = 100, MY_HERO_ARMOUR = 100;


	/** We temporarily save the user's HP for checkpoints */
	private static int tempHP = 0;
	/** We temporarily save the user's attack for checkpoints */
	private static int tempAttack = 0;
	/** We temporarily save the user's armour for checkpoints */
	private static int tempArmor = 0;

	/** Attribute Points for HP */
	private static int apHp;
	/** Attribute Points for attack */
	private static int apAttack;
	/** Attribute Points for armour */
	private static int apArmour;
	/** The remaining attribute Points */
	private static int attributePoints;

	/** @return the HP attribute points */
	public static int getApHp() { return apHp;	}

	/**
	 * Increase the HP stat of myHero and
	 * decrease by the same amount the remaining attribute points.
	 * @param apHp the amount the HP stat of myHero will increase.
	 */
	public static void setApHp(int apHp) {
		Stages.apHp += apHp;
		attributePoints -= apHp;
	}

	/** @return the attack attribute points */
	public static int getApAttack() { return apAttack;	}
	
	/**
	 * Increase the attack stat of myHero and
	 * decrease by the same amount the remaining attribute points.
	 * @param apAttack the amount the attack stat of myHero will increase.
	 */
	public static void setApAttack(int apAttack) {
		Stages.apAttack += apAttack;
		attributePoints -= apAttack;
	}

	/** @return the armour attribute points */
	public static int getApArmour() { return apArmour;	}

	/**
	 * Increase the armour stat of myHero and
	 * decrease by the same amount the remaining attribute points.
	 * @param apArmour the amount the armour stat of myHero will increase.
	 */
	public static void setApArmour(int apArmour) {
		Stages.apArmour += apArmour;
		attributePoints -= apArmour;
	}

	/**
	 * Increase the remaining attribute points by the
	 * attack attribute points
	 * and set the attack attribute points to zero.
	 * Used at reset button of attack stat.
	 * */
	public static void setAttackZero() {
		attributePoints += apAttack;
		apAttack = 0;
	}

	/** 
	 * Increase the remaining attribute points by the
	 * armour attribute points
	 * and set the armour attribute points to zero.
	 * Used at reset button of armour stat.
	 * */
	public static void setArmorZero() {
		attributePoints += apArmour;
		apArmour = 0;
	}
	
	/**
	 * Increase the remaining attribute points by the
	 * HP attribute points
	 * and set the HP attribute points to zero.
	 * Used at reset button of HP stat.
	 * */
	public static void setHpZero() {
		attributePoints += apHp;
		apHp = 0;
	}

	/**
	 * Reset the attribute points of every stat.
	 * Used in Graph.
	 */
	public static void setApStatsToZero() {
		apHp = 0;
		apAttack = 0;
		apArmour = 0;
	}

	/** @param attributePoints remaining attribute points. */
	public static void setAttributePoints(int attributePoints) { Stages.attributePoints = attributePoints; }

	/** @return the remaining attribute points. */
	public static int getAttributePoints() { return attributePoints; }

	/** The battle where the opponent is Zeus. */
	private static final int ZEUS_BATTLE = 12;
	/** Used to end change the i variable to a battle that corresponds to no god. */
	private static final int END_OF_GAME = 999;
	/** The first checkpoint (Apollo). */
	private static final int FIRST_CHECKPOINT = 6;
	/** The second checkpoint (Athena). */
	private static final int SECOND_CHECKPOINT = 11;
	/** A variable that corresponds to the number of the battle. */
	private static int i;

	/**
	 * The most important method of Stages class.
	 * Creates the hero object which is the user's character.
	 * Calls battleMethod of Battle.
	 * Creates the correct windows according to the outcome of the battles.
	 * Saves stats that are used for checkpoints and calls the method which 
	 * returns the hero's at what the were in the checkpoint.
	 */
	public static void stageControl() {
		myHero = new Hero(MY_HERO_HP, MY_HERO_ATTACK, MY_HERO_ARMOUR, MY_HERO_ENERGY);
		myHero.setName(Game.graph.getTempHeroName());


		boolean hasDied = false;
		//Creating the object for the user

		i = 1;
		while (i <= ZEUS_BATTLE) { //We define the number of attribute points

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

	/**
	 * Sets the hero's stats at what they were when they reached the checkpoint mark.
	 * It also creates the checkpoint windows or the game over windows depending on 
	 * whether the character has already used their checkpoint.
	 * 
	 * @param myHero the hero's character.
	 */
	public static void findCheckpoint(Hero myHero) { // A method that finds the checkpoint of the player
		try {
			if (i <= FIRST_CHECKPOINT) { // Before the battle with the sixth god
				System.out.println("Game Over!");
				Game.graph.createLoseWindow();
				i = END_OF_GAME;
			} else if (/* i > FIRST_CHECKPOINT && */ i < ZEUS_BATTLE) { // Before the battle with the twelfth god
				myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
				i = FIRST_CHECKPOINT;
				Game.graph.createCheckpointWindow();
				synchronized (Battle.getLock()) {
					Battle.getLock().wait();
				}
			} else if (i == ZEUS_BATTLE) { // After the player has lost by the last god
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

	/** A method that distributes the attribute points */
	public static void giveAttributesPoints() {
		myHero.setStats(apHp + myHero.getHp(), apAttack + myHero.getAttack(),
				apArmour + myHero.getArmour(), MY_HERO_ENERGY);
	}

}
