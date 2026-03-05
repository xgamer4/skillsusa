import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
// Documentation Used:
//      -CodeHS Java Documentation
//      -Oracle Java Documentation(official)
//      -Stack Overflow(offical)

public class game {
    // Game Layout Assingment: Initiliaze Base Variables
    // Number 0 = Unassigned
    // Number 1 = User
    // Number 2 = Opponent
    private int baseOne;
    private int baseTwo;
    private int baseThree;
    private int baseFour;

    private int userBases;
    private int opponentBases;

    // Intilaizes Player Units( User, Opoonent)
    private int userUnits;
    private int opponentUnits;

    private boolean playerQuit = false;

    // Initiaze Scanner
    Scanner input = new Scanner(System.in);

    public game()
    {
        //Constructor- Assign base
        this.baseOne = 1;// 1= user
        this.baseTwo = 2;// 2= opponent
        this.baseThree = 0;
        this.baseFour = 0;

        // Assign Units to Players
        this.userUnits = 2;
        this.opponentUnits = 2;

        this.userBases = 1;
        this.opponentBases = 1;
    }  
    public void startGame()
    {
        //call out user turn- will determien turn of which user and opponent will take action
        //int userTurn = 0;
        // Game- Loops 5 rounds
        System.out.println("The battle begins as follows: ");
        System.out.println();
        // Start with Base Game Stats:
        System.out.println("Base 1: " + "Player");
        System.out.println("Base 2: " + "Opponent");
        System.out.println("Base 3: " + "Unowned");
        System.out.println("Base 4: " + "Unowned");
        
        System.out.println();

        // Assign Units to Players
        System.out.println("Player: " + userUnits + " Units");
        System.out.println("Opponent: " + opponentUnits + " Units");

        // Game instructions( Just in case)
        System.out.println();
        System.out.println("Instructions: Each Round you will be prompted to enter a action:( 'attack', 'expand', 'train' or 'quit' to exit), GoodLuck!   ");

        for(int i = 0; i < 5; i++) // Creates 5 Rounds of Player and opponent Action 
        {
            // Player Actions
            //1. Train
            //2. Attack
            //3. expand
            // User Phase
            // Opponent Phase
            
            // Player action = "quit"--Implement
            if(playerQuit)
            {
                System.out.println();
                System.out.println("Thank you for playing! ");
                break;
            }
            else if( userBases == 0 && userUnits == 0)
            {
                break;
            }
            else if(opponentBases == 0 && opponentUnits == 0)
            {
                break;
            }
            System.out.println();
            System.out.println("Round: " + (i + 1));
            //1. USER TURN------------------------------------------------------------------------------
            this.player();
        
            
            //2. Opponent TURN--------------------------------------------------------------------------
            this.opponent();
        }
        // Calling determine Winner Method- to Print out Winner
        if(!playerQuit)
        {
            this.determineWinner();
        }

        input.close();
    }
    public void player()
    {
        // method holds and represents our User
        System.out.print("Player: ");
        String playerAction = input.nextLine().toLowerCase();// Ensure correct Collection of user Information

        if(playerAction.equals("train"))
        {
            // Call Attack Method
            userUnits = this.train(userUnits);
            System.out.println("Train; +" + userUnits/2 + " units; " + userUnits + " units");
        }
        else if(playerAction.equals("expand"))
        {
            // Call Expand Method
            this.expand(1);
        }
        else if(playerAction.equals("attack"))
        {
            // Call Attack Method
            this.attack(1);
        }
        else
        {
            playerQuit = true;
        }
    }
    public void opponent()
    {
        // Method holds and represents our Opponent
        //Opponent- action
            //Random- Action for opponent--- Implement Random!
        System.out.println("Opponent: ");
        
        // use randomt to choose results
        //int actionDecision = (int)(Math.random() * 3 + 1); // Gives range of 3 vallues starting from 1
        int actionDecision = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        //System.out.println("TEST");
        
        // Action Decision Output KEY:
        // 1 = train
        // 2 = attack
        // 3 = expand
        if(actionDecision == 1)
        {
            // Call Train
            this.train(opponentUnits);
            //System.out.println("ONE");
        }
        else if(actionDecision == 2)
        {
            // Call attack
            this.attack(2);
            //System.out.println("TWO");
        }
        else if(actionDecision == 3)
        {
            // Call expand
            this.expand(2);
            //System.out.println("THREE");
        }
        else
        {
            playerQuit = true;
        }
    }

    // Player Valid Actions
    /**
     * @param userNumber
     */
    public void attack(int userNumber)// entered to indicate the position of the attacker(playe or opponent)
        {
            // Innitate Attack
            // Risk Combat Dynamics
                    // Attack with all units - 1
                    // Player being attacked defends with all Units
                    // Make sure to implement system to constant Check units( units and bases = 0 --> Game ends)

            if(userNumber == 1)
            {
                System.out.println("COMBAT: ");
                // Player is attacking Opponent
                System.out.println("Player attacks with " + (userUnits - 1) + " unit; min(" + userUnits + ", " +(userUnits - 1) + ")" );// Attacker Attack Strength

                System.out.println("Opponent defends with " + opponentUnits + " unit");

            
                // Changes based on number of units player or opponent has
                int[] rolledDice = new int[userUnits - 1];// Random Select on both dice

                for(int i = 0; i < rolledDice.length; i ++)//iterates through List no matter how much dices need to be rolled 
                {
                    rolledDice[i] = (int)(Math.random() * 6 + 1);
                }

                // Sort Dice Results
                Arrays.sort(rolledDice);

                System.out.println("The Player attacks with min(3," + userUnits + ") units");

                System.out.print("Player rolls " + (userUnits - 1) + " six-side dice and orders them = ");
                System.out.println(Arrays.toString(rolledDice));

                System.out.println("The Opponent defends with min(2," + opponentUnits + ") units");
                // Assign new values

                int[] OpponentrolledDice = new int[opponentUnits];// Random Select on both dice

                for(int i = 0; i < OpponentrolledDice.length; i ++)//iterates through List no matter how much dices need to be rolled 
                {
                    OpponentrolledDice[i] = (int)(Math.random() * 6 + 1);
                }
                // Sort Dice Results
                Arrays.sort(rolledDice);

                System.out.print("Player rolls " + (opponentUnits - 1) + " six-side dice and orders them = ");
                System.out.println(Arrays.toString(OpponentrolledDice));

                // Compare Both Dice Values to each other( determines winner or Loser)

                // Implenent 2 for loops comparing each value in both Arrays
                // Compare values and determien which has bigger value( compariosi operators)
                // smaller valuesloses= Increase or Decrease Unit value on eahc comparsion
                //
            }
            else if(userNumber == 2)
            {
                // Opponent is attacking Player
                System.out.println("Opponent attacks with " + (opponentUnits - 1) + " units; min(" + opponentUnits + ", " +(opponentUnits - 1) + ")" );
                System.out.println("Player defends with " + userUnits + " unit");

            
                // Changes based on number of units player or opponent has
                int[] rolledDice = new int[opponentUnits - 1];// Random Select on both dice

                for(int i = 0; i < rolledDice.length; i ++)//iterates through List no matter how much dices need to be rolled 
                {
                    rolledDice[i] = (int)(Math.random() * 6 + 1);
                }

                // Sort Dice Results
                Arrays.sort(rolledDice);

                System.out.println("The Opponent attacks with min(3," + opponentUnits + ") units");

                System.out.print("Player rolls " + (userUnits - 1) + " six-side dice and orders them = ");
                System.out.println(Arrays.toString(rolledDice));

                System.out.println("The Player defends with min(2," + userUnits + ") units");
                // Assign new values

                int[] OpponentrolledDice = new int[userUnits];// Random Select on both dice

                for(int i = 0; i < OpponentrolledDice.length; i ++)//iterates through List no matter how much dices need to be rolled 
                {
                    OpponentrolledDice[i] = (int)(Math.random() * 6 + 1);
                }
                // Sort Dice Results
                Arrays.sort(rolledDice);

                System.out.print("Opponent rolls " + (userUnits - 1) + " six-side dice and orders them = ");
                System.out.println(Arrays.toString(OpponentrolledDice));

                // Compare Both Dice Values to each other( determiens winner or Loser)
            }
        }

    public int train(int playerbase)
        {
            // Increase Player Units by 1 Per # of bases
            // Method Collects that players Units- then increases Units( with Report)
            // Finished Output: End result with user bases correctly updated
            return playerbase + playerbase;

        
        }

    public void expand(int userNumber)// User number detremines if this is beng called bythe opponent or user- refer to top of program
        {
            // Claims an unown Base, if available
            // if no bases avaiable = Turn is given Up( Tactical Strategy)- Allows for harder expand game Mechanic
            // Number 1 = User 
            // Number 2 = Opponent

            // Checks Each base and if equels 0( meanign unassignend) it changes to user number(1 = user; 2 = opponent)
            // UPDATE: *CAN only expand to unowned bases*
            if(baseThree != 0 && baseFour != 0)// 0 value on bases indicate empty
            {
                //checks if bases are not available-
                System.out.println("Unfortunalty there are no bases avaiable, turn skipped.");

            }
            else if(baseThree == 0 )
            {
                //Base 3 is unowned- assign player
                baseThree = userNumber;
                if(userNumber == 1)
                {
                    
                    System.out.println("Expand; +1 base; (Base 3: Player ) ");
                    userBases ++;
                }
                else
                {
                    System.out.println("Expand; +1 base; (Base 3: Opponent ) ");
                    opponentBases ++;
                    
                }
                
            }
            else if(baseFour == 0)
            {
                // Base 4 is unowned- assign player 
                baseFour = userNumber;

                if(userNumber == 1)
                {
                    System.out.println("Expand; +1 base; (Base 4: Player ");
                    userBases ++;
                }
                else
                {
                    System.out.println("Expand; +1 base; (Base 4: Opponnent ");
                }
            }
            else// Repititive- Change( least priority)
            {
                System.out.println("Unfortunalty there are no bases avaiable, turn skipped.");
                opponentBases ++;
            }
        }
    public void determineWinner()
    {
        // Using Class Variables- Assigns Game Winner
        // Game Endings:
        // IF player owns 0 bases and 0 units- game ends
        // otherwise- winner is determined by player with more units
                            // TieBraker = player with more bases
                            // Other = Tie

                            
        // Result- winner of Match
        String winner = " ";

        if(userUnits > opponentUnits)
        {
            winner = "Player";
        }
        else if(opponentUnits > userUnits)
        {
            winner = "Opponent";
        }
        else if(opponentUnits == userUnits)
        {
            if(userBases > opponentBases)
            {
                winner = "Player";
            }
            else if(userBases == opponentBases)
            {
                winner = "Tie";
            }
            else
            {
                winner = "Opponent";
            }
        }
        System.out.println();
        System.out.println("GAME END; " + winner +  " WINS!");// MODIFY WINNER CONTROLLER
        System.out.println();
        System.out.println("Player Units: " + userUnits);
        System.out.println("Opponent Units: " + opponentUnits);
        

        System.out.println("FINAL STATE: ");
        System.out.println();

        // Translates the numeric Digit given to eachplayer into Player name- MAKE MORE EFFICANT(Least priority)
        if(baseOne == 1)
        {
            System.out.println("Base 1: Player ");
        }
        else
        {
            System.out.println("Base 1: Opponent");
        }

        if(baseOne == 2)
        {
            System.out.println("Base 2: Player ");
        }
        else
        {
            System.out.println("Base 2: Opponent");
        }

        if(baseOne == 3)
        {
            System.out.println("Base 3: Player ");
        }
        else
        {
            System.out.println("Base 3: Opponent");
        }

        if(baseOne == 4)
        {
            System.out.println("Base 4: Player ");
        }
        else
        {
            System.out.println("Base 4: Opponent");
        } 
    }
}
