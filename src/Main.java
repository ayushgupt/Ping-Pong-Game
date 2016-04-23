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
    public static int no_bots ;
    public static client[] sent_client ;
    public static String[] data ;
    public static boolean[] connected ;
    public static server ownServer ; 
    public static int num_connected ;
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

        //test for playing sound 
        URL url=null;
		try {
			url = new URL("file:C:\\E-drive-73865609\\COP290\\Assignment3\\ass3\\src\\ball.wav");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// end of test 

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
        JTextField field = new JTextField(10);
        frame.add(field, BorderLayout.SOUTH);
        
        //showing player screen and bot screen
        demoswing  swingControlDemo = new demoswing();      
        swingControlDemo.showTextFieldDemo();
    }
}
