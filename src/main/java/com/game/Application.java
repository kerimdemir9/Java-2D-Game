package com.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame gameWindow = new JFrame("My First 2D Game");
            GamePanel gamePanel = new GamePanel();

            // Game Window Settings
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);
            gameWindow.add(gamePanel);
            gameWindow.pack();
            gameWindow.setLocationRelativeTo(null);

            // Make sure the panel can receive focus // to get keyboard input
            gamePanel.setFocusable(true);

            // Show the window and request focus
            gameWindow.setVisible(true);
            gamePanel.requestFocusInWindow();

            // Set up the world with objects
            gamePanel.setupWorld();
            // Start the game thread
            gamePanel.startGameThread();
        });
    }
}
