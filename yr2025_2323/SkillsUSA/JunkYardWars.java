import java.util.Hashtable;
import java.util.Random;
import java.util.*;
import java.util.Scanner;

public class JunkYardWars {
    private int runway;
    private int terrain;
    private int weather;

    private Hashtable<String, Integer> competitionField = new Hashtable<String, Integer>();
    private Hashtable<String, Integer> partsList = new Hashtable<String, Integer>();
    public Hashtable<String, Integer> tempPartsList = new Hashtable<String, Integer>();

    //Interface that will be displayed to the user with the 3 options to choose from
    public void userInterface(){
        System.out.println("What would you like to do? (Type the corresponding number for that option)");
        System.out.println("1. Search");
        System.out.println("2. Compete");
        System.out.println("3. Quit");
    }  
    
    //Generates the competion field for the event
    public void generateCompetitionField(){
        Random generator = new Random();
        runway = generator.nextInt(7);
        terrain = generator.nextInt(7) + 4;
        weather = generator.nextInt(11) + 2;

        competitionField.put("Terrain", terrain);
        competitionField.put("Runway", runway);
        competitionField.put("Weather", weather);
    }

    //displays the conditions of the field to the user
    public void displayField(){
        generateCompetitionField();
        System.out.println("The conditions for this field are:");
        System.out.println("Runway: " + runway);
        System.out.println("Terrain: " + terrain);
        System.out.println("Weather: " + weather);
    }

    //determines what items are already in the parts list the calls the createTempPartList function to 
    //create the list based on what parts the user already has 
    public void generatePartsList(Scanner scanner){

        Random generator = new Random();
        List<String> partList = new ArrayList<String>();
        int[] rating = new int[3];
        int choice;

        if(partsList.containsKey("Wings") && partsList.containsKey("Engines") && partsList.containsKey("Landing Gear")){
            //Competitior already has all parts
            System.out.println("You already have all the parts you need! Now try to Compete!");
            return;
        }
        else if(partsList.containsKey("Wings") && partsList.containsKey("Landing Gear")){
            //Generate Only Engines
            for(int i = 0; i < 3; i++){
                partList.add("Engines");
                rating[i] = generator.nextInt(11);
            }
        }
        else if(partsList.containsKey("Engines") && partsList.containsKey("Landing Gear")){
            //Generate Only Wings
            for(int i = 0; i < 3; i++){
                partList.add("Wings");
                rating[i] = generator.nextInt(11);
            }
        }
        else if(partsList.containsKey("Wings") && partsList.containsKey("Engines")){
            //Generate Only Landing Gear
            for(int i = 0; i < 3; i++){
                partList.add("Landing Gear");
                rating[i] = generator.nextInt(11);
            }
        }
        else if(partsList.containsKey("Landing Gear")){
            //Generate Wings and Engines
            partList.add("Wings");
            partList.add("Engines");
            partList.add(generateRandomPart(true, true, false));

            for(int i = 0; i < 3; i++){
                rating[i] = generator.nextInt(11);
            }
        }
        else if(partsList.containsKey("Engines")){
            //Generate Wings and Landing Gear
            partList.add("Wings");
            partList.add("Engines");
            for(int i = 0; i < 3; i++){
                rating[i] = generator.nextInt(11);
            }
            partList.add(generateRandomPart(true, true, false));
        }
        else if(partsList.containsKey("Wings")){
            //Generate Engines and Landing Gear
            partList.add("Engines");
            partList.add("Landing Gear");
            for(int i = 0; i < 3; i++){
                rating[i] = generator.nextInt(11);
            }
            partList.add(generateRandomPart(false, true, true));
        }
        else{
            //Generate All
            partList.add("Engines");
            partList.add("Landing Gear");
            partList.add("Wings");
            for(int i = 0; i < 3; i++){
                rating[i] = generator.nextInt(11);
            }
        }

        for(int i = 0; i < 3; i++){
            System.out.println((i+1) + ". " + partList.get(i) + ": " + rating[i]);
        }

        System.out.println("Enter the number of the part you would like to collect");
        choice = scanner.nextInt();
        
        addPart(partList.get(choice - 1), rating[choice - 1]);
    }

    public void addPart(String key, int value){
        partsList.put(key, value);
    }

    //returns a random string for the name of a part to be used for a tempPart
    public String generateRandomPart(Boolean wings, Boolean engines, Boolean landingGear){
        Random generator = new Random();

        List<String> tempList = new ArrayList<String>();

        if(wings == true) {
            tempList.add("Wings");
        }
        if(engines == true){
            tempList.add("Engines");
        }
        if(landingGear == true){
            tempList.add("Landing Gear");
        }
        int randIndex = generator.nextInt(tempList.size());

        return tempList.get(randIndex);
    }

    public void printPartsList(){
        System.out.println(partsList);
    }

    //begins the competion phase of the event
    public void compete(){
        phase1();
    }

    //Landing gear vs runway
    public void phase1(){
        int performance;

        if(partsList.containsKey("Landing Gear")){
           performance = partsList.get("Landing Gear") - competitionField.get("Runway");
        }
        else{
            performance = -1 - competitionField.get("Runway");
        }
         
        if(performance < 0){
            phase2(performance * 2);
        }
        else{
            phase2(performance);
        }

        System.out.println(performance);
    }

    //Wings vs terrain
    public void phase2(int performance){

        if(partsList.containsKey("Wings")){
            performance = partsList.get("Wings") - competitionField.get("Terrain") + performance;
         }
         else{
             performance = -1 - competitionField.get("Terrain") + performance;
         }
          
         if(performance < 0){
             phase3(performance * 2);
         }
         else{
             phase3(performance);
         }
         System.out.println(performance);
    }

    //Engine vs weather
    public void phase3(int performance){
        if(partsList.containsKey("Engine")){
            performance = partsList.get("Engine") - competitionField.get("Weather") + performance;
         }
         else{
             performance = -1 - competitionField.get("Weather") + performance;
         }
          
         if(performance < 0){
             System.out.println("Your plane crashed... Better luck next time!");
         }
         else{
             System.out.println("You completed the course! Congratulations you win!");
         }
         System.out.println(performance);
    }
}
