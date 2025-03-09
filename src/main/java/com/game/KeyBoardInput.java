package com.game;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data
@Slf4j
public class KeyBoardInput implements KeyListener {

    boolean keyUp, keyDown, keyLeft, keyRight;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            this.keyUp = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            this.keyLeft = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            this.keyDown = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            this.keyRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_W) {
            this.keyUp = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            this.keyLeft = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            this.keyDown = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            this.keyRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NOT USED
    }
}
