package com.game;

import com.game.entity.Player;
import com.game.object.SuperObject;
import com.game.tile.TileManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

@Data
@Slf4j
public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // little square size
    final int scale = 3; // scale it by 3
    final int tileSize = originalTileSize * scale; // now tile size is 48x48
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    final int maxWorldRow = 50;
    final int maxWorldColumn = 50;
    final int worldWidth = tileSize * maxWorldColumn;
    final int worldHeight = tileSize * maxWorldRow;

    // FPS
    final int FPS = 60;

    final KeyBoardInput keyBoardInputHandler = new KeyBoardInput();
    Thread gameThread;
    final Player player = new Player(this, this.keyBoardInputHandler);
    final TileManager tileManager = new TileManager(this);
    final CollisionDetector collisionDetector = new CollisionDetector(this);
    final SuperObject objects[] = new SuperObject[10]; // max 10 object at the same time (key, door, chest, etc.)
    final AssetSetter assetSetter = new AssetSetter(this); // creates the objects 
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyBoardInputHandler);
        this.setFocusable(true);

        // Add focus listener to automatically request focus when lost
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Focus gained, no action needed
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Request focus back when lost
                requestFocusInWindow();
            }
        });
    }
    
    public void setupWorld() {
        assetSetter.setObjects();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    // Draw the game state to the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        tileManager.draw(g2d);
        
        for (SuperObject object : objects) {
            if (Objects.nonNull(object)) {
                object.draw(g2d, this);
            }
        }
        
        player.draw(g2d);

        g2d.dispose();
    }

    @Override
    public void run() {
        final double frameTime = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();
        double delta = 0;

        while (gameThread.isAlive()) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / frameTime;
            lastTime = currentTime;

            while (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            // Small sleep to prevent excessive CPU usage
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.error("Game thread interrupted", e);
            }
        }
    }
}
