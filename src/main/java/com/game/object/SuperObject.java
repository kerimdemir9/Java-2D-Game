package com.game.object;

import com.game.GamePanel;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    
    
    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = this.worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = this.worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (this.worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                this.worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                this.worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                this.worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {
            g2d.drawImage(this.image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }
}
