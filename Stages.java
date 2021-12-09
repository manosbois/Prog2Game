
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;


public class Stages {
	private final Scanner input = new Scanner(System.in);

	public static Hero myHero;
	public Stages() {
		myHero = new Hero(100 , 100 , 100 , MY_HERO_ENERGY);//Creating the object for the user
	}

	protected int tempHP = 0; //We temporarily save HP for checkpoints
	protected int tempAttack = 0; //We temporarily save attack for checkpoints
	protected int tempArmor = 0; //We temporarily save armor for checkpoints

	protected int apHp; //Attribute Points for HP
	protected int apAttack; //Attribute Points for Attack
	protected int apArmour; //Attribute Points for Armor
	private int attributePoints;

	public int getApHp() { return apHp;	}

	public void setApHp(int apHp) {
		this.apHp += apHp;
		attributePoints -= apHp;
	}

	public int getApAttack() { return apAttack;	}

	public void setApAttack(int apAttack) {
		this.apAttack += apAttack;
		attributePoints -= apAttack;
	}

	public int getApArmour() { return apArmour;	}

	public void setApArmour(int apArmour) {
		this.apArmour += apArmour;
		attributePoints -= apArmour;
	}
	public void setAttackZero() {
		attributePoints += apAttack;
		apAttack = 0;
	}
	public void setArmorZero() {
		attributePoints += apArmour;
		apArmour = 0;
	}
	public void setHpZero() {
		attributePoints += apHp;
		apHp = 0;
	}
	public void setApStatsToZero() {
		apHp = 0;
		apAttack = 0;
		apArmour = 0;
	}
	public void setAttributePoints(int attributePoints) { this.attributePoints = attributePoints; }
	public int getAttributePoints() { return attributePoints; }

	private static final int END_OF_GAME = 13;
	private static final int FIRST_CHECKPOINT = 5;
	private static final int SECOND_CHECKPOINT = 11;
	private static final int MY_HERO_ENERGY = 10;
	private int i; //Declaring i var here
	//public Stages() {

	public void stageControl()  {

		boolean death = false;
		Battle myBattle = new Battle();
		//Creating the object for the user

		i = 1;
		while (i <= 12) { //We define the number of attribute points
			try {
				attributePoints = myBattle.battleMethod(myHero, i);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			setApStatsToZero(); //Setting apStats to 0
			// so they can be used correctly in every loop

			if (attributePoints > 0) { //Give attribute points
				try {
					giveAttributesPoints();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
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
	public void giveAttributesPoints() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

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

}
