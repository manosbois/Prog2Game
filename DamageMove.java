package gr.aueb.dmst.GodsNemesis;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/** A class for Moves that do damage */
public class DamageMove extends Move {

    /** A number
     * that helps the calculations in effect method.
     */
    private static final double DENOMINATOR_MULTIPLIER = 4.35;

    /** This variable shows how much damage this move does.
     * Damage is NOT how much tempHP the opponent will lose.
     * Damage indicates how strong the move is. 
     */
    private final int damage;

    /** Class Constructor */
    public DamageMove(int energy, String name, int damage, String messageFileName, URL sound) {
        super(energy, name, messageFileName, sound);
        this.damage = damage;
    }
    /** @return the damage of the move */
    public int getDamage() {
        return damage;
    }

    /**
     * The effect method that is overridden from the Move class
     * This move's effect is that it raises removes some tempHP from hero2.
     * This depends on how much damage the move does as well as on the tempAttack of hero1
     * and tempArmour of hero2.
     */
    @Override
    public void effect(Character hero1, Character hero2, double modifier) {

        Scanner myReader = new Scanner(new LineNumberReader(
                new InputStreamReader(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("Resources/" +
                                this.getMessageFileName())), StandardCharsets.UTF_8)));

        Game.graph.moveImage(hero1);
        makeSound(getSound());
        Game.graph.modifyMes(myReader.nextLine() + hero1.getName() + myReader.nextLine() + this.getName() + ".");

        double tempHP = modifier * (getDamage() * hero1.getTempAttack())
                / (DENOMINATOR_MULTIPLIER * hero2.getTempArmour());
        //Removing HP from player taking damage
        hero2.setTempHP(hero2.getTempHP() - (int) Math.round(tempHP));
        //Removing Energy from player using the move
        hero1.setTempEnergy(hero1.getTempEnergy() - this.getEnergy());

        Graph.modifyHpLabels(hero2, hero2.getTempHP(),hero2.getHp());

        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return String.format("%s and it's damage is %d",
                super.toString(), getDamage());
    }
}
