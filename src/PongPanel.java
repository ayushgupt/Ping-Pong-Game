/**
 * Created by quantumcoder on 4/17/2016.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

// TODO : int to float
// TODO : move x and y of paddles to their centres

public class PongPanel extends JPanel implements ActionListener, KeyListener{

    public String image_src = "C:\\Users\\quantumcoder\\Desktop\\iitd\\Sem4\\cop290\\assignment3\\Game\\src\\final.png" ;
    public static final int WIDTH = Main.SIDE - 6, HEIGHT = Main.SIDE - 29;

    //the three screens whose visibility can be shown
    private static boolean showTitleScreen = true;
    private static boolean playing = false;
    private static boolean gameOver = false;


    public static double mu ;

    public static final int margin = 40;

    // public Ball ball;
    public Player playerL;
    public Player playerR;
    public Player playerT;
    public Player playerB;

    public static int paddleSpeed ;

    // Paddle specific variables
    public static int playerLRight, playerLTop, playerLBottom, playerRLeft, playerRTop, playerRBottom ;
    public static int playerTBottom, playerTLeft, playerTRight, playerBTop, playerBLeft, playerBRight ;
    public static int playerLScore, playerRScore , playerTScore, playerBScore ;   // score actually measures life

    private Image pinpon;
    // PongPanel constructor
    public PongPanel(){

        loadImage();

        setBackground(new Color(255,0,0));

        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        paddleSpeed = 4 ;   // Speed of paddle

        mu = 0.5;

        // Initialize a ball
        new Ball(HEIGHT,WIDTH,20,Main.SIDE/2,Main.SIDE/2,1,-3);


        // Initialize players
        playerL = new Player(PlayerType.L, (margin-10), Main.SIDE/2, 10, 50, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        playerR = new Player(PlayerType.R, (WIDTH - margin), Main.SIDE/2, 10, 50, KeyEvent.VK_W, KeyEvent.VK_S);
        playerT = new Player(PlayerType.T, Main.SIDE/2, (margin-10), 50, 10, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);
        playerB = new Player(PlayerType.B, Main.SIDE/2, (HEIGHT - margin), 50, 10, KeyEvent.VK_D, KeyEvent.VK_A);

        playerLScore = 3; playerRScore = 3; playerTScore = 3; playerBScore = 3;

        if (!(new String(Main.sides).contains("L"))) { playerL.setBot(); }
        if (!(new String(Main.sides).contains("T"))) { playerT.setBot(); }
        if (!(new String(Main.sides).contains("R"))) { playerR.setBot(); }
        if (!(new String(Main.sides).contains("B"))) { playerB.setBot(); }

        //call step() 60 fps, its basically frequency per second
        int fps = 60;
        Timer timer = new Timer(1000/fps, this);
        timer.start();

    }

//This is the function that is called again and again and it calls step function
    public void actionPerformed(ActionEvent e){
        step();
    }

    //The things in this are done only if the playing state is currently displayed
    public void step(){
        //System.out.println(getHeight()+" "+getWidth());
        //System.out.println(ball.getBallVelX() + " " + ball.getBallVelX());
        //System.out.println(ball.getBallX() + " " + ball.getBallX());
        //System.out.println(playerLScore + " " + playerRScore + " " + playerTScore + " " + playerBScore );
        if(playing){
            // update player positions
            playerL.update(PlayerType.L);
            playerR.update(PlayerType.R);
            playerT.update(PlayerType.T);
            playerB.update(PlayerType.B);

            playerLRight = playerL.getX() + playerL.getWidth();
            playerLTop = playerL.getY();
            playerLBottom = playerL.getY() + playerL.getHeight();

            playerRLeft = playerR.getX();
            playerRTop = playerR.getY();
            playerRBottom = playerR.getY() + playerR.getHeight();

            playerTBottom = playerT.getY() + playerT.getHeight();
            playerTLeft = playerT.getX();
            playerTRight = playerT.getX() + playerT.getWidth();

            playerBTop = playerB.getY();
            playerBLeft = playerB.getX();
            playerBRight = playerB.getX() + playerB.getWidth();

            // Update ball position
            Ball.update();

            // Update gamestate - TODO update after averaging received gamestates
            GameState.update(playerL.getY(),playerR.getY(),playerT.getX(),playerB.getX(),Ball.getBallX(),Ball.getBallY());

        }

        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(image_src);
        pinpon = ii.getImage();
    }

    //paint the game screen
    public void paintComponent(Graphics g){

        super.paintComponent(g);


        g.drawImage(pinpon, 85, 80, null);

        g.setColor(Color.WHITE);



        if (showTitleScreen) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Pong", 165, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            g.drawString("Press 'P' to play.", 175, 400);
        }
        else if (playing) {

            g.setColor(Color.BLUE);
            g.fillOval(270, 270, 60, 60);

            g.setColor(Color.WHITE);
            int leftmargin = playerL.getX();
            int rightmargin = playerR.getX() + playerR.getWidth();
            int topmargin = playerT.getY();
            int bottommargin = playerB.getY() + playerB.getHeight();

            /*
            //draw dashed line
            for (int lineY = 0; lineY < getHeight(); lineY += 50) {
                g.drawLine(Main.SIDE/2, lineY, Main.SIDE/2, lineY+25);
            }
            */

            //draw "goal lines" on each side
            g.drawLine(rightmargin, 0, rightmargin, HEIGHT);
            g.drawLine(leftmargin, 0, leftmargin, HEIGHT);
            g.drawLine(0, topmargin, WIDTH, topmargin);
            g.drawLine(0, bottommargin, WIDTH, bottommargin);

            /*
            //draw the scores
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerLScore), Main.SIDE/5, Main.SIDE/5);
            g.drawString(String.valueOf(playerRScore), 4*Main.SIDE/5, Main.SIDE/5);
            */

            // draw ball
            Ball.drawBall(g);

            //draw the paddles
            playerL.drawPaddle(g);
            playerR.drawPaddle(g);
            playerT.drawPaddle(g);
            playerB.drawPaddle(g);
        }
        else if (gameOver) {
            // TODO: improve and remove hardcoding
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerLScore), 100, 100);
            g.drawString(String.valueOf(playerRScore), 400, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Player L Wins!", 165, 200);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press space to restart.", 150, 400);
        }
    }


    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (showTitleScreen) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                playerL.setUpPress(false);
                playerL.setDownPress(false);
                playerR.setUpPress(false);
                playerR.setDownPress(false);
                playerT.setUpPress(false);
                playerT.setDownPress(false);
                playerB.setUpPress(false);
                playerB.setDownPress(false);
                setPlaying();
            }
        }
        else if(playing){
          if (!playerL.isBot()){
              if (e.getKeyCode() == playerL.getkeyup()) {
                  playerL.setUpPress(true);
              }
              else if (e.getKeyCode() == playerL.getkeydown()) {
                  playerL.setDownPress(true);
              }
            }
          if (!playerR.isBot()){
              if (e.getKeyCode() == playerR.getkeyup()) {
                  playerR.setUpPress(true);
              }
              else if (e.getKeyCode() == playerR.getkeydown()) {
                  playerR.setDownPress(true);
              }
          }
          if (!playerT.isBot()){
              if (e.getKeyCode() == playerT.getkeyup()) {
                  playerT.setUpPress(true);
              }
              else if (e.getKeyCode() == playerT.getkeydown()) {
                  playerT.setDownPress(true);
              }
          }
          if (!playerB.isBot()){
              if (e.getKeyCode() == playerB.getkeyup()) {
                  playerB.setUpPress(true);
              }
              else if (e.getKeyCode() == playerB.getkeydown()) {
                  playerB.setDownPress(true);
              }
          }
        }
        else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameOver = false;
                showTitleScreen = true;

                Ball.setX(Main.SIDE/2); Ball.setY(Main.SIDE/2);
                playerL.setY(Main.SIDE/2);
                playerR.setY(Main.SIDE/2);
                playerT.setX(Main.SIDE/2);
                playerB.setX(Main.SIDE/2);

                playerLScore = 3;
                playerRScore = 3;
                playerTScore = 3;
                playerBScore = 3;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (playing) {
          if (!playerL.isBot()){
              if (e.getKeyCode() == playerL.getkeyup()) {
                  playerL.setUpPress(false);
              }
              else if (e.getKeyCode() == playerL.getkeydown()) {
                  playerL.setDownPress(false);
              }
          }
          if (!playerR.isBot()){
            if (e.getKeyCode() == playerR.getkeyup()) {
                playerR.setUpPress(false);
            }
            else if (e.getKeyCode() == playerR.getkeydown()) {
                playerR.setDownPress(false);
            }
          }
          if (!playerT.isBot()){
            if (e.getKeyCode() == playerT.getkeyup()) {
                playerT.setUpPress(false);
            }
            else if (e.getKeyCode() == playerT.getkeydown()) {
                playerT.setDownPress(false);
            }
          }
          if (!playerB.isBot()){
            if (e.getKeyCode() == playerB.getkeyup()) {
                playerB.setUpPress(false);
            }
            else if (e.getKeyCode() == playerB.getkeydown()) {
                playerB.setDownPress(false);
            }
          }
        }
    }

    public static void setPlaying(){
        System.out.println("Game started");
        showTitleScreen = false;
        playing = true;
    }


    public static void setOver(){
        System.out.println("Game over");
        playing = false;
        gameOver = true;
    }

    public void print (Object x){
        System.out.println(x);
    }


}
