


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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
        mainFrame.setSize(600, 1500);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setBackground(new Color(0, 102, 0));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        //statusLabel.setSize(600, 100);


        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        //controlPanel.setSize(100, 400);


        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showTextFieldDemo() {
        headerLabel.setText("Welcome to Ping Pong. Please Enter the required data");
        headerLabel.setForeground(new Color(0, 153, 0));
        headerLabel.setBackground(new Color(255, 255, 102));
        controlPanel.setBackground(new Color(255, 255, 102));
        statusLabel.setBackground(new Color(255, 255, 102));

        final JLabel Ser = new JLabel("SERVER", JLabel.CENTER);
        Border borders = Ser.getBorder();
        Border margins = new EmptyBorder(0,200,0,200);
        Ser.setBorder(new CompoundBorder(borders, margins));

        final JLabel Cli = new JLabel("CLIENT", JLabel.CENTER);
        Border borderc = Cli.getBorder();
        Border marginc = new EmptyBorder(0,200,0,200);
        Cli.setBorder(new CompoundBorder(borderc, marginc));

        final JLabel playerlabel = new JLabel("Own IP", JLabel.LEFT);
        Border border = playerlabel.getBorder();
        Border margin = new EmptyBorder(0,80,0,10);
        playerlabel.setBorder(new CompoundBorder(border, margin));
        final JLabel sidelabel = new JLabel("Own Port", JLabel.LEFT);
        final JLabel sidelabel1 = new JLabel("Own Port", JLabel.LEFT);
        sidelabel.setBorder(new CompoundBorder(border, margin));
        sidelabel1.setBorder(new CompoundBorder(border, margin));
        JLabel no_players = new JLabel("Number of Players", JLabel.LEFT);
        no_players.setBorder(new CompoundBorder(border, margin));
        // JLabel no_bots = new JLabel("Number of Bots", JLabel.CENTER);
        JLabel side0 = new JLabel("SIDE: ", JLabel.LEFT);
        Border border3 = playerlabel.getBorder();
        Border margin3 = new EmptyBorder(0,-50,0,10);
        side0.setBorder(new CompoundBorder(border3, margin3));
        final JTextField playerText = new JTextField(6);
        Border border1 = playerText.getBorder();
        //top left bottom right
        Border margin1 = new EmptyBorder(0,10,0,220);
        playerText.setBorder(new CompoundBorder(border1, margin1));
        final JTextField sideText = new JTextField(6);
        final JTextField sideText1 = new JTextField(6);
        sideText.setBorder(new CompoundBorder(border1, margin1));
        sideText1.setBorder(new CompoundBorder(border1, margin1));
        final JTextField no_players_Text = new JTextField(6);
        no_players_Text.setBorder(new CompoundBorder(border1, margin1));
       // final JTextField no_bots_Text = new JTextField(6);
        final JTextField side0_t = new JTextField(1);
        side0_t.setBorder(new CompoundBorder(border1, margin1));
        JButton loginButton = new JButton("Initiate Connection");

        controlPanel.setSize(600,400);
        Border border2 = playerText.getBorder();
        //top left bottom right
        Border margin2 = new EmptyBorder(0,20,0,0);
        loginButton.setMargin(new Insets(0, 60, 0, 60));
        JButton startgame = new JButton("Start Game");
        startgame.setBackground(new Color(255, 77, 148));
        loginButton.setBackground(new Color(255, 77, 148));

        startgame.setMargin(new Insets(0, 50, 0, 50));
        side0.setBorder(new CompoundBorder(border, margin));
        controlPanel.setForeground(new Color(0, 153, 0));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
             Main.all_ip[0] =	playerText.getText();
             Main.all_port[0]=   sideText.getText() ;
             Main.no_players = Integer.parseInt(no_players_Text.getText()); 
             Main.no_bots = 4-Integer.parseInt(no_players_Text.getText());
             Main.assign_id[0] = 0 ;
             Main.str_sides[0]= side0_t.getText() ;
             
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
					System.out.println(Integer.parseInt(sideText1.getText()));
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
					Main.str_sides = JsonUtils.stringToStrarr((String)t.get("side")) ;
					System.out.println((String)t.get("side"));

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
							Main.ownId =Main.assign_id[i] ;
						}
						else
						{
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            Main.final_client[i].connect(Main.all_ip[i],Integer.parseInt(Main.all_port[i]), Main.assign_id[i]) ;  //after decoding the data
						}
					}
					
					JFrame frame = new JFrame("Pong");
                    //Exiting on pressing close
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    PongPanel pongPanel = new PongPanel();
                    //adding pong panel to the centre of the layout
                    frame.add(pongPanel, BorderLayout.CENTER);
                    //Setting the size to 600 as declared above
                    frame.setSize(Main.SIDE.intValue(), Main.SIDE.intValue());
                    //setting the visibility to on
                    frame.setVisible(true);
                    //We wont be able to resize the frame
                    frame.setResizable(false);
                    
                    mainFrame.setVisible(false) ;
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        controlPanel.add(Ser);
        controlPanel.add(playerlabel);
        controlPanel.add(playerText);
        controlPanel.add(sidelabel);
        controlPanel.add(sideText);
        controlPanel.add(no_players);
        controlPanel.add(no_players_Text);
        //controlPanel.add(no_bots);
        //controlPanel.add(no_bots_Text);
        controlPanel.add(side0) ;
        controlPanel.add(side0_t) ;
        controlPanel.add(loginButton);
        controlPanel.add(Cli);
        controlPanel.add(sidelabel1);
        controlPanel.add(sideText1);
        controlPanel.add(startgame);
        mainFrame.setVisible(true);
    }

}