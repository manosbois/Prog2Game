import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.util.Scanner;

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

        Scanner myReader = new Scanner(new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream(messageFileName))));
        
        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        Game.graph.modifyMes(Game.graph.mes1, myReader.nextLine() + hero1.getName()
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
