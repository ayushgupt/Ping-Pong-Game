/**
 * Created by quantumcoder on 4/17/2016.
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Ball {

    private static Double ballX;
    private static Double ballY;
    private static Double diameter;
    private static Double ballDeltaX;
    private static Double ballDeltaY;

    private Double h;
    private Double w;

    public Ball(Double arenaHeight, Double arenaWidth, Double diameter, Double ballX, Double ballY, Double velX, Double velY) {
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
                URL url = null;
                try {
                    url = new URL("file:".concat(new File("").getAbsolutePath().concat("\\src\\ball_paddle.wav")));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // end of test
            }
        });


        //where will the ball be after it moves?
        Double nextBallLeft = ballX + ballDeltaX;
        Double nextBallRight = ballX + diameter + ballDeltaX;
        Double nextBallTop = ballY + ballDeltaY;
        Double nextBallBottom = ballY + diameter + ballDeltaY;

        //System.out.println(nextBallLeft+" "+nextBallRight+" "+nextBallTop+" "+nextBallBottom);


        // check left collision
        if (nextBallLeft < PongPanel.playerLRight) {
            if (!PongPanel.playerL.checklost) {
                //is it going to miss the paddle?
                if (nextBallTop > PongPanel.playerLBottom || nextBallBottom < PongPanel.playerLTop) {

                    PongPanel.playerLScore--;
                    if (PongPanel.playerLScore == 0) {
                        PongPanel.playerL.checklost = true;
                    }

                    ballX = Main.SIDE / 2;
                    ballY = Main.SIDE / 2;
                } else {

                    ballDeltaX *= (-1);
                    //t1.start();
                }
            } else {
                ballDeltaX *= (-1);
                //t1.start();

            }
        }

        // check right collision
        if (nextBallRight > PongPanel.playerRLeft) {
            if (!PongPanel.playerR.checklost) {
                //is it going to miss the paddle?
                if (nextBallTop > PongPanel.playerRBottom || nextBallBottom < PongPanel.playerRTop) {

                    PongPanel.playerRScore--;
                    if (PongPanel.playerRScore == 0) {
                        PongPanel.playerR.checklost = true;
                    }

                    ballX = Main.SIDE / 2;
                    ballY = Main.SIDE / 2;
                } else {
                    ballDeltaX *= -1;
                    //t1.start() ;
                }
            } else {
                ballDeltaX *= -1;
                //t1.start() ;
            }
        }

        // check top collision
        if (nextBallTop < PongPanel.playerTBottom) {
            if (!PongPanel.playerT.checklost) {
                //is it going to miss the paddle?
                if (nextBallLeft > PongPanel.playerTRight || nextBallRight < PongPanel.playerTLeft) {
                    PongPanel.playerTScore--;
                    if (PongPanel.playerTScore == 0) {
                        PongPanel.playerT.checklost = true;
                    }

                    ballX = Main.SIDE / 2;
                    ballY = Main.SIDE / 2;

                } else {

                    ballDeltaY *= -1;
                    // t1.start();
                }
            } else {
                ballDeltaY *= -1;
                // t1.start();
            }
        }

        // check bottom collision
        if (nextBallBottom > PongPanel.playerBTop) {
            if (!PongPanel.playerB.checklost) {
                //is it going to miss the paddle?
                if (nextBallLeft > PongPanel.playerBRight || nextBallRight < PongPanel.playerBLeft) {
                    PongPanel.playerBScore--;
                    if (PongPanel.playerBScore == 0) {
                        PongPanel.playerB.checklost = true;
                    }

                    ballX = Main.SIDE / 2;
                    ballY = Main.SIDE / 2;

                } else {
                    ballDeltaY *= -1;
                    // t1.start() ;
                }
            } else {
                ballDeltaY *= -1;
                // t1.start() ;
            }
        }

        if (PongPanel.playerL.checklost && PongPanel.playerR.checklost && PongPanel.playerT.checklost && PongPanel.playerB.checklost) {
            PongPanel.setOver();
        }


        //move the ball
        //System.out.println(ballDeltaX + "," + ballDeltaY);
        ballX += ballDeltaX;
        ballY += ballDeltaY;
    }


    public static Double getBallX() {
        return ballX;
    }

    public static Double getBallY() {
        return ballY;
    }

    public static Double getBallCenterX() {
        return (ballX + diameter / 2);
    }

    public static Double getBallCenterY() {
        return (ballY + diameter / 2);
    }

    public static Double getBallVelX() {
        return ballDeltaX;
    }

    public static Double getBallVelY() {
        return ballDeltaY;
    }

    public static void setX(Double x) {
        ballX = x;
    }

    public static void setY(Double y) {
        ballY = y;
    }

    public static void setVX(Double x) {
        ballDeltaX = x;
    }

    public static void setVY(Double y) {
        ballDeltaY = y;
    }

    public static void drawBall(Graphics g) {
        g.fillOval(ballX.intValue(), ballY.intValue(), diameter.intValue(), diameter.intValue());
    }


}
