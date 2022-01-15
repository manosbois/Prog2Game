package gr.aueb.dmst.GodsNemesis;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


/**
 * A class that implements the battle mechanic of our game.
 * Class can't be extended.
 */
public final class Battle {

	/** Private default constructor so no Battle object can be constructed outside of Battle. */
	private Battle() { }

	/** Creating Random object for use in decideWhoGoesFirst and chooseOpponentsMove */
	private static final Random RAND = new Random();

	/** Object to be used as lock for Graph.battleThread */
	private static final Object LOCK = new Object();

	/** The object for the opposing god. It is used in other classes too. */
	static God god;

	/** @return LOCK for Graph.battleThread */
	public static Object getLock() {
		return LOCK;
	}

	/** The constant that shows how much energy is replenished every round. */
	private static final int ENERGY_REPLENISHMENT = 8;

	/** Constants that help with the setting the probability of every move to be used by god.
	 * They show the upper border. */
	private static final int SWORD_BORDER = 37, SPEAR_BORDER = 62, SHIELD_BORDER = 77,
							MEDITATE_BORDER = 97;

	/**	Constants for important battles */
	private static final int SIXTH_BATTLE = 6, NINTH_BATTLE = 9, ZEUS_BATTLE = 12;

	/** The most essential method of class Battle
	 *It implements the battle mechanic */
	public static int battleMethod(Hero myHero, final int numOfBattle) {

		// Setting the tempStats before the battle to the values of the non tempStats
		myHero.setTempStats(myHero.getHp(), myHero.getAttack(), myHero.getArmour(), myHero.getEnergy());

		god = new God(numOfBattle); // Creating the object for the rival god
		synchronized (Graph.getGraphLock()) {
			Graph.getGraphLock().notify();
		}

		boolean roundEnds;

		com.sun.javafx.application.PlatformImpl.startup(() -> { });
		URL file = Battle.class.getResource("Resources/Song1.mp3");
		URL fileSong2 = Battle.class.getResource("Resources/Song2.mp3");
		URL zeusMusic = Battle.class.getResource("Resources/ZeusMusic.mp3");
		Media hit;
		if (numOfBattle == ZEUS_BATTLE) {
			hit = new Media(Objects.requireNonNull(zeusMusic).toString());
		} else if (numOfBattle % 2 == 0) {
			hit = new Media(Objects.requireNonNull(fileSong2).toString());
		} else {
			hit = new Media(Objects.requireNonNull(file).toString());
		}

		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		mediaPlayer.setVolume(0.3);

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
		mediaPlayer.stop();
		return myHero.getTempHP(); // Returns the
		// temporary Hp of the user to be used in class Game
	} // End of method BattleMethod

	
	/** Lets user choose which move to use.
	 *  It calls the getChosenMove method of Graph.
	 *  It also does not allow the user to choose a move that requires 
	 *  more energy than the user has.
	 *
	 * @param myHero the user's character
	 * @param god the opponent's character (the rival god)
	 * @return the Move that the user has chosen to use
	 */
	public static Move chooseMyMove(Hero myHero, God god) {
		boolean sufficientEnergy;
		Move move; // Creating a variable of type Move to assist us in switch structure.
		do {
			sufficientEnergy = true;

			Scanner	myReader = new Scanner(new BufferedReader(
					new InputStreamReader(Objects.requireNonNull(
							Battle.class.getResourceAsStream("Resources/" +
									Graph.getLanguage() + "-Battle.txt")), StandardCharsets.UTF_8)));


			Game.graph.modifyMes(myReader.nextLine()); //Message: Choose your move!

			synchronized (LOCK) {
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int chosenMove = Graph.getChosenMove(); // Reading user's chosen move
			move = getHeroMove(myHero, chosenMove);
			if (myHero.getTempEnergy() < move.getEnergy()) {
				// Checking if the user has enough energy
				sufficientEnergy = false;
				Game.graph.clearMes();
				Game.graph.modifyMes(myReader.nextLine() + (move.getEnergy() - myHero.getTempEnergy())
						+ myReader.nextLine() + move.getName() + "."); //Message: Yoy need $ more energy to use the move %.
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (!sufficientEnergy);

		return move;
	} // End of method chooseMyMove


	/**
	 * Converts the move chosen in Graph class via mouse click into a Move variable.
	 * 1 corresponds to character's damagingMove1.
	 * 2 to damagingMove2.
	 * 3 to protectiveMove.
	 * 4 to buffMove.
	 * 5 to noMove.
	 * 
	 * @param hero the user's character
	 * @param chosenMove the move the user has chosen in Graph to use but as an integer [1,5]
	 * @return the move which corresponds to the integer chosenMove
	 */
	private static Move getHeroMove(Hero hero, int chosenMove) {

		switch (chosenMove) {
			case 1:
				return hero.getDamagingMove1();
			case 2:
				return hero.getDamagingMove2();
			case 3:
				return hero.getProtectiveMove();
			case 4:
				return hero.getBuffMove();
			default:
				return hero.getNoMove();
		}

	}

	/**
	 * Returns the move the rival god will do.
	 * It also does not allow the pc to choose a move that requires 
	 * more energy than the god has.
	 * 
	 * @param god the rival god
	 * @return the move the rival god will do.
	 */
	// The PC decides which move the rival god uses
	public static Move chooseOpponentsMove(God god) {

		boolean sufficientEnergy;
		Move move; // Creating a
		// variable of type Move to assist us in switch structure

		do {
			sufficientEnergy = true;
			move = getGodMove(god);
			if (god.getTempEnergy() < move.getEnergy()) {
				// Checking if the opponent has enough energy
				sufficientEnergy = false;
			}
		} while (!sufficientEnergy);
		return move;
	} // End of method chooseOpponentsMove

	/**
	 * The "algorithm" that decides which move will be chosen, using probabilities.
	 * 
	 * @param god the rival god
	 * @return the move the rival god will do.
	 */
	private static Move getGodMove(God god) {
		int randomNumber = RAND.nextInt(100) + 1;

		if (randomNumber <= SWORD_BORDER) { // 37% Chance for Sword to be chosen
			return god.getDamagingMove1();
		} else if (randomNumber <= SPEAR_BORDER) { // 25% Chance for Spear
			return god.getDamagingMove2();
		} else if (randomNumber <= SHIELD_BORDER) { // 15% Chance for Shield
			return god.getProtectiveMove();
		} else if (randomNumber <= MEDITATE_BORDER) { // 20% Chance for Meditate
			return god.getBuffMove();
		} else { //3% Chance for noMove
			return god.getNoMove();
		}
	}

	/**
	 * Decides if the users goes first.
	 * This depends on the god the user is battling.
	 * Gods 1-6: the user goes always first,
	 * Gods 7-9: the user has a 50% chance of going first,
	 * Gods 10-12: the user goes always last.
	 * The moves of the class protective move make the user always go first,
	 * independently of the number of the battle.
	 * 
	 * @param numOfBattle the number indicating which god the user is battling.
	 * @param myMove the user's move
	 * @param opponentsMove the rival god's move
	 * @return true if the users goes first and false if the god goes first.
	 */
	private static boolean decideWhoGoesFirst(final int numOfBattle, Move myMove, Move opponentsMove) {
		// Checking if anyone used a Protective move
		if (myMove instanceof ProtectiveMove) {
			return true;
		} else if (opponentsMove instanceof ProtectiveMove) {
			return false;
		}
		if (numOfBattle <= SIXTH_BATTLE) { // For the first 6 Stages (Rival gods)
			return true;
		} else if (numOfBattle <= NINTH_BATTLE) { // For the next 3
			// Stages the user and the rival have the
			// same chance of going first
			return RAND.nextBoolean();
		} else { // For the last 3 Stages the user
			// always goes last
			return false;
		} // End of if
	} // End of method decideWhoGoesFirst

	/**
	 * It modifies the TempStats of the objects myHero and god according to used moves.
	 * 
	 * @param myMove the user's move
	 * @param opponentsMove the rival god's move
	 * @param myHero the user's character
	 * @param god the rival god
	 * @param iPlayFirst boolean variable that is true when the user goes first
	 */
	private static void roundResult(final Move myMove, final Move opponentsMove,
                                    final Hero myHero, final God god, final boolean iPlayFirst) {
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

	/**
	 * Replenishes the energy of the hero and god,
	 * by the amount of ENERGY_REPLENISHMENT constant
	 * 
	 * @param myHero the user's character
	 * @param god the rival god
	 */
	private static void replenishEnergy(final Hero myHero, final God god) {
		myHero.setTempEnergy(myHero.getTempEnergy() + ENERGY_REPLENISHMENT);
		god.setTempEnergy(god.getTempEnergy() + ENERGY_REPLENISHMENT);
		Graph.modifyEnergyLabel(myHero.getTempEnergy());
	}

}
