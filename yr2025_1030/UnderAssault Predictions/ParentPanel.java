import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParentPanel extends JPanel {
    private PredictionPanel ppanel;
    private MatchPanel mpanel;
    private ResultsPanel rpanel;
    private Panel[] panelRotation;
    private Panel currentPanel;
    private int currentRotation;

    private JButton next;

    public ParentPanel() {
        setPreferredSize(new Dimension(1200, 1000));
        
        panelRotation = new Panel[3];
        panelRotation[0] = ppanel = new PredictionPanel();
        panelRotation[1] = mpanel = new MatchPanel(ppanel.getTeams());
        panelRotation[2] = rpanel = new ResultsPanel();
        currentRotation = -1;
        currentPanel = new Panel();

        JLabel title = new JLabel("UNDERASSAULT PREDICTIONS");
        title.setFont(new Font("Sanserif", Font.PLAIN, 72));
        currentPanel.add(title);

        next = new JButton("Start");

        currentPanel.setPreferredSize(new Dimension(1200, 400));
        next.setPreferredSize(new Dimension(200, 50));
        next.addActionListener(new NextListener());
        
        add(currentPanel);
        add(next);
    }

    private void rotatePanels() {
        next.setText("Next");
        System.out.println("AAA" + currentRotation);
        System.out.println("0");
        if(currentPanel.rotateActions(this)) {
            System.out.println("2");
            remove(currentPanel);
            remove(next);
            currentRotation++;
            currentPanel = panelRotation[currentRotation % 3];
            currentPanel.setPreferredSize(new Dimension(1200, 900));
            add(currentPanel);
            add(next);
            repaint();
            revalidate();
        }
        if(currentPanel == ppanel && currentRotation > 0) {
            System.out.println("1");
            currentRotation = 0;
            panelRotation[0] = ppanel = new PredictionPanel();
            panelRotation[1] = mpanel = new MatchPanel(ppanel.getTeams());
            panelRotation[2] = rpanel = new ResultsPanel();
        }
    }

    public PredictionPanel getPPanel() {
        return ppanel;
    }

    public MatchPanel getMPanel() {
        return mpanel;
    }

    public ResultsPanel getRPanel() {
        return rpanel;
    }

    public JButton getNextButton() {
        return this.next;
    }

    public class NextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rotatePanels();
        }

    }

}
