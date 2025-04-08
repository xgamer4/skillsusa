import javax.swing.JFrame;

public class Driver extends JFrame {
    private ParentPanel parentPanel;

    public Driver() {
        parentPanel = new ParentPanel();
        getContentPane().add(parentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Driver();
    }
}
