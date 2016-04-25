/**
 * Created by quantumcoder on 4/16/2016.
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main{

    public static final int SIDE = 600 ;
    public static int no_players ;
    public static String side ;
    public static client[] sent_client ;
    public static String[] data ;
    public static boolean[] connected ;
    public static server ownServer ; 
    public static int num_connected ;
    public static int id ;
    public static char[] sides = {'X','X','X','X'} ;


    public static void main(String[] args) {
    	//number of connected initially=0 ;
    	 num_connected =0 ;
    	//starting server 
    	ownServer = new server(9000) ;

        //declaring connection test array
    	connected = new boolean[3] ; 
    	connected[0] = false ;
    	connected[1] = false ;
    	connected[2] = false ;

        //declaring Data string array
    	data = new String[3] ; 

        //declaring client array
    	sent_client = new client[3];
    	sent_client[0] = new client() ;
    	sent_client[1] = new client() ;
    	sent_client[2] = new client() ;

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
        
        
        //showing player screen and bot screen
        demoswing  swingControlDemo = new demoswing();      
        swingControlDemo.showTextFieldDemo();
    }
}
