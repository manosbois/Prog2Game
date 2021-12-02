import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;


public class Stages {
	public Scanner input = new Scanner(System.in);
	protected int tempHP = 0;//We temporarily save HP for checkpoints
	protected int tempAttack = 0;//We temporarily save attack for checkpoints
	protected int tempArmor = 0;//We temporarily save armor for checkpoints
	
	protected int ap_hp;//Attribute Points for HP
	protected int ap_attack;//Attribute Points for Attack
	protected int ap_armor;//Attribute Points for Armor
	private int attribute_points;
	
	private final static int MYHEROSENERGY = 10;
	int i;//Declaring i var here
	static Hero myHero ;
	public Stages() {
		myHero = new Hero(100 , 100 , 100 , MYHEROSENERGY);//Creating the object for the user
	}
	public void stageControl() /*throws UnsupportedAudioFileException, IOException, LineUnavailableException*/ {

		boolean death = false;
		Battle myBattle = new Battle();
		
		
		i = 1;
		while (i <= 12) {//We define the number of attribute points
			attribute_points = myBattle.BattleMethod(myHero, i);
			setApStatstoZero();//Setting apStats to 0 so they can be used correctly in every loop
			
			if (attribute_points > 0) {//Give attribute points
				giveAttributesPoints();
				
				if (i == 5 || i == 11) {//We use temps for the checkpoints
					tempHP = myHero.getHp();
					tempAttack = myHero.getAttack();
					tempArmor = myHero.getArmour();
				}
				
			} else { 	//After the user's player is dead we check for the checkpoints
				if (death == false) {
					findCheckpoint(myHero);
					death = true;
				}else {
					System.out.println("You have already used your checkpoint.");
					System.out.println("Game over!");
					i = 13;
				}
			}
			
			i += 1;
		}
	}
	
	public void findCheckpoint(Hero myHero) {//A method that finds the checkpoint of the player
		if (i<6) {//Before the battle with the sixth god
			System.out.println("Game Over!");
			i=13;
		} else if (i>6 && i<12) {//Before the battle with the twelfth god
			myHero.setStats(tempHP, tempAttack, tempArmor, MYHEROSENERGY);
			i=5;
		} else if (i==12) {//After the player has lost by the last god
			myHero.setStats(tempHP, tempAttack, tempArmor, MYHEROSENERGY);			
			i=11;
		}
		
	}
	
	public void giveAttributesPoints() /*throws UnsupportedAudioFileException, IOException, LineUnavailableException*/ {//A method that distributes the attribute points
		int key = 0;
		int ap_Left = attribute_points;
		File file2 = new File("src/Song2.wav");
		AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
		Clip clip2 = AudioSystem.getClip();
		clip2.open(audioStream2);
		clip2.start();
		
		/*do { //We ask the player about the distribution of the attributes points
			System.out.printf("You have %d attributes points! ",ap_Left);
			System.out.printf("Choose what stat you want to upgrade:\nPress 1 for Health Power.(max %d)\nPress 2 for Attack(max %d).\nPress 3 for Armor(max %d).\n",attribute_points/2,attribute_points/2,attribute_points/2);
			key=input.nextInt();
			if (key==1) {
				System.out.print("\nHealth Power:");
				ap_hp = input.nextInt() + ap_hp; 
				ap_Left = ap_Left - ap_hp;
			}else if (key==2) {
				System.out.print("\nAttack:");
				ap_attack = input.nextInt() + ap_attack;
				ap_Left = ap_Left - ap_attack;
			}else if (key==3) {
				System.out.println("\nHealth Power:");
				ap_armor = input.nextInt()  + ap_armor;
				ap_Left = ap_Left - ap_armor;
			}else {
				System.out.println("Wrong Input");
			}
			System.out.println("If you want to rearrange your attribute points press 4. Eitherweise press anything else.");
			key = input.nextInt();
			if (key == 4) {
				setApStatstoZero();
				ap_Left = attribute_points;
			}
		} while(ap_hp <= attribute_points / 2 && ap_attack <= attribute_points / 2 && ap_armor <= attribute_points / 2 
				&& (ap_hp + ap_attack + ap_armor) < attribute_points);*/
		clip2.stop();
		
		myHero.setStats(ap_hp + myHero.getHp() , ap_attack + myHero.getAttack(), ap_armor + myHero.getArmour(), MYHEROSENERGY);
	}
	
	public int getAp_hp() {
		return ap_hp;
	}

	public void setAp_hp(int ap_hp) {
		this.ap_hp += ap_hp;
		attribute_points -= ap_hp;
	}

	public int getAp_attack() {
		return ap_attack;
	}

	public void setAp_attack(int ap_attack) {
		this.ap_attack += ap_attack;
		attribute_points -= ap_attack;
	}

	public int getAp_armor() {
		return ap_armor;
	}

	public void setAp_armor(int ap_armor) {
		this.ap_armor += ap_armor;
		attribute_points -= ap_armor;
	}
	public void setAttackZero() {
		attribute_points += ap_attack;
		ap_attack = 0;
	}
	public void setArmorZero() {
		attribute_points += ap_armor;
		ap_armor = 0;
	}
	public void setHpZero() {
		attribute_points += ap_hp;
		ap_hp = 0;
	}
	public void setApStatstoZero() {
		ap_hp = 0;
		ap_attack = 0;
		ap_armor = 0;		
	}
	public void setAttributePoints(int attribute_points) {
		this.attribute_points = attribute_points;
	}
	public int getAttributePoints() {
		return attribute_points;
	}
}
