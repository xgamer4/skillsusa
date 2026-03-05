import javax.swing.JFrame;

public class Driver{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new Main());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}