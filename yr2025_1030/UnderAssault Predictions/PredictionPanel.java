import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PredictionPanel extends Panel {
    private Team[] teams;
    private Team[] selectedTeams;
    private String[] teamNames = {"Fuel", "Outlaws", "Shock", "Dynasty"};
    private BracketPanel bracket1Panel, bracket2Panel, bracket3Panel;
    
    public PredictionPanel() {
        teams = new Team[4];
        selectedTeams = new Team[3];
        for(int i = 0; i < teams.length; i++) {
            teams[i] = new Team(teamNames[i], new SelectListener());
        }

        bracket1Panel = new BracketPanel(teams[0], teams[1]);
        bracket2Panel = new BracketPanel(teams[2], teams[3]);
        bracket3Panel = new BracketPanel();

        JLabel title = new JLabel("Select Winners");
        title.setFont(new Font("Sanserif", Font.PLAIN, 36));
        title.setPreferredSize(new Dimension(1200, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        JLabel bracketTitle1 = new JLabel("Bracket 1");
        bracketTitle1.setFont(new Font("Sanserif", Font.PLAIN, 18));
        JLabel bracketTitle2 = new JLabel("Bracket 2");
        bracketTitle2.setFont(new Font("Sanserif", Font.PLAIN, 18));
        JLabel bracketTitle3 = new JLabel("Bracket 3");
        bracketTitle3.setFont(new Font("Sanserif", Font.PLAIN, 18));

        add(title);
        add(bracketTitle1);
        add(bracket1Panel);
        add(bracketTitle2);
        add(bracket2Panel);
        add(bracketTitle3);
        add(bracket3Panel);
    }

    public Team[] getTeams() {
        return teams;
    }
    
    public Team[] getSelectedTeams() {
        return selectedTeams;
    }

    @Override
    public boolean rotateActions(ParentPanel parentPanel) {
        for(Team team : selectedTeams) {
            if(team == null) {
                return false;
            }
        }
        return true;
    }
    
    public class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton select = (JButton) e.getSource();
            Team[] bracketTeams = {bracket1Panel.getTeam1(), bracket1Panel.getTeam2(), bracket2Panel.getTeam1(), bracket2Panel.getTeam2(), bracket3Panel.getTeam1(), bracket3Panel.getTeam2()};
            for(int i = 0; i < bracketTeams.length; i++) {
                Team team = bracketTeams[i];
                if(team != null && team.getSelectButton() == select) {
                    Team newTeam = new Team(team.getName(), team.getPlayers(), new SelectListener());
                    if(i < 2) {
                        teams[0].setBackground(null);
                        teams[1].setBackground(null);
                        selectedTeams[0] = team;
                        bracket3Panel.setTeam1(newTeam);
                        if(bracket3Panel.getTeam2() != selectedTeams[2]) {
                            selectedTeams[2] = null;
                        }
                    }
                    else if(i < 4){
                        teams[2].setBackground(null);
                        teams[3].setBackground(null);
                        selectedTeams[1] = team;
                        bracket3Panel.setTeam2(newTeam);
                        if(bracket3Panel.getTeam1() != selectedTeams[2]) {
                            selectedTeams[2] = null;
                        }
                    }
                    else {
                        if(bracketTeams[4] != null) {
                            bracketTeams[4].setBackground(null);
                        }
                        if(bracketTeams[5] != null) {
                            bracketTeams[5].setBackground(null);
                        }
                        selectedTeams[2] = team;
                    }
                    team.setBackground(Color.GREEN);
                    repaint();
                    revalidate();
                    break;
                }
            }
        }
    }
}
