

//The class that creates the Move objects that the Heroes use
public class Move {

    private final int energy; //How much energy the Move consumes
    private final String name; //The name of the Move
    private static final double MODIFIER = 1;

    //Constructor
    public Move(int energy, String name) {
        this.energy = energy;
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public double getModifier() {return MODIFIER;}

    public void effect(Character hero1, Character hero2, double modifier) {
        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        System.out.printf("This move had no effect%n%n");
    }

    @Override
    public String toString() {
        return String.format("%s's energy is %d", name, energy);
    }
}
