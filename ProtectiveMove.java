import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProtectiveMove extends Move {

    public ProtectiveMove(int energy, String name, String messageFileName) {
        super(energy, name, messageFileName);
    }
    private static final double MODIFIER = 0.5;

    public double getModifier() { return MODIFIER; }

    @Override
    public void effect(Character hero1, Character hero2, double modifier) {
        Scanner myReader = null;
        try {
            myReader = new Scanner(this.getMessageFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.printf("%s used %s.%n", hero1.getName(), this.getName());
        System.out.printf("%s will be partially protected by the shield%n%n",
                hero1.getName());
        Game.graph.modifyMes(Game.graph.mes1, myReader.nextLine() + hero1.getName()
                + myReader.nextLine() + this.getName() + ". " + myReader.nextLine() + hero1.getName() + myReader.nextLine());
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());
    }
}
