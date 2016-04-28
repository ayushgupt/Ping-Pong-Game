/**
 * Created by quantumcoder on 4/16/2016.
 */
import java.applet.Applet;
import org.json.simple.*;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main{


    public static final Double SIDE = 600.0 ;

    public static String side ;
    
    public static String[] data ;
    
     
    public static int num_connected ;
    public static int id ;
    public static char[] sides = {'X','X','X','X'} ;
    public static String[] str_sides  ; 

    
    //new variables assigned here 
    public static String[] all_port ;
    public static String[] all_ip ;
    public static Integer[] assign_id ;
    public static Integer no_players ;
    public static Integer no_bots ;
    public static clientFirst[] sent_client ;
    public static boolean[] connected ;
    public static server ownServer ;
    public static boolean firstpacket ;
    public static client[] final_client ;
    public static JSONObject network_info ;
    public static String jsonstr ;
    public static Integer ownId ;  
    public static Integer no_rec ;
    public static Integer no_all_rec; 
    
    public static void main(String[] args) {
    	//initializing 
    	no_rec =0 ; 
    	no_all_rec = 0 ;
    	
    	//new variables here 
    	 all_port = new String[4] ;
    	 all_ip = new String[4] ;
    	 assign_id = new Integer[4] ;
    	 str_sides = new String[4] ; 
    	 for(int i=0;i<4;i++)
    	 {	str_sides[i]="X" ;
    		 all_port[i] ="" ;
    		 all_ip[i] ="" ;
    		 assign_id[i] = -1 ;
    	 }
    	 firstpacket = false ;
    	 
    		sent_client = new clientFirst[4];
        	sent_client[0] = new clientFirst() ;
        	sent_client[1] = new clientFirst() ;
        	sent_client[2] = new clientFirst() ;
        	sent_client[3] = new clientFirst() ;
        	
        	connected = new boolean[3] ; 
        	connected[0] = false ;
        	connected[1] = false ;
        	connected[2] = false ;
        	
        	final_client = new client[4];
        	final_client[0] = new client() ;
        	final_client[1] = new client() ;
        	final_client[2] = new client() ;
        	
        	//number of connected initially=0 ;
       	 	num_connected =0 ;
       	 
    	 //end new
    	
    	
    	//starting server 
    	//ownServer = new server(9000) ;

        //declaring connection test array
    	

        //declaring Data string array
    	data = new String[3] ; 

        //declaring client array
    

        
        
        
        //showing player screen and bot screen
        firstscreen  swingControlDemo = new firstscreen();      
        swingControlDemo.showTextFieldDemo();
    }
}
