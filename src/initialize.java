
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class initialize {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public initialize() {
        prepareGUI();
    }


    public static void main(String[] args) {
        initialize swingControlDemo = new initialize();
        swingControlDemo.showTextFieldDemo();
    }


    private void prepareGUI() {
        mainFrame = new JFrame("Initial game screen");
        mainFrame.setSize(250, 400);
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
        headerLabel.setText("Please Enter the required data");

        JButton loginButton = new JButton("connect");
        final JTextField[] iptext = new JTextField[3];
        final JTextField[] porttext = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            JLabel portlabel = new JLabel("PORT", JLabel.RIGHT);
            JLabel iplabel = new JLabel("IP", JLabel.CENTER);
            iptext[i] = new JTextField(6);
            porttext[i] = new JTextField(6);

            controlPanel.add(portlabel);
            controlPanel.add(iptext[i]);
            controlPanel.add(iplabel);
            controlPanel.add(porttext[i]);


        }
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	
            	/*for(int i=0;i<3;i++)
                {
            		Socket echoSocket = new Socket(hostname[i], portnumber[i]);
			    	
    			    PrintWriter out = new PrintWriter(echosocket.getOutputStream(), true);
    			    BufferedReader in = new BufferedReader(new InputStreamReader(echosocket.getInputStream()));
    			    
            	}*/

            }
        });
        controlPanel.add(loginButton);
        mainFrame.setVisible(true);
    }

}