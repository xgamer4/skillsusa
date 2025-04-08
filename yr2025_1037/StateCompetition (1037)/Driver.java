import java.util.Scanner;

/**
 * Driver for this project, allows command line interface
 */
public class Driver{
    /**
     * variables needed for helper functions
     */
    static String input;
    static Scanner scanner;
    static SimulatedBracket bracket;
    static int correctGuesses;

    public static void main(String[] args){
        /**
         * keeping track of variables, in case user resets things 
         * nothing starts loaded
         */
        bracket = null;
        Team fuel = null;
        Team outlaws = null;
        Team shock = null;
        Team dynasty = null;
        Team t1 = null;
        Team t2 = null;
        int gamesPlayed = 0;
        correctGuesses = 0;
        
        System.out.println("This is a program to predict winners for Underassault given two random teams with players");
        System.out.println("Each player is assigned random stas as a Tank, DPS, and Support");
        System.out.println("");
        System.out.println("To start type 's'");
        System.out.println("For all commands type 'h'");
        
        scanner = new Scanner(System.in);
        input = scanner.nextLine();

        while(!input.equalsIgnoreCase("q")){

            // Starts a new bracket, creates new teams, resets games played and correct Guesses
            if(input.equalsIgnoreCase("s")){
                bracket = new SimulatedBracket();
                fuel = bracket.getTeams()[0];
                outlaws = bracket.getTeams()[1];
                shock = bracket.getTeams()[2];
                dynasty = bracket.getTeams()[3];
                gamesPlayed = 0;
                correctGuesses = 0;
                t1 = null;
                t2 = null;
                System.out.println("A bracket has been created with random teams, and players");
            }
            //Starts a game/match between two teams if a bracket has started
            else if(input.equalsIgnoreCase("g")){
                if(bracket == null){
                    System.out.println("Bracket not started");
                    continue;
                }
                if(gamesPlayed == 0){
                    t1 = runGame(fuel, outlaws);
                    gamesPlayed++;
                }
                else if(gamesPlayed == 1){
                    t2 = runGame(shock, dynasty);
                    gamesPlayed++;
                }
                else if(gamesPlayed == 2){
                    runGame(t1, t2);
                    gamesPlayed++;
                    System.out.println("All games have been played.");
                    System.out.println("You guessed the winner correctly " + correctGuesses + " times.");
                }
                else{
                    System.out.println("All games have been played.");
                    System.out.println("You guessed the winner correctly " + correctGuesses + " times.");
                }
            }
            // prints all teams and players only if a bracket has started
            else if(input.equalsIgnoreCase("t")){
                if(bracket == null){
                    System.out.println("Bracket not started");
                    continue;
                }
                for (Team team : bracket.getTeams()) {
                    printTeam(team);
                }
            }
            if(input.equalsIgnoreCase("h")){
                System.out.println();
                System.out.println("To start a bracket, type 's'");
                System.out.println("To start a game, type 'g'");
                System.out.println("To see teams, type 't'");
                System.out.println("To quit, type 'q'");
            }

            input = scanner.nextLine();
        }
        scanner.close();
        System.out.println("Program Closed");
    }

    // runs a game, and prints things based on the two teams, as well as who won each match and which subcontests they won
    public static Team runGame(Team t1, Team t2){
        Team winner = null;
        System.out.println("Starting game between the " + t1.name + " and the " + t2.name);
        System.out.println("");
        System.out.println("Who do you think will win?");
        printTeam(t1);
        printTeam(t2);
        System.out.println("type '1' for " + t1.name + " and '2' for " + t2.name);
        input = scanner.nextLine();
        winner = bracket.SimulatedMatch(t1, t2);
        System.out.println("The " + winner.name + " win!");
        if(input.equals("1") && winner == t1)
            correctGuesses++;
        else if(input.equals("2") && winner == t2)
            correctGuesses++;
        return winner;
    }

    //prints an entire team and it's players
    public static void printTeam(Team t1){
        System.out.println("");
        System.out.println("The " + t1.name);
        System.out.println("The players on this team are: ");
        System.out.println("Tank Player" + " TANK: " + t1.getTankPlayer().getTankProficiency() + " DPS: " + t1.getTankPlayer().getDPSProficiency() + " Support: " + t1.getTankPlayer().getSupportProficiency());
        System.out.println("DPS Player" + " TANK: " + t1.getDPSPlayer().getTankProficiency() + " DPS: " + t1.getDPSPlayer().getDPSProficiency() + " Support: " + t1.getDPSPlayer().getSupportProficiency());
        System.out.println("Support Player" + " TANK: " + t1.getSupportPlayer().getTankProficiency() + " DPS: " + t1.getSupportPlayer().getDPSProficiency() + " Support: " + t1.getSupportPlayer().getSupportProficiency());
        
    }
}
    