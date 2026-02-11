public class MatchPanel extends Panel {
    private Contest[] contests;
    private Team[] teams;
    private int currentContest;
    private Team[] winnersArray;

    public MatchPanel(Team[] ts) {
        contests = new Contest[3];
        teams = ts;
        winnersArray = new Team[3];
        
        currentContest = 0;
        runContest(currentContest);
    }

    public Team runContest(int num) {
        Team contestWinner;
        if(num < 2) {
            contests[num] = new Contest(teams[num * 2], teams[num * 2 + 1]);
        }
        else {
            contests[num] = new Contest(winnersArray[0], winnersArray[1]);
        }
        Team contest1 = contests[num].runContest();
        Team contest2 = contests[num].runContest();
        if(contest1 == contest2) {
            contestWinner = contest1;
        }
        else {
            contestWinner = contests[num].runContest();
        }
        currentContest++;
        winnersArray[num] = contestWinner;
        add(contests[num]);
        repaint();
        revalidate();
        return contestWinner;
    }

    public Team[] getWinners() {
        return winnersArray;
    }

    @Override
    public boolean rotateActions(ParentPanel parentPanel) {
        if(currentContest < 3) {
            remove(contests[currentContest-1]);
            runContest(currentContest);
            return false;
        }
        else {
            for(Team team : winnersArray) {
                System.out.println(team.getName());
            }
            for(Team team : parentPanel.getPPanel().getSelectedTeams()) {
                System.out.println(team.getName());
            }
            parentPanel.getNextButton().setText("Play Again");
            parentPanel.getRPanel().setupResults(winnersArray, parentPanel.getPPanel().getSelectedTeams());
            return true;
        }
    }
    
}
