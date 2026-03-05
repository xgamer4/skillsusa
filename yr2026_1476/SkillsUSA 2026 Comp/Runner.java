import java.util.*;

public class Runner {

    public static OceanGame game;

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        String oppName = "";
        String playerName = args.length < 1 ? "Jupiter" : args[0];

        if(args.length < 2)
        {
            // If not entered, randomly select a name for the enemy.
            Random rand = new Random();
            switch(rand.nextInt(7))
            {
                case 0:
                    oppName = "Mathie";
                    break;
                case 1:
                    oppName = "Wyatt";
                    break;
                case 2:
                    oppName = "Smith";
                    break;
                case 3:
                    oppName = "John OceanDestruction2";
                    break;
                case 4:
                    oppName = "Nyan Cat";
                    break;
                case 5:
                    oppName = "Zombie from Plants vs Zombies";
                    break;
                case 6:
                    oppName = "Adam";
                    break;
            }
        }
        else {
            oppName = args[1];
        }

        game = new OceanGame(playerName, oppName);
        playGame(s);
    }

    public static void playGame(Scanner s)
    {
        System.out.println("WELCOME TO OCEAN DESTRUCTION 2!");
        System.out.println("Are you ready to play?");
        s.nextLine();
        game.round(Ownership.User, s);
    }

}