package Moves;
public class DamageMove extends Move {
	private int damage;
	public DamageMove(int energy, String name) {
		super();
	}
	public int getEnergy() {
		return energy;
	}
	public String getName() {
		return name;
	}
	public int effect() {
		return 9;
	}
}
