package com.game.object;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.File;

@Slf4j
public class BootsObject extends SuperObject {
    public BootsObject() {
        this.name = "Boots";
        try {
            this.image = ImageIO.read(new File("src/main/resources/object/boots.png"));
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
    }
}
