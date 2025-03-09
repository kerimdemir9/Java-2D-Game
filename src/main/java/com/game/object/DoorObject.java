package com.game.object;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@Data
@Slf4j
public class DoorObject extends SuperObject {
    public DoorObject() {
        this.name = "Door";
        this.collision = true;
        
        try {
            this.image = ImageIO.read(new File("src/main/resources/object/door.png"));
        } catch (final IOException e) {
            log.error(e.getMessage());
        }
    }
}
