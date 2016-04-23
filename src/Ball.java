/**
 * Created by quantumcoder on 4/17/2016.
 */
import java.awt.Graphics;

public class Ball
{

    private int ballX;
    private int ballY;
    private int diameter;
    private int ballDeltaX;
    private int ballDeltaY;

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

    public void update() {

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
                ballDeltaX *= -1;
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
            }
        }

        //move the ball
        //System.out.println(ballDeltaX + "," + ballDeltaY);
        ballX += ballDeltaX;
        ballY += ballDeltaY;
    }


    public int getBallX(){ return ballX; }
    public int getBallY(){ return ballY; }
    public int getBallVelX(){ return ballDeltaX; }
    public int getBallVelY(){ return ballDeltaY; }

    public void setX(int x){ ballX = x; }
    public void setY(int y){ ballY = y; }

    public void drawBall(Graphics g){
        g.fillOval(ballX, ballY, diameter, diameter);
    }


}