/**
 * Created by quantumcoder on 4/17/2016.
 */
import java.awt.*;

public class Player
{

    private PlayerType type;

    private Double playerX ;
    private Double playerY ;
    private Double playerWidth;
    private Double playerHeight;
    private int keyup;
    private int keydown;

    //private int score;

    private boolean upPressed ;
    private boolean downPressed ;

    private boolean isBot ;


    public Player(PlayerType type, Double playerX, Double playerY, Double playerWidth, Double playerHeight, int keyup, int keydown){
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
                  if(this.getCenterY() > Ball.getBallCenterY()){    // Ball is above player - move up
                    if (playerY - PongPanel.paddleSpeed > 0) {
                        playerY -= PongPanel.paddleSpeed;
                    }
                  }
                  else {
                      if (playerY + PongPanel.paddleSpeed + playerHeight < PongPanel.HEIGHT) {
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
                    if(this.getCenterY() > Ball.getBallCenterY()){    // Ball is above player - move up
                        if (playerY - PongPanel.paddleSpeed > 0) {
                            playerY -= PongPanel.paddleSpeed;
                        }
                    }
                    else {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < PongPanel.HEIGHT) {
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
                    if (this.getCenterX() > Ball.getBallCenterX()) {    // Ball to left of player - move left
                        if (playerX - PongPanel.paddleSpeed > 0) {
                            playerX -= PongPanel.paddleSpeed;
                        }
                    }
                    else {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < PongPanel.WIDTH) {
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
                    if (this.getCenterX() > Ball.getBallCenterX()) {    // Ball to left of player - move left
                        if (playerX - PongPanel.paddleSpeed > 0) {
                            playerX -= (this.getCenterX() - Ball.getBallCenterX())*0.8;
                        }
                    }
                    else {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < PongPanel.WIDTH) {
                            playerX += (Ball.getBallCenterX() - this.getCenterX())*0.8;
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
                g.setColor(Color.GREEN);
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

            case R :
                g.setColor(Color.BLUE);
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

            case T :
                g.setColor(Color.ORANGE);
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

            case B :
                g.setColor(Color.PINK);
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

        }


    }


    public Double getX(){ return playerX; }
    public Double getY(){ return playerY; }
    public Double getWidth(){ return playerWidth; }
    public Double getHeight(){ return playerHeight; }

    public Double getCenterX(){ return (playerX+playerWidth); }
    public Double getCenterY(){ return (playerY+playerHeight); }

    public int getkeyup(){ return keyup; }
    public int getkeydown(){ return keydown; }

    public void setX(Double x){ playerX = x; }
    public void setY(Double y){ playerY = y; }
    public void setUpPress(boolean b){ upPressed = b; }
    public void setDownPress(boolean b){ downPressed = b; }
    public boolean isUp(){ return upPressed; }
    public boolean isDown(){ return downPressed; }

    public void setBot(){ isBot = true; }
    public boolean isBot(){ return isBot; }

}
