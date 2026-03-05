import java.util.Random;

public class OpponentController extends Player {
    private Random rand = new Random();

    public OpponentController(Game game){
        super(game, new ActionManager());
        super.playerNumber = 2;
    }

    public String getAction(){
        if(actionManager.getPlayer() == null){
            actionManager.setPlayer(this);
        }

        //get decision
        int action = rand.nextInt(20);
        if(action < 10){
            return actionManager.Train();
        }
        else if(action < 16){
            return (actionManager.CheckAvailability()) ? actionManager.Expand() : getAction();
        }
        else{
            return (units > 0) ? actionManager.Attack() : getAction();
        }
    }
}
