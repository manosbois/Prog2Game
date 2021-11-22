//The class that has the main method
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;
public class Game {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Stages stage = new Stages();
		stage.stageControl();
	}
	
}
