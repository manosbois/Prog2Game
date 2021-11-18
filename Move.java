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
       
       public void effect(Hero hero1 , Hero hero2) {
    	   System.out.printf("%s used %s.\n\n" , hero1.getName() , this.getName());
    	   System.out.println("This move had no effect");
       }

      @Override
      public String toString() {
    	   return String.format("%s's energy is %d", name, energy);
       }
}
