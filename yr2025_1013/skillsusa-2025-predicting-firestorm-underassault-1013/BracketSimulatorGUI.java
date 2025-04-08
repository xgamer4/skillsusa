import java.awt.*;
import javax.swing.*;

public class BracketSimulatorGUI {

    public static JFrame window;
    private static JPanel contentsPane;

    /**
     * Initialize the inner contents of the main Swing window.
     * @param c the container to add the contents to
     */
    private static void initContents(Container c) {
        c.setLayout(new BorderLayout());

        // Create header for the window
        JLabel headerLabel = new JLabel("Brackets and Results");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        c.add(headerLabel, BorderLayout.NORTH);

        // Create main contents pane
        contentsPane = new JPanel();
        contentsPane.setLayout(new BoxLayout(contentsPane, BoxLayout.Y_AXIS));
        contentsPane.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        c.add(contentsPane, BorderLayout.CENTER);

        window.revalidate();
        window.repaint();
    }

    /**
     * userPrediction allows for a graphical environment to predict which team they think will win.
     * @param team1 the first team in the bracket
     * @param team2 the second team in the bracket
     * @param winner which team won the bracket
     */
    private static void userPrediction(Team team1, Team team2, Team winner) {
        String[] teams = new String[] {team1.teamName, team2.teamName};

        // Get the user's choice for who they think will win
        int userSelection = JOptionPane.CLOSED_OPTION;
        do {
            userSelection = JOptionPane.showOptionDialog(window,
                    "Bracket for teams: " + team1.teamName + " vs " + team2.teamName + ".\n" + team1 + "\n" + team2,
                    "Bracket prediction", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, teams, teams[0]);
        } while (userSelection == JOptionPane.CLOSED_OPTION);

        // Congratulate if the user is correct, else console
        if (teams[userSelection].equals(winner.teamName)) {
            JOptionPane.showMessageDialog(window, "Correct! You got this bracket correctly.", "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(window, "Sorry, you got this incorrect. The correct team was Team " + winner.teamName + "." , "Incorrect Guess", JOptionPane.ERROR_MESSAGE);
        }

        // Determine the likelihood of the selected team winning
        double winMargin = 999;
        if (userSelection == 0) {
            winMargin = Math.abs(BracketSimulator.subContestEquation(team1, team2) - 0.5);
        } else {
            winMargin = Math.abs(BracketSimulator.subContestEquation(team2, team1) - 0.5);
        }

        Team selectedTeam = userSelection == 0 ? team1 : team2;

        String likelihoodMessage;
        if (winMargin <= 0.15) {
            likelihoodMessage = "This team is weak, the likelihood of team " + selectedTeam.teamName + " winning is low.";
        } else if (winMargin <= 0.3) {
            likelihoodMessage = "This team was okay, the likelihood of " + selectedTeam.teamName + " winning later is definitely possible.";
        } else {
            likelihoodMessage = "It was pretty likely that there was no randomness affecting team " + selectedTeam.teamName + " winning.";
        }

        // Display to the user the likelihood
        JOptionPane.showMessageDialog(window, likelihoodMessage, "Likelihood Analysis", JOptionPane.INFORMATION_MESSAGE);

        // Create a representation of the bracket to show on the screen
        BracketInfo bracketInfo = new BracketInfo(team1, team2, winner, teams[userSelection].equals(winner.teamName));
        contentsPane.add(bracketInfo.getGUIRepresentation());
        contentsPane.revalidate();
        contentsPane.repaint();
    }

    /**
     * main is the entry point of the program.
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        // Create the window to display to the user
        window = new JFrame("Firestorm UnderAssault Bracket Simulator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(650, 200);
        window.setVisible(true);

        initContents(window.getContentPane());

        // Generate teams
        Team teamFuel = BracketSimulator.generateRandomTeam("Fuel"),
                teamOutlaws = BracketSimulator.generateRandomTeam("Outlaws"),
                teamShock = BracketSimulator.generateRandomTeam("Shock"),
                teamDynasty = BracketSimulator.generateRandomTeam("Dynasty");

        // Determine winners of the first two brackets
        boolean fuelVsOutlaws = BracketSimulator.canTeamWin(teamFuel, teamOutlaws);
        boolean shockVsDynasty = BracketSimulator.canTeamWin(teamShock, teamDynasty);

        Team team1 = fuelVsOutlaws ? teamFuel : teamOutlaws;
        Team team2 = shockVsDynasty ? teamShock : teamDynasty;

        // Final bracket
        boolean finalBracket = BracketSimulator.canTeamWin(team1, team2);
        Team finalWinner = finalBracket ? team1 : team2;

        // Start the user prediction stage
        userPrediction(teamFuel, teamOutlaws, team1);
        userPrediction(teamShock, teamDynasty, team2);
        userPrediction(team1, team2, finalWinner);
    }

}

class BracketInfo {
    private Team team1;
    private Team team2;
    private Team winner;

    private boolean correct;

    /**
     * Constructor requiring team values to ensure information is provided.
     * @param team1 the first time in the bracket
     * @param team2 the second team in the bracket
     * @param winner the winner between the two teams
     */
    public BracketInfo(Team team1, Team team2, Team winner, boolean correct) {
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.correct = correct;
    }

    /**
     * getGUIRepresentation creates a JPanel that has extended functionality, showing the general bracket info like the
     * competing teams and who won, as well as having a more info button that displays a dialog showing the stats of the
     * individual teams.
     * @return a JPanel with helpful team information
     */
    public JPanel getGUIRepresentation() {
        JPanel bracketInfoItem = new JPanel();
        bracketInfoItem.setLayout(new BoxLayout(bracketInfoItem, BoxLayout.X_AXIS));

        // Display the teams information
        bracketInfoItem.add(new JLabel("Team " + team1.teamName + " vs Team " + team2.teamName + ": "));
        bracketInfoItem.add(new JLabel("Team " + winner.teamName));

        // Add an info button that displays more context regarding the teams
        JButton moreInfoButton = new JButton("Info");
        moreInfoButton.addActionListener(e -> {
            // Show a dialog showing the stats of the teams too
            String infoText = team1 + "\n" + team2 + "\n\n" + (this.correct ? "You predicted this correctly." : "You did not predict correctly.");
            JOptionPane.showMessageDialog(BracketSimulatorGUI.window, infoText, "More Info", JOptionPane.INFORMATION_MESSAGE);
        });
        bracketInfoItem.add(moreInfoButton);

        bracketInfoItem.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        return bracketInfoItem;
    }
}