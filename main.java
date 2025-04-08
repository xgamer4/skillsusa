import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HelloWorldGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World");
        JLabel label = new JLabel("Hello, World!", SwingConstants.CENTER);

        frame.add(label);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
