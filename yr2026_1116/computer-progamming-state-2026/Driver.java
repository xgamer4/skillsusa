import java.util.Scanner;

/**
 * This is a standardized Driver for the OceanDestruction 2 game by
 * @author Ghammond
 */
public class Driver{
    public static void main(String[] args) {
        Game game = new Game();
        //MainGame instantiates the CLI
        CLI cli = game.cli;
        
        //Just made sure that Scanner doesn't have any issues here and that it closes.
        try (Scanner scan = new Scanner(System.in)){
            while (game.isRunning()) {
                
                if(cli.round > 5){
                    cli.gameEnds();
                    break;
                }
                cli.print("\nRound " + cli.round + ": "); //which round
                System.out.print("Enter a command 'Expand', 'Train', or 'Attack': "); //Which round
                //Scans the next line and gives it to the printer
                cli.command(scan.nextLine());
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}