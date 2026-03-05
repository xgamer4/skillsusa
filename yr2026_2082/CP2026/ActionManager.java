import java.util.ArrayList;

public class ActionManager {
    private Player player;

    public ActionManager(){

    }

    /* actions */

    /* Returns phrase to trigger an attack from a player
     */
    public String Attack(){
        return "Attack";
    }

    /* Trains units based on number of bases player controls
     */
    public String Train(){
        player.setUnits(player.getUnits() + player.getBases());
        return "Train; +" + player.getBases() + 
            ((player.getBases() > 1) ? " units; " : " unit; ") + player.getUnits() + 
            ((player.getUnits() != 1) ? " unit" : " unit");
    }
    /* Expands number of player bases if any available
     */
    public String Expand(){
        int[] bases = player.getGame().getBases();
        ArrayList<Integer> available = new ArrayList<>();
        for(int i = 0; i < bases.length; i++){
            if(bases[i] == 0){
                available.add(i);
            }
        }

        if(!CheckAvailability()){
            return "Expand; No available bases";
        }
        else{
            player.incrementBases();
            player.getGame().setBase(available.get(0), player.getPlayerNumber());
            return "Expand; +1 base; (" + player.getGame().getBaseString(available.get(0)) + ")";
        }

    }
    
    public Boolean CheckAvailability(){
        int[] bases = player.getGame().getBases();
        ArrayList<Integer> available = new ArrayList<>();
        for(int i = 0; i < bases.length; i++){
            if(bases[i] == 0){
                available.add(i);
            }
        }
        return !available.isEmpty();
    }

    /* getter/setter */
    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
