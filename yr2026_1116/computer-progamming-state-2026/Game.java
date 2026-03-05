import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.lang.Thread;

/**
 * The brains of the opperation and where all logic takes place
 * @Author Ghammond
 */
public class Game {
    public static final String RESET = "\u001B[0m"; // removes fx
    public static final String RED = "\u001B[31m"; //# enemy units for attack
    public static final String BRED = "\u001B[91m"; //bad thing for player
    public static final String GREEN = "\u001B[32m"; //good thing for player
    public static final String YELLOW = "\u001B[33m"; //Important message/Delineator
    public static final String CYAN = "\u001B[36m"; //# player units for attack
    public static final String BOLD = "\u001B[1m"; // important text
    public static final String DIM = "\u001B[2m"; // ignorable stuff
    public static final String UNDERLINE = "\u001B[4m"; // alt important
    
    private Player user = new Player(); //YOU
    private Player opponent = new Player(); // MY MAGICAL AI
    private AI robot = new AI(opponent, user, this);
    public CLI cli; // Because the game needs to say when it ends it Needs CLI to call end game.
    public enum Actions{
        TRAIN,
        EXPAND,
        ATTACK
    }
    private boolean running = true; // Whether the game is waiting for a response or not
    private Base[] bases = new Base[4];

    /**
     * Instantiates a new Game class with all of its fun things!
     */
    public Game(){
        bases[0] = new Base(Base.occupier.PLAYER);
        bases[1] = new Base(Base.occupier.OPPONENT);
        bases[2] = new Base(Base.occupier.UNCLAIMED);
        bases[3] = new Base(Base.occupier.UNCLAIMED);
        cli = new CLI(this);
    }

    /**
     * Checks if the game is running
     * @return true if the game is and false otherwise
     */
    public boolean isRunning(){
        return running;
    }

    /**
     * ends the game
     * but why would you want that?
     */
    public void quit(){
        running = false;
    }

    /**
     * Takes and action based on the user and what action you want
     * @param userActing which user is acting
     * @param action an action cleaned by the user
     */
    public void action(Boolean userActing, Actions action){
        if(userActing){
            //Player Actions
            
            switch (action) {
                case TRAIN:
                    this.user.setUnits(this.user.getUnits() + 1 * this.user.getBases()); // Increases units
                    cli.print("Player: Train; +" + user.getBases() + " unit; " + user.getUnits() + " units");
                    break;

                case EXPAND:
                    Base objectiveBase = getOpenBase();
                    if(objectiveBase != null){
                        user.setBases(user.getBases() + 1);
                        objectiveBase.setOccupation(Base.occupier.PLAYER); //Checks if there is a base, if so adds it
                        cli.print("Player: Expand; +1 base; (Base " + objectiveBase.number + ": Player)");
                    }else{
                        cli.print("Player: Expand; Failed All Bases Full;");
                    }
                    break;

                case ATTACK:
                    if(user.getUnits() <= 1){
                        cli.print(YELLOW +"You taunt the enemy and stall as you try to attack with a single Unit;" + RESET); //Cannot attack with one unit
                        break;
                    }
                    cli.print("Player Attacks.");
                    initiateCombat(true);
                    break;
            }
            action(false, robot.attemptMove());
        }else{
            //AI actions See above comments
            switch (action) {
                case TRAIN:
                    opponent.setUnits(opponent.getUnits() + 1 * opponent.getBases());
                    cli.print("Opponent: Train; +" + opponent.getBases() + " unit; " + opponent.getUnits() + " units");
                    break;

                case EXPAND:
                    Base objectiveBase = getOpenBase();
                    if(objectiveBase != null){
                        opponent.setBases(opponent.getBases() + 1);
                        objectiveBase.setOccupation(Base.occupier.OPPONENT);
                        cli.print("Opponent: Expand; +1 base; (Base " + objectiveBase.number + ": Opponent)");
                    }else{
                        cli.print("Opponent: Expand; Failed All Bases Full;");
                    }
                    break;

                case ATTACK:
                    if(opponent.getUnits() <= 1){
                        cli.print(YELLOW + "Your Opponent Taunts you and Stalls as they try to attack with 1 unit" + RESET);
                        break;
                    }
                    cli.print("Opponent Attacks.");
                    initiateCombat(false);
                    break;
            }
        }
    }

    /**
     * changes my array of bases into an array of occupiers
     * @return returns an array of the bases occupiers...
     */
    public Base.occupier[] getAllBaseOccupations(){
        Base.occupier[] retVal = new Base.occupier[4];

        retVal[0] = bases[0].getOccupation();
        retVal[1] = bases[1].getOccupation();
        retVal[2] = bases[2].getOccupation();
        retVal[3] = bases[3].getOccupation();

        return retVal;
    }

    /**
     * returns either player based on a bool
     * @param amIUser If it is true it returns the user
     * @return returns the opponent if amIUser is false
     */
    public Player Player(Boolean amIUser){
        return (amIUser ? user : opponent);
    }

    /**
     * gives you an open base
     * @return returns the base that is open
     */
    public Base getOpenBase(){
        for (Base base : bases) {
            if(base.getOccupation() == Base.occupier.UNCLAIMED)
                return base;
        }
        return null;
    }

    /**
     * returns whether or not the user has a claimed base
     * @param isClaimedByUser whether or not the player has the base or not
     * @return which base
     */
    public Base getClaimedBase(boolean isClaimedByUser){
        for (Base base : bases) {
            if(base.getOccupation() == (isClaimedByUser?Base.occupier.PLAYER: Base.occupier.OPPONENT))
                return base;
        }
        return null;
    }

    /**
     * Runs through combat and prints out stuff as well as doing the logic.
     * THIS function is a beast primarily because of the way it works.
     * There are a variety of sleeps as getting all 5 rounds of an attack at once is overwhelming...
     * @param isUserAttacking On true then the user is attacking, on false then the opponent is attacking
     * I highly advise you use alt + z as there is some long code in here... Most of it is print functions.
     */
    public void initiateCombat(boolean isUserAttacking){
        try {
            Random rand = new Random();
            int phase = 0;
            //Attacker Inits
            String attackerName = (isUserAttacking ? "Player": "Opponent"); //Checks names
            String defenderName = (!isUserAttacking ? "Player": "Opponent");
            Player attacker = Player(isUserAttacking); //Figures out data for the player
            Player defender = Player(!isUserAttacking);
            int attackerUnits = (attacker.units-1); //HP
            int defenderUnits = defender.units;
            ArrayList<Integer> attackersRolls = new ArrayList<Integer>(); //What you roll
            ArrayList<Integer> defendersRolls = new ArrayList<Integer>();
            //Yapp Attack
            cli.print("COMBAT:\n");
            while(attackerUnits > 0 && defenderUnits > 0){ // Runs until there are no longer any viable units
                //Clearing rolls and making things work
                phase ++;
                attackersRolls.clear();
                defendersRolls.clear();
                //Defines the phase
                cli.print(BOLD + YELLOW  + "Phase: " + phase + RESET);
                Thread.sleep(1000);
                cli.print(UNDERLINE + attackerName + RESET + "'s war reserve has " + BOLD + attackerUnits + RESET + " units (" + (attackerUnits+1) + " - 1)");
                cli.print(UNDERLINE + defenderName + RESET + "'s city force has " + BOLD + defenderUnits + RESET + " units\n");
                Thread.sleep(600);
                cli.print(attackerName + " phase:\nAttacks with " + BOLD +(isUserAttacking ? CYAN : RED) + Math.min(attackerUnits, 3) + RESET + " units;" + DIM + " min(3, " 
                + attackerUnits + ")" + RESET);

                cli.print(defenderName + " defends with " + BOLD + (!isUserAttacking ? CYAN : RED) + Math.min(defenderUnits, 2) + RESET + " units;" + DIM + " min(2, " 
                + defenderUnits + ")" + RESET + "\n");

                // Checks the rolls for both of them
                for (int i = 0; i < Math.min(attackerUnits, 3); i++) {
                    attackersRolls.add(rand.nextInt(6) + 1);
                }
                for (int i = 0; i < Math.min(defenderUnits, 2); i++) {
                    defendersRolls.add(rand.nextInt(6) + 1);
                }
                attackersRolls.sort(Comparator.reverseOrder());
                defendersRolls.sort(Comparator.reverseOrder());

                Thread.sleep(600);
                cli.print(attackerName + " rolls " + attackersRolls.size() + " 6-sided dice and orders them = " +  attackersRolls);
                cli.print(defenderName + " rolls " + defendersRolls.size() + " 6-sided dice and orders them = " +  defendersRolls + "\n");
                //Shows rolls
                // Win logic
                boolean atkrWin = attackersRolls.get(0) > defendersRolls.get(0);
                Thread.sleep(600); //Slowing the robot down
                cli.print((atkrWin && isUserAttacking  || !atkrWin && !isUserAttacking? GREEN: BRED) + "Attacker's " + attackersRolls.get(0) + " compares to Defender's " 
                + defendersRolls.get(0) + ", " + (atkrWin ? "Attacker wins, defender -1 unit" : "Defender wins, attacker -1 unit") + RESET);
                //Above code decides round winner, compares vals, shows winner.
                if(atkrWin) 
                    defenderUnits--;
                else
                    attackerUnits--;
                //Removes units
                if(defendersRolls.size() <= 1 || attackersRolls.size() <= 1) continue; // Checks if the defender or attacker only rolled once.
                attackersRolls.remove(0);
                defendersRolls.remove(0); //Removes the first value
                
                atkrWin = attackersRolls.get(0) > defendersRolls.get(0);
                cli.print((atkrWin && isUserAttacking  || !atkrWin && !isUserAttacking? GREEN: BRED) + "Attacker's " + attackersRolls.get(0) + " compares to Defender's " 
                + defendersRolls.get(0) + ", " + (atkrWin ? "Attacker wins, defender -1 unit" : "Defender wins, attacker -1 unit\n") + RESET);
                //Above code decides round winner, compares vals, shows winner.
                if(atkrWin) 
                    defenderUnits--;
                else
                    attackerUnits--;
                //Removes units
            }

            //Checks who one the attack
            if(defenderUnits == 0){
                cli.print((isUserAttacking? GREEN : BRED) + "COMBAT ENDS: ATTACKERS WIN" + RESET); // Shows who won
                if(defender.getBases() > 0){
                    attacker.setBases(attacker.getBases() + 1);
                    defender.setBases(defender.getBases() - 1);
                    getClaimedBase(!isUserAttacking).setOccupation((isUserAttacking?Base.occupier.PLAYER:Base.occupier.OPPONENT)); 
                    //Shows the defender (non-attacker) is getting their bases claimed.
                    cli.print((isUserAttacking? GREEN : BRED) + attackerName + " Claimed " + defenderName + "'s base!" + RESET); // You stole it!
                }else{
                    cli.gameEnds(); // No bases/ troops
                }
                
            }else{
                cli.print((!isUserAttacking? GREEN : BRED) + "COMBAT ENDS: DEFENDERS WIN" + RESET);// Shows who won
            }
            //Sets units
            attacker.setUnits(attackerUnits + 1);
            defender.setUnits(defenderUnits);

            if(user.lost() || opponent.lost()){
                cli.gameEnds();
            }
            Thread.sleep(2000);
            cli.assess();
        } catch (InterruptedException e) {
            System.err.println("SLEEP WAS INTERRUPTED GAME ENDING");//Fatal error
            quit();
        }

    }
}
