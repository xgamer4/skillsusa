public class Team {
    // Team members for each role
    public TeamMember dps;
    public TeamMember tank;
    public TeamMember support;

    public String teamName;

    /**
     * Constructor forcing the creator of the team to have a team name set
     * @param teamName the name of the team to be stored
     */
    public Team(String teamName) {
        this.teamName = teamName;
    }

    /**
     * toString formats the Team class in a way that is easily readable for the user of the bracket simulator.
     * @return a formatted string representing a team
     */
    @Override
    public String toString() {
        return "Team " + teamName + "\n  DPS: " + dps.toString() + "\n  Tank: " + tank.toString() + "\n  Support: " + support.toString();
    }
}
