import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultsPanel extends Panel {

    public ResultsPanel() {

        setPreferredSize(new Dimension(1000, 800));
    }

    public void setupResults(Team[] winners, Team[] selectedWinners) {
        for(int i = 0; i < 3; i++) {
            Team winner = winners[i];
            Team selected = selectedWinners[i];
            JPanel panel = new JPanel();
            JLabel winnerLabel = new JLabel(winner.getName());
            JLabel selectedLabel = new JLabel(selected.getName());

            panel.setPreferredSize(new Dimension(1000, 150));

            panel.add(winnerLabel);
            panel.add(selectedLabel);
            add(panel);
        }
    }

    @Override
    public boolean rotateActions(ParentPanel parentPanel) {
        return true;
    }
    
}
