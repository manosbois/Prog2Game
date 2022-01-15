package gr.aueb.dmst.GodsNemesis;

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
    /** 
     * The default constant modifier that is used.
     * In this class the value of MODIFIER is less than 1
     * in order to lessen the damage the hero using the move
     * will take from the opponent's move.
     */
    private static final double MODIFIER = 0.25;

    /** @return the modifier */
    public double getModifier() { return MODIFIER; }


    /**
     * The effect method that is overridden from the Move class.
     * This move's effect method is almost the same as Move Class's.
     * Its effect is actually implemented just by the MODIFIER constant.
     */
    @Override
    public void effect(Character hero1, Character hero2, double modifier) {

        Scanner myReader = new Scanner(new LineNumberReader(
                new InputStreamReader(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("Resources/" +
                                this.getMessageFileName())), StandardCharsets.UTF_8)));

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
