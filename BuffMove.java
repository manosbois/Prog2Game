package Moves;


public class BuffMove extends Move {

	public BuffMove(int energy, String name) {
		super();
	}
	public int getEnergy() {
 	   return energy;
    }
    public String getName() {
 	   return name;
    }
	public int effect() {
 	   return 9;
    }
}
