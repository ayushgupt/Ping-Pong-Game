

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class firstscreen {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public firstscreen() {
        prepareGUI();
    }

   
   public static void main(String[] args){
      firstscreen  swingControlDemo = new firstscreen();      
      swingControlDemo.showTextFieldDemo();
   }
   

    private void prepareGUI() {
        mainFrame = new JFrame("Initial game screen");
        mainFrame.setSize(280, 500);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showTextFieldDemo() {
        headerLabel.setText("Welcome to Ping Pong. Please Enter the required data");

        final JLabel playerlabel = new JLabel("Own IP", JLabel.RIGHT);
        final JLabel sidelabel = new JLabel("Own Port", JLabel.CENTER);
        JLabel no_players = new JLabel("Number of Players", JLabel.CENTER);
        JLabel no_bots = new JLabel("Number of Bots", JLabel.CENTER);
        final JTextField playerText = new JTextField(6);
        final JTextField sideText = new JTextField(6);
        final JTextField no_players_Text = new JTextField(6);
        final JTextField no_bots_Text = new JTextField(6);
        
        JButton loginButton = new JButton("Initiate Connection");
        JButton startgame = new JButton("Start Game");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
             Main.all_ip[0] =	playerText.getText();
             Main.all_port[0]=   sideText.getText() ;
             Main.no_players = Integer.parseInt(no_players_Text.getText()); 
             Main.no_bots = Integer.parseInt(no_bots_Text.getText());
             Main.assign_id[0] = 0 ;
             
             //switching off old Frame
             mainFrame.setVisible(false);
             //showing new Frame 
             inputbox  secondScreen = new inputbox();      
             secondScreen.showTextFieldDemo();
            }
        });

        startgame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("yo dude");
            	//starting server on accepting machine 
            	try {
					ServerSocket serverSocket = new ServerSocket(Integer.parseInt(sideText.getText()) );
					System.out.println(Integer.parseInt(sideText.getText()));
					Socket temp=serverSocket.accept() ;
					System.out.println("yo dude2");
					BufferedReader in = new BufferedReader(new InputStreamReader(temp.getInputStream()));
					System.out.println("yo dude3");
					String initialize_data =null;
					try{
					 initialize_data= in.readLine() ;
					}catch(Exception e1)
					{
						e1.printStackTrace();
					}
					
					Main.firstpacket = true ;
					
					System.out.println("yo done");
					
					JSONObject t =null;
					try {
						 t = JsonUtils.stringToJson(initialize_data) ;
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Main.assign_id =  JsonUtils.stringToIntarr((String)t.get("id")) ;
					Main.all_port = JsonUtils.stringToStrarr((String)t.get("port")) ;
					Main.all_ip = JsonUtils.stringToStrarr((String)t.get("ip")) ;
					Main.no_players =  Integer.parseInt(t.get("player").toString()) ;
					Main.no_bots = Integer.parseInt(t.get("bots").toString()) ;
				
					
					temp.close() ;
					serverSocket.close() ;
					//start making other connections
					int ownport = Integer.parseInt(sideText.getText()) ;
					Main.ownServer = new server(Integer.parseInt(sideText.getText())) ;
					
					
					//Main.final_client[0].connect("localhost", 9000, 1) ;
					for(int i= 0; i <Main.no_players;i++)
					{
						if(ownport==Integer.parseInt(Main.all_port[i]))
						{
							
						}
						else
						{Main.final_client[i].connect(Main.all_ip[i],Integer.parseInt(Main.all_port[i]), Main.assign_id[i]) ;  //after decoding the data 
						}
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        controlPanel.add(playerlabel);
        controlPanel.add(playerText);
        controlPanel.add(sidelabel);
        controlPanel.add(sideText);
        controlPanel.add(no_players);
        controlPanel.add(no_players_Text);
        controlPanel.add(no_bots);
        controlPanel.add(no_bots_Text);
        controlPanel.add(loginButton);
        controlPanel.add(startgame);
        mainFrame.setVisible(true);
    }
}