

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class demoswing {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public demoswing() {
        prepareGUI();
    }

   /*
   public static void main(String[] args){
      demoswing  swingControlDemo = new demoswing();      
      swingControlDemo.showTextFieldDemo();
   }
   */

    private void prepareGUI() {
        mainFrame = new JFrame("Initial game screen");
        mainFrame.setSize(400, 400);
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

        JLabel playerlabel = new JLabel("Number of players", JLabel.RIGHT);
        JLabel sidelabel = new JLabel("Chosen side", JLabel.CENTER);
        final JTextField playerText = new JTextField(6);
        final JTextField sideText = new JTextField(6);

        JButton loginButton = new JButton("Submit");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (playerText.getText() == "") {
                    statusLabel.setText("Please enter number of players");
                    return;
                }
                if (sideText.getText() == "") {
                    statusLabel.setText("Please enter your chosen side");
                    return;
                }
                int p = Integer.parseInt(playerText.getText());
                String b = sideText.getText();
                if (p > 4 || p < 1) {
                    statusLabel.setText("number of players must be less than or equal to 4");
                    return;
                }

                // TODO get c by clicking button, and check uniqueness
                // updating of  L,T,R,B buttons can be done in real time using threads
                // also check consistency - equal number of players entered, ...

                Main.no_players = Integer.parseInt(playerText.getText());
                Main.side = sideText.getText();
                statusLabel.setText("Successully submitted");

                // Hardcoded - TODO get id and update sides array
                Main.id = 0;
                Main.sides[0] = 'L';
                Main.sides[1] = 'T';
                Main.sides[2] = 'R';
                Main.sides[3] = 'B';


                final inputbox swingControlDemo2 = new inputbox();
                swingControlDemo2.showTextFieldDemo();

                // thread for switching off the inputbox frame
                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        while (true) { //System.out.print(Main.num_connected) ;
                            if (Main.num_connected == Main.no_players - 1) {
                                swingControlDemo2.mainFrame.setVisible(false);
                                break;
                            }
                        }
                    }
                });
                t2.start();
                mainFrame.setVisible(false);

            }
        });

        controlPanel.add(playerlabel);
        controlPanel.add(playerText);
        controlPanel.add(sidelabel);
        controlPanel.add(sideText);
        controlPanel.add(loginButton);
        mainFrame.setVisible(true);
    }
}