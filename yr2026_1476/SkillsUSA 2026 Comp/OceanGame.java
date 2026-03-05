import java.util.*;

public class OceanGame {
    
    public int roundNum = 0;

    public ArrayList<Base> allBases;

    public User user;
    public Opponent opponent;


    // Initialize Game
    public OceanGame(String userName, String opponentName)
    {
        allBases = new ArrayList<Base>();
        allBases.add(new Base(Ownership.User, "St. Aquamarine"));
        allBases.add(new Base(Ownership.None, "Gravelfall"));
        allBases.add(new Base(Ownership.None, "Salmon Guard"));
        allBases.add(new Base(Ownership.Opponent, "Fort Ink"));

        user = new User(userName);
        opponent = new Opponent(opponentName);

        user.setTroops(2);
        opponent.setTroops(2);
    }

    // ---------
    // Main Loop
    // ---------
    public void round(Ownership turn, Scanner s)
    {
        user.updateBases(allBases);
        opponent.updateBases(allBases);

        if(user.getTroops() <= 0 && user.getBaseAmount() <= 0)
        {
            System.out.println(opponent.name.toUpperCase() + " HAS WON!!!");
            endGame(s);
        }
        else if(opponent.getTroops() <= 0 && opponent.getBaseAmount() <= 0)
        {
            System.out.println(user.name.toUpperCase() + " HAS WON!!!");
            endGame(s);
        }

        System.out.println("ROUND NUMBER " + (roundNum+1));

        if(turn == Ownership.User)
        {
            // ------------
            // PLAYERS TURN
            // ------------
            System.out.println("--- WELCOME " + user.name.toUpperCase() + " ---"); // ToUpperCase: W3 Schools (https://www.w3schools.com/java/ref_string_touppercase.asp)
            System.out.println("Your avilable actions are:\n  >info\n  >train\n  >attack\n  >expand\n  >exit");
            user.isDone = false;
            while(!user.isDone)
            {
                System.out.println("Please input an action.");
                String line = s.nextLine().toLowerCase();
                switch(line)
                {
                    case "info":
                        userInfo(s);
                        break;
                    case "train":
                        userTrain();
                        break;
                    case "attack":
                        userAttack();
                        break;
                    case "expand":
                        userExpand(s);
                        break;
                    case "exit":
                        // Quit
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            }
            round(Ownership.Opponent, s);

        }
        else {
            System.out.println("OPPONENT'S TURN:");
            // --------------
            // OPPONENTS TURN
            // --------------
            double trainWant = 0;
            double attackWant = 0;
            double expandWant = 0;

            trainWant += roundNum == 5 ? -1 : 0;
            trainWant += (user.getTroops() - opponent.getTroops()) / 4;

            attackWant += roundNum >= 3 ? 2 : roundNum == 0 ? -1 : 0;
            attackWant += opponent.getTroops() - user.getTroops();

            expandWant += roundNum == 1 ? 1 : 0;
            expandWant += 1 / opponent.getBaseAmount();

            if(trainWant > attackWant && trainWant > expandWant)
            {
                opponentTrain();
            }
            else if(expandWant > trainWant && expandWant > attackWant)
            {
                opponentExpand();
            }
            else if(attackWant > trainWant && attackWant > expandWant)
            {
                opponentAttack();                 
            }
            else {
                Random rand = new Random();
                switch(rand.nextInt(3))
                {
                    case 0:
                        opponentTrain();
                        break;
                    case 1:
                        opponentExpand();
                        break;
                    case 2:
                        opponentAttack();
                        break;
                }
            }
            nextRound(s);
            round(Ownership.User, s);
        }
    }

    public void userInfo(Scanner s)
    {
        String line;
        // Information about x
        System.out.println("Info about what? Base (b) or User (u) or Opponent (o)");
        line = s.nextLine().toLowerCase();
        switch(line)
        {
            case "b":
            System.out.println("Which base? St. Aquamarine (st) or Gravelfall (gr) or Salmon Guard (sg) or Fort Ink (fi).");
            switch(s.nextLine())
            {
                case "st":
                    System.out.println(allBases.get(0).toString());
                    break;
                case "gr":
                    System.out.println(allBases.get(1).toString());
                    break;
                case "sg":
                    System.out.println(allBases.get(2).toString());
                    break;
                case "fi":
                    System.out.println(allBases.get(3).toString());
            }
                break;
            case "u":
                System.out.println(user.toString());
                break;
            case "o":
                System.out.println(opponent.toString());
                break;
            default:
                System.out.println("Unknown command.");
                break;
        }
    }
    public void userTrain()
    {
        // Train new soldiers
        user.trainTroops(user.getBaseAmount());
        user.isDone = true;
    }
    public void userAttack()
    {
        boolean dontExit = true;
        // Attack the opponent
        while(user.getTroops() >= 1 && opponent.getTroops() > 0 && dontExit)
        {
            // User Attack
            int[] userrolls = user.attackDice();
            String rollU = "[ ";
            for (int i : userrolls) {
                rollU += i + " ";
            }
            rollU += "]";
            System.out.println("Users Attack Rolls: " + rollU);
            if(rollU.equals("[ 0 ]"))
            {
                dontExit = false;
                break;
            }
            // Opponent Defend
            int[] opponentrolls = opponent.defendDice();
            String rollO = "[ ";
            for (int i : opponentrolls) {
                rollO += i + " ";
            }
            rollO += "]";
            System.out.println("Oppnents Defend Rolls: " + rollO);
            int userlost = 0; int opponentlost = 0;
            int rolltime = userrolls.length < opponentrolls.length ? userrolls.length : opponentrolls.length;
            for(int i = 0; i < rolltime; i++)
            {
                if(userrolls[i] > opponentrolls[i])
                {
                    opponent.killTroops(1);
                    opponentlost++;
                }
                else if(userrolls[i] <= opponentrolls[i])
                {
                   user.killTroops(1);
                   userlost++;
                }
            }
            System.out.println("RESULTS:");
            System.out.println(user.name + " lost " + userlost + " units. Now has " + user.getTroops() + ".");
            System.out.println(opponent.name + " lost " + opponentlost + " units. Now has " + opponent.getTroops() + ".");
        }
        if(user.getTroops() == 1)
        {
            user.loseBase();
        }
        else if(opponent.getTroops() == 0)
        {
            opponent.loseBase();
        }

        user.isDone = true;
    }
    public void userExpand(Scanner s)
    {
        // Expand into new base
        System.out.println("Expand into which base? St. Aquamarine (st) or Gravelfall (gr) or Salmon Guard (sg) or Fort Ink (fi).");
        switch(s.nextLine())
        {
            case "st":
                if(allBases.get(0).whoOwns() == Ownership.None)
                {
                    allBases.get(0).setOwns(Ownership.User);
                    System.out.println("You claimed St. Aquamarine!");
                }
                else {
                    System.out.println("That base is already owned!");
                }
                break;
            case "gr":
                if(allBases.get(1).whoOwns() == Ownership.None)
                {
                    allBases.get(1).setOwns(Ownership.User);
                    System.out.println("You claimed Gravelfall!");
                }
                else {
                    System.out.println("That base is already owned!");
                }
                break;
            case "sg":
                if(allBases.get(2).whoOwns() == Ownership.None)
                {
                    allBases.get(2).setOwns(Ownership.User);
                    System.out.println("You claimed Salmon Guard!");
                }
                else {
                    System.out.println("That base is already owned!");
                }
                break;
            case "fi":
                if(allBases.get(3).whoOwns() == Ownership.None)
                {
                    allBases.get(3).setOwns(Ownership.User);
                    System.out.println("You claimed Fort Ink!");
                }
                else {
                    System.out.println("That base is already owned!");
                }
                break;
            default:
                System.out.println("Unknown base.");
        }
        user.updateBases(allBases);
        user.isDone = true;
    }


    // Opponent trains units
    public void opponentTrain()
    {
        opponent.trainTroops(opponent.getBaseAmount());
        System.out.println(opponent.name + " is training troops! \nThey now have " + opponent.getTroops());
    }
    // Opponents attempts to expand
    public void opponentExpand()
    {
        if(allBases.get(0).whoOwns() == Ownership.None)
                {
                    allBases.get(0).setOwns(Ownership.Opponent);
                    System.out.println("Enemy claimed St. Aquamarine!");
                }
                else if(allBases.get(1).whoOwns() == Ownership.None)
                {
                    allBases.get(1).setOwns(Ownership.Opponent);
                    System.out.println("Enemy claimed Gravelfall!");
                }
                else if(allBases.get(2).whoOwns() == Ownership.None)
                {
                    allBases.get(2).setOwns(Ownership.Opponent);
                    System.out.println("Enemy claimed Salmon Guard!");
                }
                else if(allBases.get(3).whoOwns() == Ownership.None)
                {
                    allBases.get(3).setOwns(Ownership.Opponent);
                    System.out.println("Enemy claimed Fort Ink!");
                }
                else {
                    System.out.println("Enemy tried to expand but couldn't!");
                }
                opponent.updateBases(allBases);
    }
    // Opponent attacks user
    public void opponentAttack()
    {
        boolean dontExit = true;
        while(opponent.getTroops() >= 1 && user.getTroops() > 0 && dontExit) {
            // User Defend
            int[] userrolls = user.defendDice();
            String rollU = "[ ";
            for (int i : userrolls) {
                rollU += i + " ";
            }
            rollU += "]";
            System.out.println("Users Defend Rolls: " + rollU);
            // Opponent Attack
            int[] opponentrolls = opponent.attackDice();
            String rollO = "[ ";
            for (int i : opponentrolls) {
                rollO += i + " ";
            }
            rollO += "]";
            if(rollO.equals("[ 0 ]"))
            {
                dontExit = false;
                break;
            }
            System.out.println("Oppnents Attack Rolls: " + rollO);
            int userlost = 0; int opponentlost = 0;
            int rolltime = userrolls.length < opponentrolls.length ? userrolls.length : opponentrolls.length;
            for(int i = 0; i < rolltime; i++)
            {
                if(userrolls[i] > opponentrolls[i])
                {
                    opponent.killTroops(1);
                    opponentlost++;
                }
                else if(userrolls[i] <= opponentrolls[i])
                {
                    user.killTroops(1);
                    userlost++;
                }
            }
            System.out.println("RESULTS:");
            System.out.println(user.name + " lost " + userlost + " units. Now has " + user.getTroops() + ".");
            System.out.println(opponent.name + " lost " + opponentlost + " units. Now has " + opponent.getTroops() + ".");
        }

        if(user.getTroops() == 0)
        {
            user.loseBase();
        }
        else if(opponent.getTroops() == 1)
        {
            opponent.loseBase();
        }
    }


    public void nextRound(Scanner s)
    {
        roundNum++;
        if(roundNum > 4)
        {
            if(user.getTroops() > opponent.getTroops())
            {
                System.out.println(user.name.toUpperCase() + " HAS WON!!!");
            }
            else if(opponent.getTroops() > user.getTroops())
            {
                System.out.println(opponent.name.toUpperCase() + " HAS WON!!!");
            }
            else if(user.getBaseAmount() > opponent.getBaseAmount())
            {
                System.out.println(user.name.toUpperCase() + " HAS WON!!!");
            }
            else if(opponent.getBaseAmount() > user.getBaseAmount())
            {
                System.out.println(opponent.name.toUpperCase() + " HAS WON!!!");
            }
            else {
                System.out.println("IT WAS A TIE!?");
            }
            endGame(s);
        }
    }

    public void endGame(Scanner s)
    {
        System.out.println("End game!");
        System.exit(0);
    }

}
