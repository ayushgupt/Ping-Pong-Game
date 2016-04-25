import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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


        
        switch(Main.no_players-1)
        {  
        case 3: 
            JLabel  portlabel3= new JLabel("PORT"+": ", JLabel.RIGHT);
            JLabel  ipLabel3 = new JLabel("IP"+": ", JLabel.CENTER);
            port[3] = new JTextField(6);
            ip[3] = new JTextField(6);

             loginButton[3] = new JButton("Connect");
             
             loginButton[3].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 String ip_address = ip[3].getText();
                     int port_server = Integer.parseInt(port[3].getText());
                     Main.sent_client[3].connect(ip_address, port_server,3) ;
                     
                     try {
 						Thread.sleep(1000) ;
 					} catch (InterruptedException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                     
                     if(Main.connected[3]) 
                     {	 Main.num_connected++ ;
                         statusLabel.setText("player with ip "+ip_address+" is connected");
                     }
                     else
                     {
                    	 statusLabel.setText("player with ip "+ip_address+" could not be connected. Please try again");
                    	 
                     }
                 }
                 
                 
             });
             
            controlPanel.add(portlabel3);
            controlPanel.add(port[3]);
            controlPanel.add(ipLabel3);
            controlPanel.add(ip[3]);
            controlPanel.add(loginButton[3]);
        case 2 :
            JLabel  portlabel2= new JLabel("PORT"+": ", JLabel.RIGHT);
            JLabel  ipLabel2 = new JLabel("IP"+": ", JLabel.CENTER);
            port[2] = new JTextField(6);
            ip[2] = new JTextField(6);

             loginButton[2] = new JButton("Connect");
             loginButton[2].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 String ip_address = ip[2].getText();
                     int port_server = Integer.parseInt(port[2].getText());
                     Main.sent_client[2].connect(ip_address, port_server,2) ;
                     try {
 						Thread.sleep(1000) ;
 					} catch (InterruptedException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                     if(Main.connected[2]) 
                     {	Main.num_connected++ ;
                         statusLabel.setText("player with ip "+ip_address+" is connected");
                     }
                     else
                     {
                    	 statusLabel.setText("player with ip "+ip_address+" could not be connected. Please try again");
                    	 
                     }
                 }
             });
            controlPanel.add(portlabel2);
            controlPanel.add(port[2]);
            controlPanel.add(ipLabel2);
            controlPanel.add(ip[2]);
            controlPanel.add(loginButton[2]);
        	
        case 1 :
        	JLabel  portlabel1= new JLabel("PORT"+": ", JLabel.RIGHT);
	        JLabel  ipLabel1 = new JLabel("IP"+": ", JLabel.CENTER);
	        port[1] = new JTextField(6);
	        ip[1] = new JTextField(6);
	
	         loginButton[1] = new JButton("Connect");
	         loginButton[1].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 String ip_address = ip[1].getText();
                     int port_server = Integer.parseInt(port[1].getText());
                     Main.sent_client[1].connect(ip_address, port_server,1) ;
                     
                     try {
						Thread.sleep(1000) ;
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                     if(Main.connected[1]) 
                     {
                    	 Main.num_connected++ ;
                         statusLabel.setText("player with ip "+ip_address+" is connected");
                     }
                     else
                     {
                    	 statusLabel.setText("player with ip "+ip_address+" could not be connected. Please try again");
                    	 
                     }
                 }
             });
	        controlPanel.add(portlabel1);
	        controlPanel.add(port[1]);
	        controlPanel.add(ipLabel1);
	        controlPanel.add(ip[1]);
	        controlPanel.add(loginButton[1]);
	
	        	
        default :
        }


        mainFrame.setVisible(true);
    }
}