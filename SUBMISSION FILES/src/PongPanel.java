/**
 * Created by quantumcoder on 4/17/2016.
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;


public class PongPanel extends JPanel implements ActionListener, KeyListener {

    public String image_src = new File("").getAbsolutePath() + "\\src\\final.png";
    public static final Double WIDTH = Main.SIDE - 6, HEIGHT = Main.SIDE - 29;

    //the three screens whose visibility can be shown
    public static boolean showTitleScreen = true;
    public static boolean playing = false;
    public static boolean gameOver = false;

    public static double mu;

    public static final Double margin = 40.0;

    // public Ball ball;
    public static Player playerL;
    public static Player playerR;
    public static Player playerT;
    public static Player playerB;

    public static Double paddleSpeed;
    public static Double ballvx = 1.0, ballvy = -3.0;
    public static Double difficulty ;


    // Paddle specific variables
    public static Double playerLRight, playerLTop, playerLBottom, playerRLeft, playerRTop, playerRBottom;
    public static Double playerTBottom, playerTLeft, playerTRight, playerBTop, playerBLeft, playerBRight;
    public static int playerLScore, playerRScore, playerTScore, playerBScore;   // score actually measures life

    private String seconds;
    public static JSONObject[] received_gamestate;

    private boolean[] sign;
    private boolean[] oldsign;

    private Image pinpon;

    // PongPanel constructor
    public PongPanel() {

        loadImage();



        //listen to key presses
        setFocusable(true);
        addKeyListener(this);


        paddleSpeed = 4.0;   // Speed of paddle

        mu = 0.5;
        seconds = "00";

        for (int i = 0; i < 4; i++) {
            //System.out.print(Main.str_sides[i]);
            if (Main.str_sides[i].equals("L") || Main.str_sides[i].equals("R") || Main.str_sides[i].equals("T") || Main.str_sides[i].equals("B")) {
                String curr_side = Main.str_sides[i];
                Main.sides[i] = curr_side.charAt(0);
                //System.out.println(Main.sides[i]);
            }
        }


        // Initialize a ball
        new Ball(HEIGHT, WIDTH, 20.0, Main.SIDE / 2, Main.SIDE / 2, ballvx, ballvy);

        boolean ball_sign = (ballvx * ballvy) > 0;
        sign = new boolean[]{ball_sign, ball_sign, ball_sign, ball_sign};
        oldsign = new boolean[]{ball_sign, ball_sign, ball_sign, ball_sign};


        // Initialize players
        playerL = new Player(PlayerType.L, (margin - 10), Main.SIDE / 2 - 10, 10.0, 50.0, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        playerR = new Player(PlayerType.R, (WIDTH - margin), Main.SIDE / 2 - 10, 10.0, 50.0, KeyEvent.VK_W, KeyEvent.VK_S);
        playerT = new Player(PlayerType.T, Main.SIDE / 2 - 10, (margin - 10), 50.0, 10.0, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);
        playerB = new Player(PlayerType.B, Main.SIDE / 2 - 10, (HEIGHT - margin), 50.0, 10.0, KeyEvent.VK_D, KeyEvent.VK_A);

        playerLScore = 3;
        playerRScore = 3;
        playerTScore = 3;
        playerBScore = 3;

        difficulty = 0.1;

        // assign sides to players and bots
        if (!(new String(Main.sides).contains("L"))) {
            playerL.setBot();
        }
        if (!(new String(Main.sides).contains("T"))) {
            playerT.setBot();
        }
        if (!(new String(Main.sides).contains("R"))) {
            playerR.setBot();
        }
        if (!(new String(Main.sides).contains("B"))) {
            playerB.setBot();
        }

        String chosen_side = Main.str_sides[Main.ownId];
        switch (chosen_side.charAt(0)) {
            case 'L':
                playerL.isOwn = true;
                break;
            case 'R':
                playerR.isOwn = true;
                break;
            case 'T':
                playerT.isOwn = true;
                break;
            case 'B':
                playerB.isOwn = true;
                break;
        }

        // initialize gamestate
        new GameState();
        received_gamestate = new JSONObject[]{GameState.gamestate, GameState.gamestate, GameState.gamestate, GameState.gamestate};

        //call step() 60 fps, its basically frequency per second
        int fps = 60;
        Timer timer = new Timer(1000 / fps, this);
        timer.start();

    }

    //This is the function that is called again and again and it calls step function
    public void actionPerformed(ActionEvent e) {
        step();
    }

    //The things in this are done only if the playing state is currently displayed
    public void step() {
        if (showTitleScreen) {
            setBackground(new Color(102, 140, 255));
            //setPlaying();

            if (seconds.equals("19") || seconds.equals("39") || seconds.equals("59")) {
                setPlaying();
            }
        }
        //System.out.println(getHeight()+" "+getWidth());
        //System.out.println(ball.getBallVelX() + " " + ball.getBallVelX());
        //System.out.println(ball.getBallX() + " " + ball.getBallX());
        //System.out.println(playerLScore + " " + playerRScore + " " + playerTScore + " " + playerBScore );
        if (playing) {
            setBackground(new Color(255, 255, 128));
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


            // Update gamestate after averaging received gamestates
            GameState.update(playerL.getY(), playerR.getY(), playerT.getX(), playerB.getX(), Ball.getBallX(), Ball.getBallY(), Ball.getBallVelX(), Ball.getBallVelY());
            received_gamestate[Main.ownId] = GameState.gamestate;

            Double sum_playerLYnew = 0.0, sum_playerRYnew = 0.0, sum_playerTXnew = 0.0, sum_playerBXnew = 0.0;
            Double sum_ballXnew = 0.0, sum_ballYnew = 0.0, sum_ballVXnew = 0.0, sum_ballVYnew = 0.0;
            Double playerLYnew = playerL.getY(), playerRYnew = playerR.getY(), playerTXnew = playerT.getX(), playerBXnew = playerB.getX();
            Double ballXnew = Ball.getBallX(), ballYnew = Ball.getBallY(), ballVXnew = Ball.getBallVelX(), ballVYnew = Ball.getBallVelY();

            // Average states of bots
            for (int i = 0; i < Main.no_players; i++) {
                sum_playerLYnew += (Double) (received_gamestate[i].get("playerLY"));
                sum_playerRYnew += (Double) (received_gamestate[i].get("playerRY"));
                sum_playerTXnew += (Double) (received_gamestate[i].get("playerTX"));
                sum_playerBXnew += (Double) (received_gamestate[i].get("playerBX"));
            }
            if (playerL.isBot()) {
                playerLYnew = playerL.getY();
            }
            if (playerR.isBot()) {
                playerRYnew = playerR.getY();
            }
            if (playerT.isBot()) {
                playerTXnew = playerT.getX();
            }
            if (playerB.isBot()) {
                playerBXnew = playerB.getX();
            }


            // Update paddles by data received from owner players
            for (int i = 0; i < Main.no_players; i++) {
                switch (Main.sides[i]) {
                    case 'L':
                        playerLYnew = (Double) (received_gamestate[i].get("playerLY"));
                        break;
                    case 'R':
                        playerRYnew = (Double) (received_gamestate[i].get("playerRY"));
                        break;
                    case 'T':
                        playerTXnew = (Double) (received_gamestate[i].get("playerTX"));
                        break;
                    case 'B':
                        playerBXnew = (Double) (received_gamestate[i].get("playerBX"));
                        break;
                }
            }

            // Update ball by giving preference to direction change
            // check if all ball variables have same sign


            for (int i = 0; i < Main.no_players; i++) {
                sum_ballXnew += (Double) (received_gamestate[i].get("ballX"));
                sum_ballYnew += (Double) (received_gamestate[i].get("ballY"));
                sum_ballVXnew += (Double) (received_gamestate[i].get("ballVX"));
                sum_ballVYnew += (Double) (received_gamestate[i].get("ballVY"));
            }

            //ballXnew = sum_ballXnew/Main.no_players;
            //ballYnew = sum_ballYnew/Main.no_players;
            ballVXnew = sum_ballVXnew / Main.no_players;
            ballVYnew = sum_ballVYnew / Main.no_players;

            ballXnew = sum_ballXnew / Main.no_players + ballVXnew * (Main.no_players - 1);
            ballYnew = sum_ballYnew / Main.no_players + ballVYnew * (Main.no_players - 1);

            for (int i = 0; i < Main.no_players; i++) {
                sign[i] = ((Double) (received_gamestate[i].get("ballVX")) * (Double) (received_gamestate[i].get("ballVY"))) > 0;
                if (oldsign[i] != sign[i]) {
                    ballXnew = (Double) (received_gamestate[i].get("ballX"));
                    ballYnew = (Double) (received_gamestate[i].get("ballY"));
                    ballVXnew = (Double) (received_gamestate[i].get("ballVX"));
                    ballVYnew = (Double) (received_gamestate[i].get("ballVY"));
                }
            }


            ballVXnew = Ball.getBallVelX();
            ballVYnew = Ball.getBallVelY();


            if (!playerL.isOwn) {
                playerL.setY(playerLYnew);
            }
            if (!playerR.isOwn) {
                playerR.setY(playerRYnew);
            }
            if (!playerT.isOwn) {
                playerT.setX(playerTXnew);
            }
            if (!playerB.isOwn) {
                playerB.setX(playerBXnew);
            }

            Ball.setX(ballXnew);
            Ball.setY(ballYnew);
            Ball.setVX(ballVXnew);
            Ball.setVY(ballVYnew);


            GameState.update(playerLYnew, playerRYnew, playerTXnew, playerBXnew, ballXnew, ballYnew, ballVXnew, ballVYnew);
            //System.out.println(GameState.getString());

            for (int i = 0; i < Main.no_players; i++) {
                oldsign[i] = sign[i];
            }

        }

        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(image_src);
        pinpon = ii.getImage();
    }

    //paint the game screen
    public void paintComponent(Graphics g) {

        super.paintComponent(g);


        g.setColor(Color.WHITE);


        if (showTitleScreen) {



            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("The Game will Start in", 120, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(cal.getTime());
            int second_start = time.lastIndexOf(':');
            seconds = time.substring(second_start + 1);
            Integer sec = Integer.parseInt(seconds);
            String rem_seconds = "";
            if (sec >= 0 && sec <= 19) {
                rem_seconds = String.valueOf(19 - sec);
            }
            if (sec >= 20 && sec <= 39) {
                rem_seconds = String.valueOf(39 - sec);
            }
            if (sec >= 40 && sec <= 59) {
                rem_seconds = String.valueOf(59 - sec);
            }
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(rem_seconds, 300, 135);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Seconds", 250, 160);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Get Ready People.!!", 140, 400);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));



            //g.drawString("Press 'P' to play.", 175, 400);
        } else if (playing) {

            g.setColor(Color.ORANGE);
            //g.fillRect(playerX, playerY, playerWidth, playerHeight);
            //Bottom
            g.fillRect(0, Main.SIDE.intValue() - 68, (Main.SIDE.intValue()), 12);
            //Top
            g.fillRect(0, margin.intValue() - 10, (Main.SIDE.intValue()), 10);
            //Left
            g.fillRect(margin.intValue() - 12, 0, 12, (Main.SIDE.intValue()));
            //Right
            g.fillRect(Main.SIDE.intValue() - 45, 0, 12, (Main.SIDE.intValue()));

            g.setColor(new Color(204, 68, 0));
            //g.fillRect(playerX, playerY, playerWidth, playerHeight);
            //Bottom
            g.fillRect(0, Main.SIDE.intValue() - margin.intValue() - 18, (Main.SIDE.intValue()), margin.intValue() - 12);
            //Top
            g.fillRect(0, 0, (Main.SIDE.intValue()), margin.intValue() - 10);
            //Left
            g.fillRect(0, 0, margin.intValue() - 10, (Main.SIDE.intValue()));
            //Right
            g.fillRect(Main.SIDE.intValue() - margin.intValue() + 4, 0, margin.intValue() - 10, (Main.SIDE.intValue()));

            g.setColor(new Color(102, 34, 0));
            //g.fillRect(playerX, playerY, playerWidth, playerHeight);
            //top left
            g.fillRect(margin.intValue() - 10, margin.intValue() - 10, 10, 10);
            //top right
            g.fillRect(Main.SIDE.intValue() - margin.intValue() + 4 - 10, margin.intValue() - 10, 10, 12);
            //bottom left
            g.fillRect(margin.intValue() - 10, Main.SIDE.intValue() - margin.intValue() - 30, 10, 12);
            //bottom right
            g.fillRect(Main.SIDE.intValue() - margin.intValue() + 4 - 10, Main.SIDE.intValue() - margin.intValue() - 30, 12, 12);


            g.setColor(Color.BLUE);
            g.fillOval(270, 270, 60, 60);
            g.drawImage(pinpon, 85, 80, null);

            g.setColor(Color.WHITE);
            Double leftmargin = playerL.getX();
            Double rightmargin = playerR.getX() + playerR.getWidth();
            Double topmargin = playerT.getY();
            Double bottommargin = playerB.getY() + playerB.getHeight();

            /*
            //draw dashed line
            for (int lineY = 0; lineY < getHeight(); lineY += 50) {
                g.drawLine(Main.SIDE/2, lineY, Main.SIDE/2, lineY+25);
            }
            */

            //draw "goal lines" on each side
            g.drawLine(rightmargin.intValue(), 0, rightmargin.intValue(), HEIGHT.intValue());
            g.drawLine(leftmargin.intValue(), 0, leftmargin.intValue(), HEIGHT.intValue());
            g.drawLine(0, topmargin.intValue(), WIDTH.intValue(), topmargin.intValue());
            g.drawLine(0, bottommargin.intValue(), WIDTH.intValue(), bottommargin.intValue());

            /*
            //draw the scores
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerLScore), Main.SIDE/5, Main.SIDE/5);
            g.drawString(String.valueOf(playerRScore), 4*Main.SIDE/5, Main.SIDE/5);
            */

            // draw ball
            Ball.drawBall(g);
            paintscores(g, playerLScore, playerRScore, playerTScore, playerBScore);
            //draw the paddles
            if (!playerL.checklost) {
                playerL.drawPaddle(g);
            }
            if (!playerR.checklost) {
                playerR.drawPaddle(g);
            }
            if (!playerT.checklost) {
                playerT.drawPaddle(g);
            }
            if (!playerB.checklost) {
                playerB.drawPaddle(g);
            }
        } else if (gameOver) {
            // TODO: improve and remove hardcoding
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Player L Wins!", 165, 200);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press space to restart.", 150, 400);


        }
    }

    public static void paintscores(Graphics g, int l, int r, int u, int d) {
        String ls = Integer.toString(l);
        String rs = Integer.toString(r);
        String us = Integer.toString(u);
        String ds = Integer.toString(d);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        g.setColor(Color.white);

        //top
        g.drawString("LIFE:" + us, 230, 25);
        //bottom
        g.drawString("LIFE:" + ds, 230, 570);
        //left
        ///g.drawString("", 5, 200);
        g.drawString("L", 5, 225);
        g.drawString("I", 5, 250);
        g.drawString("F", 5, 275);
        g.drawString("E", 5, 300);
        g.drawString(ls, 5, 335);
        //left
        //g.drawString("L", 570, 200);
        g.drawString("L", 570, 225);
        g.drawString("I", 570, 250);
        g.drawString("F", 570, 275);
        g.drawString("E", 570, 300);
        g.drawString(rs, 570, 335);
    }

    public void keyTyped(KeyEvent e) {
    }

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
                //setPlaying();
            }
        } else if (playing) {
            if (!playerL.isBot() && playerL.isOwn && !playerL.checklost) {
                if (e.getKeyCode() == playerL.getkeyup()) {
                    playerL.setUpPress(true);
                } else if (e.getKeyCode() == playerL.getkeydown()) {
                    playerL.setDownPress(true);
                }
            }
            if (!playerR.isBot() && playerR.isOwn && !playerR.checklost) {
                if (e.getKeyCode() == playerR.getkeyup()) {
                    playerR.setUpPress(true);
                } else if (e.getKeyCode() == playerR.getkeydown()) {
                    playerR.setDownPress(true);
                }
            }
            if (!playerT.isBot() && playerT.isOwn && !playerT.checklost) {
                if (e.getKeyCode() == playerT.getkeyup()) {
                    playerT.setUpPress(true);
                } else if (e.getKeyCode() == playerT.getkeydown()) {
                    playerT.setDownPress(true);
                }
            }
            if (!playerB.isBot() && playerB.isOwn && !playerB.checklost) {
                if (e.getKeyCode() == playerB.getkeyup()) {
                    playerB.setUpPress(true);
                } else if (e.getKeyCode() == playerB.getkeydown()) {
                    playerB.setDownPress(true);
                }
            }
        } else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameOver = false;
                showTitleScreen = true;

                Ball.setX(Main.SIDE / 2);
                Ball.setY(Main.SIDE / 2);
                playerL.setY(Main.SIDE / 2);
                playerR.setY(Main.SIDE / 2);
                playerT.setX(Main.SIDE / 2);
                playerB.setX(Main.SIDE / 2);

                playerLScore = 3;
                playerRScore = 3;
                playerTScore = 3;
                playerBScore = 3;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (playing) {
            if (!playerL.isBot() && playerL.isOwn && !playerL.checklost) {
                if (e.getKeyCode() == playerL.getkeyup()) {
                    playerL.setUpPress(false);
                } else if (e.getKeyCode() == playerL.getkeydown()) {
                    playerL.setDownPress(false);
                }
            }
            if (!playerR.isBot() && playerR.isOwn && !playerR.checklost) {
                if (e.getKeyCode() == playerR.getkeyup()) {
                    playerR.setUpPress(false);
                } else if (e.getKeyCode() == playerR.getkeydown()) {
                    playerR.setDownPress(false);
                }
            }
            if (!playerT.isBot() && playerT.isOwn && !playerT.checklost) {
                if (e.getKeyCode() == playerT.getkeyup()) {
                    playerT.setUpPress(false);
                } else if (e.getKeyCode() == playerT.getkeydown()) {
                    playerT.setDownPress(false);
                }
            }
            if (!playerB.isBot() && playerB.isOwn && !playerB.checklost) {
                if (e.getKeyCode() == playerB.getkeyup()) {
                    playerB.setUpPress(false);
                } else if (e.getKeyCode() == playerB.getkeydown()) {
                    playerB.setDownPress(false);
                }
            }
        }
    }

    public static void setPlaying() {
        System.out.println("Game started");
        showTitleScreen = false;
        playing = true;
    }


    public static void setOver(String side) {
        System.out.println("Game over");
        System.out.println("Player "+side+" won");
        playing = false;
        gameOver = true;
    }

    public void print(Object x) {
        System.out.println(x);
    }


}
