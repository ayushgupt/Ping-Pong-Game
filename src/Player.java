/**
 * Created by quantumcoder on 4/17/2016.
 */
import java.awt.*;

public class Player
{

    private PlayerType type;

    private int playerX ;
    private int playerY ;
    private int playerWidth;
    private int playerHeight;
    private int keyup;
    private int keydown;

    //private int score;

    private boolean upPressed ;
    private boolean downPressed ;

    private boolean isBot ;


    public Player(PlayerType type, int playerX, int playerY, int playerWidth, int playerHeight, int keyup, int keydown){
        this.type = type;
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;
        this.keyup = keyup;
        this.keydown = keydown;
        upPressed = false;
        downPressed = false;
        //score = 0;
        isBot = false;
    }

    public void update(PlayerType type){
        switch(type){
            case L :
                if(isBot){
                  if(playerY > Ball.getBallY()){    // Ball is above player - move up
                    if (playerY - PongPanel.paddleSpeed > PongPanel.margin+2) {
                        playerY -= PongPanel.paddleSpeed;
                    }
                  }
                  else {
                      if (playerY + PongPanel.paddleSpeed + playerHeight < Main.SIDE-PongPanel.margin-30) {
                          playerY += PongPanel.paddleSpeed;
                      }
                  }
                }

                else {
                  if (isUp()) {
                      if (playerY - PongPanel.paddleSpeed > 0) {
                          playerY -= PongPanel.paddleSpeed;
                      }
                  }
                  if (isDown()) {
                      if (playerY + PongPanel.paddleSpeed + playerHeight < PongPanel.HEIGHT) {
                          playerY += PongPanel.paddleSpeed;
                      }
                  }
                }
                break;

            case R :
                if(isBot){
                    if(playerY > Ball.getBallY()){    // Ball is above player - move up
                        if (playerY - PongPanel.paddleSpeed > PongPanel.margin+2) {
                            playerY -= PongPanel.paddleSpeed;
                        }
                    }
                    else {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < Main.SIDE-PongPanel.margin-30) {
                            playerY += PongPanel.paddleSpeed;
                        }
                    }
                }
                else {
                    if (isUp()) {
                        if (playerY - PongPanel.paddleSpeed > 0) {
                            playerY -= PongPanel.paddleSpeed;
                        }
                    }
                    if (isDown()) {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < PongPanel.HEIGHT) {
                            playerY += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

            case T :
                if(isBot){
                    if (playerX > Ball.getBallX()) {    // Ball to left of player - move left
                        if (playerX - PongPanel.paddleSpeed > PongPanel.margin) {
                            playerX -= PongPanel.paddleSpeed;
                        }
                    }
                    else {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < Main.SIDE-PongPanel.margin-6) {
                            playerX += PongPanel.paddleSpeed;
                        }
                    }
                }
                else {
                    if (isDown()) {
                        if (playerX - PongPanel.paddleSpeed > 0) {
                            playerX -= PongPanel.paddleSpeed;
                        }
                    }
                    if (isUp()) {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < PongPanel.WIDTH) {
                            playerX += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

            case B :
                if(isBot){
                    if (playerX > Ball.getBallX()) {    // Ball to left of player - move left
                        if (playerX - PongPanel.paddleSpeed > PongPanel.margin) {
                            playerX -= PongPanel.paddleSpeed;
                        }
                    }
                    else {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < Main.SIDE-PongPanel.margin-6) {
                            playerX += PongPanel.paddleSpeed;
                        }
                    }
                }
                else {
                    if (isDown()) {
                        if (playerX - PongPanel.paddleSpeed > 0) {
                            playerX -= PongPanel.paddleSpeed;
                        }
                    }
                    if (isUp()) {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < PongPanel.WIDTH) {
                            playerX += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

        }

    }

    public void drawPaddle(Graphics g){
        switch(type){
            case L :
                g.setColor(new Color(77,26,0));
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

            case R :
                g.setColor(new Color(0, 128, 43));
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

            case T :
                g.setColor(new Color(0, 0, 153));
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

            case B :
                g.setColor(new Color(204, 0, 82));
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

        }


    }


    public int getX(){ return playerX; }
    public int getY(){ return playerY; }
    public int getWidth(){ return playerWidth; }
    public int getHeight(){ return playerHeight; }
    public int getkeyup(){ return keyup; }
    public int getkeydown(){ return keydown; }

    public void setX(int x){ playerX = x; }
    public void setY(int y){ playerY = y; }
    public void setUpPress(boolean b){ upPressed = b; }
    public void setDownPress(boolean b){ downPressed = b; }
    public boolean isUp(){ return upPressed; }
    public boolean isDown(){ return downPressed; }

    public void setBot(){ isBot = true; }
    public boolean isBot(){ return isBot; }

}
