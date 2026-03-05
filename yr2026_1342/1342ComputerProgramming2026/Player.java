public class Player {
    public String team;
    public int units;
    public int bases;

    public Player(String team)
    {
        this.team = team;
        this.units = 2;
        this.bases = 1;
    }

    // Getter that returns either "Player" or "Enemy."
    public String getTeam()
    {
        return this.team;
    }

    public int getUnits()
    {
        return this.units;
    }

    public int getBases()
    {
        return this.bases;
    }
}