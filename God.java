package SuperCharacter.src;

public class God extends Character {

    public God(int numOfBattle) {
        super(); //This line is needed but not useful
        switch (numOfBattle) {
            // Hestia
            case 1:
                this.setName("Hestia");
                setTempStats(100, 100, 100, 10);
                break;
            // Hephaestus
            case 2:
                this.setName("Hephaestus");
                setTempStats(110, 110, 110, 10);
                break;
            // Hermes
            case 3:
                this.setName("Hermes");
                setTempStats(120, 120, 120, 10);
                break;
            // Demetra
            case 4:
                this.setName("Demetra");
                setTempStats(130, 130, 130, 10);
                break;
            // Aphrodite
            case 5:
                this.setName("Aphrodite");
                setTempStats(140, 140, 140, 10);
                break;
            // Apollo
            case 6:
                this.setName("Apollo");
                setTempStats(150, 150, 150, 10);
                break;
            // Artemis
            case 7:
                this.setName("Artemis");
                setTempStats(160, 160, 160, 10);
                break;
            // Poseidon
            case 8:
                this.setName("Poseidon");
                setTempStats(170, 170, 170, 10);
                break;
            // Ares
            case 9:
                this.setName("Ares");
                setTempStats(180, 180, 180, 10);
                break;
            // Hera
            case 10:
                this.setName("Hera");
                setTempStats(190, 190, 190, 10);
                break;
            // Athena
            case 11:
                this.setName("Athena");
                setTempStats(200, 200, 200, 10);
                break;
            // Zeus
            case 12:
                this.setName("Zeus");
                setTempStats(210, 210, 210, 10);
                break;
            default:
                System.out.println("There is no such god");
        }
    }

}
