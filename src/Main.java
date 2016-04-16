/**
 * Created by quantumcoder on 4/16/2016.
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main{

    public static final int SIDE = 600 ;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PongPanel pongPanel = new PongPanel();
        frame.add(pongPanel, BorderLayout.CENTER);

        frame.setSize(SIDE, SIDE);
        frame.setVisible(true);
        frame.setResizable(false);

    }
}
