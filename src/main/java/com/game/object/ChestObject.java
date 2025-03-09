package com.game.object;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@Data
@Slf4j
public class ChestObject extends SuperObject {
    public ChestObject() {
        this.name = "Chest";
        this.collision = true;
        try {
            this.image = ImageIO.read(new File("src/main/resources/object/chest.png"));
        } catch (final IOException e) {
            log.error(e.getMessage());
        }
    }
}
