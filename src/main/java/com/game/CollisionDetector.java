package com.game;

import com.game.entity.Entity;
import com.game.object.SuperObject;
import lombok.*;

import java.util.Objects;

@Data
public class CollisionDetector {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    GamePanel gamePanel;

    public CollisionDetector(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int checkObjectForCollision(Entity entity, boolean player) {
        int index = Integer.MAX_VALUE;

        for (int i = 0; i < this.gamePanel.getObjects().length; i++) {
            SuperObject object = this.gamePanel.getObjects()[i];

            if (Objects.nonNull(object)) {
                entity.getSolidArea().setLocation
                        (entity.getWorldX() + (int) entity.getSolidArea().getX(),
                                entity.getWorldY() + (int) entity.getSolidArea().getY());

                object.getSolidArea().setLocation
                        (object.getWorldX() + (int) object.getSolidArea().getX(),
                                object.getWorldY() + (int) object.getSolidArea().getY());

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().setLocation
                                ((int) entity.getSolidArea().getX(),
                                        (int) entity.getSolidArea().getY() - entity.getSpeed());
                        if (entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            } 
                        }
                        break;
                    case "down":
                        entity.getSolidArea().setLocation
                                ((int) entity.getSolidArea().getX(),
                                        (int) entity.getSolidArea().getY() + entity.getSpeed());
                        if (entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().setLocation
                                ((int) entity.getSolidArea().getX() - entity.getSpeed(),
                                        (int) entity.getSolidArea().getY());
                        if (entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().setLocation
                                ((int) entity.getSolidArea().getX() + entity.getSpeed(),
                                        (int) entity.getSolidArea().getY());
                        if (entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().setLocation(entity.getSolidAreaDefaultX(), entity.getSolidAreaDefaultY());
                object.getSolidArea().setLocation(entity.getSolidAreaDefaultX(), entity.getSolidAreaDefaultY());
            }
        }
        return index;
    }


    public void checkTileForCollision(Entity entity) {
        int entityWorldLeftX = entity.getWorldX() + (int) entity.getSolidArea().getLocation().getX();
        int entityWorldRightX = entity.getWorldX() + (int) entity.getSolidArea().getLocation().getX() + (int) entity.getSolidArea().getSize().getWidth();

        int entityWorldTopY = entity.getWorldY() + (int) entity.getSolidArea().getLocation().getY();
        int entityWorldBottomY = entity.getWorldY() + (int) entity.getSolidArea().getLocation().getY() + (int) entity.getSolidArea().getSize().getHeight();

        int entityLeftColumn = entityWorldLeftX / this.gamePanel.getTileSize();
        int entityRightColumn = entityWorldRightX / this.gamePanel.getTileSize();

        int entityTopRow = entityWorldTopY / this.gamePanel.getTileSize();
        int entityBottomRow = entityWorldBottomY / this.gamePanel.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityWorldTopY - entity.getSpeed()) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().getTileMap()[entityTopRow][entityLeftColumn];
                tileNum2 = this.gamePanel.getTileManager().getTileMap()[entityTopRow][entityRightColumn];

                if (this.gamePanel.getTileManager().getTiles()[tileNum1].isCollision() ||
                        this.gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityWorldBottomY + entity.getSpeed()) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().getTileMap()[entityBottomRow][entityLeftColumn];
                tileNum2 = this.gamePanel.getTileManager().getTileMap()[entityBottomRow][entityRightColumn];

                if (this.gamePanel.getTileManager().getTiles()[tileNum1].isCollision() ||
                        this.gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftColumn = (entityWorldLeftX - entity.getSpeed()) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().getTileMap()[entityTopRow][entityLeftColumn];
                tileNum2 = this.gamePanel.getTileManager().getTileMap()[entityBottomRow][entityRightColumn];

                if (this.gamePanel.getTileManager().getTiles()[tileNum1].isCollision() ||
                        this.gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightColumn = (entityWorldRightX + entity.getSpeed()) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().getTileMap()[entityTopRow][entityLeftColumn];
                tileNum2 = this.gamePanel.getTileManager().getTileMap()[entityBottomRow][entityRightColumn];

                if (this.gamePanel.getTileManager().getTiles()[tileNum1].isCollision() ||
                        this.gamePanel.getTileManager().getTiles()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}
