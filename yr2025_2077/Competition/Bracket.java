import java.util.Random;
import java.util.Scanner;

public class Bracket {
    private Team[] teams;
    private Team[][] matchups;
    private Team winnerTeam;
    private Team[][] playerBracketMatchups;
    private Team playerBracketWinner;
    //should be able to expand beyond 4, but currently built to hold an even number of teams.
    public Bracket(Team... teams){
        this.teams = teams;
        matchups = new Team[teams.length-1][2];
        playerBracketMatchups = new Team[teams.length-1][2];
        for (int i = 0; i < this.teams.length; i+=2) {
            matchups[i/2][0] =teams[i];
            playerBracketMatchups[i/2][0] =teams[i];
            if(i +1 < teams.length){
                matchups[i/2][1] =teams[i+1];
                playerBracketMatchups[i/2][1] =teams[i+1];
            }
        }//undecided matchups will be left as null
    }

    public Team tournament(Random randomizer) {
        System.out.println("The Tournament begins!");
        tournamentLevel(randomizer, 0, matchups.length / 2 );
        return winnerTeam;
    }

    public void tournamentLevel(Random randomizer, int start, int end) {
        if(start >= end){//if we are on the last matchup just perform that matchup.
            winnerTeam = matchUp(start, randomizer);
            return;
        }else {//cycle through matchups at a level.
            for (int i = start; i <= end; i+=2) {
                matchups[end+i+1][0] = matchUp(i, randomizer);

                if(i +1 <= end){
                    matchups[end+i+1][1] =matchUp(i+1, randomizer);
                }
            }
            tournamentLevel(randomizer, end+1, end+((start-end)/2));
        }
        
    }

    public Team matchUp(int match, Random randomizer) {
        System.out.printf("\n%s VS %s", matchups[match][0].getName(), matchups[match][1].getName());
        Match m = new Match(matchups[match][0], matchups[match][1]);
        Team winner = m.simulate(randomizer);
        System.out.printf("\nTeam %s wins!\n", winner.getName());
        return winner;
    }

    public String toString() {
        String s = "";
        for (Team team : teams) {
            s+= ("\nTeam: " + team.getName());
        }

        for (int i = 0; i < matchups.length; i++) {
            if(matchups[i][0] == null || matchups[i][1] == null){
                s += ("\n??TBD??");
            }else {
                s += String.format("\n%s VS %s", matchups[i][0].getName(), matchups[i][1].getName());
            }
            
        }
        if(winnerTeam != null){
            s += "\n" + winnerTeam.getName() + " is the winner!";
        }
        return s;
    }

    public void playerBracketGuess() {
        Scanner input = new Scanner(System.in);
        System.out.println("Fill out Your Bracket: ");
        fillBracketLevel(input, 0, playerBracketMatchups.length/2);
        System.out.printf("So, you think %s will win?", playerBracketWinner.getName());
        input.close();
    }

    public void fillBracketLevel(Scanner s, int start, int end){
        if(start >= end){//if we are on the last matchup just perform that matchup to find the winner.
            playerBracketWinner = askForMatch(start, s);
            return;
        }else {//cycle through matchups at a level.
            for (int i = start; i <= end; i+=2) {
                playerBracketMatchups[end+i+1][0] = askForMatch(i, s);

                if(i +1 <= end){
                    playerBracketMatchups[end+i+1][1] =askForMatch(i+1, s);
                }
            }
            fillBracketLevel(s, end+1, end+((start-end)/2));
        }
    }

    private Team askForMatch(int match, Scanner s){
        System.out.printf("\n%s VS %s\n", playerBracketMatchups[match][0].getName(), playerBracketMatchups[match][1].getName());
        System.out.printf("\nWhat is your Prediction?\nEnter 1 for %s or 2 for %s\n", playerBracketMatchups[match][0].getName(), playerBracketMatchups[match][1].getName());
        Team winner = null;
        do{
            String entry = s.nextLine();
            if(entry.contains("1")){
                winner = playerBracketMatchups[match][0];
            }else if (entry.contains("2")){
                winner = playerBracketMatchups[match][1];
            }else {
                System.out.println("Wrong entry, try again.");
            }
        } while (winner == null);
        
        System.out.printf("\nTeam %s marked to win!\n", winner.getName());
        return winner;
    }

    public void compareMatches(){
        int numberCorrect = 0;
        int total = 0;
        for (int i = 0; i < matchups.length; i++) {
            for (int j = 0; j < matchups[i].length; j++) {
                if(matchups[i][j] == playerBracketMatchups[i][j]){
                    numberCorrect ++;
                }
                total++;
            }
        }
        if(winnerTeam == playerBracketWinner){
            numberCorrect++;
            System.out.println("You guesed the winner wright!");
        }else {
            System.out.println("You did not guess the winner.");
        }
        total++;
        System.out.println("You got " + Math.round((double) numberCorrect/(double) total * 100.0) + "% right");
    }

    public Team[] getTeams(){
        return teams;
    }

    


}
