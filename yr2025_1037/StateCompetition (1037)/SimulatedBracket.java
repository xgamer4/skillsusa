import java.util.Random;
/**
 * A class to simulate a tournament bracket for Underassault
 * based on four teams with 3 members each
 */
public class SimulatedBracket {
    private Team fuel;
    private Team outlaws;
    private Team shock;
    private Team dynasty;

    /**
     * creates the teams and gives team members random amounts of proficiency
     * in tank, dps, and support
     */
    public SimulatedBracket(){
        fuel = new Team("Dallas Fuel");
        outlaws = new Team("Houston Outlaws");
        shock = new Team("San Francisco Shock");
        dynasty = new Team("Seoul Dynasty");
    }

    /**
     * a method to simulate a match and print out the results
     * @param team1
     * @param team2
     * @return the winning team
     */
    public Team SimulatedMatch(Team team1, Team team2){
        int team1Score = 0;
        int team2Score = 0;

        for(int i = 0; i < 3; i++){
            System.out.println("Starting game " + (i+1) + " between the " + team1.name + " and the " + team2.name + ".");
            if(SimulatedGame(team1, team2)){ //if return true team1 wins else team2 wins
                team1Score++;
                System.out.println("The " + team1.name + " win match " + (i+1) + "!");
                System.out.println("The current score is " + team1Score + ":" + team2Score);
                System.out.println();
            }
            else{
                team2Score++;
                System.out.println("The " + team2.name + " win match " + (i+1) + "!");
                System.out.println("The current score is " + team1Score + ":" + team2Score);
                System.out.println();

            }
            if(team1Score == 2 || team2Score == 2)
                break;
        }
        if(team1Score == 2)
            return team1;
        else
            return team2;
    }

    /**
     * A method to simulate a game based on probability that a player will win against another player
     * @param team1 to play in the game
     * @param team2 to play in the game
     * @return true if team1 wins or false if team2 wins
     */
    public boolean SimulatedGame(Team team1, Team team2){
        int team1SubScore = 0;
        Random random = new Random();
        double randomVal;
        Player p1;
        Player p2;

        //team1 tank vs team2 dps
        randomVal = random.nextDouble(1);
        p1 = team1.getTankPlayer();
        p2 = team2.getDPSPlayer();
        if(randomVal <= p1.getTankProficiency() / (p1.getTankProficiency() + p2.getDPSProficiency())){
            team1SubScore++;
            System.out.println(randomVal + " is less than " + (p1.getTankProficiency() / (p1.getTankProficiency() + p2.getDPSProficiency())));
            System.out.println(team1.name +"'s Tank player proves their dominance against " + team2.name + "'s DPS player!");
        }
        else{
            System.out.println(randomVal + " is greater than " + (p1.getTankProficiency() / (p1.getTankProficiency() + p2.getDPSProficiency())));
            System.out.println(team2.name +"'s dps player absolutely destroyed " + team1.name + "'s Tank player!");
        }

        System.out.println();

        //team1 dps vs team2 tank
        randomVal = random.nextDouble(1);
        p1 = team1.getDPSPlayer();
        p2 = team2.getTankPlayer();
        if(randomVal <= p1.getDPSProficiency() / (p1.getDPSProficiency() + p2.getTankProficiency())){
            team1SubScore++;
            System.out.println(randomVal + " is less than " + (p1.getDPSProficiency() / (p1.getDPSProficiency() + p2.getTankProficiency())));
            System.out.println(team1.name +"'s DPS player melted the " + team2.name + "'s Tank player!");
        }
        else{
            System.out.println(randomVal + " is greater than " + (p1.getDPSProficiency() / (p1.getDPSProficiency() + p2.getTankProficiency())));
            System.out.println(team2.name +"'s Tank player was truly unkillable against " + team2.name + "'s DPS player!");
        }

        System.out.println();

        //team1 support vs team2 support
        randomVal = random.nextDouble(1);
        p1 = team1.getSupportPlayer();
        p2 = team2.getSupportPlayer();
        if(randomVal <= p1.getSupportProficiency() / (p1.getSupportProficiency() + p2.getSupportProficiency())){
            team1SubScore++;
            System.out.println(randomVal + " is less than " + (p1.getSupportProficiency() / (p1.getSupportProficiency() + p2.getSupportProficiency())));
            System.out.println(team1.name +"'s Support player blew the " + team2.name + "'s support player out of the water!");
        }
        else{
            System.out.println(randomVal + " is greater than " + (p1.getSupportProficiency() / (p1.getSupportProficiency() + p2.getSupportProficiency())));
            System.out.println(team2.name +"'s Support player just kept his team alive better than " + team2.name + "'s Support player.");
        }

        System.out.println();

        if(team1SubScore == 2)
            return true;
        else    
            return false;
    }

    // returns an array of teams, Helper method
    public Team[] getTeams(){
        Team[] teams = new Team[4];
        teams[0] = fuel;
        teams[1] = outlaws;
        teams[2] = shock;
        teams[3] = dynasty;
        return teams;
    }

 // 42, 54, 28
 // 39, 34, 59
 // 39, 02, 47
}
