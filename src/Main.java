/**
 * Created by quantumcoder on 4/16/2016.
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main{

    public static final int SIDE = 600 ;

    public static void main(String[] args) {

        //Giving name to Jframe
        JFrame frame = new JFrame("Pong");
        //Exiting on pressing close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        PongPanel pongPanel = new PongPanel();
        //adding pong panel to the centre of the layout
        frame.add(pongPanel, BorderLayout.CENTER);
        //Setting the size to 600 as declared above
        frame.setSize(SIDE, SIDE);
        //setting the visibility to on
        frame.setVisible(true);
        //We wont be able to resize the frame
        frame.setResizable(false);

    }
}
