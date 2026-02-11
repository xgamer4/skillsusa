import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Team extends JPanel {
    private int[][] players;
    private String name;

    private JButton select;

    public Team(String name, int[][] players, PredictionPanel.SelectListener selectListener) {
        this.name = name;
        this.players = players;
        
        setupVisual(selectListener);
    }
    
    public Team(String name, PredictionPanel.SelectListener selectListener) {
        this.name = name;
        players = new int[3][3];

        Random rand = new Random();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                players[i][j] = rand.nextInt(101);
            }
        }

        int[][] sortedPlayers = new int[3][3];
        ArrayList<Integer> usedPlayers = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            int highestPlayerIndex = -1;
            int[] highestPlayer = {-1, -1, -1};
            for(int j = 0; j < 3; j++) {
                if(highestPlayer[i] < players[j][i] && !usedPlayers.contains(j)) {
                    highestPlayer = players[j];
                    highestPlayerIndex = j;
                }
            }
            sortedPlayers[i] = highestPlayer;
            usedPlayers.add(highestPlayerIndex);
        }
        players = sortedPlayers;

        setupVisual(selectListener);
    }

    public void setupVisual(PredictionPanel.SelectListener selectListener) {
        JLabel[] playerLabels = new JLabel[4];
        playerLabels[0] = new JLabel(name);
        playerLabels[0].setFont(new Font("sanserif", Font.PLAIN, 18));
        playerLabels[0].setPreferredSize(new Dimension(500, 32));
        playerLabels[0].setHorizontalAlignment(JLabel.CENTER);

        String[] profLabelStrings = {"DPS", "TANK", "SUPPORT"};
        for(int i = 0; i < 3; i++) {
            String playerLabelString = profLabelStrings[i] + " PLAYER: ";
            for(int j = 0; j < 3; j++) {
                playerLabelString += profLabelStrings[j].substring(0, 1) + profLabelStrings[j].substring(1).toLowerCase() + " Proficiency: " + players[i][j] + (j != 2 ? ", " : "");
            }
            playerLabels[i+1] = new JLabel(playerLabelString);
        }

        setPreferredSize(new Dimension(500, 150));
        select = new JButton("Select as Bracket Winner");
        select.addActionListener(selectListener);

        for(JLabel label : playerLabels) {
            add(label);
        }
        add(select);
    }

    public int[] getPlayer(int player) {
        return players[player];
    }

    public int[][] getPlayers() {
        return this.players;
    }

    public String getName() {
        return this.name;
    }

    public JButton getSelectButton() {
        return this.select;
    }

}
