/**
 * A simple player class for OceanDestruction 2
 * @Author Ghammond
 */
public class Player {
    protected int units;
    protected int bases;

    /**
     * A constructor with variable start amounts
     * @param units
     * @param bases
     */
    public Player(int units, int bases ){
        this.units = units;
        this.bases = bases;
    }

    /**
     * A default constructor for normal game us
     */
    public Player(){
        units = 2;
        bases = 1;
    }

    /**
     * returns the number of units a player has
     * @return the number of units the player has
     */
    public int getUnits() {return units;}

    /**
     * returns the number of bases a player has
     * @return the bases the player has
     */
    public int getBases() {return bases;}

    /**
     * Sets the number of units
     * @param newUnits how many units to set it to
     */
    public void setUnits(int newUnits) {units = newUnits;}

    /**
     * Sets the number of Bases
     * @param newBases how many bases to set it to
     */
    public void setBases(int newBases) {bases = newBases;}

    public boolean lost(){
        return units == 0 && bases == 0;
    }
}
