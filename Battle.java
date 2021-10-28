import java.util.Scanner;
import java.util.Random;
//A class that implements the battle mechanic of our game
public class Battle {
	public Scanner sc = new Scanner(System.in);//Creating Scanner object
	Random rand = new Random();//Creating Random object
	
	//The most essential method of class Battle
	//It implements the battle mechanic
	public int BattleMethod(Hero my_Hero, int num_of_Battle) {
		Hero god = new Hero(num_of_Battle);//Creating the object for the rival god
		
		do {//Start of do...while loop that implements the round system
			Move myMove = chooseMyMove(my_Hero);//Lets user choose which move to use
			Move opponentsMove = chooseOpponentsMove(god);//The PC decides which move the rival god uses
			boolean iPlayFirst = decideWhoGoesFirst(num_of_Battle);//Decides if the users goes first
			roundResult(myMove , opponentsMove , my_Hero, god , iPlayFirst);//It modifies the Stats of the objects my_Hero and god according to used moves
		}while ((my_Hero.getTempHP > 0) && god.getTempHP > 0 ); // End of do...while loop
		return my_Hero.getTempHP();//Returns the temporary Hp of the user to be used in class Main
	}//End of method BattleMethod
	
	//Lets user choose which move to use 
	public Move chooseMyMove(Hero my_Hero) {
		//Printing user's moves
		System.out.println(my_Hero.getMove1.toString());
		System.out.println(my_Hero.getMove2.toString());
		System.out.println(my_Hero.getMove3.toString());
		System.out.println(my_Hero.getMove4.toString());
		System.out.println("Choose your Move");
		//Scanner sc = new Scanner(System.in);
		int chosenMove = sc.nextInt();//Reading user's chosen move
		switch (chosenMove) {//Matching integer variable chosenMove with the right move of the user's Hero
			case 1:
				//if (my_Hero.getEnergy >= my_Hero.getMove1.getEnergy)
					return my_Hero.getMove1(); 
					break;
			case 2:
				return my_Hero.getMove2(); 
				break;
			case 3:
				return my_Hero.getMove3(); 
				break;
			case 4:	
				return my_Hero.getMove4(); 
				break;
		}//End of switch
	}//End of method chooseMymove
	
	//The PC decides which move the rival god uses
	public Move chooseOpponentsMove(Hero god) {
		//Random rand = new Random();
		int randomMove = rand.nextInt(4) + 1;//Making a random integer [1,4]
		switch (randomMove) {//Matching integer variable randomMove with the right move of the rival god
		case 1:
			return god.getMove1(); 
			break;
		case 2:
			return god.getMove2(); 
			break;
		case 3:
			return god.getMove3(); 
			break;
		case 4:	
			return god.getMove4(); 
			break;
		}//End of switch
	}//End of method chooseOpponentsMove
	
	//Decides if the users goes first
	private boolean decideWhoGoesFirst(int num_of_Battle) {
		if (num_of_Battle <= 6) {//For the first 6 Stages (Rival gods) 
			return true;
		}else if (num_of_Battle <= 9){//For the next 3 Stages the user and the rival have the same chance of going first
			//Random rand = new Random();
			return rand.nextBoolean();
		}else {//For the last 3 Stages the user always goes last
			return false;
		}//End of if
	}//End of method decideWhoGoesFirst
}
