import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BracketPanel extends JPanel {
    private Team team1, team2;
    
    public BracketPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        setPreferredSize(new Dimension(1200, 200));
    }
    
    public BracketPanel(Team team1, Team team2) {
        this();
        this.team1 = team1;
        this.team2 = team2;
        add(team1);
        add(team2);
    }

    public void setTeam1(Team team1) {
        if(this.team1 != null) {
            remove(this.team1);
        }
        this.team1 = team1;
        add(this.team1);
        if(this.team2 != null) {
            remove(this.team2);
            add(this.team2);
        }
    }

    public void setTeam2(Team team2) {
        if(this.team2 != null) {
            remove(this.team2);
        }
        this.team2 = team2;
        add(this.team2);
    }

    public Team getTeam1() {
        return this.team1;
    }

    public Team getTeam2() {
        return this.team2;
    }
}
