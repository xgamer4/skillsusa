import java.util.Random;

public class Test {
    public static void main(String[] args) {


        Random randomizer = new Random();
        Team[] teams = {
            new Team("Fuel", randomizer),
            new Team("Outlaws", randomizer),
            new Team("Shock", randomizer),
            new Team("Dynasy", randomizer)
        };

        for (Team team : teams) {
            System.out.println(team);
        }

        // Match test = new Match(teams[0], teams[1]);
        // System.out.println("winner is: " + test.simulate(randomizer));
        // System.out.println(3/2);

        Bracket b = new Bracket(teams);
        System.out.println();
        System.out.println(b);
        b.playerBracketGuess();
        System.out.println();
        b.tournament(randomizer);
        System.out.println();
        System.out.println(b);

    }
}
