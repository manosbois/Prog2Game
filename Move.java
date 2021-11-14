package Moves;
public class Move {
       private int energy;
       private String name;
       
       public Move(int energy , String name) {
    	   this.energy=energy;
    	   this.name=name;
       }
       public int getEnergy() {
    	   return energy;
       }
       public String getName() {
    	   return name;
       }
       
       public void effect(Hero my_Hero , Hero god) {
    	   System.out.println("No move has been selected");
       }

      @Override
      public String toString() {
    	   return String.format("The %s's energy is %d",name,energy);
       }
}
