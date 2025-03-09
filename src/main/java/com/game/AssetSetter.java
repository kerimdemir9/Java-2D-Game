package com.game;

import com.game.object.BootsObject;
import com.game.object.ChestObject;
import com.game.object.DoorObject;
import com.game.object.KeyObject;
import lombok.val;

public class AssetSetter {
    final GamePanel gamePanel;
    
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public void setObjects() {
        this.gamePanel.getObjects()[0] = new KeyObject();
        this.gamePanel.getObjects()[0].setWorldX(23 * this.gamePanel.getTileSize());       
        this.gamePanel.getObjects()[0].setWorldY(7 * this.gamePanel.getTileSize());
        
        this.gamePanel.getObjects()[1] = new KeyObject();
        this.gamePanel.getObjects()[1].setWorldX(23 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[1].setWorldY(40 * this.gamePanel.getTileSize());

        this.gamePanel.getObjects()[2] = new KeyObject();
        this.gamePanel.getObjects()[2].setWorldX(38 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[2].setWorldY(8 * this.gamePanel.getTileSize());

        this.gamePanel.getObjects()[3] = new DoorObject();
        this.gamePanel.getObjects()[3].setWorldX(10 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[3].setWorldY(11 * this.gamePanel.getTileSize());
        
        this.gamePanel.getObjects()[4] = new DoorObject();
        this.gamePanel.getObjects()[4].setWorldX(8 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[4].setWorldY(28 * this.gamePanel.getTileSize());
        
        this.gamePanel.getObjects()[5] = new DoorObject();
        this.gamePanel.getObjects()[5].setWorldX(12 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[5].setWorldY(22 * this.gamePanel.getTileSize());

        this.gamePanel.getObjects()[6] = new ChestObject();
        this.gamePanel.getObjects()[6].setWorldX(10 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[6].setWorldY(7 * this.gamePanel.getTileSize());

        this.gamePanel.getObjects()[7] = new BootsObject();
        this.gamePanel.getObjects()[7].setWorldX(37 * this.gamePanel.getTileSize());
        this.gamePanel.getObjects()[7].setWorldY(42 * this.gamePanel.getTileSize());
    }
}
