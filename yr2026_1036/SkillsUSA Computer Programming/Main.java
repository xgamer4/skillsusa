import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JPanel{
    Base[] bases = new Base[4];
    OverLay overlay;
    int round;
    boolean playing;
    int player1units;
    int player2units;
    int player2Action;
    Random rand = new Random();
    int winner = -1;
    Point[] basePos = new Point[4];
    int turn = 0;
    Timer timer = new Timer();
    int attackOutcome = 0;
    public Main(){
        this.setPreferredSize(new Dimension(500, 500));
        overlay = new OverLay();
        this.add(overlay);
        this.addMouseListener(new Click());
        playing = false;
    }
    public void startGame(){
        for (int i = 0; i < 4; i++) {
            bases[i] = new Base();
            basePos[i] = new Point(rand.nextInt(10) - 5, rand.nextInt(10) - 5);
        }
        bases[0].setTeam(1);
        bases[3].setTeam(2);
        player1units = 2;
        player2units = 2;
        round = 1;
        winner = -1;
        turn = 1;
    }
    public void checkEnd(){
        int baseCount1 = 0;
        int baseCount2 = 0;
        for (Base base : bases) {
            if(base.getTeam() == 1){
                baseCount1++;
            }
            else if(base.getTeam() == 2){
                baseCount2++;
            }
        }
        if(player1units == 0 && baseCount1 == 0){
            endGame();
        }
        else if(player2units == 0 && baseCount2 == 0){
            endGame();
        }
    }
    public void endGame(){
        if(player1units > player2units){
            winner = 1;
        }
        else if(player2units > player1units){
            winner = 2;
        }
        else{
            int baseCount1 = 0;
            int baseCount2 = 0;
            for (Base base : bases) {
                if(base.getTeam() == 1){
                    baseCount1++;
                }
                else if(base.getTeam() == 2){
                    baseCount2++;
                }
            }
            if(baseCount1 > baseCount2){
                winner = 1;
            }
            else if(baseCount2 > baseCount1){
                winner = 2;
            }
            else{
                winner = 0;
            }
        }
        playing = false;
        overlay.repaint();
    }
    public boolean hasUnclaimedBases(){
        for (Base base : bases) {
            if(base.getTeam() == 0){
                return true;
            }
        }
        return false;
    }
    public void attack(int attacker, Base base){
        int attackerUnits;
        int defenderUnits;
        if(attacker == 1){
            attackerUnits = Math.min(3, player1units);
            defenderUnits = Math.min(2, player2units);
        }
        else{
            attackerUnits = Math.min(3, player2units);
            defenderUnits = Math.min(2, player1units);
        }
        int[] attackerRolls = new int[attackerUnits];
        int[] defenderRolls = new int[defenderUnits];
        for(int i = 0; i < attackerUnits; i++){
            attackerRolls[i] = rand.nextInt(6) + 1;
        }
        for(int i = 0; i < defenderUnits; i++){
            defenderRolls[i] = rand.nextInt(6) + 1;
        }
        attackerRolls = sort(attackerRolls);
        defenderRolls = sort(defenderRolls);
        int attackerLoses = 0;
        int defenderLoses = 0;
        while(attackerLoses < attackerUnits && defenderLoses < defenderUnits){
            for(int i = 0; i < Math.min(attackerUnits - attackerLoses, defenderUnits - defenderLoses); i++){
                if(attackerRolls[i + attackerLoses] > defenderRolls[i + defenderLoses]){
                    defenderLoses++;
                }
                else{
                    attackerLoses++;
                }
            }

        }
        if(attackerLoses > defenderLoses){
            if(attacker == 1){
                player1units -= attackerLoses;
                attackOutcome = 2;
            }
            else{
                player2units -= attackerLoses;
                attackOutcome = 1;
            }
        }
        else if(defenderLoses > attackerLoses){
            if(attacker == 1){
                player2units -= defenderLoses;
                for(int i = 0; i < 4; i++){
                    if(bases[i].getTeam() == 2){
                        bases[i].setTeam(1);
                        break;
                    }
                }
                attackOutcome = 1;
            }
            else{
                player1units -= defenderLoses;
                for(int i = 3; i >= 0; i--){
                    if(bases[i].getTeam() == 1){
                        bases[i].setTeam(2);
                        break;
                    }
                }
                attackOutcome = 2;
            }
        }
    }
    public int[] sort(int[] array){
        int[] sortedArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            int highestIndex = 0;
            for(int j = 0; j < array.length; j++){
                if(array[j] > array[highestIndex]){
                    highestIndex = j;
                }
            }
            sortedArray[i] = array[highestIndex];
            array[highestIndex] = -1;
        }
        return sortedArray;
    }
    public class OverLay extends JComponent {
        public OverLay() {
            this.setPreferredSize(new Dimension(500, 500));
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setFont(new Font("Arial", 1, 20));
            
            if(playing){
                for(int i = 0; i < 4; i++){
                    switch(bases[i].getTeam()){
                        case 0:
                            g.setColor(Color.GRAY);
                            break;
                        case 1:
                            g.setColor(Color.BLUE);
                            break;
                        case 2:
                            g.setColor(Color.RED);
                            break;
                    }
                    int XBase = 135*i+10 + (int)basePos[i].getX();
                    int YBase = 100 + (int)basePos[i].getY();
                    int[] Xpolygon = {XBase + 5, XBase + 10, XBase + 3, XBase + 3, XBase + 18, XBase + 18, XBase + 30, XBase + 30, XBase + 44, XBase + 44, XBase + 56, XBase + 56, XBase + 72, XBase + 72, XBase + 65, XBase + 70};
                    int[] Ypolygon = {YBase + 75, YBase + 55, YBase + 45, YBase + 25, YBase + 25, YBase + 35, YBase + 35, YBase + 25, YBase + 25, YBase + 35, YBase + 35, YBase + 25, YBase + 25, YBase + 45, YBase + 55, YBase + 75};
                    g.fillPolygon(Xpolygon, Ypolygon, 16);
                    g.setColor(Color.BLACK);
                    g.drawPolygon(Xpolygon, Ypolygon, 16);
                    g.fillRect(XBase + 30, YBase + 60, 15, 15);
                    g.fillOval(XBase + 30, YBase + 55, 15, 10);
                    g.fillRect(XBase + 20, YBase + 45, 8, 10);
                    g.fillOval(XBase + 20, YBase + 43, 8, 5);
                    g.fillRect(XBase + 47, YBase + 45, 8, 10);
                    g.fillOval(XBase + 47, YBase + 43, 8, 5);
                }
                g.drawLine(0, 300, 500, 300);
                g.drawRect(20, 310, 100, 180);
                g.drawRect(140, 310, 100, 180);
                if(!hasUnclaimedBases()){
                    g.setColor(Color.GRAY);
                    g.fillRect(260, 310, 100, 180);
                    g.setColor(Color.BLACK);
                }
                g.drawRect(260, 310, 100, 180);
                g.drawRect(380, 310, 100, 180);
                g.drawString("Train", 46, 400);
                g.drawString("Attack", 160, 400);
                g.drawString("Expand", 274, 400);
                g.drawString("Quit", 410, 400);
                g.setColor(Color.BLUE);
                g.drawString("Player 1 units: " + player1units, 10, 250);
                g.setColor(Color.RED);
                g.drawString("Player 2 units: " + player2units, 340, 250);
                switch (player2Action) {
                    case 1:
                        g.drawString("Player 2 Trains!", 175, 75);
                        break;
                    case 2:
                        g.drawString("Player 2 Expands!", 160, 75);
                        break;
                    case 3:
                        g.drawString("Player 2 Attacks!", 175, 75);
                        break;
                }
                if(attackOutcome == 1){
                    g.setColor(Color.BLUE);
                    g.drawString("Player 1 won the attack!", 135, 25);
                }
                else if(attackOutcome == 2){
                    g.setColor(Color.RED);
                    g.drawString("Player 2 won the attack!", 135, 25);
                }
                attackOutcome = 0;
            }
            else{
                g.drawString("OceanDestruction 2", 150, 150);
                switch (winner) {
                    case 0:
                        g.drawString("It's a Tie!", 195, 50);
                        break;
                    case 1:
                        g.setColor(Color.BLUE);
                        g.drawString("Player 1 wins!", 175, 50);
                        g.setColor(Color.BLACK);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        g.drawString("Player 2 wins!", 175, 50);
                        g.setColor(Color.BLACK);
                        break;
                }
                g.drawLine(0, 300, 500, 300);
                g.drawRect(20, 310, 460, 180);
                g.drawString("Play", 230, 400);
            }
        }
    }
    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getY() > 300){
                if(playing){
                    if(turn == 1){
                        if(e.getX() < 120){
                            for(Base base : bases){
                                if(base.getTeam() == 1){
                                    player1units++;
                                }
                            }
                            checkEnd();
                            turn = 2;
                            timer.schedule(new enemyTurn(), 1000);
                        }
                        else if(e.getX() < 240){
                            for(int i = 0; i < 4; i++){
                                if(bases[i].getTeam() == 2){
                                    attack(1, bases[i]);
                                    break;
                                }
                            }
                            checkEnd();
                            turn = 2;
                            timer.schedule(new enemyTurn(), 1000);
                        }
                        else if(e.getX() < 360){
                            if(hasUnclaimedBases()){
                                for(int i = 0; i < 4; i++){
                                    if(bases[i].getTeam() == 0){
                                        bases[i].setTeam(1);
                                        break;
                                    }
                                }
                                checkEnd();
                                turn = 2;
                                timer.schedule(new enemyTurn(), 1000);
                            }
                        }
                        else if(e.getX() < 480){
                            playing = false;
                        }
                    }
                    else{
                        if(e.getX() > 370){
                            playing = false;
                        }
                    }
                }
                else{
                    startGame();
                    playing = true;
                }
                overlay.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
    public class enemyTurn extends TimerTask{

        @Override
        public void run() {
            if(playing && turn == 2){
                if(player1units > player2units){        // checks if the player has more units
                    if(hasUnclaimedBases()){        // checks if there are unclaimed bases on the board
                        if(rand.nextBoolean()){         //randomly decides between Expanding and Training
                            for(int i = 3; i >= 0; i--){
                                if(bases[i].getTeam() == 0){
                                    bases[i].setTeam(2);
                                    break;
                                }
                            }
                            player2Action = 2;
                        }
                        else{
                            for(Base base : bases){
                                if(base.getTeam() == 2){
                                    player2units++;
                                }
                            }
                            player2Action = 1;
                        }
                    }
                    else{       // if there are no unclaimed bases, the AI trains
                        for(Base base : bases){
                            if(base.getTeam() == 2){
                                player2units++;
                            }
                        }
                        player2Action = 1;
                    }
                }
                else{       // if the AI as equal or greater units to the player
                    if(hasUnclaimedBases()){        // checks if there are unclaimed bases
                        for(int i = 3; i >= 0; i--){        //randomly decides between expanding and attacking
                            if(bases[i].getTeam() == 0){
                                bases[i].setTeam(2);
                                break;
                            }
                        }
                        player2Action = 2;
                    }
                    else{       // if there are no unclaimed bases, the AI attacks
                        for(int i = 3; i >= 0; i--){
                            if(bases[i].getTeam() == 1){
                                attack(2, bases[i]);
                                break;
                            }
                        }
                        player2Action = 3;
                    }
                }
                round++;
                if(round == 6){         // once the fifth round ends, end the game
                    endGame();
                }
                else{                   //otherwise, checks if either player has lost
                    checkEnd();
                    turn = 1;
                }
                overlay.repaint();
            }
        }
    }
}
