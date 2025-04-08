import java.util.Random;

public class Team {
    
    private TeamMember dps;
    private TeamMember tank;
    private TeamMember support;

    private String name;

    public Team (String name, TeamMember dps, TeamMember tank, TeamMember support){
        this.name = name;
        this.dps = dps;
        this.tank = tank;
        this.support = support;
    }
    
    public Team (String name, Random random) {
        this.name = name;
        generateRandomTeam(random);
    }

    public Team(String name) {
        this(name, new Random());
    }

    public void generateRandomTeam(Random randomizer) {
        dps = new TeamMember(randomizer);
        tank = new TeamMember(randomizer);
        support = new TeamMember(randomizer);
    }
    
    //gets the related proficiency of each team member.
    public int getDpsProficiency() {
        return dps.getDpsProf();
    }

    public int getTankProficiency() {
        return tank.getTankProf();
    }

    public int getSupportProficiency() {
        return support.getSupportProf();
    }

    public TeamMember getDps() {
        return dps;
    }

    public TeamMember getTank() {
        return tank;
    }

    public TeamMember getSupport() {
        return support;
    }

    public String getName(){
        return name;
    }

    public String toString() {
        return String.format(
            "TEAM: %s \n\tDPS: %s\n\tTANK: %s\n\tSUPPORT: %s", 
            this.getName(), 
            this.getDps(), 
            this.getTank(), 
            this.getSupport()
        );
    }
}
