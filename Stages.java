import java.util.Scanner;

public class Stages {
	public Scanner input = new Scanner(System.in);
	protected int tempHP=0;//We temporarily save HP for checkpoints
	protected int tempAttack=0;//We temporarily save attack for checkpoints
	protected int tempArmor=0;//We temporarily save armor for checkpoints
	
	protected int ap_hp=0;//Attribute Points for HP
	protected int ap_attack=0;//Attribute Points for Attack
	protected int ap_armor=0;//Attribute Points for Armor
	
	public void stageControl() {
		
		int attribute_points;
		boolean death=false;
		Battle myBattle = new Battle();
		Hero myHero = new Hero(0);//Creating the object for the rival god
		
		int i=1;
		while (i<=12) {//We define the number of attribute points
			attribute_points = myBattle.BattleMethod(myHero, i);
			
			if (attribute_points>0) {//Give attribute points
				giveAttributesPoints(attribute_points, myHero);
				
				if (i==5 || i==11) {//We use temps for the checkpoints
					tempHP = myHero.getHp();
					tempAttack = myHero.getAttack();
					tempArmor = myHero.getArmour();
				}
				
			} else { 	//After he have died we check about the checkpoints
				if (death==false) {
					findCheckpoint(i, myHero);
					death = true;
				}else {
					System.out.println("You have already used your checkpoint.");
				}
			}
			
			i += 1;
		}
	}
	public void findCheckpoint(int i, Hero myHero) {//A method that finds the checkpoint of the player
		if (i<6) {//Before the battle with the sixth god
			System.out.println("Game Over!");
			i=13;
		} else if (i>6 && i<12) {//Before the battle with the twelfth god
			myHero.setHp(tempHP);
			myHero.setAttack(tempAttack);
			myHero.setArmour(tempArmor);
			i=5;
		} else if (i==12) {//After the player had lost by the last god
			myHero.setHp(tempHP);
			myHero.setAttack(tempAttack);
			myHero.setArmour(tempArmor);
			
			i=11;
		}
		
	}
	public void giveAttributesPoints(int attribute_points, Hero myHero) {//A method that distributes the attribute points
		int key=0;
		//Να φτιάξουμε το max δια 2
		do { //We ask the player about the distribution of the attributes points
			System.out.printf("You have %d attributes points!",attribute_points);
			System.out.printf("Choose what stat you want to upgrade:\nPress 1 for Health Power.(max %d)\nPress 2 for Attack(max %d).\nPress 3 for Armor(max %d).\n",attribute_points/2,attribute_points/2,attribute_points/2);
			key=input.nextInt();
			if (key==1) {
				System.out.print("Health Power:");
				ap_hp = input.nextInt();
			}else if (key==2) {
				System.out.print("\nAttack:");
				ap_attack = input.nextInt();
			}else if (key==3) {
				System.out.println("\nHealth Power:");
				ap_armor = input.nextInt();
			}else {
				System.out.println("Wrong Input");
			}
			System.out.println("If you want to rearrange your attribute points press 4 or press Enter.");
			key = input.nextInt();
			if (key==4) {
				ap_hp=0;
				ap_attack=0;
				ap_armor=0;
			}
		} while(ap_hp<=attribute_points/2 && ap_attack<=attribute_points/2 && ap_armor<=attribute_points/2 && ap_hp+ap_attack+ap_armor==attribute_points);
		
		myHero.setHp(ap_hp);
		myHero.setAttack(ap_attack);
		myHero.setArmour(ap_hp);
	}
}
