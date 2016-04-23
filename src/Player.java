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
    }

    public void update(PlayerType type){
        switch(type){
            case L :
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
                break;

            case R :
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
                break;

            case T :
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
                break;

            case B :
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
                break;

        }

    }

    public void drawPaddle(Graphics g){
        switch(type){
            case L :
                g.setColor(Color.GREEN);
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

            case R :
                g.setColor(Color.BLUE);
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

            case T :
                g.setColor(Color.ORANGE);
                g.fillRect(playerX, playerY, playerWidth, playerHeight);
                break;

            case B :
                g.setColor(Color.PINK);
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

}

