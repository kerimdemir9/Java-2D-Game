package com.game.entity;


import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public class Entity {
    int worldX, worldY;
    int speed;

    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    String direction;

    int spriteCounter = 0;
    int spriteNumber = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
