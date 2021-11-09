import java.util.Scanner;
import java.util.Random;
//A class that implements the battle mechanic of our game
public class Battle {
	
	private final int energyReplenishment = 8;//The variable that shows how much energy is replenished every round
	
	//The most essential method of class Battle
	//It implements the battle mechanic
	public int BattleMethod(Hero my_Hero, int num_of_Battle) {
		Hero god = new Hero(num_of_Battle);//Creating the object for the rival god
		boolean roundEnds;
		do {//Start of do...while loop that implements the round system
			Move myMove = chooseMyMove(my_Hero);//Lets user choose which move to use
			Move opponentsMove = chooseOpponentsMove(god);//The PC decides which move the rival god uses
			boolean iPlayFirst = decideWhoGoesFirst(num_of_Battle);//Decides if the users goes first
			roundResult(myMove , opponentsMove , my_Hero, god , iPlayFirst);//It modifies the TempStats of the objects my_Hero and god according to used moves
			roundEnds = (my_Hero.getTempHP <= 0) || (god.getTempHP <= 0);//The round ends when either side's tempHP is below zero
			if (roundEnds == false)//It will not replenish the energy if either side has lost
				replenishEnergy(my_Hero, god);//Replenishes the energy of the hero and god
		}while (roundEnds == false); // End of do...while loop
		
		return my_Hero.getTempHP();//Returns the temporary Hp of the user to be used in class Main
	}//End of method BattleMethod
	
	//Lets user choose which move to use
	public Move chooseMyMove(Hero my_Hero) {
		boolean sufficientEnergy;
		do {
			sufficientEnergy = true;
			//Printing user's moves
			System.out.println(my_Hero.getDamagingMove1.toString());
			System.out.println(my_Hero.getDamagingMove2.toString());
			System.out.println(my_Hero.getBuffMove.toString());
			System.out.println(my_Hero.getNoMove.toString());
			System.out.println("Choose your Move");
			Scanner sc = new Scanner(System.in);//Creating Scanner object
			int chosenMove = sc.nextInt();//Reading user's chosen move
			Move move;//Creating a variable of type Move to assist us in switch structure
			switch (chosenMove) {//Matching integer variable chosenMove with the right move of the user's Hero
				case 1:
					move = my_Hero.getDamagingMove1(); //Assigning the chosen move to the variable move
					break;
				case 2:
					move = my_Hero.getDamagingMove2(); 
					break;
				case 3:
					move = my_Hero.getBuffMove(); 
					break;
				case 4:	
					move = my_Hero.getNoMove(); 
					break;
			}//End of switch
			if (my_Hero.getTempEnergy < move.getEnergy) {//Checking if the user has enough energy
				sufficientEnergy = false;
				System.out.printf("You need %d more Energy to use the move %s\n" , move.getEnergy() - my_Hero.getTempEnergy() , move.getName());
			}
		}while(sufficientEnergy == false);
	}//End of method chooseMymove
	
	//The PC decides which move the rival god uses
	public Move chooseOpponentsMove(Hero god) {
		Random rand = new Random();//Creating Random object
		boolean sufficientEnergy;
		do {		
			sufficientEnergy = true;	
			int randomMove = rand.nextInt(4) + 1;//Making a random integer [1,4]
			Move move;//Creating a variable of type Move to assist us in switch structure
			switch (randomMove) {//Matching integer variable randomMove with the right move of the rival god
			case 1:
				move = god.getDamagingMove1();//Assigning the chosen move to the variable move
				break;
			case 2:
				move = god.getDamagingMove2(); 
				break;
			case 3:
				move = god.getBuffMove(); 
				break;
			case 4:	
				move = god.getNoMove(); 
				break;
			}//End of switch
			if (god.getTempEnergy < move.getEnergy)//Checking if the opponent has enough energy
				sufficientEnergy = false;
		}while (sufficientEnergy == false);
	}//End of method chooseOpponentsMove
	
	//Decides if the users goes first
	private boolean decideWhoGoesFirst(int num_of_Battle) {
		if (num_of_Battle <= 6) {//For the first 6 Stages (Rival gods) 
			return true;
		}else if (num_of_Battle <= 9){//For the next 3 Stages the user and the rival have the same chance of going first
			Random rand = new Random();
			return rand.nextBoolean();
		}else {//For the last 3 Stages the user always goes last
			return false;
		}//End of if
	}//End of method decideWhoGoesFirst
	
	//It modifies the TempStats of the objects my_Hero and god according to used moves
	private void roundResult(Move my_Move, Move opponentsMove, Hero my_Hero, Hero god, boolean iPlayFirst) {
		//Checks who plays first
		if (iPlayFirst == true) {//If the user plays first
			my_Move.effect(my_Hero, god);//The user makes his move
			if (god.getTempHP <= 0)//Checks if the opponent has lost 
				return;
			opponentsMove.effect(god, my_Hero);//The opponent makes his move
		} else {//If the opponent plays first
			opponentsMove.effect(god, my_Hero);//The opponent makes his move
			if (my_Hero.getTempHP <= 0)//Checks if the user has lost
				return;
			my_Move.effect(my_Hero, god);//The user makes his move
		}
	}
	//Replenishes the energy of the hero and god
	public void replenishEnergy(Hero my_Hero, Hero god) {
		my_Hero.setTempEnergy(my_Hero.getTempEnergy() + energyReplenishment);
		god.setTempEnergy(god.getTempEnergy() + energyReplenishment);
	}
}
