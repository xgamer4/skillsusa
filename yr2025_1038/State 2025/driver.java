import java.util.Scanner;

/**
 * Predicting Firestorm's UnderAssualt
 * 
 * @author Nicole Prosser
 */
public class driver{
    public static void main(String[] args) {
        //All the base variables that will be needed
        team fuel = new team("Fuel");
        team outlaws = new team("Outlaws");
        team shock = new team("Shock");
        team dynasty = new team("Dynasty");
        Scanner userIn = new Scanner(System.in);


        //Printing out a quick welcome, and the player state, since it is hard to do anything without being able to see the stats
        System.out.println("Welcome to the UnderAssault Competition");
        System.out.println("*****************************");
        System.out.println("*       Team Stats          *");
        System.out.println("*****************************");

        fuel.printTeamStats();
        System.out.println();
        outlaws.printTeamStats();
        System.out.println();
        shock.printTeamStats();
        System.out.println();
        dynasty.printTeamStats();
        System.out.println();

        System.out.println("*****************************");
        System.out.println("*       Program Menu        *");
        System.out.println("*****************************");
        System.out.println("(T)eam Stats");
        System.out.println("(R)un Full Prediction");
        System.out.println("(S)pecific Match");
        System.out.println("(P)ick full match");
        System.out.println("(Q)uit");
        System.out.println();
        System.out.print("Please enter a command (press 'm' for Menu): ");
        String userCommand = userIn.nextLine();

        while (true) {//allow the user to continuously run sims
            switch (userCommand.toUpperCase()) {
                case "T"://if the user inputs t, it will reprint all the team's stats
                    fuel.printTeamStats();
                    System.out.println();
                    outlaws.printTeamStats();
                    System.out.println();
                    shock.printTeamStats();
                    System.out.println();
                    dynasty.printTeamStats();
                    System.out.println();
                    System.out.print("Please enter a command (press 'm' for Menu): ");
                    userCommand = userIn.nextLine();    
                    break;
                case "R"://if the user selects r, it will ask for a prediction, then run a full simulation and give a message based on if the user was correct
                    String winnerCheck = "test";
                    System.out.println("Which team is your prediction?");
                    String prediction = userIn.nextLine();

                    runAllMatches(fuel, outlaws);
                    runAllMatches(shock, dynasty);

                    if(fuel.getOverall() == 1){
                        if(shock.getOverall() == 1){
                            winnerCheck = runAllMatches(fuel, shock);
                        } else{
                            winnerCheck = runAllMatches(fuel, dynasty);
                        }
                    } else if(outlaws.getOverall() == 1){
                        if(shock.getOverall() == 1){
                            winnerCheck = runAllMatches(outlaws, shock);
                        } else{
                            winnerCheck = runAllMatches(outlaws, dynasty);
                        }
                    }

                    prediction = prediction.toLowerCase();

                    System.out.print("This predicted winner of the UnderAssualt Competition is: ");
                    if(fuel.getOverall() == 2){
                        System.out.println(fuel.getName());
                        if(prediction.equals("fuel")){
                            System.out.println("You predicted correctly!");
                        } else{
                            System.out.println("Sorry, your prediction was incorrect");
                        }
                    }else if(outlaws.getOverall() == 2){
                        System.out.println(outlaws.getName());
                        if(prediction.equals("outlaws")){
                            System.out.println("You predicted correctly!");
                        } else{
                            System.out.println("Sorry, your prediction was incorrect");
                        }
                    }else if(shock.getOverall() == 2){
                        System.out.println(shock.getName());
                        if(prediction.equals("shock")){
                            System.out.println("You predicted correctly!");
                        } else{
                            System.out.println("Sorry, your prediction was incorrect");
                        }
                    }else{
                        System.out.println(dynasty.getName());
                        if(prediction.equals("dynasty")){
                            System.out.println("You predicted correctly!");
                        } else{
                            System.out.println("Sorry, your prediction was incorrect");
                        }
                    }

                    fuel.resetOverallScores();
                    outlaws.resetOverallScores();
                    shock.resetOverallScores();
                    dynasty.resetSubScores();
                    

                    System.out.print("Please enter a command (press 'm' for Menu): ");
                    userCommand = userIn.nextLine();
                    break;
                case "S":// if the user inputs s, it will all them to run a subcontest with two teams of their choosing and make a prediction on who will win

                    team team1, team2;
                    System.out.print("What is the first team for the match you want to simulate?: ");
                    String input1 = userIn.nextLine();
                    switch (input1) {
                        case "fuel":
                                team1 = fuel;
                            break;
                        case "outlaws":
                            team1 = outlaws;
                            break;
                        case "shock":
                            team1 = shock;
                            break;
                        case "dynasty":
                            team1 = dynasty;
                            break;
                        default:
                            System.out.print("That is not a team. Please select a team: ");
                            input1 = null;
                            team1 = fuel;
                            while(input1 == null){
                                input1 = userIn.nextLine();
                                switch (input1) {
                                    case "fuel":
                                            team1 = fuel;
                                        break;
                                    case "outlaws":
                                        team1 = outlaws;
                                        break;
                                    case "shock":
                                        team1 = shock;
                                        break;
                                    case "dynasty":
                                        team1 = dynasty;
                                    break;
                                default:
                                    System.out.print("That is not a team. Please select a team: ");
                                    input1 = null;
                                    break;
                            }
                            break;
                        }
                    }



                    System.out.print("What is the second team for the match you want to simulate?: ");
                    String input2 = userIn.nextLine();
                    if(input2.toLowerCase().equals("fuel") && !input1.toLowerCase().equals("fuel")){
                        team2 = fuel;
                    } else if(input2.toLowerCase().equals("outlaws") && !input1.toLowerCase().equals("outlaws")){
                        team2 = outlaws;
                    } else if(input2.toLowerCase().equals("shock") && !input1.toLowerCase().equals("shock")){
                        team2 = shock;
                    } else if(input2.toLowerCase().equals("dynasty") && !input1.toLowerCase().equals("dynasty")){
                        team2 = dynasty;
                    } else{
                        System.out.print("That is not a team or you have already selected that team. Please select a team: ");
                        input2 = null;
                        team2 = fuel;
                        while(input2 == null){
                            input2 = userIn.nextLine();
                            if(input2.toLowerCase().equals("fuel") && !input1.toLowerCase().equals("fuel")){
                                team2 = fuel;
                            } else if(input2.toLowerCase().equals("outlaws") && !input1.toLowerCase().equals("outlaws")){
                                team2 = outlaws;
                            } else if(input2.toLowerCase().equals("shock") && !input1.toLowerCase().equals("shock")){
                                team2 = shock;
                            } else if(input2.toLowerCase().equals("dynasty") && !input1.toLowerCase().equals("dynasty")){
                                team2 = dynasty;
                            } else{
                                System.out.print("That is not a team. Please select a team: ");
                                input2 = null;
                            }
                        }
                    }



                    System.out.println("*Which match do you want to run (the default is Dps vs. Tank*");
                    System.out.println("(D)ps vs. Tank");
                    System.out.println("(T)ank vs. Dps");
                    System.out.println("(S)upport vs. Suppot");
                    userCommand = userIn.nextLine();
                    System.out.println("Which team is your prediction?");
                    prediction = userIn.nextLine();
                    switch (userCommand.toUpperCase()) {
                        case "D":
                                winnerCheck = team1.runDpsMatch(team2);
                            break;
                        case "T":
                            winnerCheck = team1.runTankMatch(team2);
                            break;
                        case "S":
                            winnerCheck = team1.runSupportMatch(team2);
                            break;
                    
                        default:
                            winnerCheck = team1.runDpsMatch(team2);
                            break;
                    }

                    
                    
                    winnerCheck = winnerCheck.toLowerCase();
                    prediction = prediction.toLowerCase();

                    if(winnerCheck.equals(prediction)){
                        System.out.println("You Predicted Correctly!");
                    } else{
                        System.out.println("Sorry, you predicted incorrectly");
                    }






                    System.out.print("Please enter a command (press 'm' for Menu): ");
                    userCommand = userIn.nextLine();


                    break;

                case "P":// if the user inputs p, it will allow them to pick two teams to do a full match with, as well as allow them to predict which one will win
                    System.out.print("What is the first team for the match you want to simulate?: ");
                    input1 = userIn.nextLine();
                    System.out.println(input1.toLowerCase());
                    switch (input1) {
                        case "fuel":
                                team1 = fuel;
                            break;
                        case "outlaws":
                            team1 = outlaws;
                            break;
                        case "shock":
                            team1 = shock;
                            break;
                        case "dynasty":
                            team1 = dynasty;
                            break;
                        default:
                            System.out.print("That is not a team. Please select a team: ");
                            input1 = null;
                            team1 = fuel;
                            while(input1 == null){
                                input1 = userIn.nextLine();
                                switch (input1) {
                                    case "fuel":
                                            team1 = fuel;
                                        break;
                                    case "outlaws":
                                        team1 = outlaws;
                                        break;
                                    case "shock":
                                        team1 = shock;
                                        break;
                                    case "dynasty":
                                        team1 = dynasty;
                                    break;
                                default:
                                    System.out.print("That is not a team. Please select a team: ");
                                    input1 = null;
                                    break;
                            }
                            break;
                        }
                    }



                    System.out.print("What is the second team for the match you want to simulate?: ");
                    input2 = userIn.nextLine();
                    if(input2.toLowerCase().equals("fuel") && !input1.toLowerCase().equals("fuel")){
                        team2 = fuel;
                    } else if(input2.toLowerCase().equals("outlaws") && !input1.toLowerCase().equals("outlaws")){
                        team2 = outlaws;
                    } else if(input2.toLowerCase().equals("shock") && !input1.toLowerCase().equals("shock")){
                        team2 = shock;
                    } else if(input2.toLowerCase().equals("dynasty") && !input1.toLowerCase().equals("dynasty")){
                        team2 = dynasty;
                    } else{
                        System.out.print("That is not a team or you have already selected that team. Please select a team: ");
                        input2 = null;
                        team2 = fuel;
                        while(input2 == null){
                            input2 = userIn.nextLine();
                            if(input2.toLowerCase().equals("fuel") && !input1.toLowerCase().equals("fuel")){
                                team2 = fuel;
                            } else if(input2.toLowerCase().equals("outlaws") && !input1.toLowerCase().equals("outlaws")){
                                team2 = outlaws;
                            } else if(input2.toLowerCase().equals("shock") && !input1.toLowerCase().equals("shock")){
                                team2 = shock;
                            } else if(input2.toLowerCase().equals("dynasty") && !input1.toLowerCase().equals("dynasty")){
                                team2 = dynasty;
                            } else{
                                System.out.print("That is not a team. Please select a team: ");
                                input2 = null;
                            }
                        }
                    }

                    System.out.println("Which team is your prediction?");
                    prediction = userIn.nextLine();
                    winnerCheck = runAllMatches(team1, team2);
                    
                    
                    winnerCheck = winnerCheck.toLowerCase();
                    prediction = prediction.toLowerCase();

                    if(winnerCheck.equals(prediction)){
                        System.out.println("You Predicted Correctly!");
                    } else{
                        System.out.println("Sorry, you predicted incorrectly");
                    }






                    System.out.print("Please enter a command (press 'm' for Menu): ");
                    userCommand = userIn.nextLine();


                    break;

                case "Q"://If the user inputs q it will quit the program and end the code
                    System.out.println("Goodbye!");
                    userIn.close();
                    System.exit(0);
                    break;
                case "M":// if the user inputs m, it will reprint the menu options.
                    System.out.println("*****************************");
                    System.out.println("*       Program Menu        *");
                    System.out.println("*****************************");
                    System.out.println("(T)eam Stats");
                    System.out.println("(R)un Full Prediction");
                    System.out.println("(S)pecific Match");
                    System.out.println("(P)ick full match");
                    System.out.println("(Q)uit");
                    System.out.println();
                    System.out.print("Please enter a command (press 'm' for Menu): ");
                    userCommand = userIn.nextLine();
                    break;
            
                
                
                default:
                    System.out.println("Invalid selection!");
                    System.out.print("Please enter a command (press m for Menu)");
                    userCommand = userIn.nextLine();
                    break;
            }
        }

        
    }


    /**
     * This function runs the simulation of a match
     * @param team1 the first team to pull numbers for
     * @param team2 the second team to pull numbers for
     * @return returns the name of the team that won the match
     */
    public static String runAllMatches(team team1, team team2){
        String winner;
        team1.runDpsMatch(team2);
        team1.runTankMatch(team2);
        if(team1.getSubScore() < 2 && team2.getSubScore() <2){
            team1.runSupportMatch(team2);
        }


        System.out.print(team1.getName() + " won " + team1.getSubScore() + " sub-contests, " + team2.getName() + "won " + team2.getSubScore() + " sub-contests, so ");

        if(team1.getSubScore() == 2 ){
            System.out.println(team1.getName() + " has won this game");
            team1.addToOverall();
            winner = team1.getName();
        } else{
            System.out.println(team2.getName() + " has won this game");
            team2.addToOverall();
            winner = team2.getName();
        }

        team1.resetSubScores();
        team2.resetSubScores();

        return winner;
    }
}

