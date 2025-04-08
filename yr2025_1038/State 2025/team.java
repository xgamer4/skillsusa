import java.util.Random;

/**
 * Predicting Firestorm's UnderAssualt
 * 
 * @author Nicole Prosser
 */
public class team {
    private String teamName;
    private DPS dpsPlayer;
    private support supportPlayer;
    private tank tankPlayer;
    private Random random = new Random();
    private int subScore;
    private int overallScore;


    /**
     * Constructs a new team
     * @param name name of the team
     */
    public team(String name){
        this.teamName = name;
        this.dpsPlayer = new DPS(name + "'s dps player");
        this.tankPlayer = new tank(name + "'s tank player");
        this.supportPlayer = new support(name + "'s support player");
        this.subScore = 0;
        this.overallScore = 0;
    }

    /**
     * Prints out all the stats in a formatted way
     */
    public void printTeamStats(){
        System.out.println("TEAM: " + this.teamName);
        this.dpsPlayer.getProfScores();
        this.tankPlayer.getProfScores();
        this.supportPlayer.getProfScores();
    }
    
    /**
     * @return the dps player of the team
     */
    public DPS getDpsPlayer(){
        return this.dpsPlayer;
    }

    /**
     * @return the tank player of the team
     */
    public tank getTankPlayer(){
        return this.tankPlayer;
    }

    /**
     * @return the support player of the team
     */
    public support getSupportPlayer(){
        return this.supportPlayer;
    }

    /**
     * @return the team name
     */
    public String getName(){
        return this.teamName;
    }

    /**
     * @return the current score of how many sub-matches the team has won
     */
    public int getSubScore(){
        return this.subScore;
    }

    /**
     * @return the current score of how many overall matches the team has won
     */
    public int getOverall(){
        return this.overallScore;
    }

    /**
     * adds one point to the teams score for sub-matches
     */
    public void addToSub(){
        this.subScore += 1;
    }

    /**
     * adds one point to the teams score for overall matches
     */
    public void addToOverall(){
        this.overallScore += 1;
    }

    /**
     * resets the team's subscore total
     */
    public void resetSubScores(){
        this.subScore = 0;
    }

    /**
     * resets the team's overall score total
     */
    public void resetOverallScores(){
        this.overallScore= 0;
    }

    /**
     * Runs a simulation of a DPS submatch
     * @param team2 the team that this team will face off against
     * @return the name of the winning team
     */
    public String runDpsMatch(team team2){
        DPS dps = this.getDpsPlayer();
        int dpsScore = dps.getDPS();

        tank tank = team2.getTankPlayer();
        int tankScore = tank.getTank();

        System.out.println(dpsScore + " / (" + dpsScore + " + " + tankScore + ")");

        float sum = dpsScore + tankScore;
        float chanceOfWinning = ((float) dpsScore) / sum;
        chanceOfWinning *= 100;
        chanceOfWinning = Math.round(chanceOfWinning);
        chanceOfWinning /= 100;

        float oddToBeat = random.nextFloat(0, 1);
        oddToBeat *= 100;
        oddToBeat = Math.round(oddToBeat);
        oddToBeat /= 100;
        System.out.println(oddToBeat);

        if(oddToBeat <= chanceOfWinning){
            System.out.println(this.getName() + "'s DPS has beaten " + team2.getName() + "'s Tank as " + chanceOfWinning +  " >= " + oddToBeat);
            System.out.println(this.getName() + " wins this sub-contest");
            this.addToSub();
            return this.getName();
        } else{
            System.out.println(team2.getName() + "'s Tank has beaten " + this.getName() + "'s DPS as " + chanceOfWinning +  " < " + oddToBeat);
            System.out.println(team2.getName() + " wins this sub-contest");
            team2.addToSub();
            return team2.getName();

        }

    }

    /**
     * Runs a simulation of a Tank submatch
     * @param team2 the team that this team will face off against
     * @return the name of the winning team
     */
    public String runTankMatch(team team2){
        tank tank = this.getTankPlayer();
        int tankScore = tank.getTank();

        DPS dps = team2.getDpsPlayer();
        int dpsScore = dps.getDPS();

        float sum = tankScore + dpsScore;
        float chanceOfWinning = ((float) tankScore) / sum;
        chanceOfWinning *= 100;
        chanceOfWinning = Math.round(chanceOfWinning);
        chanceOfWinning /= 100;

        float oddToBeat = random.nextFloat(0, 1);
        oddToBeat *= 100;
        oddToBeat = Math.round(oddToBeat);
        oddToBeat /= 100;
        System.out.println(oddToBeat);

        if(oddToBeat <= chanceOfWinning){
            System.out.println(this.getName() + "'s Tank has beaten " + team2.getName() + "'s DPS as " + chanceOfWinning +  " >= " + oddToBeat);
            System.out.println(this.getName() + " wins this sub-contestas " + chanceOfWinning +  " < " + oddToBeat);
            this.addToSub();
            return this.getName();
        } else{
            System.out.println(team2.getName() + "'s DPS has beaten " + this.getName() + "'s Tank");
            System.out.println(team2.getName() + " wins this sub-contest");
            team2.addToSub();
            return team2.getName();
        }

    }

    /**
     * Runs a simulation of a Support submatch
     * @param team2 the team that this team will face off against
     * @return the name of the winning team
     */
    public String runSupportMatch(team team2){
        support support1 = this.getSupportPlayer();
        int supportScore1 = support1.getSupport();

        support support2 = team2.getSupportPlayer();
        int supportScore2 = support2.getSupport();

        System.out.println(supportScore1 + " / (" + supportScore1 + " + " + supportScore2 + ")");

        float sum = supportScore1 + supportScore2;
        float chanceOfWinning = ((float) supportScore1) / sum;
        chanceOfWinning *= 100;
        chanceOfWinning = Math.round(chanceOfWinning);
        chanceOfWinning /= 100;
        System.out.println(" = " + chanceOfWinning);

        float oddToBeat = random.nextFloat(0, 1);
        oddToBeat *= 100;
        oddToBeat = Math.round(oddToBeat);
        oddToBeat /= 100;
        System.out.println(oddToBeat);

        if(oddToBeat <= chanceOfWinning){
            System.out.println(this.getName() + "'s Support has beaten " + team2.getName() + "'s Support as " + chanceOfWinning +  " >= " + oddToBeat);
            System.out.println(this.getName() + " wins this sub-contest");
            this.addToSub();
            return this.getName();
        } else{
            System.out.println(team2.getName() + "'s Support has beaten " + this.getName() + "'s Support as " + chanceOfWinning +  " < " + oddToBeat);
            System.out.println(team2.getName() + " wins this sub-contest");
            team2.addToSub();
            return team2.getName();
        }

    }
    
}
