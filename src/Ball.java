/**
 * Created by quantumcoder on 4/17/2016.
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Ball
{

    private static int ballX;
    private static int ballY;
    private static int diameter;
    private static int ballDeltaX;
    private static int ballDeltaY;

    private int h;
    private int w;

    public Ball(int arenaHeight, int arenaWidth,int diameter, int ballX, int ballY, int velX, int velY ) {
        this.h = arenaHeight;
        this.w = arenaWidth;
        this.diameter = diameter;
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballDeltaX = velX;
        this.ballDeltaY = velY;
    }

    public static void update() {
          //thread for sound
    	  Thread t1 = new Thread(new Runnable() {
			     public void run() {
			    	 URL url=null ;
			    	 try {
		        			url = new URL("file:C:\\E-drive-73865609\\COP290\\Assignment3\\ass3\\src\\ball_paddle.wav");
		        		} catch (MalformedURLException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		                AudioClip clip = Applet.newAudioClip(url);
		                clip.play();
		        		try {
		        			Thread.sleep(10000);
		        		} catch (InterruptedException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        		// end of test
			     }
			});


        //where will the ball be after it moves?
        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + diameter + ballDeltaX;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + diameter + ballDeltaY;

        //System.out.println(nextBallLeft+" "+nextBallRight+" "+nextBallTop+" "+nextBallBottom);


        // check left collision
        if (nextBallLeft < PongPanel.playerLRight) {
            //is it going to miss the paddle?
            if (nextBallTop > PongPanel.playerLBottom || nextBallBottom < PongPanel.playerLTop) {

                PongPanel.playerLScore --;
                if (PongPanel.playerLScore == 0) {
                    PongPanel.setOver();
                }

                ballX = Main.SIDE/2;
                ballY = Main.SIDE/2;
            }
            else {

                ballDeltaX *= (-1);
     			t1.start();





            }
        }

        // check right collision
        if (nextBallRight > PongPanel.playerRLeft) {
            //is it going to miss the paddle?
            if (nextBallTop > PongPanel.playerRBottom || nextBallBottom < PongPanel.playerRTop) {

                PongPanel.playerRScore --;
                if (PongPanel.playerRScore == 0) {
                    PongPanel.setOver();
                }

                ballX = Main.SIDE/2;
                ballY = Main.SIDE/2;
            }
            else {
                ballDeltaX *= -1;
                t1.start() ;
            }
        }

        // check top collision
        if (nextBallTop < PongPanel.playerTBottom) {
            //is it going to miss the paddle?
            if (nextBallLeft > PongPanel.playerTRight || nextBallRight < PongPanel.playerTLeft){
                PongPanel.playerTScore --;
                if (PongPanel.playerTScore == 0) {
                    PongPanel.setOver();
                }

                ballX = Main.SIDE/2;
                ballY = Main.SIDE/2;

            }
            else {


                ballDeltaY *= -1;
                t1.start();
            }
        }

        // check bottom collision
        if (nextBallBottom > PongPanel.playerBTop) {
            //is it going to miss the paddle?
            if (nextBallLeft > PongPanel.playerBRight || nextBallRight < PongPanel.playerBLeft){
                PongPanel.playerBScore --;
                if (PongPanel.playerBScore == 0) {
                    PongPanel.setOver();
                }

                ballX = Main.SIDE/2;
                ballY = Main.SIDE/2;

            }
            else {
                ballDeltaY *= -1;
                t1.start() ;
            }
        }

        //move the ball
        //System.out.println(ballDeltaX + "," + ballDeltaY);
        ballX += ballDeltaX;
        ballY += ballDeltaY;
    }


    public static int getBallX(){ return ballX; }
    public static int getBallY(){ return ballY; }
    public static int getBallVelX(){ return ballDeltaX; }
    public static int getBallVelY(){ return ballDeltaY; }

    public static void setX(int x){ ballX = x; }
    public static void setY(int y){ ballY = y; }

    public static void drawBall(Graphics g){
        g.fillOval(ballX, ballY, diameter, diameter);
    }


}
