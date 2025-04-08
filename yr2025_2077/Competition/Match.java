import java.util.Random;

public class Match {
    private Team team1;
    private Team team2;

    public Match(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }
    //simulate a single round
    public boolean simulateContest(Random randomizer) {
        int t1score = 0;
        t1score += dpsVtank(randomizer) ? 1 : 0;
        t1score += tankVdps(randomizer) ? 1 : 0;
        t1score += supportVsupport(randomizer)? 1 : 0;
        return t1score >= 2;
    }

    private double chanceToWin(int prof1, int prof2) {
        return (double) prof1 / ((double) prof1 + (double) prof2);
    }

    private boolean subContest(Random randomizer, int p1, int p2){
        double chance = chanceToWin(p1, p2);
        double result = randomizer.nextDouble();
        return result <= chance;
    }

    //simulate a 3-round match

    public Team simulate(Random randomizer){
        int team1Wins = 0;
        int team2Wins = 0;
        do {
            boolean result = simulateContest(randomizer);
            if(result){
                team1Wins ++;
            }else {
                team2Wins ++;
            }
        } while (team1Wins < 2 && team2Wins < 2);
        if(team1Wins >= 2){
            return team1;
        } else {
            return team2;
        }
    }

    //each sub-contest returns true if team1 wins

    //simulate a dps v tank sub-contest
    public boolean dpsVtank(Random randomizer) {
        return subContest(randomizer, team1.getDpsProficiency(), team2.getTankProficiency());
    }

    //simulate a tank v dps sub-contest
    public boolean tankVdps(Random randomizer) {
        return subContest(randomizer, team1.getDpsProficiency(), team2.getTankProficiency());
    }
    //simulate a support sub-contest
    public boolean supportVsupport(Random randomizer) {
        return subContest(randomizer, team1.getDpsProficiency(), team2.getTankProficiency());
    }
}
