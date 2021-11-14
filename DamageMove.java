package Moves;
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
		int tempHP = (this.damage * hero1.getTempAttack()) / (4 * hero2.getTempDefense());
		hero1.setTempHP(hero1.getTempHP() - tempHP);
	}
}
