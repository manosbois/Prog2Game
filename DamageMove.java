//A class for Moves that do damage
public class DamageMove extends Move {
	private int damage;
	
	public DamageMove(int energy, String name, int damage) {
		super(energy , name);
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public void effect(Hero hero1, Hero hero2) {
		System.out.printf("%s used %s.\n" , hero1.getName() , this.getName());
		int tempHP = (this.damage * hero1.getTempAttack()) / (4 * hero2.getTempArmour());
		hero2.setTempHP(hero2.getTempHP() - tempHP);
 		hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
		System.out.printf("%s's HP is now %d. The damage was %d HP.\n\n" , hero2.getName() , hero2.getTempHP() , tempHP);
	}
	
	@Override
	public String toString() {
		return String.format("%s and it's damage is %d", super.toString(), damage);
	}
}
