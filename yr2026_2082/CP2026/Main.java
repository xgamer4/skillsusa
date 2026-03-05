/*
 * Runs the program
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(5);
        UserController user = new UserController(game);
        OpponentController opponent = new OpponentController(game);
        game.setPlayers(user, opponent);

        printMenu(game, user, opponent);

        //gameloop
        String playerInput = "";
        String opponentDialogue = "";
        do{
            //userPhase
            System.out.println("\n================= Round: " + game.getCurrentRound() + " =================");
            playerInput = user.getAction();
            System.out.println("Player: " + playerInput);

            if(playerInput == "Attack"){
                game.simAttack(user, opponent);
            }

            if(playerInput == "quit"){
                break;
            }

            if(game.getIsGameOver()) break;

            //opponentPhase
            opponentDialogue = opponent.getAction();
            System.out.println("Opponent: " + opponentDialogue);

            if(opponentDialogue == "Attack"){
                game.simAttack(opponent, user);
            }

            game.incrementRound();

            if(playerInput != "quit" && !game.getIsGameOver()){
                System.out.println("\nCurrent Standings");
                System.out.println(game.getAllBasesString());
                System.out.println("Player: " + user.getUnits() + " units");
                System.out.println("Opponent: " + opponent.getUnits() + " units");
            }
        }
        while(playerInput != "quit" && !game.getIsGameOver());

        //exit message
        if(playerInput == "quit"){
            System.out.println("Player quit game early");
            return;
        }
        
        if(game.getIsGameOver()){
            System.out.println(game.getResults());
        }
    }

    /*
     * Prints the starting menu for user input and shows starting stats
     */
    private static void printMenu(Game game, Player user, Player opponent){
        System.out.println("Actions:");
        System.out.println("- a  -  attack");
        System.out.println("- t  -  train");
        System.out.println("- e  -  expand");
        System.out.println("- q  -  quit");
        System.out.println("\nThe battle begins as follows:");
        System.out.println("\n" + game.getAllBasesString());
        System.out.println("\nPlayer: " + user.getUnits() + " units");
        System.out.println("Opponent: " + user.getUnits() + " units");

        System.out.println("\nThe turns progress as follows:");
    }
}
