import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BracketSimulator {

    private final static Random rng = new Random();
    private static Scanner inputScanner;

    /**
     * generateTeamMember creates a random team member that can be used as part of a team.
     * @return the random team member
     */
    private static TeamMember generateTeamMember() {
        TeamMember randomTeamMember = new TeamMember();

        // Generate random proficiency values for each role
        randomTeamMember.setDpsProficiency(rng.nextInt(101));
        randomTeamMember.setTankProficiency(rng.nextInt(101));
        randomTeamMember.setSupportProficiency(rng.nextInt(101));

        return randomTeamMember;
    }

    /**
     * generateRandomTeam creates different randomized team members to simulate a real team.
     * @param teamName the name of the newly created team
     * @return the randomized team
     */
    public static Team generateRandomTeam(String teamName) {
        Team randomTeam = new Team(teamName);

        // Generate random team members for the team
        randomTeam.dps = generateTeamMember();
        randomTeam.tank = generateTeamMember();
        randomTeam.support = generateTeamMember();

        return randomTeam;
    }

    /**
     * subContest determines whether the team member with proficiency 1 is more or less likely to win in a battle with
     * the team member with proficiency 2.
     * @param proficiency1 the proficiency of the first team member
     * @param proficiency2 the proficiency of the second team member
     * @return a boolean representing whether the first team member will win or not
     */
    private static boolean subContest(int proficiency1, int proficiency2) {
        double teamMember1WinChance = (double) proficiency1 / ((double) proficiency1 + (double) proficiency2);
        return rng.nextDouble(1) <= teamMember1WinChance;
    }

    /**
     * canTeamWin allows you to determine the likeliness of a team winning.
     * in a match with team2.
     * @param team1 the team of which to determine the possibility of winning in a match with team 2
     * @param team2 the team to go against team 1
     * @return whether team1 is likely to win or not
     */
    public static boolean canTeamWin(Team team1, Team team2) {
        // Get the proficiencies of the individual team members in team 1 (in order: DPS, Tank, Support)
        boolean dps1VsTank2 = subContest(team1.dps.getDpsProficiency(), team2.tank.getTankProficiency()),
                tank1VsDps2 = subContest(team1.tank.getTankProficiency(), team2.dps.getDpsProficiency()),
                support1VsSupport2 = subContest(team1.support.getSupportProficiency(), team2.support.getSupportProficiency());

        // Count how many wins team 1 would get in this match
        int bestOf3 = 0;
        for (boolean possibleWin : new boolean[] {dps1VsTank2, tank1VsDps2, support1VsSupport2}) {
            if (possibleWin) bestOf3++;
        }

        // Return whether team 1 got best of 3 (two or more match wins)
        return bestOf3 >= 2;
    }

    /**
     * subContestEquation does the same calculation as the subContest function for all parts of the team but with just
     * the math so an accuracy can be calculated.
     * @param team1 the team to determine the equation average for
     * @param team2 the team to compare against
     * @return an average of the probability of all the team members wining
     */
    public static double subContestEquation(Team team1, Team team2) {
        // Calculates the percentage chance of winning
        double dpsVsTank = (double)team1.dps.getDpsProficiency() / ((double)team1.dps.getDpsProficiency() + team2.tank.getTankProficiency());
        double tankVsDps = (double)team1.tank.getTankProficiency() / ((double)team1.tank.getTankProficiency() + team2.dps.getDpsProficiency());
        double supportVsSupport = (double)team1.support.getSupportProficiency() / ((double)team1.support.getSupportProficiency() + team2.support.getSupportProficiency());

        // Averages out
        return (dpsVsTank + tankVsDps + supportVsSupport) / 3;
    }

    private static void userPrediction(Team team1, Team team2, Team winner) {
        // Display the information about the specific bracket
        System.out.println("This bracket is between team " + team1.teamName + " and team " + team2.teamName + "." +
                "\nHere are their stats:\n" + team1 + "\n" + team2);

        // Prepare for getting the team the user think will win
        int userGuess = 0;

        // Get the user guess (validation section)
        do {
            System.out.print("Which team do you think will win?\n(1 for team " + team1.teamName + ", 2 for team " + team2.teamName + "): ");
            // Attempt to parse the response from the user
            try {
                String inputtedGuess = inputScanner.nextLine();
                userGuess = Integer.parseInt(inputtedGuess);
                if (userGuess >= 1 && userGuess <= 2) break;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a number. Try again.");
                continue;
            }
            // If the number is out of range, let the user know
            if (userGuess < 1 || userGuess > 2) {
                System.out.println("You did not enter the number 1 or 2. Try again.");
            }
        } while (true); // Repeat until the user has entered 1 or 2 (a break is used when there is a successful parse)

        // Congratulate or console the user depending on their guess
        Team selectedTeam = userGuess == 1 ? team1 : team2;
        if (selectedTeam.teamName.equals(winner.teamName)) {
            System.out.println("Congratulations! You got the right team.");
        } else {
            System.out.println("Sorry! The team that won was team " + winner.teamName + ".");
        }

        // Determine the accuracy of the guess
        double winMargin = 999;
        if (userGuess == 1) {
            winMargin = Math.abs(subContestEquation(team1, team2) - 0.5); // How far from zero?
        } else {
            winMargin = Math.abs(subContestEquation(team2, team1) - 0.5);
        }
        String likelihoodMessage;
        if (winMargin <= 0.15) {
            likelihoodMessage = "This team is weak, the likelihood of team " + selectedTeam.teamName + " winning is low.";
        } else if (winMargin <= 0.3) {
            likelihoodMessage = "This team was okay, the likelihood of " + selectedTeam.teamName + " winning later is definitely possible.";
        } else {
            likelihoodMessage = "It was pretty likely that there was no randomness affecting team " + selectedTeam.teamName + " winning.";
        }

        System.out.println(likelihoodMessage);

        // Add a delay so the user can read the result of the bracket
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted. Continuing as normal...");
        }

        System.out.println();
    }

    /**
     * main is the entry point of the program.
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        inputScanner = new Scanner(System.in);

        // Generate the four teams for the bracket simulation
        Team teamFuel = generateRandomTeam("Fuel"), teamOutlaws = generateRandomTeam("Outlaws"),
                teamShock = generateRandomTeam("Shock"), teamDynasty = generateRandomTeam("Dynasty");

        // Determine the winners of the first-level brackets
        boolean fuelVsOutlaws = canTeamWin(teamFuel, teamOutlaws);
        boolean shockVsDynasty = canTeamWin(teamShock, teamDynasty);

        // Get the winners of the first two brackets
        Team team1 = fuelVsOutlaws ? teamFuel : teamOutlaws;
        Team team2 = shockVsDynasty ? teamShock : teamDynasty;

        // Determine the final winning team in the match between the two prevailing teams
        boolean finalBracket = canTeamWin(team1, team2);
        Team finalWinner = finalBracket ? team1 : team2;

        // Start the user prediction stage
        userPrediction(teamFuel, teamOutlaws, team1);
        userPrediction(teamShock, teamDynasty, team2);
        userPrediction(team1, team2, finalWinner);

        inputScanner.close();
    }

}
