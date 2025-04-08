import java.util.Random;
/**
 * A class defining a player, their role, and their proficiency
 * in every role in the game Underassalt
 */
public class Player {
    public double tankProficiency;
    public double dpsProficiency;
    public double supportProficiency;
    public enum Role{
        tank, dps, support, undecided
    }
    public Role role;

    /**
     * creates a player with 3 proficiencies, one for each role
     */
    public Player(){
        Random random = new Random();
        tankProficiency = random.nextInt(0, 101);
        dpsProficiency = random.nextInt(0, 101);
        supportProficiency = random.nextInt(0, 101);
        role = Role.undecided;
    }


    // getter methods for each proficiency
    public double getTankProficiency(){
        return tankProficiency;
    }
    public double getDPSProficiency(){
        return dpsProficiency;
    }
    public double getSupportProficiency(){
        return supportProficiency;
    }

    //getter and setter to determine role of player
    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }
}
