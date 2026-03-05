import java.util.Random;
import java.util.TreeSet;

/*
 * The Game class runs games states and deterines when the game has been completed
 */
public class Game {
    private int rounds = 5;
    private int currentRound = 1;
    private int[] bases = {0,0,0,0};
    private Random rand = new Random();
    private Player user, opponent;

    /*
     * Game Constructor, starts game positions
     */
    public Game(int rounds){
        //set user base
        this.rounds = rounds;
        setBase(rand.nextInt(4), 1);

        //set opponent base
        int opponentStart = -1;
        while(opponentStart < 0 || bases[opponentStart] == 1){
            opponentStart = rand.nextInt(4);
        }
        setBase(opponentStart, 2);
    }

    /* Simulates an attack from one player to another
     * @param attacker - the attacking player
     * @param defender - the defending player
     */
    public void simAttack(Player attacker, Player defender){
        int attUnits = attacker.getUnits() - 1;
        int defUnits = defender.getUnits();
        int phase = 1;

        //attack loop
        while(attUnits > 0 && defUnits > 0){
            System.out.println("\n----------------- Combat Round " + phase + " begins -----------------");
            System.out.println("Attacking Units: " + attUnits + ", Defending Units: " + defUnits + "\n");
            //roll attacks
            TreeSet<Integer> attSet = new TreeSet<>();
            TreeSet<Integer> defSet = new TreeSet<>();
            for(int i = 0; i < attUnits; i++){
                attSet.add(rand.nextInt(6) + 1);
            }
            for(int i = 0; i < defUnits; i++){
                defSet.add(rand.nextInt(6) + 1);
            }

            //fight
            for(int i = 0; i < Math.min(attSet.size(), defSet.size()); i++){
                System.out.println("Fight " + (i + 1) + "\nAttacker rolled: " + attSet.last() + ", Defender rolled: " + defSet.last());

                if(attSet.last() > defSet.last()){
                    defUnits--;
                    System.out.println("Attacking unit won");
                }
                if(attSet.last() < defSet.last()) {
                    attUnits--;
                    System.out.println("Defending unit won");
                }

                attSet.removeLast();
                defSet.removeLast();
            }
            phase++;
        }

        //combat end mesage
        if(attUnits > defUnits){
            System.out.println("\n================= COMBAT ENDS; ATTACKER WINS =================");
            attacker.incrementBases();
            defender.decrementBases();

            for(int i = 0; i < bases.length; i++){
                if(bases[i] == defender.getPlayerNumber()){
                    setBase(i, attacker.getPlayerNumber());
                    break;
                }
            }
        }
        else{
            System.out.println("\n================= COMBAT ENDS; DEFENDER WINS =================");
        }
        attacker.setUnits(attUnits + 1);
        defender.setUnits(defUnits);
    }

    /* getter/setter/incrementors */

    public void incrementRound(){
        currentRound++;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int[] getBases() {
        return bases;
    }

    public void setBase(int base, int value) {
        bases[base] = value;
    }
    
    public void setPlayers(Player user, Player opponent){
        this.user = user;
        this.opponent = opponent;
    }

    public Boolean getIsGameOver(){
        if(currentRound > rounds){
            return true;
        }

        if(user.getBases() <= 0 && user.getUnits() <= 0){
            return true;
        }

        if(opponent.getBases() <= 0 && opponent.getUnits() <= 0){
            return true;
        }

        return false;
    }

    public String getBaseString(int base){
        return "Base 1: " + ((bases[base] == 1) ? "Player" : (bases[base] == 2) ? "Opponent" : "Unclaimed");
    }

    public String getAllBasesString(){
        String base1 = "Base 1: " + ((bases[0] == 1) ? "Player" : (bases[0] == 2) ? "Opponent" : "Unclaimed");
        String base2 = "Base 2: " + ((bases[1] == 1) ? "Player" : (bases[1] == 2) ? "Opponent" : "Unclaimed");
        String base3 = "Base 3: " + ((bases[2] == 1) ? "Player" : (bases[2] == 2) ? "Opponent" : "Unclaimed");
        String base4 = "Base 4: " + ((bases[3] == 1) ? "Player" : (bases[3] == 2) ? "Opponent" : "Unclaimed");

        return base1 + "\n" + base2 + "\n" + base3 + "\n" + base4;
    }

    public String getResults(){
        String winner = "";

        //compare units
        if(user.getUnits() > opponent.getUnits() && winner == ""){
            winner = "PLAYER WINS";
        }

        if(user.getUnits() < opponent.getUnits() && winner == ""){
            winner = "OPPONENT WINS";
        }

        //compare bases
        if(user.getBases() > opponent.getBases() && winner == ""){
            winner = "PLAYER WINS";
        }

        if(user.getBases() < opponent.getBases() && winner == ""){
            winner = "OPPONENT WINS";
        }

        return "GAME END; " + winner + "\n\n" + "FINAL STATE:\n\n" + getAllBasesString();
    }
}
