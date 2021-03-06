package gr.aueb.dmst.GodsNemesis;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class to help implement the Move concept
 * This class is used by Character class to create the moves the Character
 * objects will use. It is also useful to create the effects of every Move.
 */
public class Move {
    /** Every move consumes an amount of energy */
    private final int energy;
    /** Every move has a name */
    private final String name;
    /** Every move has a message that is displayed when used. The variable
     * messageFileName is the name of the file that includes those messages.
     * Every move must have two files: one in english and one in greek.
     * English files have the En prefix and greek files the Gr prefix.
     * After the prefixes the "-" character is used.
     * So every move file name is like the following: La-MoveClass
     * where La is the language of the file.
     * This variable will always start with the "-" character and
     * the language is not included.  
     */
    private final String messageFileName;
    /** The file that contains the sound that every move makes */
    private final URL sound;
    /** Constant that helps with damage calculations.
     * It is always 1 except when the damage that the
     * character using the move must be modified.
     */
    private static final double MODIFIER = 1;
    /** The time the screen waits after a message is shown */
    protected static final int WAIT_TIME = 1500;

    /** Class Constructor */
    public Move(int energy, String name, String messageFileName, URL sound) {
        this.energy = energy;
        this.name = name;
        this.messageFileName = messageFileName;
        this.sound = sound;
    }

    /** @return the energy the move consumes */
    public int getEnergy() {
        return energy;
    }
    
    /** @return the name of the move used */
    public String getName() {
        return name;
    }

    /** @return the name of the file that contains the messages */
    public String getMessageFileName() {
        return messageFileName;
    }

    /** @return the sound the URL that contains the sound the move makes */
    public URL getSound() { return sound; }

    
     /** @return the modifier of the move */
    public double getModifier() { return MODIFIER; }

    /**
     * Method that implements the effect of every move.
     * This includes moving the image of hero1 with moveImage method,
     * making the sound of the move with makeSound method,
     * altering the message shown on screen with modifyMes.
     * 
     * The effect method of Move Class has no other use.
     * 
     * @param hero1 the character that is using the move
     * @param hero2 the other character (that is not using the move but might
     *              be affected by it
     * @param modifier the modifier of the move hero2 uses.
     */
    public void effect(Character hero1, Character hero2, double modifier) {

        Scanner myReader = new Scanner(new LineNumberReader(
                new InputStreamReader(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("Resources/" +
                                messageFileName)), StandardCharsets.UTF_8)));

        Game.graph.moveImage(hero1);
        makeSound(getSound());
        Game.graph.modifyMes(myReader.nextLine() + hero1.getName()
                + myReader.nextLine() + this.getName() + ". " + myReader.nextLine());
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * Method that makes the sound of the move.
     * It creates a Media object using the parameter and then a Media player
     * object, using the Media object. After that it just plays the music.
     * 
     * @param moveSound the sound the move makes. Essentially it is the sound instance variable.
     */
    protected static void makeSound(URL moveSound) {
        Media hit2 =
                new Media((moveSound).toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);

        mediaPlayer2.play();
        mediaPlayer2.setVolume(1);
    }

    @Override
    public String toString() {
        return String.format("%s's energy is %d", name, energy);
    }
}
