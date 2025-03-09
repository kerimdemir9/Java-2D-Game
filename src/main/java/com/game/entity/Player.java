package com.game.entity;

import com.game.GamePanel;
import com.game.KeyBoardInput;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@Slf4j
public class Player extends Entity {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    GamePanel gamePanel;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    KeyBoardInput keyBoardInputHandler;

    int screenX;
    int screenY;

    int keyCounter = 0;


    public Player(GamePanel gamePanel, KeyBoardInput keyBoardInput) {
        this.gamePanel = gamePanel;
        this.keyBoardInputHandler = keyBoardInput;

        this.screenX = (this.gamePanel.getScreenWidth() - this.gamePanel.getTileSize()) / 2;
        this.screenY = (this.gamePanel.getScreenHeight() - this.gamePanel.getTileSize()) / 2;

        this.solidArea = new Rectangle();
        this.solidArea.setLocation(8, 16);
        this.solidArea.setSize(32, 32);

        this.solidAreaDefaultX = (int) this.solidArea.getLocation().getX();
        this.solidAreaDefaultY = (int) this.solidArea.getLocation().getY();

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = this.gamePanel.getTileSize() * 23;
        this.worldY = this.gamePanel.getTileSize() * 21;
        this.speed = 4;
        this.direction = "down";
    }

    public void getPlayerImage() {
        try {
            this.up1 = ImageIO.read(new File("src/main/resources/player/boy_up_1.png"));
            this.up2 = ImageIO.read(new File("src/main/resources/player/boy_up_2.png"));
            this.down1 = ImageIO.read(new File("src/main/resources/player/boy_down_1.png"));
            this.down2 = ImageIO.read(new File("src/main/resources/player/boy_down_2.png"));
            this.left1 = ImageIO.read(new File("src/main/resources/player/boy_left_1.png"));
            this.left2 = ImageIO.read(new File("src/main/resources/player/boy_left_2.png"));
            this.right1 = ImageIO.read(new File("src/main/resources/player/boy_right_1.png"));
            this.right2 = ImageIO.read(new File("src/main/resources/player/boy_right_2.png"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void update() {
        if (this.keyBoardInputHandler.isKeyUp() || keyBoardInputHandler.isKeyDown() || keyBoardInputHandler.isKeyLeft()
                || keyBoardInputHandler.isKeyRight()) {
            spriteCounter++;

            if (keyBoardInputHandler.isKeyUp()) {
                this.direction = "up";
            } else if (keyBoardInputHandler.isKeyDown()) {
                this.direction = "down";
            } else if (keyBoardInputHandler.isKeyLeft()) {
                this.direction = "left";
            } else if (keyBoardInputHandler.isKeyRight()) {
                this.direction = "right";
            }

            this.collisionOn = false;
            this.gamePanel.getCollisionDetector().checkTileForCollision(this);
            int objectIdx = this.gamePanel.getCollisionDetector().checkObjectForCollision(this, true);
            handleObject(objectIdx);

            if (!collisionOn) {
                switch (this.direction) {
                    case "up":
                        this.worldY -= speed;
                        break;
                    case "down":
                        this.worldY += speed;
                        break;
                    case "left":
                        this.worldX -= speed;
                        break;
                    case "right":
                        this.worldX += speed;
                        break;
                }
            }
        }

        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void handleObject(int index) {
        if (index != Integer.MAX_VALUE) {
            String objectName = this.gamePanel.getObjects()[index].getName();
            switch (objectName) {
                case "Key":
                    this.gamePanel.getObjects()[index] = null;
                    keyCounter++;
                    break;
                case "Door":
                    if (keyCounter > 0) {
                        this.gamePanel.getObjects()[index] = null;
                        keyCounter--;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void draw(Graphics g2d) {
        BufferedImage image = null;

        switch (this.direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = this.up1;
                }
                if (spriteNumber == 2) {
                    image = this.up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = this.down1;
                }
                if (spriteNumber == 2) {
                    image = this.down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = this.left1;
                }
                if (spriteNumber == 2) {
                    image = this.left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = this.right1;
                }
                if (spriteNumber == 2) {
                    image = this.right2;
                }
                break;
        }
        g2d.drawImage(image, this.screenX, this.screenY, this.gamePanel.getTileSize(), this.gamePanel.getTileSize(), null);
    }
}
