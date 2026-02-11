import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;



public class UnderAssaultBracketSim{ 
    public static void main(String[] args) throws InterruptedException {
    Random rand = new Random();
HashMap<String, Integer> fuel1 = new HashMap<>();
fuel1.put("DPSProf", rand.nextInt(1,101));
fuel1.put("TankProf", rand.nextInt(1,101));
fuel1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> fuel2 = new HashMap<>();
fuel2.put("DPSProf", rand.nextInt(1,101));
fuel2.put("TankProf", rand.nextInt(1,101));
fuel2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> fuel3 = new HashMap<>();
fuel3.put("DPSProf", rand.nextInt(1,101));
fuel3.put("TankProf", rand.nextInt(1,101));
fuel3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> fuelRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
int player1role = rand.nextInt(1,4);

if(player1role == 1){
    fuelRoster.put("DPS", fuel1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        fuelRoster.put("Tank", fuel2);
        fuelRoster.put("Support", fuel3);
    }else{
        fuelRoster.put("Support", fuel2);
        fuelRoster.put("Tank", fuel3);
    }


} else if(player1role == 2){
    fuelRoster.put("Tank", fuel1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        fuelRoster.put("Support", fuel2);
        fuelRoster.put("DPS", fuel3);
    }else{
        fuelRoster.put("DPS", fuel2);
        fuelRoster.put("Support", fuel3);
    }


} else if(player1role == 3){
    fuelRoster.put("Support", fuel1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        fuelRoster.put("DPS", fuel2);
        fuelRoster.put("Tank", fuel3);
    }else{
        fuelRoster.put("Tank", fuel2);
        fuelRoster.put("DPS", fuel3);
    }
}


//Outlaw Players
HashMap<String, Integer> outlaw1 = new HashMap<>();
outlaw1.put("DPSProf", rand.nextInt(1,101));
outlaw1.put("TankProf", rand.nextInt(1,101));
outlaw1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> outlaw2 = new HashMap<>();
outlaw2.put("DPSProf", rand.nextInt(1,101));
outlaw2.put("TankProf", rand.nextInt(1,101));
outlaw2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> outlaw3 = new HashMap<>();
outlaw3.put("DPSProf", rand.nextInt(1,101));
outlaw3.put("TankProf", rand.nextInt(1,101));
outlaw3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> outlawRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
player1role = rand.nextInt(1,4);

if(player1role == 1){
    outlawRoster.put("DPS", outlaw1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        outlawRoster.put("Tank", outlaw2);
        outlawRoster.put("Support", outlaw3);
    }else{
        outlawRoster.put("Support", outlaw2);
        outlawRoster.put("Tank", outlaw3);
    }


} else if(player1role == 2){
    outlawRoster.put("Tank", outlaw1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        outlawRoster.put("Support", outlaw2);
        outlawRoster.put("DPS", outlaw3);
    }else{
        outlawRoster.put("DPS", outlaw2);
        outlawRoster.put("Support", outlaw3);
    }


} else if(player1role == 3){
    outlawRoster.put("Support", outlaw1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        outlawRoster.put("DPS", outlaw2);
        outlawRoster.put("Tank", outlaw3);
    }else{
        outlawRoster.put("Tank", outlaw2);
        outlawRoster.put("DPS", outlaw3);
    }
}



//Shock Players
HashMap<String, Integer> shock1 = new HashMap<>();
shock1.put("DPSProf", rand.nextInt(1,101));
shock1.put("TankProf", rand.nextInt(1,101));
shock1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> shock2 = new HashMap<>();
shock2.put("DPSProf", rand.nextInt(1,101));
shock2.put("TankProf", rand.nextInt(1,101));
shock2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> shock3 = new HashMap<>();
shock3.put("DPSProf", rand.nextInt(1,101));
shock3.put("TankProf", rand.nextInt(1,101));
shock3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> shockRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
player1role = rand.nextInt(1,4);

if(player1role == 1){
    shockRoster.put("DPS", shock1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        shockRoster.put("Tank", shock2);
        shockRoster.put("Support", shock3);
    }else{
        shockRoster.put("Support", shock2);
        shockRoster.put("Tank", shock3);
    }


} else if(player1role == 2){
    shockRoster.put("Tank", shock1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        shockRoster.put("Support", shock2);
        shockRoster.put("DPS", shock3);
    }else{
        shockRoster.put("DPS", shock2);
        shockRoster.put("Support", shock3);
    }


} else if(player1role == 3){
    shockRoster.put("Support", shock1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        shockRoster.put("DPS", shock2);
        shockRoster.put("Tank", shock3);
    }else{
        shockRoster.put("Tank", shock2);
        shockRoster.put("DPS", shock3);
    }
}


//Dynasty Players
HashMap<String, Integer> dynasty1 = new HashMap<>();
dynasty1.put("DPSProf", rand.nextInt(1,101));
dynasty1.put("TankProf", rand.nextInt(1,101));
dynasty1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> dynasty2 = new HashMap<>();
dynasty2.put("DPSProf", rand.nextInt(1,101));
dynasty2.put("TankProf", rand.nextInt(1,101));
dynasty2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> dynasty3 = new HashMap<>();
dynasty3.put("DPSProf", rand.nextInt(1,101));
dynasty3.put("TankProf", rand.nextInt(1,101));
dynasty3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> dynastyRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
player1role = rand.nextInt(1,4);

if(player1role == 1){
    dynastyRoster.put("DPS", dynasty1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        dynastyRoster.put("Tank", dynasty2);
        dynastyRoster.put("Support", dynasty3);
    }else{
        dynastyRoster.put("Support", dynasty2);
        dynastyRoster.put("Tank", dynasty3);
    }


} else if(player1role == 2){
    dynastyRoster.put("Tank", dynasty1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        dynastyRoster.put("Support", dynasty2);
        dynastyRoster.put("DPS", dynasty3);
    }else{
        dynastyRoster.put("DPS", dynasty2);
        dynastyRoster.put("Support", dynasty3);
    }


} else if(player1role == 3){
    dynastyRoster.put("Support", dynasty1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        dynastyRoster.put("DPS", dynasty2);
        dynastyRoster.put("Tank", dynasty3);
    }else{
        dynastyRoster.put("Tank", dynasty2);
        dynastyRoster.put("DPS", dynasty3);
    }
}

    
    boolean active = true;
    while(active){
        System.out.println("*****************************");
        System.out.println("*      Bracket Menu         *");
        System.out.println("*****************************");
        System.out.println("(P)redict the Bracket \n(S)imulate the Bracket\n(Q)uit: ");
        Scanner commandScanner = new Scanner(System.in);
        System.out.print("Please enter a command (press 'm' for Menu): ");
        String commandString = commandScanner.nextLine();
        switch(commandString.toLowerCase()){
            case("p"):
            
            Randomize();
            System.out.print("Round 1, (F)uel vs. (O)utlaws. Who do you think wins?: ");
            String round1prediction = commandScanner.nextLine();
            
            while(!round1prediction.toLowerCase().equals("f") && !round1prediction.toLowerCase().equals("o")){
                System.out.print("Please only enter f or o, for (F)uel vs. (O)utlaws. Who do you think wins?: ");
                round1prediction = commandScanner.nextLine();
            }

            System.out.print("Round 2, (S)hock vs. (D)ynasty. Who do you think wins?: ");
            String round2prediction = commandScanner.nextLine();

            while(!round2prediction.toLowerCase().equals("s") && !round2prediction.toLowerCase().equals("d")){
                System.out.println("Please only enter s or d, for (S)hock vs. (D)ynasty. Who do you think wins?: ");
                round2prediction = commandScanner.nextLine();
            }
            
            String round1winner = "";
            String round2winner ="";
            if(round1prediction.toLowerCase().equals("f")){
                round1winner = "(F)uel";
            }else if (round1prediction.toLowerCase().equals("o")){
                round1winner = "(O)utlaws";
            }
            if(round2prediction.toLowerCase().equals("s")){
                round2winner = "(S)hock";
            }else if (round2prediction.toLowerCase().equals("d")){
                round2winner = "(D)ynasty";
            }

            System.out.print("Finals! " + round1winner + " vs. " + round2winner + " Who do you think wins?: ");
            String finalPrediction = commandScanner.nextLine();
            
            while(finalPrediction.toLowerCase().equals("s") && finalPrediction.toLowerCase().equals("d") && finalPrediction.toLowerCase().equals("o") && finalPrediction.toLowerCase().equals("f") ){
                System.out.print("Please only enter the first letter for each team. " + round1winner + " vs. " + round2winner + " Who do you think wins?: ");
                finalPrediction = commandScanner.nextLine();
            }

            System.out.println("Let's see if you were right!");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("Round 1, Fuel vs. Outlaws");
            TimeUnit.SECONDS.sleep(1);
            Boolean team1winSide1 = null;
            int team1matchscore = 0;
            int team2matchscore = 0;
            //Match 1
            int team1microscore = 0;
            int team2microscore = 0;
            double chanceSum = 0;
            int chancesUsed = 0;
            
                //Tank vs. DPS
                    double chance = (fuelRoster.get("Tank").get("TankProf")) / (double)(fuelRoster.get("Tank").get("TankProf") + (outlawRoster.get("DPS").get("DPSProf")));
                    float check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's tank wins
                        team1microscore++;
                    }else{
                        //Team 2's DPS wings
                        team2microscore++;
                    }
    
                //DPS vs. Tank
                    chance = (fuelRoster.get("DPS").get("DPSProf")) / (double)(fuelRoster.get("DPS").get("DPSProf") + (outlawRoster.get("Tank").get("TankProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's DPS wins
                        team1microscore++;
                    }else{
                        //Team 2's Tank wings
                        team2microscore++;
                    }
                    
                
                //Support vs. Support
                if(team1microscore == 2){
                    team1matchscore++;
                }else if(team2microscore == 2){
                    team2matchscore++;
                }else{
                    chance = (fuelRoster.get("Support").get("SupportProf")) / (double)(fuelRoster.get("Support").get("SupportProf") + (outlawRoster.get("Support").get("SupportProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's support wins
                        team1microscore++;
                        team1matchscore++;
                    }else{
                        //Team 2's support wins
                        team2microscore++;
                        team2matchscore++;
                    }
                }
    
                
            //Match 2
            team1microscore = 0;
            team2microscore = 0;
                //Tank vs. DPS
                chance = (fuelRoster.get("Tank").get("TankProf")) / (double)(fuelRoster.get("Tank").get("TankProf") + (outlawRoster.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscore++;
                }else{
                    //Team 2's DPS wings
                    team2microscore++;
                }
    
            //DPS vs. Tank
                chance = (fuelRoster.get("DPS").get("DPSProf")) / (double)(fuelRoster.get("DPS").get("DPSProf") + (outlawRoster.get("Tank").get("TankProf")));
                check = (rand.nextFloat());
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscore++;
                }else{
                    //Team 2's Tank wings
                    team2microscore++;
                }
                
            
            //Support vs. Support
            if(team1microscore == 2){
                team1matchscore++;
            }else if(team2microscore == 2){
                team2matchscore++;
            }else{
                chance = (fuelRoster.get("Support").get("SupportProf")) / (double)(fuelRoster.get("Support").get("SupportProf") + (outlawRoster.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscore++;
                    team1matchscore++;
                }else{
                    //Team 2's support wins
                    team2microscore++;
                    team2matchscore++;
                }
            }
    
            //Match 3 (optional)
            int correctGuesses = 0;
            if(team1matchscore == 2){
                team1winSide1 = true;
            }else if (team2matchscore == 2){
                team1winSide1 = false;
            }else
            team1microscore = 0;
            team2microscore = 0;
                //Tank vs. DPS
                chance = (fuelRoster.get("Tank").get("TankProf")) / (double)(fuelRoster.get("Tank").get("TankProf") + (outlawRoster.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscore++;
                }else{
                    //Team 2's DPS wings
                    team2microscore++;
                }
    
            //DPS vs. Tank
                chance = (fuelRoster.get("DPS").get("DPSProf")) / (double)(fuelRoster.get("DPS").get("DPSProf") + (outlawRoster.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscore++;
                }else{
                    //Team 2's Tank wings
                    team2microscore++;
                }
                
            
            //Support vs. Support
            if(team1microscore == 2){
                team1matchscore++;
            }else if(team2microscore == 2){
                team2matchscore++;
            }else{
                chance = (fuelRoster.get("Support").get("SupportProf")) / (double)(fuelRoster.get("Support").get("SupportProf") + (outlawRoster.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscore++;
                    team1matchscore++;
                }else{
                    //Team 2's support wins
                    team2microscore++;
                    team2matchscore++;
                }
            }
            if(team1matchscore >= 2){
                team1winSide1 = true;
                if(round1prediction.equals("f")){
                    correctGuesses++;
                }
                System.out.println("Fuel wins!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }else if (team2matchscore >= 2){
                team1winSide1 = false;
                if(round1prediction.equals("o")){
                    correctGuesses++;
                }
                System.out.println("Outlaws win!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }
            TimeUnit.SECONDS.sleep(1);
    
            System.out.println("Round 2, Shock vs. Dynasty");
            TimeUnit.SECONDS.sleep(1);
            Boolean team1winSide2 = null;
            chancesUsed = 0;
            chanceSum = 0;
            int team1matchscoreSide2 = 0;
            int team2matchscoreSide2 = 0;
            //Match 1
            int team1microscoreSide2 = 0;
            int team2microscoreSide2 = 0;
            
            
                //Tank vs. DPS
                    chance = (shockRoster.get("Tank").get("TankProf")) / (double)(shockRoster.get("Tank").get("TankProf") + (dynastyRoster.get("DPS").get("DPSProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's tank wins
                        team1microscoreSide2++;
                    }else{
                        //Team 2's DPS wings
                        team2microscoreSide2++;
                    }
    
                //DPS vs. Tank
                    chance = (shockRoster.get("DPS").get("DPSProf")) / (double)(shockRoster.get("DPS").get("DPSProf") + (dynastyRoster.get("Tank").get("TankProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's DPS wins
                        team1microscoreSide2++;
                    }else{
                        //Team 2's Tank wings
                        team2microscoreSide2++;
                    }
                    
                
                //Support vs. Support
                if(team1microscoreSide2 == 2){
                    team1matchscoreSide2++;
                }else if(team2microscoreSide2 == 2){
                    team2matchscoreSide2++;
                }else{
                    chance = (shockRoster.get("Support").get("SupportProf")) / (double)(shockRoster.get("Support").get("SupportProf") + (dynastyRoster.get("Support").get("SupportProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's support wins
                        team1microscoreSide2++;
                        team1matchscoreSide2++;
                    }else{
                        //Team 2's support wins
                        team2microscoreSide2++;
                        team2matchscoreSide2++;
                    }
                }
    
                
            //Match 2
            team1microscoreSide2 = 0;
            team2microscoreSide2 = 0;
                //Tank vs. DPS
                chance = (shockRoster.get("Tank").get("TankProf")) / (double)(shockRoster.get("Tank").get("TankProf") + (dynastyRoster.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscoreSide2++;
                }else{
                    //Team 2's DPS wings
                    team2microscoreSide2++;
                }
    
            //DPS vs. Tank
                chance = (shockRoster.get("DPS").get("DPSProf")) / (double)(shockRoster.get("DPS").get("DPSProf") + (dynastyRoster.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscoreSide2++;
                }else{
                    //Team 2's Tank wings
                    team2microscoreSide2++;
                }
                
            
            //Support vs. Support
            if(team1microscoreSide2 == 2){
                team1matchscoreSide2++;
            }else if(team2microscoreSide2 == 2){
                team2matchscoreSide2++;
            }else{
                chance = (shockRoster.get("Support").get("SupportProf")) / (double)(shockRoster.get("Support").get("SupportProf") + (dynastyRoster.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscoreSide2++;
                    team1matchscoreSide2++;
                }else{
                    //Team 2's support wins
                    team2microscoreSide2++;
                    team2matchscoreSide2++;
                }
            }
    
            //Match 3 (optional)
            if(team1matchscoreSide2 == 2){
                team1winSide2 = true;
            }else if (team2matchscoreSide2 == 2){
                team1winSide2 = false;
            }else
            team1microscoreSide2 = 0;
            team2microscoreSide2 = 0;
                //Tank vs. DPS
                chance = (shockRoster.get("Tank").get("TankProf")) / (double)(shockRoster.get("Tank").get("TankProf") + (dynastyRoster.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscoreSide2++;
                }else{
                    //Team 2's DPS wings
                    team2microscoreSide2++;
                }
    
            //DPS vs. Tank
                chance = (shockRoster.get("DPS").get("DPSProf")) / (double)(shockRoster.get("DPS").get("DPSProf") + (dynastyRoster.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscoreSide2++;
                }else{
                    //Team 2's Tank wings
                    team2microscoreSide2++;
                }
                
            
            //Support vs. Support
            if(team1microscoreSide2 == 2){
                team1matchscoreSide2++;
            }else if(team2microscoreSide2 == 2){
                team2matchscoreSide2++;
            }else{
                chance = (shockRoster.get("Support").get("SupportProf")) / (double)(shockRoster.get("Support").get("SupportProf") + (dynastyRoster.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscoreSide2++;
                    team1matchscoreSide2++;
                }else{
                    //Team 2's support wins
                    team2microscoreSide2++;
                    team2matchscoreSide2++;
                }
            }
            if(team1matchscoreSide2 >= 2){
                team1winSide2 = true;
                if(round2prediction.equals("s")){
                    correctGuesses++;
                }
                System.out.println("Shock Wins!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }else if (team2matchscoreSide2 >= 2){
                team1winSide2 = false;
                if(round2prediction.equals("d")){
                    correctGuesses++;
                }
                System.out.println("Dynasty Wins!!!"); 
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }
            TimeUnit.SECONDS.sleep(1);
    
        //Finals
    
        HashMap<String, HashMap<String, Integer>> team1;
        HashMap<String, HashMap<String, Integer>> team2;
        if(team1winSide1 == true){
            team1 = fuelRoster;
        }else{
            team1 = outlawRoster;
        }
    
        if(team1winSide2 == true){
            team2 = shockRoster;
        }else{
            team2 = dynastyRoster;
        }
        if(team1 == fuelRoster){
            System.out.print("Finals!, Fuel vs.");
        }else{
            System.out.print("Finals!, Outlaws vs.");
        }
    
        if(team2 == shockRoster){
            System.out.println(" Shock!");
        }else{
            System.out.println(" Dynasty!");
        }
        TimeUnit.SECONDS.sleep(1);

    
            chanceSum = 0;
            chancesUsed = 0;
            Boolean team1winFinal;
            int team1matchscoreFinal = 0;
            int team2matchscoreFinal = 0;
            //Match 1
            int team1microscoreFinal = 0;
            int team2microscoreFinal = 0;
            
                //Tank vs. DPS
                    chance = (team1.get("Tank").get("TankProf")) / (double)(team1.get("Tank").get("TankProf") + (team2.get("DPS").get("DPSProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's tank wins
                        team1microscoreFinal++;
                    }else{
                        //Team 2's DPS wings
                        team2microscoreFinal++;
                    }
    
                //DPS vs. Tank
                    chance = (team1.get("DPS").get("DPSProf")) / (double)(team1.get("DPS").get("DPSProf") + (team2.get("Tank").get("TankProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's DPS wins
                        team1microscoreFinal++;
                    }else{
                        //Team 2's Tank wings
                        team2microscoreFinal++;
                    }
                    
                
                //Support vs. Support
                if(team1microscoreFinal == 2){
                    team1matchscoreFinal++;
                }else if(team2microscoreFinal == 2){
                    team2matchscoreFinal++;
                }else{
                    chance = (team1.get("Support").get("SupportProf")) / (double)(team1.get("Support").get("SupportProf") + (team2.get("Support").get("SupportProf")));
                    check = rand.nextFloat();
                    chanceSum += chance;
                    chancesUsed++;
                    if(chance >= check){
                        //Team 1's support wins
                        team1microscoreFinal++;
                        team1matchscoreFinal++;
                    }else{
                        //Team 2's support wins
                        team2microscoreFinal++;
                        team2matchscoreFinal++;
                    }
                }
    
                
            //Match 2
            team1microscoreFinal = 0;
            team2microscoreFinal = 0;
                //Tank vs. DPS
                chance = (team1.get("Tank").get("TankProf")) / (double)(team1.get("Tank").get("TankProf") + (team2.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscoreFinal++;
                }else{
                    //Team 2's DPS wings
                    team2microscoreFinal++;
                }
    
            //DPS vs. Tank
                chance = (team1.get("DPS").get("DPSProf")) / (double)(team1.get("DPS").get("DPSProf") + (team2.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscoreFinal++;
                }else{
                    //Team 2's Tank wings
                    team2microscoreFinal++;
                }
                
            
            //Support vs. Support
            if(team1microscoreFinal == 2){
                team1matchscoreFinal++;
            }else if(team2microscoreFinal == 2){
                team2matchscoreFinal++;
            }else{
                chance = (team1.get("Support").get("SupportProf")) / (double)(team1.get("Support").get("SupportProf") + (team2.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscoreFinal++;
                    team1matchscoreFinal++;
                }else{
                    //Team 2's support wins
                    team2microscoreFinal++;
                    team2matchscoreFinal++;
                }
            }
    
            //Match 3 (optional)
            if(team1matchscoreFinal == 2){
                team1winFinal = true;
            }else if (team2matchscoreFinal == 2){
                team1winFinal = false;
            }else
            team1microscoreFinal = 0;
            team2microscoreFinal = 0;
                //Tank vs. DPS
                chance = (team1.get("Tank").get("TankProf")) / (double)(team1.get("Tank").get("TankProf") + (team2.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscoreFinal++;
                }else{
                    //Team 2's DPS wings
                    team2microscoreFinal++;
                }
    
            //DPS vs. Tank
                chance = (team1.get("DPS").get("DPSProf")) / (double)(team1.get("DPS").get("DPSProf") + (team2.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscoreFinal++;
                }else{
                    //Team 2's Tank wings
                    team2microscoreFinal++;
                }
                
            
            //Support vs. Support
            if(team1microscoreFinal == 2){
                team1matchscoreFinal++;
            }else if(team2microscoreFinal == 2){
                team2matchscoreFinal++;
            }else{
                chance = (team1.get("Support").get("SupportProf")) / (double)(team1.get("Support").get("SupportProf") + (team2.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                    chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscoreFinal++;
                    team1matchscoreFinal++;
                }else{
                    //Team 2's support wins
                    team2microscoreFinal++;
                    team2matchscoreFinal++;
                }
            }
            if(team1matchscoreFinal >= 2){
                team1winFinal = true;
                if(team1 == fuelRoster){
                    System.out.println("Fuel Wins!!!");
                    System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
                    if(finalPrediction.equals("f")){
                        correctGuesses++;
                    }
                }else{
                    System.out.println("Outlaws Win!!!");
                    System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
                    if(finalPrediction.equals("o")){
                        correctGuesses++;
                    }
                }
                
            }else if (team2matchscoreFinal >= 2){
                team1winFinal = false;
                if(team2 == shockRoster){
                    System.out.println("Shock Wins!!!");
                    System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
                    if(finalPrediction.equals("s")){
                        correctGuesses++;
                    }
                }else{
                    System.out.println("Dynasty Win!!!");
                    System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
                    if(finalPrediction.equals("d")){
                        correctGuesses++;
                    }
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println("You got " + correctGuesses + " out of 3 games!");
            TimeUnit.SECONDS.sleep(2);
            break;

            case("s"):
            Randomize();
            System.out.println("Round 1, Fuel vs. Outlaws");
        team1winSide1 = null;
        team1matchscore = 0;
        team2matchscore = 0;
        //Match 1
        team1microscore = 0;
        team2microscore = 0;
        chanceSum = 0;
        chancesUsed = 0;
        
            //Tank vs. DPS
                chance = (fuelRoster.get("Tank").get("TankProf")) / (double)(fuelRoster.get("Tank").get("TankProf") + (outlawRoster.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscore++;
                }else{
                    //Team 2's DPS wings
                    team2microscore++;
                }

            //DPS vs. Tank
                chance = (fuelRoster.get("DPS").get("DPSProf")) / (double)(fuelRoster.get("DPS").get("DPSProf") + (outlawRoster.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscore++;
                }else{
                    //Team 2's Tank wings
                    team2microscore++;
                }
                
            
            //Support vs. Support
            if(team1microscore == 2){
                team1matchscore++;
            }else if(team2microscore == 2){
                team2matchscore++;
            }else{
                chance = (fuelRoster.get("Support").get("SupportProf")) / (double)(fuelRoster.get("Support").get("SupportProf") + (outlawRoster.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscore++;
                    team1matchscore++;
                }else{
                    //Team 2's support wins
                    team2microscore++;
                    team2matchscore++;
                }
            }

            
        //Match 2
        team1microscore = 0;
        team2microscore = 0;
            //Tank vs. DPS
            chance = (fuelRoster.get("Tank").get("TankProf")) / (double)(fuelRoster.get("Tank").get("TankProf") + (outlawRoster.get("DPS").get("DPSProf")));
            check = rand.nextFloat();
            chanceSum += chance;
            chancesUsed++;
            if(chance >= check){
                //Team 1's tank wins
                team1microscore++;
            }else{
                //Team 2's DPS wings
                team2microscore++;
            }

        //DPS vs. Tank
            chance = (fuelRoster.get("DPS").get("DPSProf")) / (double)(fuelRoster.get("DPS").get("DPSProf") + (outlawRoster.get("Tank").get("TankProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's DPS wins
                team1microscore++;
            }else{
                //Team 2's Tank wings
                team2microscore++;
            }
            
        
        //Support vs. Support
        if(team1microscore == 2){
            team1matchscore++;
        }else if(team2microscore == 2){
            team2matchscore++;
        }else{
            chance = (fuelRoster.get("Support").get("SupportProf")) / (double)(fuelRoster.get("Support").get("SupportProf") + (outlawRoster.get("Support").get("SupportProf")));
            check = rand.nextFloat();
            chanceSum += chance;
            chancesUsed++;
            if(chance >= check){
                //Team 1's support wins
                team1microscore++;
                team1matchscore++;
            }else{
                //Team 2's support wins
                team2microscore++;
                team2matchscore++;
            }
        }

        //Match 3 (optional)
        if(team1matchscore == 2){
            team1winSide1 = true;
        }else if (team2matchscore == 2){
            team1winSide1 = false;
        }else
        team1microscore = 0;
        team2microscore = 0;

            //Tank vs. DPS
            chance = (fuelRoster.get("Tank").get("TankProf")) / (double)(fuelRoster.get("Tank").get("TankProf") + (outlawRoster.get("DPS").get("DPSProf")));
            check = rand.nextFloat();
            chanceSum += chance;
            chancesUsed++;
            if(chance >= check){
                //Team 1's tank wins
                team1microscore++;
            }else{
                //Team 2's DPS wings
                team2microscore++;
            }

        //DPS vs. Tank
            chance = (fuelRoster.get("DPS").get("DPSProf")) / (double)(fuelRoster.get("DPS").get("DPSProf") + (outlawRoster.get("Tank").get("TankProf")));
            check = rand.nextFloat();
            chanceSum += chance;
            chancesUsed++;
            if(chance >= check){
                //Team 1's DPS wins
                team1microscore++;
            }else{
                //Team 2's Tank wings
                team2microscore++;
            }
            
        
        //Support vs. Support
        if(team1microscore == 2){
            team1matchscore++;
        }else if(team2microscore == 2){
            team2matchscore++;
        }else{
            chance = (fuelRoster.get("Support").get("SupportProf")) / (double)(fuelRoster.get("Support").get("SupportProf") + (outlawRoster.get("Support").get("SupportProf")));
            check = rand.nextFloat();
            chanceSum += chance;
            chancesUsed++;
            if(chance >= check){
                //Team 1's support wins
                team1microscore++;
                team1matchscore++;
            }else{
                //Team 2's support wins
                team2microscore++;
                team2matchscore++;
            }
        }
        System.out.print("Do you want (F)uel to win, (O)utlaws to win, or (Si)mulate it?: ");
        String winnerChosen = commandScanner.nextLine();
        while(!winnerChosen.toLowerCase().equals("f") && !winnerChosen.toLowerCase().equals("o") && !winnerChosen.toLowerCase().equals("si")){
            System.out.print("Do you want (F)uel to win, (O)utlaws to win, or (Si)mulate it?: ");
            winnerChosen = commandScanner.nextLine();
        }
        if(winnerChosen.equals("f")){
            team1winSide1 = true;
            System.out.println("Fuel wins!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        } else if(winnerChosen.equals("o")){
            team1winSide1 = false;
            System.out.println("Outlaws win!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }else if(team1matchscore >= 2){
            team1winSide1 = true;
            System.out.println("Fuel wins!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }else if (team2matchscore >= 2){
            team1winSide1 = false;
            System.out.println("Outlaws win!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }


        System.out.println("Round 2, Shock vs. Dynasty");
        team1winSide2 = null;
        chancesUsed = 0;
        chanceSum = 0;
        team1matchscoreSide2 = 0;
        team2matchscoreSide2 = 0;
        //Match 1
        team1microscoreSide2 = 0;
        team2microscoreSide2 = 0;
        
        
            //Tank vs. DPS
                chance = (shockRoster.get("Tank").get("TankProf")) / (double)(shockRoster.get("Tank").get("TankProf") + (dynastyRoster.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscoreSide2++;
                }else{
                    //Team 2's DPS wings
                    team2microscoreSide2++;
                }

            //DPS vs. Tank
                chance = (shockRoster.get("DPS").get("DPSProf")) / (double)(shockRoster.get("DPS").get("DPSProf") + (dynastyRoster.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscoreSide2++;
                }else{
                    //Team 2's Tank wings
                    team2microscoreSide2++;
                }
                
            
            //Support vs. Support
            if(team1microscoreSide2 == 2){
                team1matchscoreSide2++;
            }else if(team2microscoreSide2 == 2){
                team2matchscoreSide2++;
            }else{
                chance = (shockRoster.get("Support").get("SupportProf")) / (double)(shockRoster.get("Support").get("SupportProf") + (dynastyRoster.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscoreSide2++;
                    team1matchscoreSide2++;
                }else{
                    //Team 2's support wins
                    team2microscoreSide2++;
                    team2matchscoreSide2++;
                }
            }

            
        //Match 2
        team1microscoreSide2 = 0;
        team2microscoreSide2 = 0;
            //Tank vs. DPS
            chance = (shockRoster.get("Tank").get("TankProf")) / (double)(shockRoster.get("Tank").get("TankProf") + (dynastyRoster.get("DPS").get("DPSProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's tank wins
                team1microscoreSide2++;
            }else{
                //Team 2's DPS wings
                team2microscoreSide2++;
            }

        //DPS vs. Tank
            chance = (shockRoster.get("DPS").get("DPSProf")) / (double)(shockRoster.get("DPS").get("DPSProf") + (dynastyRoster.get("Tank").get("TankProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's DPS wins
                team1microscoreSide2++;
            }else{
                //Team 2's Tank wings
                team2microscoreSide2++;
            }
            
        
        //Support vs. Support
        if(team1microscoreSide2 == 2){
            team1matchscoreSide2++;
        }else if(team2microscoreSide2 == 2){
            team2matchscoreSide2++;
        }else{
            chance = (shockRoster.get("Support").get("SupportProf")) / (double)(shockRoster.get("Support").get("SupportProf") + (dynastyRoster.get("Support").get("SupportProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's support wins
                team1microscoreSide2++;
                team1matchscoreSide2++;
            }else{
                //Team 2's support wins
                team2microscoreSide2++;
                team2matchscoreSide2++;
            }
        }

        //Match 3 (optional)
        if(team1matchscoreSide2 == 2){
            team1winSide2 = true;
        }else if (team2matchscoreSide2 == 2){
            team1winSide2 = false;
        }else
        team1microscoreSide2 = 0;
        team2microscoreSide2 = 0;
            //Tank vs. DPS
            chance = (shockRoster.get("Tank").get("TankProf")) / (double)(shockRoster.get("Tank").get("TankProf") + (dynastyRoster.get("DPS").get("DPSProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's tank wins
                team1microscoreSide2++;
            }else{
                //Team 2's DPS wings
                team2microscoreSide2++;
            }

        //DPS vs. Tank
            chance = (shockRoster.get("DPS").get("DPSProf")) / (double)(shockRoster.get("DPS").get("DPSProf") + (dynastyRoster.get("Tank").get("TankProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's DPS wins
                team1microscoreSide2++;
            }else{
                //Team 2's Tank wings
                team2microscoreSide2++;
            }
            
        
        //Support vs. Support
        if(team1microscoreSide2 == 2){
            team1matchscoreSide2++;
        }else if(team2microscoreSide2 == 2){
            team2matchscoreSide2++;
        }else{
            chance = (shockRoster.get("Support").get("SupportProf")) / (double)(shockRoster.get("Support").get("SupportProf") + (dynastyRoster.get("Support").get("SupportProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's support wins
                team1microscoreSide2++;
                team1matchscoreSide2++;
            }else{
                //Team 2's support wins
                team2microscoreSide2++;
                team2matchscoreSide2++;
            }
        }
        System.out.print("Do you want (S)hock to win, (D)ynasty to win, or (Si)mulate it?: ");
        winnerChosen = commandScanner.nextLine();
        while(!winnerChosen.toLowerCase().equals("s") && !winnerChosen.toLowerCase().equals("d") && !winnerChosen.toLowerCase().equals("si")){
            System.out.print("Do you want (S)hock to win, (D)ynasty to win, or (Si)mulate it?: ");
            winnerChosen = commandScanner.nextLine();
        }
        if(winnerChosen.equals("s")){
            team1winSide2 = true;
            System.out.println("Shock Wins!!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }else if(winnerChosen.equals("d")){
            team1winSide2 = false;
            System.out.println("Dynasty Wins!!!"); 
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }else if(team1matchscoreSide2 >= 2){
            team1winSide2 = true;
            System.out.println("Shock Wins!!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }else if (team2matchscoreSide2 >= 2){
            team1winSide2 = false;
            System.out.println("Dynasty Wins!!!"); 
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
        }

    //Finals

    team1 = null;
    team2 = null;
    if(team1winSide1 == true){
        team1 = fuelRoster;
    }else{
        team1 = outlawRoster;
    }

    if(team1winSide2 == true){
        team2 = shockRoster;
    }else{
        team2 = dynastyRoster;
    }
    if(team1 == fuelRoster){
        System.out.print("Finals!, Fuel vs.");
    }else{
        System.out.print("Finals!, Outlaws vs.");
    }

    if(team2 == shockRoster){
        System.out.println(" Shock!");
    }else{
        System.out.println(" Dynasty!");
    }

        chanceSum = 0;
        chancesUsed = 0;
        team1winFinal = null;
        team1matchscoreFinal = 0;
        team2matchscoreFinal = 0;
        //Match 1
        team1microscoreFinal = 0;
        team2microscoreFinal = 0;
        
            //Tank vs. DPS
                chance = (team1.get("Tank").get("TankProf")) / (double)(team1.get("Tank").get("TankProf") + (team2.get("DPS").get("DPSProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's tank wins
                    team1microscoreFinal++;
                }else{
                    //Team 2's DPS wings
                    team2microscoreFinal++;
                }

            //DPS vs. Tank
                chance = (team1.get("DPS").get("DPSProf")) / (double)(team1.get("DPS").get("DPSProf") + (team2.get("Tank").get("TankProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's DPS wins
                    team1microscoreFinal++;
                }else{
                    //Team 2's Tank wings
                    team2microscoreFinal++;
                }
                
            
            //Support vs. Support
            if(team1microscoreFinal == 2){
                team1matchscoreFinal++;
            }else if(team2microscoreFinal == 2){
                team2matchscoreFinal++;
            }else{
                chance = (team1.get("Support").get("SupportProf")) /(double) (team1.get("Support").get("SupportProf") + (team2.get("Support").get("SupportProf")));
                check = rand.nextFloat();
                chanceSum += chance;
                chancesUsed++;
                if(chance >= check){
                    //Team 1's support wins
                    team1microscoreFinal++;
                    team1matchscoreFinal++;
                }else{
                    //Team 2's support wins
                    team2microscoreFinal++;
                    team2matchscoreFinal++;
                }
            }

            
        //Match 2
        team1microscoreFinal = 0;
        team2microscoreFinal = 0;
            //Tank vs. DPS
            chance = (team1.get("Tank").get("TankProf")) / (double)(team1.get("Tank").get("TankProf") + (team2.get("DPS").get("DPSProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's tank wins
                team1microscoreFinal++;
            }else{
                //Team 2's DPS wings
                team2microscoreFinal++;
            }

        //DPS vs. Tank
            chance = (team1.get("DPS").get("DPSProf")) / (double)(team1.get("DPS").get("DPSProf") + (team2.get("Tank").get("TankProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's DPS wins
                team1microscoreFinal++;
            }else{
                //Team 2's Tank wings
                team2microscoreFinal++;
            }
            
        
        //Support vs. Support
        if(team1microscoreFinal == 2){
            team1matchscoreFinal++;
        }else if(team2microscoreFinal == 2){
            team2matchscoreFinal++;
        }else{
            chance = (team1.get("Support").get("SupportProf")) / (double)(team1.get("Support").get("SupportProf") + (team2.get("Support").get("SupportProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's support wins
                team1microscoreFinal++;
                team1matchscoreFinal++;
            }else{
                //Team 2's support wins
                team2microscoreFinal++;
                team2matchscoreFinal++;
            }
        }

        //Match 3 (optional)
        if(team1matchscoreFinal == 2){
            team1winFinal = true;
        }else if (team2matchscoreFinal == 2){
            team1winFinal = false;
        }else
        team1microscoreFinal = 0;
        team2microscoreFinal = 0;
            //Tank vs. DPS
            chance = (team1.get("Tank").get("TankProf")) / (double)(team1.get("Tank").get("TankProf") + (team2.get("DPS").get("DPSProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's tank wins
                team1microscoreFinal++;
            }else{
                //Team 2's DPS wings
                team2microscoreFinal++;
            }

        //DPS vs. Tank
            chance = (team1.get("DPS").get("DPSProf")) / (double)(team1.get("DPS").get("DPSProf") + (team2.get("Tank").get("TankProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's DPS wins
                team1microscoreFinal++;
            }else{
                //Team 2's Tank wings
                team2microscoreFinal++;
            }
            
        
        //Support vs. Support
        if(team1microscoreFinal == 2){
            team1matchscoreFinal++;
        }else if(team2microscoreFinal == 2){
            team2matchscoreFinal++;
        }else{
            chance = (team1.get("Support").get("SupportProf")) / (double)(team1.get("Support").get("SupportProf") + (team2.get("Support").get("SupportProf")));
            check = rand.nextFloat();
            chanceSum += chance;
                chancesUsed++;
            if(chance >= check){
                //Team 1's support wins
                team1microscoreFinal++;
                team1matchscoreFinal++;
            }else{
                //Team 2's support wins
                team2microscoreFinal++;
                team2matchscoreFinal++;
            }
        }
        if(team1 == fuelRoster){
            System.out.print("Who do you want to win? (F)uel, ");
        }else if (team1 == outlawRoster){
            System.out.print("Who do you want to win? (O)utlaws, ");
        }

        if(team2 == shockRoster){
            System.out.print("(S)hock, or (Si)mulate it ?: ");
        }else if (team2 == dynastyRoster){
            System.out.print("(D)ynasty, or (Si)mulate it ?: ");
        }
        winnerChosen = commandScanner.nextLine();

        
        while(!winnerChosen.toLowerCase().equals("f") && !winnerChosen.toLowerCase().equals("o") && !winnerChosen.toLowerCase().equals("si") && !winnerChosen.toLowerCase().equals("d") && !winnerChosen.toLowerCase().equals("s")){
            if(team1 == fuelRoster){
                System.out.print("Who do you want to win? (F)uel, ");
            }else if (team1 == outlawRoster){
                System.out.print("Who do you want to win? (O)utlaws, ");
            }
    
            if(team2 == shockRoster){
                System.out.print("(S)hock, or (Si)mulate it ?: ");
            }else if (team2 == dynastyRoster){
                System.out.print("(D)ynasty, or (Si)mulate it ?: ");
            }
            winnerChosen = commandScanner.nextLine();
        }
        if(winnerChosen.equals("f")){
            if(team1 == fuelRoster){
                System.out.println("Fuel Wins!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }

        }else if(winnerChosen.equals("o")){
            if(team1 == outlawRoster){
                System.out.println("Outlaws Win!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }

        }else if(winnerChosen.equals("d")){
            if(team2 == dynastyRoster){
            System.out.println("Dynasty Win!!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }
        }else if(winnerChosen.equals("s")){
            if(team2 == shockRoster){
            System.out.println("Shock Wins!!!");
            System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }
        }




        if(team1matchscoreFinal >= 2){
            team1winFinal = true;
            if(team1 == fuelRoster){
                System.out.println("Fuel Wins!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }else{
                System.out.println("Outlaws Win!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }
            
        }else if (team2matchscoreFinal >= 2){
            team1winFinal = false;
            if(team2 == shockRoster){
                System.out.println("Shock Wins!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }else{
                System.out.println("Dynasty Win!!!");
                System.out.println("Odds of winning this round " + (100 - (Math.round((chanceSum/chancesUsed)*100))) + "%");
            }
        }
        break;
        case("q"):
            active = false;
            break;
        case("m"):
            break;
        default:
            System.out.println("That's not an option, silly!");
        
    }
    }
    }

    public static void Randomize(){
//Fuel Players
Random rand = new Random();
HashMap<String, Integer> fuel1 = new HashMap<>();
fuel1.put("DPSProf", rand.nextInt(1,101));
fuel1.put("TankProf", rand.nextInt(1,101));
fuel1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> fuel2 = new HashMap<>();
fuel2.put("DPSProf", rand.nextInt(1,101));
fuel2.put("TankProf", rand.nextInt(1,101));
fuel2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> fuel3 = new HashMap<>();
fuel3.put("DPSProf", rand.nextInt(1,101));
fuel3.put("TankProf", rand.nextInt(1,101));
fuel3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> fuelRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
int player1role = rand.nextInt(1,4);

if(player1role == 1){
    fuelRoster.put("DPS", fuel1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        fuelRoster.put("Tank", fuel2);
        fuelRoster.put("Support", fuel3);
    }else{
        fuelRoster.put("Support", fuel2);
        fuelRoster.put("Tank", fuel3);
    }


} else if(player1role == 2){
    fuelRoster.put("Tank", fuel1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        fuelRoster.put("Support", fuel2);
        fuelRoster.put("DPS", fuel3);
    }else{
        fuelRoster.put("DPS", fuel2);
        fuelRoster.put("Support", fuel3);
    }


} else if(player1role == 3){
    fuelRoster.put("Support", fuel1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        fuelRoster.put("DPS", fuel2);
        fuelRoster.put("Tank", fuel3);
    }else{
        fuelRoster.put("Tank", fuel2);
        fuelRoster.put("DPS", fuel3);
    }
}





//Outlaw Players
HashMap<String, Integer> outlaw1 = new HashMap<>();
outlaw1.put("DPSProf", rand.nextInt(1,101));
outlaw1.put("TankProf", rand.nextInt(1,101));
outlaw1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> outlaw2 = new HashMap<>();
outlaw2.put("DPSProf", rand.nextInt(1,101));
outlaw2.put("TankProf", rand.nextInt(1,101));
outlaw2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> outlaw3 = new HashMap<>();
outlaw3.put("DPSProf", rand.nextInt(1,101));
outlaw3.put("TankProf", rand.nextInt(1,101));
outlaw3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> outlawRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
player1role = rand.nextInt(1,4);

if(player1role == 1){
    outlawRoster.put("DPS", outlaw1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        outlawRoster.put("Tank", outlaw2);
        outlawRoster.put("Support", outlaw3);
    }else{
        outlawRoster.put("Support", outlaw2);
        outlawRoster.put("Tank", outlaw3);
    }


} else if(player1role == 2){
    outlawRoster.put("Tank", outlaw1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        outlawRoster.put("Support", outlaw2);
        outlawRoster.put("DPS", outlaw3);
    }else{
        outlawRoster.put("DPS", outlaw2);
        outlawRoster.put("Support", outlaw3);
    }


} else if(player1role == 3){
    outlawRoster.put("Support", outlaw1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        outlawRoster.put("DPS", outlaw2);
        outlawRoster.put("Tank", outlaw3);
    }else{
        outlawRoster.put("Tank", outlaw2);
        outlawRoster.put("DPS", outlaw3);
    }
}



//Shock Players
HashMap<String, Integer> shock1 = new HashMap<>();
shock1.put("DPSProf", rand.nextInt(1,101));
shock1.put("TankProf", rand.nextInt(1,101));
shock1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> shock2 = new HashMap<>();
shock2.put("DPSProf", rand.nextInt(1,101));
shock2.put("TankProf", rand.nextInt(1,101));
shock2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> shock3 = new HashMap<>();
shock3.put("DPSProf", rand.nextInt(1,101));
shock3.put("TankProf", rand.nextInt(1,101));
shock3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> shockRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
player1role = rand.nextInt(1,4);

if(player1role == 1){
    shockRoster.put("DPS", shock1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        shockRoster.put("Tank", shock2);
        shockRoster.put("Support", shock3);
    }else{
        shockRoster.put("Support", shock2);
        shockRoster.put("Tank", shock3);
    }


} else if(player1role == 2){
    shockRoster.put("Tank", shock1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        shockRoster.put("Support", shock2);
        shockRoster.put("DPS", shock3);
    }else{
        shockRoster.put("DPS", shock2);
        shockRoster.put("Support", shock3);
    }


} else if(player1role == 3){
    shockRoster.put("Support", shock1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        shockRoster.put("DPS", shock2);
        shockRoster.put("Tank", shock3);
    }else{
        shockRoster.put("Tank", shock2);
        shockRoster.put("DPS", shock3);
    }
}


//Dynasty Players
HashMap<String, Integer> dynasty1 = new HashMap<>();
dynasty1.put("DPSProf", rand.nextInt(1,101));
dynasty1.put("TankProf", rand.nextInt(1,101));
dynasty1.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> dynasty2 = new HashMap<>();
dynasty2.put("DPSProf", rand.nextInt(1,101));
dynasty2.put("TankProf", rand.nextInt(1,101));
dynasty2.put("SupportProf", rand.nextInt(1,101));

HashMap<String, Integer> dynasty3 = new HashMap<>();
dynasty3.put("DPSProf", rand.nextInt(1,101));
dynasty3.put("TankProf", rand.nextInt(1,101));
dynasty3.put("SupportProf", rand.nextInt(1,101));


HashMap<String, HashMap<String, Integer>> dynastyRoster = new HashMap<>();
//1 is DPS, 2 is Tank, 3 is Support
player1role = rand.nextInt(1,4);

if(player1role == 1){
    dynastyRoster.put("DPS", dynasty1);
    int player2role = rand.nextInt(2,4);
    if(player2role == 2){
        dynastyRoster.put("Tank", dynasty2);
        dynastyRoster.put("Support", dynasty3);
    }else{
        dynastyRoster.put("Support", dynasty2);
        dynastyRoster.put("Tank", dynasty3);
    }


} else if(player1role == 2){
    dynastyRoster.put("Tank", dynasty1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 2){
        dynastyRoster.put("Support", dynasty2);
        dynastyRoster.put("DPS", dynasty3);
    }else{
        dynastyRoster.put("DPS", dynasty2);
        dynastyRoster.put("Support", dynasty3);
    }


} else if(player1role == 3){
    dynastyRoster.put("Support", dynasty1);
    int player2role = rand.nextInt(1,3);
    if(player2role == 1){
        dynastyRoster.put("DPS", dynasty2);
        dynastyRoster.put("Tank", dynasty3);
    }else{
        dynastyRoster.put("Tank", dynasty2);
        dynastyRoster.put("DPS", dynasty3);
    }
}

    }
}