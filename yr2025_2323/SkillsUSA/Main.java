import java.util.*;

public class Main{

    public static void main(String[] args){
        System.out.println("Wlecome to Junkyard Wars Airplane Edition");
        System.out.println("In this competition you will be finding 3 parts from a Junkyard:\nEngine\nWings\nLanding Gear");
        System.out.println("Each will have a rating and the corresponding rating will have to beat the values of the Variables provided");
        System.out.println("Goodluck!");
        System.out.println("Press enter to begin");

        int userInput;
        String intialInput;
        Scanner scanner = new Scanner(System.in);
        intialInput = scanner.nextLine();

        JunkYardWars event = new JunkYardWars();

        event.displayField();

        do{
            event.userInterface();

            userInput = scanner.nextInt();

            //int i = 1;

            if(userInput == 1){
                event.generatePartsList(scanner);
            }

            if (userInput == 2) {
                event.compete();
                userInput = 3;
             }

             if (userInput == 4){
                event.printPartsList();
             }

        }
        while(userInput != 3);
        //System.out.println(userInput);


        scanner.close();
    }

}