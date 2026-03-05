public class Player{
    protected ActionManager actionManager;
    protected Game game;
    protected int units = 2;
    protected int bases = 1;
    protected int playerNumber = 0;

    public Player(Game game, ActionManager actionManager){
        this.game = game;
        this.actionManager = actionManager;
    }    

    /* bases increment/decrement */
    public void incrementBases(){
        bases++;
    }

    public void decrementBases(){
        bases--;
    }

    /* getter/setter */
    public Game getGame(){
        return game;
    }

    public int getBases(){
        return bases;
    }

    public int getUnits(){
        return units;
    }

    public void setUnits(int units){
        this.units = units;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
