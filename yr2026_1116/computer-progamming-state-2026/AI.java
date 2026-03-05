/**
 * A methodical AI to beat opponents in OceanDestruction 2
 * @Author Ghammond
 */
public class AI {
    private Player robot;
    private Player player;
    private Game game;
    /**
     * Instantiates a simple object to choose actions
     * @param ai Itself as a player
     * @param opponent you
     * @param game the game for game info
     */
    public AI(Player ai, Player opponent, Game game){
        robot = ai;
        player = opponent;
        this.game = game;
    }

    /**
     * I decides whether or not it is safer to attack or not.
     * @return the action it takes
     */
    public Game.Actions attemptMove(){
        if(game.getOpenBase() != null){
            return Game.Actions.EXPAND;
        }else if((player.getUnits() <= robot.getUnits() || robot.getUnits() >= 4) && robot.getUnits() > 1){
            return Game.Actions.ATTACK;
        }else{
            return Game.Actions.TRAIN;
        }      
    }
}
