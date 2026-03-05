/**
 * A command line interface which takes in input and will do the printing of stuff
 * @Author Ghammond
 */
public class CLI {

    //Its parent
    private Game game;
    // Which round we are on
    // This is here as it is easy to keep track based on when we get a response from the user
    public int round = 1;

    /**
     * Instantiates the game prints a simple intro and shows the basic battlefield
     * @param game passes in the game for misc details
     */
    public CLI(Game inputGame){
        game = inputGame;
        System.out.println("Welcome to OceanDestruction 2 \nThe battle begins as follows:");
        assess();
    }

    /**
     * Shows a basic battlefield with all of the bases and how many units you have
     */
    public void assess(){
        Base.occupier[] bases = game.getAllBaseOccupations();
        for (int i = 0; i < bases.length; i++) {
            System.out.println("Base " + (i+1) + ": " + bases[i]);
        }

        System.out.println("\nPlayer: " + game.Player(true).getUnits() + " units");
        System.out.println("Opponent: " + game.Player(false).getUnits() + " units");
    }

    /**
     * Takes in a command (likely from the user) then it acts upon that by telling the game. 
     * Pretty much checks to make sure everything is valid.
     * @param input // Input of the user
     */
    public void command(String input) {
        input = input.toLowerCase().trim();// cleaning
        switch (input) {
            case "train":
            case "tra":
            case "t":
                game.action(true, Game.Actions.TRAIN);
                round ++;
                break;

            case "expand":
            case "exp":
            case "e":
                game.action(true, Game.Actions.EXPAND);
                round ++;
                break;

            case "attack":
            case "atk":
            case "a":
                game.action(true, Game.Actions.ATTACK);
                round ++;
                break;

            case "quit":
            case "q":
                game.quit();
                System.out.println("Thanks for Playing!");
                break;

            case "!":
                System.out.println("Snake!!!");
                break;

                //I see you looking at my code :3
            case "cheats":
            case "cheat":
            case "dev":
            case "developer":
            case "escape":
            case "/win":
            case "win":
            case "esc":
            case "break":
            case "free_units":
            case "freeunits":
            case "freebase":
            case "free_base":
            case "debug":
            case "konami":
                System.out.println("I am disappointed...");
                game.quit();
                break;
            default:
                System.out.println("Please input one of the following inputs: train, expand, attack, or quit");
                break;
        }
    }

    /**
     * shows that the game is over and prints that out
     */
    public void gameEnds(){
        System.out.print("GAME END; ");
        Player user = game.Player(true);
        Player ai = game.Player(false);
        if(user.getUnits() > ai.getUnits()){
            System.out.print(Game.BOLD + Game.GREEN + " PLAYER " + Game.RESET);
        }else if(user.getUnits() < ai.getUnits()){
            System.out.print(Game.BRED + Game.UNDERLINE + " OPPONENT " + Game.RESET);
        }else{
            if(user.getBases() > ai.getBases()){
                System.out.print(Game.BOLD + Game.GREEN + " PLAYER " + Game.RESET);
            }else if(user.getBases() < ai.getBases()){
                System.out.print(Game.BRED + Game.UNDERLINE + " OPPONENT " + Game.RESET);
            }else{
                System.out.print(Game.DIM + " NO ONE " + Game.RESET);
            }
        }
        System.out.println("WINS\n");
        assess();
        game.quit();
    }

    /**
     * Delegates printing to the CLI
     * @param string
     */
    public void print(String string){
        System.out.println(string);
    }
}
