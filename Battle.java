import javax.sound.sampled.*;
import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//A class that implements the battle mechanic of our game
public class Battle {
	private static final Random rand = new Random(); // Creating Random object

	private static final Object lock = new Object();

	public static God god;

	public static Object getLock() {
		return lock;
	}

	private static final int ENERGY_REPLENISHMENT = 8; // The variable that
	// shows how much energy is replenished every round

	// The most essential method of class Battle
	// It implements the battle mechanic
	public static int battleMethod(Hero myHero, int numOfBattle)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

		// Setting the tempStats before the battle to the values of the non tempStats
		myHero.setTempStats(myHero.getHp(), myHero.getAttack(), myHero.getArmour(), myHero.getEnergy());

		god = new God(numOfBattle); // Creating the object for the rival god
		synchronized (Graph.getGraphLock()) {
			Graph.getGraphLock().notify();
		}

		boolean roundEnds;
		System.out.println(myHero.getName() + " VS " + god.getName());

		InputStream file = Battle.class.getResourceAsStream("Song1.wav");
		InputStream fileSong2 = Battle.class.getResourceAsStream("Song2.wav");
		InputStream zeusMusic = Battle.class.getResourceAsStream("ZeusMusic.wav");
		AudioInputStream audioStream;
		if (numOfBattle == 12) {
			audioStream = AudioSystem.getAudioInputStream
					(new BufferedInputStream(Objects.requireNonNull(zeusMusic)));
		} else if (numOfBattle % 2 == 0) {
			audioStream = AudioSystem.getAudioInputStream
					(new BufferedInputStream(Objects.requireNonNull(fileSong2)));
		} else {
			audioStream = AudioSystem.getAudioInputStream
					(new BufferedInputStream(Objects.requireNonNull(file)));
		}

		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		Graph.godName.setText(god.getName());
		do { // Start of do...while loop that implements the round system
			Move myMove = chooseMyMove(myHero, god);
			// Lets user choose which move to use
			Move opponentsMove = chooseOpponentsMove(god);
			// The PC decides which move the rival god uses
			boolean iPlayFirst = decideWhoGoesFirst(numOfBattle, myMove, opponentsMove);
			// Decides if the users goes first
			roundResult(myMove, opponentsMove, myHero, god, iPlayFirst);
			// It modifies the TempStats of the objects myHero and
			// god according to used moves
			roundEnds = (myHero.getTempHP() <= 0) || (god.getTempHP() <= 0);
			// The round ends when either side's tempHP is below zero
			if (!roundEnds) { // It will not replenish the
				// energy if either side has lost
				replenishEnergy(myHero, god); // Replenishes the
				// energy of the hero and god
			}
		} while (!roundEnds); // End of do...while loop
		clip.stop();
		return myHero.getTempHP(); // Returns the
		// temporary Hp of the user to be used in class Game
	} // End of method BattleMethod

	// Lets user choose which move to use
	public static Move chooseMyMove(Hero myHero, God god) throws InterruptedException {
		boolean sufficientEnergy;
		Move move; // Creating a variable of type Move to assist us in switch structure.
		do {
			sufficientEnergy = true;
			System.out.printf("%s %14s%nHP: %d %14d%nEnergy: %d%n%n", myHero.getName(), god.getName(),
					myHero.getTempHP(), god.getTempHP(), myHero.getTempEnergy());
			System.out.println("Choose your move!");
			// Printing user's moves
			System.out.println(myHero.getDamagingMove1().toString());
			System.out.println(myHero.getDamagingMove2().toString());
			System.out.println(myHero.getBuffMove().toString());
			System.out.println(myHero.getProtectiveMove().toString());
			System.out.println(myHero.getNoMove().toString());

			Scanner	myReader = new Scanner(new BufferedReader(new InputStreamReader(Objects.requireNonNull
					(Battle.class.getResourceAsStream(Graph.getLanguage() + "-Battle.txt")))));


			Game.graph.modifyMes(myReader.nextLine()); //Message: Choose your move!

			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int chosenMove = Graph.getChosenMove(); // Reading user's chosen move
			move = getMove(myHero, chosenMove);
			if (myHero.getTempEnergy() < move.getEnergy()) {
				// Checking if the user has enough energy
				sufficientEnergy = false;
				Game.graph.clearMes();
				System.out.printf("You need %d more Energy to use the move %s%n",
						move.getEnergy() - myHero.getTempEnergy(), move.getName());
				Game.graph.modifyMes(myReader.nextLine() + (move.getEnergy() - myHero.getTempEnergy()) +
						myReader.nextLine() + move.getName() + "."); //Message: Yoy need $ more energy to use the move %.
				Thread.sleep(1500);
			}
		} while (!sufficientEnergy);
		
		return move;
	} // End of method chooseMyMove

	// The PC decides which move the rival god uses
	public static Move chooseOpponentsMove(God god) {

		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException ie) {
		 * Thread.currentThread().interrupt(); }
		 */

		boolean sufficientEnergy;
		Move move; // Creating a
		// variable of type Move to assist us in switch structure

		do {
			sufficientEnergy = true;
			int randomMove, randomNumber ;
			randomNumber= rand.nextInt(100) + 1;
			if(randomNumber<=37) {
				randomMove=1;
			}else if(randomNumber<=62) {
				randomMove=2;
			}else if(randomNumber<=77) {
				randomMove=3;
			}else {
				randomMove=4;
			}
			move = getMove(god, randomMove);
			if (god.getTempEnergy() < move.getEnergy()) {
				// Checking if the opponent has enough energy
				sufficientEnergy = false;
			}
		} while (!sufficientEnergy);
		return move;
	} // End of method chooseOpponentsMove

	private static Move getMove(Character hero, int chosenMove) {

		InputStream swordSound = Battle.class.getResourceAsStream("Swordsound.wav");
		InputStream spearSound = Battle.class.getResourceAsStream("Spearsound.wav");
		InputStream meditate = Battle.class.getResourceAsStream("Meditate.wav");
		InputStream noMove = Battle.class.getResourceAsStream("Nomove.wav");
		InputStream shieldSound = Battle.class.getResourceAsStream("Shield.wav");
		Clip clip3;
		try {
			clip3 = AudioSystem.getClip();

			switch (chosenMove) {
				case 1:
					makeSound(swordSound, clip3);
					return hero.getDamagingMove1();
				case 2:
					makeSound(spearSound, clip3);
					return hero.getDamagingMove2();
				case 3:
					makeSound(shieldSound, clip3);
					return hero.getProtectiveMove();
				case 4:
					makeSound(meditate, clip3);
					return hero.getBuffMove();
				default:
					makeSound(noMove, clip3);
					return hero.getNoMove();
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hero.getNoMove(); // In case try fails
	}

	private static void makeSound(InputStream moveSound, Clip clip3)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioStreamMove = AudioSystem.getAudioInputStream
				(new BufferedInputStream(moveSound));
		clip3.open(audioStreamMove);
		clip3.start();
	}

	// Decides if the users goes first
	private static boolean decideWhoGoesFirst(int numOfBattle, Move myMove, Move opponentsMove) {
		// Checking if anyone used a Protective move
		if (myMove instanceof ProtectiveMove) {
			return true;
		} else if (opponentsMove instanceof ProtectiveMove) {
			return false;
		}
		if (numOfBattle <= 6) { // For the first 6 Stages (Rival gods)
			return true;
		} else if (numOfBattle <= 9) { // For the next 3
			// Stages the user and the rival have the
			// same chance of going first
			return rand.nextBoolean();
		} else { // For the last 3 Stages the user
			// always goes last
			return false;
		} // End of if
	}// End of method decideWhoGoesFirst

	// It modifies the TempStats of the objects myHero and god according to used
	// moves
	private static void roundResult(Move myMove, Move opponentsMove, Hero myHero, God god, boolean iPlayFirst) {
		// Method that checks who plays first
		if (iPlayFirst) { // If the user plays first
			myMove.effect(myHero, god, opponentsMove.getModifier()); // The user makes his move
			if (god.getTempHP() <= 0) { // Checks if the opponent has lost
				return;
			}
			opponentsMove.effect(god, myHero, myMove.getModifier()); // The opponent makes his move
		} else { // If the opponent plays first
			opponentsMove.effect(god, myHero, myMove.getModifier()); // The opponent makes his move
			if (myHero.getTempHP() <= 0) { // Checks if the user has lost
				return;
			}
			myMove.effect(myHero, god, opponentsMove.getModifier()); // The user makes his move
		}
	}

	// Replenishes the energy of the hero and god
	private static void replenishEnergy(Hero myHero, God god) {
		myHero.setTempEnergy(myHero.getTempEnergy() + ENERGY_REPLENISHMENT);
		god.setTempEnergy(god.getTempEnergy() + ENERGY_REPLENISHMENT);
		Graph.modifyEnergyLabel(myHero.getTempEnergy());
	}
}
