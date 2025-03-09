package com.game;

import com.game.object.KeyObject;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

@Data
public class UI {

    final GamePanel gamePanel;
    Font FONT_ARIAL;
    BufferedImage keyImage;
    boolean messageOn = false;
    String message;
    int messageCounter = 0;
    boolean gameOver = false;
    double playTime = 0.0;
    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.FONT_ARIAL = new Font("Arial", Font.PLAIN, 40);
        KeyObject keyObject = new KeyObject();
        this.keyImage = keyObject.getImage();
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics g2d) {
        g2d.setFont(FONT_ARIAL);
        g2d.setColor(Color.WHITE);

        if (gameOver) {
            String text = "You found the treasure!";
            int textLength = g2d.getFontMetrics().stringWidth(text);
            int x = this.gamePanel.getScreenWidth()/2 - textLength/2;
            int y = this.gamePanel.getScreenHeight()/2 - this.gamePanel.getTileSize()/2;
            g2d.drawString(text, x, y);
            
            text = "Your time was: " + decimalFormat.format(playTime);
            textLength = g2d.getFontMetrics().stringWidth(text);
            x = this.gamePanel.getScreenWidth()/2 - textLength/2;
            y = this.gamePanel.getScreenHeight()/2 + this.gamePanel.getTileSize()*4;
            g2d.drawString(text, x, y);
            
            g2d.setFont(g2d.getFont().deriveFont(80.0f));
            g2d.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = g2d.getFontMetrics().stringWidth(text);
            x = this.gamePanel.getScreenWidth()/2 - textLength/2;
            y = this.gamePanel.getScreenHeight()/2 + this.gamePanel.getTileSize()*2;
            g2d.drawString(text, x, y);
            
            this.gamePanel.stopGameThread();
        } else {
            g2d.drawImage(this.keyImage, this.gamePanel.getTileSize() / 2, this.gamePanel.getTileSize() / 2,
                    this.gamePanel.getTileSize(), this.gamePanel.getTileSize(), null);
            g2d.drawString(String.valueOf(this.gamePanel.getPlayer().getKeyCounter()), 80, 60);

            playTime += 1.0/60.0;
            
            if (messageOn) {
                g2d.setFont(g2d.getFont().deriveFont(30.0f));
                g2d.drawString(this.message, this.gamePanel.getTileSize() / 2, this.gamePanel.getTileSize() * 5);
                messageCounter++;
                if (messageCounter > 60) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}
