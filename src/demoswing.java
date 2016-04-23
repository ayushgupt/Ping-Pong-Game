
 
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
 
public class demoswing {
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public demoswing(){
      prepareGUI();
   }

   public static void main(String[] args){
      demoswing  swingControlDemo = new demoswing();      
      swingControlDemo.showTextFieldDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    

      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   public void showTextFieldDemo(){
      headerLabel.setText("Welcome to Ping Pong. Please Enter the required data"); 

      JLabel  namelabel= new JLabel("Number of players", JLabel.RIGHT);
      JLabel  passwordLabel = new JLabel("Number of Bots", JLabel.CENTER);
      final JTextField userText = new JTextField(6);
      final JTextField passwordText = new JTextField(6);      

      JButton loginButton = new JButton("Submit");
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
            
        	 if(userText.getText()=="")
        	 {
        		 statusLabel.setText("Please enter number of players");  
        		 return ;
        	 }
        	 if(passwordText.getText()=="")
        	 {
        		 statusLabel.setText("Please enter number of bots");  
        		 return ;
        	 }
        	 int p = Integer.parseInt(userText.getText()) ;
        	 int b = Integer.parseInt(passwordText.getText());
        	 if(p>4||p<1)
        	 {
        		 statusLabel.setText("number of players must be less than 5");
        		 return ;
        	 }
        	 if((p+b)>4)
        	 {
        		 statusLabel.setText("total number of players must be less than 5");
        		 return ;
        	 }
            Main.no_players = Integer.parseInt(userText.getText());
            Main.no_bots = Integer.parseInt(passwordText.getText());
            statusLabel.setText("Successully submitted");
            
  		 	
            
            final inputbox  swingControlDemo2 = new inputbox();
            swingControlDemo2.showTextFieldDemo();
            
            // thread for switching off the inputbox frame 
            Thread t2 = new Thread(new Runnable() {
			     public void run() {
			    	while(true)
			    	{ //System.out.print(Main.num_connected) ;
			    		if(Main.num_connected==Main.no_players-1)
			    		{
			    			
			    			swingControlDemo2.mainFrame.setVisible(false) ;
			    		 break ;
			    		}
			    	}
			     }
			});  
			t2.start(); 
            mainFrame.setVisible(false);  

         }
      }); 

      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(passwordLabel);       
      controlPanel.add(passwordText);
      controlPanel.add(loginButton);
      mainFrame.setVisible(true);  
   }
}