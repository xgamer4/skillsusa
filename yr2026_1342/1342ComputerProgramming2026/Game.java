import java.util.Random;
import java.util.Scanner;

public class Game
{
    public String phase;
    public Base[] bases = new Base[4];

    public Player player;
    public Player enemy;

    public int totalRounds;
    public int round;

    public Scanner scanner = new Scanner(System.in);

    public Random random = new Random();

    // This method instantiates the players, and also creates the bases.
    public void setup()
    {
        System.out.println("How many rounds should this game be? (Min: 2)");

        try
        {
            totalRounds = Math.max(2, Integer.parseInt(scanner.nextLine()));
        }
        catch (Exception e)
        {
            totalRounds = 5;
        }

        System.out.println();

        round = 1;

        // Creates the player and the enemy. Both are instantiated with 2 units and 1 claimed base.
        player = new Player("Player");
        enemy = new Player("Enemy");

        // The bases are added and each given a unique index; the player and enemy both own one base.
        bases[0] = new Base("Player");
        bases[1] = new Base("Unclaimed");
        bases[2] = new Base("Unclaimed");
        bases[3] = new Base("Enemy");

        // Whenever getGameState() is printed out, the current stats are displayed (e.g. player units, bases, base occupancy)
        System.out.println(getGameState());

        main();
    }

    // The main function contains the main game loop, where turns alternate and the game ends early if one player has 0 units and 0 bases.
    public void main()
    {
        while (round <= totalRounds) // Repeats until 5 turns have passed, resulting in overtime.
        {
            System.out.println(
                "\n--------------------\n" + 
                "ROUND " + round + "\n" +
                "--------------------\n"
            );

            System.out.println(getGameState());

            turn(player);

            // Checking for early game end as a result of Player's action.
            if ((player.getBases() == 0 && player.getUnits() == 0) || (enemy.getBases() == 0 && enemy.getUnits() == 0)) // Game ends after player's turn.
            {
                end();
            }

            turn(enemy);

            round++;

            // Checking for early game end as a result of Enemy's action.
            if ((player.getBases() == 0 && player.getUnits() == 0) || (enemy.getBases() == 0 && enemy.getUnits() == 0)) // Game ends after player's turn.
            {
                end();
            }
        }

        // Overtime game end
        end();
    }

    // This method contains the bulk of the logic behind taking turns. The user can choose between 4 options, whereas the enemy has a randomly-generated option.
    public void turn(Player turn)
    {
        int action = 0;

        if (turn == player)
        {
            System.out.println(
                "\nPlayer's turn! Make an action:\n" +
                "1: Train (Increase units by 1 per # of bases owned)\n" +
                "2: Attack (Initiate combat with the enemy)\n" +
                "3: Expand: Claim an unowned base if available; if not, skip turn.\n" +
                "4: Forfeit: Quit the game.\n"
            );

            boolean proceed = true;

            do
            {
                try {
                    action = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    action = 0;
                }

                System.out.println();

                switch (action)
                {
                    case 1: // Train
                        System.out.println("TRAINING\n---------------\n");

                        player.units += player.getBases();

                        System.out.println("Player trains, gaining " + player.getBases() + " unit(s); " + player.getUnits() + " units");

                        proceed = true;
                        break;
                    case 2: // Attack
                        System.out.println("ATTACKING\n---------------\n");

                        System.out.println("Player is attacking Enemy!\n");

                        attack(player, enemy);

                        proceed = true;
                        break;
                    case 3: // Expand
                        System.out.println("EXPANDING\n---------------\n");

                        boolean claimedBase = false;

                        for (int i = 0; i < bases.length; i++)
                        {
                            System.out.println(bases[i].claimed);
                            if (bases[i].getClaimed() == "Unclaimed")
                            {
                                claimedBase = true;
                                bases[i].claimed = "Player";
                                player.bases++;
                                System.out.println("Player has claimed Base " + (i + 1) + "!");
                                break;
                            }
                        }

                        if (!claimedBase)
                        {
                            System.out.println("No bases were unowned. Turn skipped.");
                        }

                        proceed = true;
                        break;
                    case 4: // Quit
                        System.out.println("QUIT\n---------------\n");

                        System.out.println(
                            "Player has forfeited the game. Enemy wins!\n\n" +
                            "Goodbye!"
                        );
                        System.exit(0);
                        proceed = true;
                        break;
                    default: // Invalid
                        System.out.println(
                            "Invalid Choice! (Choose an action 1-4)\n" +
                            "1: Train (Increase units by 1 per # of bases owned)\n" +
                            "2: Attack (Initiate combat with the enemy)\n" +
                            "3: Expand: Claim an unowned base if available; if not, skip turn.\n" +
                            "4: Forfeit: Quit the game.\n"
                        );
                        proceed = false;
                        break;
                }

                System.out.println();
            } while (!proceed);
        }
        else
        {
            action = random.nextInt(3) + 1;

            if (random.nextInt(100) + 1 == 67) // Easter egg where enemy forfeits the game.
            {
                action = 4;
            }

            System.out.println("Enemy's turn!");

            System.out.println();

            switch (action)
            {
                case 1: // Train
                    System.out.println("TRAINING\n---------------\n");

                    enemy.units += enemy.getBases();

                    System.out.println("Enemy trains, gaining " + enemy.getBases() + " unit(s); " + enemy.getUnits() + " units");

                    break;
                case 2: // Attack
                    System.out.println("ATTACKING\n---------------\n");

                    System.out.println("Enemy is attacking Player!\n");

                    attack(enemy, player);

                    break;
                case 3: // Expand
                    System.out.println("EXPANDING\n---------------\n");

                    boolean claimedBase = false;

                    for (int i = 0; i < bases.length; i++)
                    {
                        if (bases[i].getClaimed() == "Unclaimed")
                        {
                            claimedBase = true;
                            bases[i].claimed = "Enemy";
                            enemy.bases++;
                            System.out.println("Enemy has claimed Base " + (i + 1) + "!");
                            break;
                        }
                    }

                    if (!claimedBase)
                    {
                        System.out.println("No bases were unowned. Turn skipped.");
                    }

                    break;
                case 4: // Quit
                    System.out.println("QUIT\n---------------\n");

                    System.out.println(
                        "For some reason, the enemy has decided to forfeit the game! Player wins!\n\n" +
                        "Goodbye!"
                    );
                    System.exit(0);
                    break;
            }
        }
    }

    // This method handles the attacking logic.
    public void attack(Player attacker, Player defender)
    {
        // Integer arrays are created that contain random integers for each index.
        int[] attackingUnits = new int[Math.max(0, Math.min(3, attacker.getUnits() - 1))];
        int[] defendingUnits = new int[Math.max(0, Math.min(2, defender.getUnits()))];

        // Loops until the attacker or defender has run out of units.
        while (attackingUnits.length > 0 && defendingUnits.length > 0)
        {
            System.out.println(getGameState() + "\n");
            System.out.println(attacker.getTeam() + " is attacking with " + attackingUnits.length + " units!");
            System.out.println(defender.getTeam() + " is defending with " + defendingUnits.length + " units!\n");

            for (int i = 0; i < attackingUnits.length; i++)
            {
                attackingUnits[i] = random.nextInt(6) + 1;
            }

            for (int i = 0; i < defendingUnits.length; i++)
            {
                defendingUnits[i] = random.nextInt(6) + 1;
            }

            // Sorts the randomly-generated numbers from largest to smallest.
            sort(attackingUnits);
            sort(defendingUnits);

            System.out.print(attacker.getTeam() + "'s units: [");

            for (int i = 0; i < attackingUnits.length; i++)
            {
                System.out.print(attackingUnits[i]);

                if (i < attackingUnits.length - 1)
                {
                    System.out.print(", ");
                }
            }

            System.out.print("]\n");

            System.out.print(defender.getTeam() + "'s units: [");

            for (int i = 0; i < defendingUnits.length; i++)
            {
                System.out.print(defendingUnits[i]);

                if (i < defendingUnits.length - 1)
                {
                    System.out.print(", ");
                }
            }

            System.out.print("]\n");

            // Each unit faces the opponent's, comparing values. The unit with the higher value wins. On a tie, the defender wins.
            for (int i = 0; i < Math.min(attackingUnits.length, defendingUnits.length); i++)
            {
                int attackerUnit = attackingUnits[i];
                int defenderUnit = defendingUnits[i];

                System.out.println(attacker.getTeam() + "'s " + attackingUnits[i] + " VS " + defender.getTeam() + "'s " + defendingUnits[i]);

                if (attackerUnit > defenderUnit)
                {
                    defendingUnits = loseUnit(defendingUnits);
                    System.out.println(attacker.getTeam() + " won! " + defender.getTeam() + " -1 unit.");
                }
                else if (attackerUnit <= defenderUnit)
                {
                    attackingUnits = loseUnit(attackingUnits);
                    System.out.println(defender.getTeam() + " won! " + attacker.getTeam() + " -1 unit.");
                }
            }

            System.out.println();
        }

        if (attackingUnits.length > defendingUnits.length) // Attacker wins
        {
            System.out.println("WINNER: " + attacker.getTeam());
            System.out.println("LOSER: " + defender.getTeam());

            defender.units = 0;

            if (defender.bases > 0)
            {
                defender.bases--;
                attacker.bases++;

                gainBase(attacker);
            }
        }
        else // Defender wins
        {
            System.out.println("WINNER: " + defender.getTeam());
            System.out.println("LOSER: " + attacker.getTeam());

            attacker.units = 1;
        }
    }

    public void end()
    {
        System.out.println(getGameState());

        System.out.println(
            "\n--------------------\n" + 
            "END OF GAME\n" +
            "--------------------\n"
        );

        if ((player.getBases() == 0 && player.getUnits() == 0)) // Enemy wins
        {
            System.out.println("ENEMY WINS!");
        }
        else if ((enemy.getBases() == 0 && enemy.getUnits() == 0)) // Player wins
        {
            System.out.println("PLAYER WINS!");
        }
        else // Overtime
        {
            if (player.getUnits() < enemy.getUnits()) // Enemy wins
            {
                System.out.println("ENEMY WINS!");
            }
            else if (player.getUnits() > enemy.getUnits()) // Player wins
            {
                System.out.println("PLAYER WINS!");
            }
            else // Tiebreaker
            {
                if (player.getBases() < enemy.getBases()) // Enemy wins
                {
                    System.out.println("ENEMY WINS!");
                }
                else if (player.getBases() > enemy.getBases()) // Player wins
                {
                    System.out.println("PLAYER WINS!");
                }
                else
                {
                    System.out.println("IT WAS A TIE.");
                }
            }
        }

        System.out.println("\nPlay again? (1 for yes, 0 for no)");

        if (Integer.parseInt(scanner.nextLine()) == 1)
        {
            System.out.println();
            setup();
        }
        else
        {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }

    private void sort(int[] array)
    {
        for (int i = array.length - 1; i >= 0; i--)
        {
            for (int j = i - 1; j >= 0; j--)
            {
                if (array[i] > array[j])
                {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // Since I am using arrays, which have fixed lengths, I needed to copy the array, but leave out the last index (length - 1).
    private int[] loseUnit(int[] array)
    {
        int[] newArray = new int[array.length - 1];

        for (int i = 0; i < array.length - 1; i++)
        {
            newArray[i] = array[i];
        }

        return newArray;
    }

    // Loops through the bases to find one owned by the opponent, then claims it.
    private void gainBase(Player attacker)
    {
        for (int i = 0; i < bases.length; i++)
        {
            if (attacker.getTeam() == "Player" && bases[i].getClaimed() == "Enemy")
            {
                bases[i].claimed = "Player";
                break;
            }
            else if (attacker.getTeam() == "Enemy" && bases[i].getClaimed() == "Player")
            {
                bases[i].claimed = "Enemy";
                break;
            }
        }
    }

    // Prints out basic stats during the game.
    private String getGameState()
    {
        return
        "GAME STATE:\n\n" +
        "Player: " + player.getUnits() + " units, " + player.getBases() + " bases\n" +
        "Enemy: " + enemy.getUnits() + " units, " + enemy.getBases() + " bases\n\n" +
        "Base 1: " + bases[0].getClaimed() + "\n" +
        "Base 2: " + bases[1].getClaimed() + "\n" +
        "Base 3: " + bases[2].getClaimed() + "\n" +
        "Base 4: " + bases[3].getClaimed();
    }
}