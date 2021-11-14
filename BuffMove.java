package Moves;

public class BuffMove extends Move {

	public BuffMove(int energy, String name) {
		super(energy , name);
	}
	
	@Override
	public void effect(Hero hero1, Hero hero2) {
		int tempAttack = hero1.getTempAttack() + hero1.getTempAttack() / 2;
		int tempArmour = hero1.getTempArmour() + hero1.getTempArmour() / 2;
 		hero1.setTempAttack(tempAttack);
 		hero1.setTempArmour(tempArmour);
	}
}
