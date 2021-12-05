package SuperCharacter.src;

import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.IOException;


public class Stages {
	private final Scanner input = new Scanner(System.in);
	protected int tempHP = 0; //We temporarily save HP for checkpoints
	protected int tempAttack = 0; //We temporarily save attack for checkpoints
	protected int tempArmor = 0; //We temporarily save armor for checkpoints

	protected int apHp; //Attribute Points for HP
	protected int apAttack; //Attribute Points for Attack
	protected int apArmour; //Attribute Points for Armor

	private static final int END_OF_GAME = 13;
	private static final int FIRST_CHECKPOINT = 5;
	private static final int SECOND_CHECKPOINT = 11;
	private static final int MY_HERO_ENERGY = 10;
	private int i; //Declaring i var here
	//public Stages() {

	public void stageControl() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {

		int attributePoints;
		boolean death = false;
		Battle myBattle = new Battle();
		Hero myHero = new Hero(100, 100, 100, MY_HERO_ENERGY, "THE HERO");
		//Creating the object for the user

		i = 1;
		while (i <= 12) { //We define the number of attribute points
			attributePoints = myBattle.battleMethod(myHero, i);
			setApStatsToZero(); //Setting apStats to 0
			// so they can be used correctly in every loop
		
			if (attributePoints > 0) { //Give attribute points
				giveAttributesPoints(attributePoints, myHero);
			
				if (i == FIRST_CHECKPOINT || i == SECOND_CHECKPOINT) { //We use temps for the checkpoints
					tempHP = myHero.getHp();
					tempAttack = myHero.getAttack();
					tempArmor = myHero.getArmour();
				}
			
			} else { //After the user's player is dead we check for the checkpoints
				if (death == false) {
					findCheckpoint(myHero);
					death = true;
				} else {
					System.out.println("You have already used your checkpoint.");
					System.out.println("Game over!");
					i = END_OF_GAME;
				}
			}
	
			i += 1;
		}
	}

	public void findCheckpoint(Hero myHero) { //A method that finds the checkpoint of the player
		if (i < 6) { //Before the battle with the sixth god
			System.out.println("Game Over!");
			i = END_OF_GAME;
		} else if (i > 6 && i < 12) { //Before the battle with the twelfth god
			myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
			i = FIRST_CHECKPOINT;
		} else if (i == 12) { //After the player has lost by the last god
			myHero.setStats(tempHP, tempAttack, tempArmor, MY_HERO_ENERGY);
			i = SECOND_CHECKPOINT;
		}

	}

	//A method that distributes the attribute points
	public void giveAttributesPoints(int attributePoints, Hero myHero)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		int key;
		int apLeft = attributePoints;

		/*File file2 = new File("Song2.wav");
		AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
		Clip clip2 = AudioSystem.getClip();
		clip2.open(audioStream2);
		clip2.loop(Clip.LOOP_CONTINUOUSLY);*/

		do { //We ask the player about the distribution of the attributes points
			System.out.printf("You have %d attributes points! ", apLeft);
			System.out.printf("Choose which stat you want to upgrade:"
							+ "%nPress 1 for Health Power.(max %d)%nPress 2 for "
							+ "Attack(max %d).%nPress 3 for Armor(max %d).%n",
					attributePoints / 2, attributePoints / 2, attributePoints / 2);
			key = input.nextInt();
			if (key == 1) {
				System.out.printf("%nHealth Power:");
				apHp = input.nextInt() + apHp; 
				apLeft = apLeft - apHp;
			} else if (key == 2) {
				System.out.printf("%nAttack:");
				apAttack = input.nextInt() + apAttack;
				apLeft = apLeft - apAttack;
			} else if (key == 3) {
				System.out.printf("%nArmour:");
				apArmour = input.nextInt()  + apArmour;
				apLeft = apLeft - apArmour;
			} else {
				System.out.println("Wrong Input");
			}
			System.out.println("If you want to rearrange your "
					+ "attribute points press 4. Otherwise press anything else.");
			key = input.nextInt();
			if (key == 4) {
				setApStatsToZero();
				apLeft = attributePoints;
			}
		} while (apHp <= attributePoints / 2 && apAttack <= attributePoints / 2
				&& apArmour <= attributePoints / 2
				&& (apHp + apAttack + apArmour) < attributePoints);
		/*clip2.stop();*/
		
		myHero.setStats(apHp + myHero.getHp(), apAttack + myHero.getAttack(), apArmour + myHero.getArmour(), MY_HERO_ENERGY);
	}

	public void setApStatsToZero() {
		apHp = 0;
		apAttack = 0;
		apArmour = 0;
	}
}
