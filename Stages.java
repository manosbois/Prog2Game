import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Stages {
	private static final Scanner input = new Scanner(System.in);

	private static final int MY_HERO_ENERGY = 10;
	public static Hero myHero = new Hero(100 , 100 , 100 , MY_HERO_ENERGY);//Creating the object for the user;


	protected static int tempHP = 0; //We temporarily save HP for checkpoints
	protected static int tempAttack = 0; //We temporarily save attack for checkpoints
	protected static int tempArmor = 0; //We temporarily save armor for checkpoints

	protected static int apHp; //Attribute Points for HP
	protected static int apAttack; //Attribute Points for Attack
	protected static int apArmour; //Attribute Points for Armor
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
	private static final int END_OF_GAME = 13;
	private static final int FIRST_CHECKPOINT = 5;
	private static final int SECOND_CHECKPOINT = 11;
	private static int i; //Declaring i var here
	//public Stages() {

	public static void stageControl() throws InterruptedException  {

		boolean death = false;
		//Creating the object for the user

		i = 1;
		while (i <= 12) { //We define the number of attribute points
			try {
				setAttributePoints(Battle.battleMethod(myHero, i));
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			setApStatsToZero(); //Setting apStats to 0
			// so they can be used correctly in every loop
			if (attributePoints > 0 & i == ZEUS_BATTLE) { //The user has won the entire game
				Game.graph.createWinWindow();
			}
			if (attributePoints > 0 & i != ZEUS_BATTLE) { //Give attribute points

				//Game.graph.createStatisticsWindow();

				synchronized (Battle.getLock()) {
					try {
						Battle.getLock().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (i == FIRST_CHECKPOINT || i == SECOND_CHECKPOINT) { //We use temps for the checkpoints
					tempHP = myHero.getHp();
					tempAttack = myHero.getAttack();
					tempArmor = myHero.getArmour();
				}
			} else { //After the user's player is dead we check for the checkpoints
				if (!death) {
					findCheckpoint(myHero);
					death = true;
				} else {
					System.out.println("You have already used your checkpoint.");
					System.out.println("Game over!");
					Game.graph.createLoseWindow();
					i = END_OF_GAME;
				}
			}

			i += 1;
		}
	}

	public static void findCheckpoint(Hero myHero) { //A method that finds the checkpoint of the player
		if (i < 6) { //Before the battle with the sixth god
			System.out.println("Game Over!");
			Game.graph.createLoseWindow();
			i = END_OF_GAME;
		} else if (i > 6 && i < 12) { //Before the battle with the twelfth god
			myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
			i = FIRST_CHECKPOINT;
			Game.graph.createCheckpointWindow();
		} else if (i == 12) { //After the player has lost by the last god
			myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
			i = SECOND_CHECKPOINT;
			Game.graph.createCheckpointWindow();
		}

	}

	//A method that distributes the attribute points
	public static void giveAttributesPoints() {

		int apLeft = attributePoints;

		myHero.setStats(apHp + myHero.getHp(), apAttack + myHero.getAttack(),
				apArmour + myHero.getArmour(), MY_HERO_ENERGY);
	}

}
