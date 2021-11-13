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

//NEW
package Moves;
public class DamageMove extends Move {
    private int damage;
	public DamageMove(int e, String n) {
		this.energy=energy;
		this.name=name;
	}
	public void effect(Hero mh, Hero g) {
		int tempHP = (damage * Hero.temp_attack) / (4 * Hero.temp_defense);
		Hero.tempHP=Hero.tempHP-tempHP;
	}
}
