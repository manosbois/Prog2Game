
//A class for Moves that raise the tempStats
public class BuffMove extends Move {

	private static final double MULTIPLICATION_FACTOR = 1.5;
	//Variable that raises the stats by an amount
	public BuffMove(int energy, String name) {
		super(energy, name);
	}

	@Override
	public void effect(Character hero1, Character hero2, double modifier) {
		System.out.printf("%s used %s.%n",
				hero1.getName(), this.getName());
		double tempAttack = hero1.getTempAttack() * MULTIPLICATION_FACTOR; //Raising tempAttack by half
		double tempArmour = hero1.getTempArmour() * MULTIPLICATION_FACTOR; //Raising tempArmour by half
		hero1.setTempAttack((int) Math.round(tempAttack));
		hero1.setTempArmour((int) Math.round(tempArmour));
		System.out.printf("%s's Attack and Armour "
				+ "were raised by half%n%n", hero1.getName());
		String message = hero1.getName() + " used " + this.getName() +". " + hero1.getName() +"'s Attack and Armour were raised by half.";
		Game.graph.modifyMes(Game.graph.mes1, message);
		/*try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//Removing Energy from player using the move
		hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
	}
}
