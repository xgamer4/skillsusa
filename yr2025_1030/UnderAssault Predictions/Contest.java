import java.awt.Dimension;

import javax.swing.JPanel;

public class Contest extends JPanel {
    private Team team1, team2;
    
    public Contest(Team t1, Team t2) {
        team1 = t1;
        team2 = t2;

        setPreferredSize(new Dimension(1200, 900));
    }

    public Team runContest() {
        JPanel panel = new JPanel();
        int player1Wins = 0;
        int player2Wins = 0;
        int p1;
        int p2;
    
        p1 = team1.getPlayer(0)[0];
        p2 = team2.getPlayer(1)[1];
        Subcontest sub1 = new Subcontest(p1, p2, "DPS", "Tank", team1, team2);
        if(sub1.getWinner() == team1) {
            player1Wins++;
        }
        else {
            player2Wins++;
        }
        panel.add(sub1);

        p1 = team1.getPlayer(1)[1];
        p2 = team2.getPlayer(0)[0];
        Subcontest sub2 = new Subcontest(p1, p2, "Tank", "DPS", team1, team2);
        if(sub2.getWinner() == team1) {
            player1Wins++;
        }
        else {
            player2Wins++;
        }
        panel.add(sub2);

        p1 = team1.getPlayer(2)[2];
        p2 = team2.getPlayer(2)[2];
        Subcontest sub3 = new Subcontest(p1, p2, "Support", "Support", team1, team2);
        if(sub3.getWinner() == team1) {
            player1Wins++;
        }
        else {
            player2Wins++;
        }
        panel.add(sub3);

        panel.setPreferredSize(new Dimension(1200, 150));
        add(panel);

        return player1Wins > player2Wins ? team1 : team2;
    }
}
