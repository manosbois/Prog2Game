package gr.aueb.dmst.GodsNemesis;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/** A class for Moves that raise the tempStats */
public class BuffMove extends Move {

	/** The constant Multiplication factor that is used in raising the tempStats */
	private static final double MULTIPLICATION_FACTOR = 1.2;

	/** Class Constructor */
	public BuffMove(int energy, String name, String messageFileName, URL sound) {
		super(energy, name, messageFileName, sound);
	}

	/**
	 * The effect method that is overridden from the Move class
	 * This move's effect is that it raises the tempStats of hero1, multiplying them with the MULTIPLICATION_FACTOR
	 * and using the set methods.
	 */
	@Override
	public void effect(Character hero1, Character hero2, double modifier) {

		Scanner myReader = new Scanner(new LineNumberReader(
				new InputStreamReader(Objects.requireNonNull
						(this.getClass().getResourceAsStream("Resources/" +
								this.getMessageFileName())), StandardCharsets.UTF_8)));

		double tempAttack = hero1.getTempAttack() * MULTIPLICATION_FACTOR; //Raising tempAttack by half
		double tempArmour = hero1.getTempArmour() * MULTIPLICATION_FACTOR; //Raising tempArmour by half
		hero1.setTempAttack((int) Math.round(tempAttack));
		hero1.setTempArmour((int) Math.round(tempArmour));

		Game.graph.moveImage(hero1);
		makeSound(getSound());
		String message = myReader.nextLine() + hero1.getName() + myReader.nextLine()
				+ this.getName() +". " + myReader.nextLine() + hero1.getName() + myReader.nextLine();
		Game.graph.modifyMes(message);
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Removing Energy from player using the move
		hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
	}
}
