import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/** A class for Moves that protect */
public class ProtectiveMove extends Move {

    public ProtectiveMove(int energy, String name,
                          String messageFileName, URL sound) {
        super(energy, name, messageFileName, sound);
    }
    /** The default constant modifier that is used*/
    private static final double MODIFIER = 0.5;

     /** @return the modifier */
    public double getModifier() { return MODIFIER; }

    
    /** The effect method that is overriden from the Move class  */
    @Override
    public void effect(Character hero1, Character hero2, double modifier) {

        Scanner myReader = new Scanner(new LineNumberReader(
                new InputStreamReader(Objects.requireNonNull(
                        this.getClass().getResourceAsStream(
                                this.getMessageFileName())), StandardCharsets.UTF_8)));

        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        System.out.printf("%s will be partially protected by the shield%n%n",
                hero1.getName());
        Game.graph.moveImage(hero1);
        makeSound(getSound());
        Game.graph.modifyMes(myReader.nextLine() + hero1.getName()
                + myReader.nextLine() + this.getName() + ". " + myReader.nextLine()
                + hero1.getName() + myReader.nextLine());
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
    }
}
