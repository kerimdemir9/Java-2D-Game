package com.game;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

@Data
@Slf4j
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        this.soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        this.soundURL[1] = getClass().getResource("/sound/coin.wav");
        this.soundURL[2] = getClass().getResource("/sound/powerup.wav");
        this.soundURL[3] = getClass().getResource("/sound/unlock.wav");
        this.soundURL[4] = getClass().getResource("/sound/fanfare.wav");
    }

    public void setFile(int soundIdx) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[soundIdx]);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void playSound() {
        this.clip.start();
    }

    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound() {
        this.clip.stop();
    }
}
