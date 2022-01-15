package gr.aueb.dmst.GodsNemesis;

/** Class to help implement the stats of the Gods 
 * Gods have only Temp stats and HP.
 */
public class God extends Character {

    /** Class Constructor 
     * The Gods are created depending on the numOfBattle. 
     *@param numOfBattle, the number of the Battle.
     */
    public God(int numOfBattle) {

        super(); //This line is needed but not useful
        switch (numOfBattle) {
            // Dionysus
            case 1:
                this.setName("Dionysus");
                setTempStats(1.0* Stages.myHero.getHp(),1.0* Stages.myHero.getAttack(),1.0* Stages.myHero.getArmour(), 8);
                break;
            // Hephaestus
            case 2:
                this.setName("Hephaestus");
                setTempStats(1.05* Stages.myHero.getHp(),1.05* Stages.myHero.getAttack(),1.05* Stages.myHero.getArmour(), 8);
                break;
            // Hermes
            case 3:
                this.setName("Hermes");
                setTempStats(1.075* Stages.myHero.getHp(),1.075* Stages.myHero.getAttack(),1.075* Stages.myHero.getArmour(), 8);
                break;
            // Demetra
            case 4:
                this.setName("Demetra");
                setTempStats(1.1* Stages.myHero.getHp(),1.1* Stages.myHero.getAttack(),1.1* Stages.myHero.getArmour(), 8);
                break;
            // Aphrodite
            case 5:
                this.setName("Aphrodite");
                setTempStats(1.125* Stages.myHero.getHp(),1.125* Stages.myHero.getAttack(),1.125* Stages.myHero.getArmour(), 8);
                break;
            // Apollo
            case 6:
                this.setName("Apollo");
                setTempStats(1.125* Stages.myHero.getHp(),1.125* Stages.myHero.getAttack(),1.125* Stages.myHero.getArmour(), 8);
                break;
            // Artemis
            case 7:
                this.setName("Artemis");
                setTempStats(1.15* Stages.myHero.getHp(),1.15* Stages.myHero.getAttack(),1.15* Stages.myHero.getArmour(), 10);
                break;
            // Ares
            case 8:
                this.setName("Ares");
                setTempStats(1.15* Stages.myHero.getHp(),1.15* Stages.myHero.getAttack(),1.15* Stages.myHero.getArmour(), 10);
                break;
            // Hera
            case 9:
                this.setName("Hera");
                setTempStats(1.175* Stages.myHero.getHp(),1.175* Stages.myHero.getAttack(),1.175* Stages.myHero.getArmour(), 10);
                break;
            // Poseidon
            case 10:
                this.setName("Poseidon");
                setTempStats(1.175* Stages.myHero.getHp(),1.175* Stages.myHero.getAttack(),1.175* Stages.myHero.getArmour(), 11);
                break;
            // Athena
            case 11:
                this.setName("Athena");
                setTempStats(1.225* Stages.myHero.getHp(),1.225* Stages.myHero.getAttack(),1.225* Stages.myHero.getArmour(), 13);
                break;
            // Zeus
            case 12:
                this.setName("Zeus");
                setTempStats(1.33* Stages.myHero.getHp(),1.33* Stages.myHero.getAttack(),1.33* Stages.myHero.getArmour(), 33);
                break;
            default:
                break; // There is no such god.
        }
    }

    /** Method that sets the Statistics of the God 
     * and turns doubles into integers for better gaming experience. 
     */
    public void setTempStats(double hp, double attack, double armour, int energy) {
        setTempHP((int) hp);
        setTempAttack((int) attack);
        setTempArmour((int) armour);
        setTempEnergy(energy);
        setHp((int) hp);
    }

}
