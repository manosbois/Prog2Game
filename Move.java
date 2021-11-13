package Moves;
public class Move {
       protected int energy;
       protected String name;
       
       public void Move() {
    	   this.energy=energy;
    	   this.name=name;
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
       @Override
      public String toString() {
    	   return String.format("The %s's energy is %d",name,energy);
       }
}

//NEW
package Moves;
public class Move {
       protected int energy;
       protected String name;
       
       public void Move(int e, String n) {
    	   this.energy=energy;
    	   this.name=name;
       }
       public int getEnergy() {
    	   return energy;
       }
       public String getName() {
    	   return name;
       }
       public void effect() {
    	   System.out.println("No move has been selected");
       }
       @Override
      public String toString() {
    	   return String.format("The %s's energy is %d",name,energy);
       }
}
