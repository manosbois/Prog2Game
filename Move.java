import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Objects;
import java.nio.charset.StandardCharsets;

//The class that creates the Move objects that the Heroes use
public class Move {

    private final int energy; //How much energy the Move consumes
    private final String name; //The name of the Move

    String messageFileName;

    private static final double MODIFIER = 1;
    protected static final int WAIT_TIME = 2500;

    //Constructor
    public Move(int energy, String name, String messageFileName) {
        this.energy = energy;
        this.name = name;
        this.messageFileName = messageFileName;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public String getMessageFileName() {
        return messageFileName;
    }

    public double getModifier() {return MODIFIER;}

    public void effect(Character hero1, Character hero2, double modifier) {

        Scanner myReader = new Scanner(new LineNumberReader(
                new InputStreamReader(Objects.requireNonNull
                        (this.getClass().getResourceAsStream(messageFileName)), StandardCharsets.UTF_8)));
        
        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        Game.graph.modifyMes(myReader.nextLine() + hero1.getName()
                + myReader.nextLine() + this.getName() + ". " + myReader.nextLine());
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("This move had no effect%n%n");
        System.out.println(Graph.getLanguage());
    }

    @Override
    public String toString() {
        return String.format("%s's energy is %d", name, energy);
    }
}
