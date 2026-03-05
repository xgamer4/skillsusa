/**
 * A simple class which just keeps track of occupation of a base
 */
public class Base {
    //What a base is occupied by
    public enum occupier {
        PLAYER,
        OPPONENT,
        UNCLAIMED
    }

    private occupier occupiedBy;
    public int number;

    /**
     * Initaites a base 
     */
    public Base(){
        occupiedBy = occupier.UNCLAIMED;
    }

    /**
     * Initiates a base with how it is occupied
     * @param startOutOccupied how it is occupied
     */
    public Base(occupier startOutOccupied){
        occupiedBy = startOutOccupied;
    }

    /**
     * Changes the base occupation
     * @param newOccupier see name
     */
    public void setOccupation(occupier newOccupier){
        occupiedBy = newOccupier;
    }

    /**
     * checks the base occupation
     * @return gives back the base occupation
     */
    public occupier getOccupation(){return occupiedBy;}
}
