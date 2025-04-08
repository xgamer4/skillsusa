import java.util.ArrayList;
import java.util.Random;

/**
 * Predicting Firestorm's UnderAssualt
 * 
 * @author Nicole Prosser
 */
public class tank {
    private int dps_prof;
    private int tank_prof;
    private int support_prof;
    private String username;
    private Random random = new Random();
    private ArrayList<Integer> scores;


    /**
     * Creates a tank player
     * @param username the name of the player
     */
    public tank(String username){
        this.username = username;
        this.dps_prof = random.nextInt(0, 100);
        this.tank_prof = random.nextInt(0, 100);
        this.support_prof = random.nextInt(0, 100);
        this.scores = new ArrayList<Integer>(dps_prof);
        this.scores.add(tank_prof);
        this.scores.add(support_prof);
    }

    /**
     * Prints the profficiency scores of the player
     */
    public void getProfScores(){
        System.out.println("    " + username + " - Tank: {DPS Proficiency: " + dps_prof + ", Tank Proficiency: " + tank_prof + ", Supporty Proficiency: " + support_prof + "}");
    } 

    /**
     * @return the DPS score
     */
    public int getDPS(){
        return this.dps_prof;
    }

    /**
     * @return the tank score
     */
    public int getTank(){
        return this.tank_prof;
    }

    /**
     * @return the support score
     */
    public int getSupport(){
        return this.support_prof;
    }

    /**
     * @return the player's username
     */
    public String getName(){
        return this.username;
    }


}
