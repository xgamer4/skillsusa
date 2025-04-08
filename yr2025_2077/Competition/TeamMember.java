//class to hold and generate team members

import java.util.Random;

public class TeamMember {
    
    private int dpsProf;
    private int tankProf;
    private int supportProf;

    public TeamMember () {
        this(new Random());
    }
    
    public TeamMember (Random randomizer){
        generate(randomizer);
    }

    private void generate(Random randomizer) {
        this.dpsProf = randomizer.nextInt(0, 100);
        this.tankProf = randomizer.nextInt(0, 100);
        this.supportProf = randomizer.nextInt(0, 100);
    }

    public int getDpsProf(){
        return dpsProf;
    }

    public int getTankProf(){
        return tankProf;
    }

    public int getSupportProf(){
        return supportProf;
    }

    public String toString() {
        return String.format("{dps prof: %d, tank prof: %d, support prof: %d}", this.getDpsProf(), this.getTankProf(), this.getSupportProf());
    }
}
