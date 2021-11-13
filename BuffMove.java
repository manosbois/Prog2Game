package Moves;

public class BuffMove extends Move {

	public BuffMove(int energy, String name) {
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
public class BuffMove extends Move {
	public BuffMove(int e, String n) {
		this.energy=energy;
		this.name=name;
	}
	public void effect(Hero mh, Hero g) {
		int tempAttack = Hero.tempAttack + Hero.tempAttack * 0.5;
		int tempArmour = Hero.tempArmour + Hero.tempArmour * 0.5;
 	    Hero.setTempAttack(int tempAttack);
 	    Hero.setTempArmour(int tempArmour);
    }
}
