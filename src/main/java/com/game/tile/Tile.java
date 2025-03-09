package com.game.tile;

import lombok.Data;
import java.awt.image.BufferedImage;

@Data
public class Tile {
    BufferedImage image;
    boolean collision = false;
}
