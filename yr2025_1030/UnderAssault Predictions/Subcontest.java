import java.awt.Dimension;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Subcontest extends JPanel {
    private Team winner;
    private double winChance;
    private String winChanceTeam;
    private String team1Name;
    private String team2Name;
    private Team team1;
    private Team team2;
    
    public Subcontest(int p1, int p2, String pt1, String pt2, Team t1, Team t2) {
        team1 = t1;
        team2 = t2;
        team1Name = team1.getName();
        team2Name = team2.getName();

        Random rand = new Random();
        if(p1 > p2) {
            winChanceTeam = team1Name;
            winChance = p1 / (p1 + p2);
            if(rand.nextDouble() < winChance) {
                winner = team1;
            }
            else {
                winner = team2;
            }
        }
        else {
            double winChance = p2 / (p2 + p1);
            if(rand.nextDouble() < winChance) {
                winner = team2;
            }
            else {
                winner = team1;
            }
        }

        JLabel p1Label = new JLabel(team1Name + " " + pt1 + " Proficiency: " + p1);
        JLabel p2Label = new JLabel(team2Name + " " + pt2 + " Proficiency: " + p2);
        JLabel winChanceLabel = new JLabel("Chance " + winChanceTeam + " Wins: " + ((int) winChance * 100));
        JLabel winLabel = new JLabel(winner.getName() + " Wins! ");
        p1Label.setPreferredSize(new Dimension(300, 30));
        p2Label.setPreferredSize(new Dimension(300, 30));
        winChanceLabel.setPreferredSize(new Dimension(300, 30));
        winLabel.setPreferredSize(new Dimension(300, 30));
        add(p1Label);
        add(p2Label);
        add(winChanceLabel);
        add(winLabel);

        setPreferredSize(new Dimension(300, 200));
    }

    public Team getWinner() {
        return this.winner;
    }
}
