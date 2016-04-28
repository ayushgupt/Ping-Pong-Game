
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

import org.json.simple.*; 

public class inputbox {

    public JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public inputbox(){
        prepareGUI();
    }

    /*
    public static void main(String[] args){
        inputbox  swingControlDemo = new inputbox();
        swingControlDemo.showTextFieldDemo();
    }
    */

    private void prepareGUI(){
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(340,340);
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
        headerLabel.setText("Enter the port and ip address");

        JButton[] loginButton= new  JButton[4] ;
        final JTextField[] port= new  JTextField[4] ;
        final JTextField[] ip= new  JTextField[4] ;
        final JTextField[] side = new JTextField[4] ;

        
        switch(Main.no_players-1)
        {  
        case 3: 
            JLabel  portlabel3= new JLabel("PORT"+": ", JLabel.RIGHT);
            JLabel  ipLabel3 = new JLabel("IP"+": ", JLabel.CENTER);
            port[3] = new JTextField(6);
            ip[3] = new JTextField(6);
            
            JLabel side3 = new JLabel("SIDE: ", JLabel.RIGHT);
            side[3] = new JTextField(1) ;
            
             loginButton[3] = new JButton("Connect");
             
             loginButton[3].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 String ip_address = ip[3].getText();
                     int port_server = Integer.parseInt(port[3].getText());
                     Main.sent_client[3].connect(ip_address, port_server) ;
                     

                     
                     	 Main.num_connected++ ;
                         statusLabel.setText("player with ip "+ip_address+" is connected");
                         Main.assign_id[3] = 3 ;
                         Main.all_ip[3]= ip_address;
                         Main.all_port[3]= port[3].getText() ;
                         
                         Main.str_sides[3] = side[3].getText() ;
                     
                 }
                 
                 
             });
             
            controlPanel.add(portlabel3);
            controlPanel.add(port[3]);
            controlPanel.add(ipLabel3);
            controlPanel.add(ip[3]);
            controlPanel.add(side3) ;
            controlPanel.add(side[3]) ;
            controlPanel.add(loginButton[3]);
        case 2 :
            JLabel  portlabel2= new JLabel("PORT"+": ", JLabel.RIGHT);
            JLabel  ipLabel2 = new JLabel("IP"+": ", JLabel.CENTER);
            port[2] = new JTextField(6);
            ip[2] = new JTextField(6);
            JLabel side2 = new JLabel("SIDE: ", JLabel.RIGHT);
            side[2] = new JTextField(1) ;
           
            
             loginButton[2] = new JButton("Connect");
             loginButton[2].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 String ip_address = ip[2].getText();
                     int port_server = Integer.parseInt(port[2].getText());
                     Main.sent_client[2].connect(ip_address, port_server) ;

                 	 
                     Main.num_connected++ ;
                     statusLabel.setText("player with ip "+ip_address+" is connected");
                     Main.assign_id[2] = 2 ;
                     Main.all_ip[2]= ip_address;
                     Main.all_port[2]= port[2].getText() ;
                     Main.str_sides[2] = side[2].getText() ;
                     
                 }
             });
            controlPanel.add(portlabel2);
            controlPanel.add(port[2]);
            controlPanel.add(ipLabel2);
            controlPanel.add(ip[2]);
            controlPanel.add(side2) ;
            controlPanel.add(side[2]) ;
            controlPanel.add(loginButton[2]);
        	
        case 1 :
        	JLabel  portlabel1= new JLabel("PORT"+": ", JLabel.RIGHT);
	        JLabel  ipLabel1 = new JLabel("IP"+": ", JLabel.CENTER);
	        port[1] = new JTextField(6);
	        ip[1] = new JTextField(6);
	        JLabel side1 = new JLabel("SIDE: ", JLabel.RIGHT);
            side[1] = new JTextField(1) ;
           
	
	         loginButton[1] = new JButton("Connect");
	         loginButton[1].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 String ip_address = ip[1].getText();
                     int port_server = Integer.parseInt(port[1].getText());
                     
                     System.out.println("working");
                     
                     Main.sent_client[1].connect(ip_address, port_server) ;
                    
                     

                 	 Main.num_connected++ ;
                     statusLabel.setText("player with ip "+ip_address+" is connected");
                     Main.assign_id[1] = 1 ;
                     Main.all_ip[1]= ip_address; 
                     Main.all_port[1]= port[1].getText() ;
                     Main.str_sides[1] = side[1].getText() ;
                 }
             });
	        controlPanel.add(portlabel1);
	        controlPanel.add(port[1]);
	        controlPanel.add(ipLabel1);
	        controlPanel.add(ip[1]);
	        controlPanel.add(side1) ;
            controlPanel.add(side[1]) ;
	        controlPanel.add(loginButton[1]);
	
	        	
        default : JButton start = new JButton("Start");
        		  controlPanel.add(start) ;
        		  start.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                          if (Main.num_connected == Main.no_players - 1) {
                              Main.network_info = new JSONObject();
                              Main.ownId = 0;  //starting id is always 0

                              String port_arr = JsonUtils.arrToStr(Main.all_port);
                              String ip_arr = JsonUtils.arrToStr(Main.all_ip);
                              String id_arr = JsonUtils.arrToStr(Main.assign_id);
                              String sides_arr = JsonUtils.arrToStr(Main.str_sides) ;
                              
                              Main.network_info.put("port", port_arr);
                              Main.network_info.put("ip", ip_arr);
                              Main.network_info.put("id", id_arr);
                              Main.network_info.put("side", sides_arr) ;
                              
                              Main.network_info.put("player", Main.no_players);
                              Main.network_info.put("bots", Main.no_bots);

                              Main.jsonstr = JsonUtils.jsonToString(Main.network_info);
                              //make data in JSON format
                              for (int i = 1; i < Main.no_players; i++) {
                                  Main.sent_client[i].send();

                                  //starting own server


                              }
                              
                              Main.ownServer = new server(Integer.parseInt(Main.all_port[0]));

                              //System.out
                              for (int i = 1; i < Main.no_players; i++) {

                            	  Main.sent_client[i].close() ;
                                 // Main.final_client[i].connect(Main.all_ip[i], Integer.parseInt(Main.all_port[i]), Main.assign_id[i]);


                              }
                              for (int i = 1; i < Main.no_players; i++) {
                            	  try {
  									Thread.sleep(1000);
  								} catch (InterruptedException e1) {
  									// TODO Auto-generated catch block
  									e1.printStackTrace();
  								}
                            	  
                            	  Main.final_client[i].connect(Main.all_ip[i], Integer.parseInt(Main.all_port[i]), Main.assign_id[i]);
                                
                            	 
                              }
                              mainFrame.setVisible(false) ;
                            //Giving name to Jframe
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
                              
                          } else {
                              System.out.println(Main.num_connected);
                              System.out.println(Main.no_players);
                              statusLabel.setText("Not all Players are connected");
                          }
                          //initializing sides


                      }
                  });
        }


        mainFrame.setVisible(true);
    }

}