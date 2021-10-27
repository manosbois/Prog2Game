import java.util.Scanner;

public class Stages {
	public Scanner input = new Scanner(System.in);
	public void stageControl() {
		
		int attribute_points;
		int ap_hp=0;
		int ap_attack=0;
		int ap_armor=0;
		int key;
		
		int tempHP=0;
		int tempAttack=0;
		int tempArmor=0;
		
		boolean death=false;
		Battle myBattle = new Battle();
		Hero myHero = new Hero(0);//Creating the object for the rival god
		
		int i=1;
		while (i<=12) {
			attribute_points = myBattle.BattleMethod(myHero, i);
			
			if (attribute_points>0) {//Δίνουμε attribute points
				System.out.printf("You have %d attributes points!",attribute_points);
				System.out.printf("Choose what stat you want to upgrade:\nPress 1 for Health Power.(max %d)\nPress 2 for Attack(max %d).\nPress 3 for Armor(max %d).\n",attribute_points/2,attribute_points/2,attribute_points/2);
				do { //Μοιράζουμε τα attributes points
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
				
				myHero.setHP(ap_hp);
				myHero.setAttack(ap_attack);
				myHero.setArmor(ap_hp);
				
				if (i==5 || i==11) {
					tempHP = myHero.getHP();
					tempAttack = myHero.getAttack();
					tempArmor = myHero.getArmor();
				}
				
			} else { 	//Έχει πεθάνει τσεκάρουμε για checkpoints
				if (i<6) {
					System.out.println("Game Over!");
					i=13;
				} else if (i>6 && i<12 && death==false) {
					myHero.setHP(tempHP);
					myHero.setAttack(tempAttack);
					myHero.setArmor(tempArmor);
					death=true ;
					i=5;
				} else if (i==12 && death==false) {
					myHero.setHP(tempHP);
					myHero.setAttack(tempAttack);
					myHero.setArmor(tempArmor);
					death=true ;
					i=11;
				}
			}
			
			i += 1;
		}
	}
}
