package com.game.tile;

import com.game.GamePanel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Data
@Slf4j
public class TileManager {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    GamePanel gamePanel;
    
    Tile[] tiles; // tile types are stored in this array
    int[][] tileMap; // world map is stored in this 2D array
    final String worldMap = "worldMap.txt"; // world map name

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10]; // kinds of tiles
        this.tileMap = new int[this.gamePanel.getMaxWorldRow()][this.gamePanel.getMaxWorldColumn()];
        getTileImage();
        loadMap(worldMap);
    }

    public void loadMap(String mapPath) {
        try {
            // read the file from a txt into a 2D array
            File file = new File("src/main/resources/maps/".concat(mapPath));
            Scanner scanner = new Scanner(file);
            int row = 0;
            while (row < this.gamePanel.getMaxWorldRow()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                for (int column = 0; column < this.gamePanel.getMaxWorldColumn() && column < tokens.length; column++) {
                    this.tileMap[row][column] = Integer.parseInt(tokens[column]);
                }
                row++;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(new File("src/main/resources/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(new File("src/main/resources/tiles/wall.png")));
            tiles[1].setCollision(true);

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(new File("src/main/resources/tiles/water.png")));
            tiles[2].setCollision(true);

            tiles[3] = new Tile();
            tiles[3].setImage(ImageIO.read(new File("src/main/resources/tiles/earth.png")));

            tiles[4] = new Tile();
            tiles[4].setImage(ImageIO.read(new File("src/main/resources/tiles/tree.png")));
            tiles[4].setCollision(true);

            tiles[5] = new Tile();
            tiles[5].setImage(ImageIO.read(new File("src/main/resources/tiles/sand.png")));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void draw(Graphics2D g2d) {
        // Calculate the starting row and column based on player position
        int playerWorldCol = this.gamePanel.getPlayer().getWorldX() / this.gamePanel.getTileSize();
        int playerWorldRow = this.gamePanel.getPlayer().getWorldY() / this.gamePanel.getTileSize();

        // Calculate how many tiles we can display on screen
        int startCol = playerWorldCol - (this.gamePanel.getMaxScreenColumn() / 2);
        int startRow = playerWorldRow - (this.gamePanel.getMaxScreenRow() / 2);

        // Calculate end points
        int endCol = startCol + this.gamePanel.getMaxScreenColumn() + 2;
        int endRow = startRow + this.gamePanel.getMaxScreenRow() + 2;

        // Clamp the values to prevent array out of bounds
        startCol = Math.max(0, startCol);
        startRow = Math.max(0, startRow);
        endCol = Math.min(this.gamePanel.getMaxWorldColumn(), endCol);
        endRow = Math.min(this.gamePanel.getMaxWorldRow(), endRow);

        for (int worldRow = startRow; worldRow < endRow; worldRow++) {
            for (int worldCol = startCol; worldCol < endCol; worldCol++) {
                // Calculate world coordinates
                int worldX = worldCol * this.gamePanel.getTileSize();
                int worldY = worldRow * this.gamePanel.getTileSize();

                // Calculate screen coordinates
                int screenX = worldX - this.gamePanel.getPlayer().getWorldX() + this.gamePanel.getScreenWidth() / 2
                        - this.gamePanel.getTileSize() / 2;
                int screenY = worldY - this.gamePanel.getPlayer().getWorldY() + this.gamePanel.getScreenHeight() / 2
                        - this.gamePanel.getTileSize() / 2;

                // Only draw if the tile would be visible on screen
                if (screenX + this.gamePanel.getTileSize() > 0 &&
                        screenX - this.gamePanel.getTileSize() < this.gamePanel.getScreenWidth() &&
                        screenY + this.gamePanel.getTileSize() > 0 &&
                        screenY - this.gamePanel.getTileSize() < this.gamePanel.getScreenHeight()) {

                    g2d.drawImage(this.tiles[this.tileMap[worldRow][worldCol]].getImage(),
                            screenX, screenY,
                            this.gamePanel.getTileSize(), this.gamePanel.getTileSize(), null);
                }
            }
        }
    }
}
