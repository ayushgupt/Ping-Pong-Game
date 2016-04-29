/**
 * Created by quantumcoder on 4/17/2016.
 */

import java.awt.*;

public class Player {

    private PlayerType type;

    private Double playerX;
    private Double playerY;
    private Double playerWidth;
    private Double playerHeight;
    private int keyup;
    private int keydown;

    //private int score;

    private boolean upPressed;
    private boolean downPressed;

    private boolean isBot;
    public boolean isOwn;
    public boolean checklost;


    public Player(PlayerType type, Double playerX, Double playerY, Double playerWidth, Double playerHeight, int keyup, int keydown) {
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
        isOwn = false;
        checklost = false;
    }

    public void update(PlayerType type) {
        switch (type) {
            case L:
                if (isBot) {
                    Double increment = Math.min(Math.abs(Ball.getBallCenterY() - this.getCenterY()), PongPanel.paddleSpeed);
                    if (this.getCenterY() > Ball.getBallCenterY()) {    // Ball is above player - move up
                        if (playerY - PongPanel.paddleSpeed > PongPanel.margin + 2) {
                            playerY -= increment;
                        }
                    } else {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < Main.SIDE - PongPanel.margin - 30) {
                            playerY += increment;
                        }
                    }
                } else {
                    if (isUp()) {
                        if (playerY - PongPanel.paddleSpeed > PongPanel.margin + 2) {
                            playerY -= PongPanel.paddleSpeed;
                        }
                    }
                    if (isDown()) {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < Main.SIDE - PongPanel.margin - 30) {
                            playerY += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

            case R:
                if (isBot) {
                    Double increment = Math.min(Math.abs(Ball.getBallCenterY() - this.getCenterY()), PongPanel.paddleSpeed);
                    if (this.getCenterY() > Ball.getBallCenterY()) {    // Ball is above player - move up
                        if (playerY - PongPanel.paddleSpeed > PongPanel.margin + 2) {
                            playerY -= increment;
                        }
                    } else {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < Main.SIDE - PongPanel.margin - 30) {
                            playerY += increment;
                        }
                    }
                } else {
                    if (isUp()) {
                        if (playerY - PongPanel.paddleSpeed > PongPanel.margin + 2) {
                            playerY -= PongPanel.paddleSpeed;
                        }
                    }
                    if (isDown()) {
                        if (playerY + PongPanel.paddleSpeed + playerHeight < Main.SIDE - PongPanel.margin - 30) {
                            playerY += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

            case T:
                if (isBot) {
                    Double increment = Math.min(Math.abs(Ball.getBallCenterX() - this.getCenterX()), PongPanel.paddleSpeed);
                    if (this.getCenterX() > Ball.getBallCenterX()) {    // Ball to left of player - move left
                        if (playerX - PongPanel.paddleSpeed > PongPanel.margin) {
                            playerX -= increment;
                        }
                    } else {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < Main.SIDE - PongPanel.margin - 6) {
                            playerX += increment;
                        }
                    }
                } else {
                    if (isDown()) {
                        if (playerX - PongPanel.paddleSpeed > PongPanel.margin) {
                            playerX -= PongPanel.paddleSpeed;
                        }
                    }
                    if (isUp()) {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < Main.SIDE - PongPanel.margin - 6) {
                            playerX += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

            case B:
                if (isBot) {
                    Double increment = Math.min(Math.abs(Ball.getBallCenterX() - this.getCenterX()), PongPanel.paddleSpeed);
                    if (this.getCenterX() > Ball.getBallCenterX()) {    // Ball to left of player - move left
                        if (playerX - PongPanel.paddleSpeed > PongPanel.margin) {
                            playerX -= increment;
                        }
                    } else {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < Main.SIDE - PongPanel.margin - 6) {
                            playerX += increment;
                        }
                    }
                } else {
                    if (isDown()) {
                        if (playerX - PongPanel.paddleSpeed > PongPanel.margin) {
                            playerX -= PongPanel.paddleSpeed;
                        }

                    }
                    if (isUp()) {
                        if (playerX + PongPanel.paddleSpeed + playerWidth < Main.SIDE - PongPanel.margin - 6) {
                            playerX += PongPanel.paddleSpeed;
                        }
                    }
                }
                break;

        }

    }

    public void drawPaddle(Graphics g) {
        switch (type) {
            case L:
                g.setColor(new Color(77, 26, 0));
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

            case R:
                g.setColor(new Color(0, 128, 43));
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

            case T:
                g.setColor(new Color(0, 0, 153));
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

            case B:
                g.setColor(new Color(204, 0, 82));
                g.fillRect(playerX.intValue(), playerY.intValue(), playerWidth.intValue(), playerHeight.intValue());
                break;

        }


    }


    public Double getX() {
        return playerX;
    }

    public Double getY() {
        return playerY;
    }

    public Double getWidth() {
        return playerWidth;
    }

    public Double getHeight() {
        return playerHeight;
    }

    public Double getCenterX() {
        return (playerX + playerWidth / 2);
    }

    public Double getCenterY() {
        return (playerY + playerHeight / 2);
    }

    public int getkeyup() {
        return keyup;
    }

    public int getkeydown() {
        return keydown;
    }

    public void setX(Double x) {
        playerX = x;
    }

    public void setY(Double y) {
        playerY = y;
    }

    public void setUpPress(boolean b) {
        upPressed = b;
    }

    public void setDownPress(boolean b) {
        downPressed = b;
    }

    public boolean isUp() {
        return upPressed;
    }

    public boolean isDown() {
        return downPressed;
    }

    public void setBot() {
        isBot = true;
    }

    public boolean isBot() {
        return isBot;
    }

}
