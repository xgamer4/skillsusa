import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        Bracket b = new Bracket(
            new Team("Fuel", randomizer),
            new Team("Outlaws", randomizer), 
            new Team("Shock", randomizer), 
            new Team("Dynasty", randomizer)
        );
        System.out.println("Here are the Teams: \n");
        for (Team team: b.getTeams()) {
            System.out.println(team);
        }

        b.playerBracketGuess();
        System.out.println("Now we simulate a game");
        b.tournament(randomizer);

        b.compareMatches();
    }

    
}
