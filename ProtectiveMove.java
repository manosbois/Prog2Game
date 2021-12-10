
public class ProtectiveMove extends Move {

    public ProtectiveMove(int energy, String name) {
        super(energy, name);
    }
    private static final double MODIFIER = 0.5;

    public double getModifier() { return MODIFIER; }

    @Override
    public void effect(Character hero1, Character hero2, double modifier) {
        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        System.out.printf("%s will be partially protected by the shield%n%n",
                hero1.getName());
        hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
    }
}
