//A class for Moves that raise the tempStats
public class BuffMove extends Move {

	public BuffMove(int energy, String name) {
		super(energy, name);
	}

	@Override
	public void effect(Hero hero1, Hero hero2) {
		System.out.printf("%s used %s.\n", hero1.getName(), this.getName());
		int tempAttack = hero1.getTempAttack() + hero1.getTempAttack() / 2;
		int tempArmour = hero1.getTempArmour() + hero1.getTempArmour() / 2;
		hero1.setTempAttack(tempAttack);
		hero1.setTempArmour(tempArmour);
		System.out.printf("%s's Attack and Armour were raised by half\n\n", hero1.getName());
		hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
	}
}
