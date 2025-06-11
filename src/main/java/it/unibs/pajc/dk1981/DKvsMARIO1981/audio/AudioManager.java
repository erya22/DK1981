package it.unibs.pajc.dk1981.DKvsMARIO1981.audio; 

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
    private static Clip backgroundClip;

    // Suona musica di gioco in loop
    public static void playBackgroundMusic(String path) {
        stopBackgroundMusic(); // ferma se già attiva
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(AudioManager.class.getResource(path));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioIn);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ferma la musica di gioco
    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }

    // Pausa (senza chiudere il Clip)
    public static void pauseBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    // Riprende da dove era stata messa in pausa
    public static void resumeBackgroundMusic() {
        if (backgroundClip != null && !backgroundClip.isRunning()) {
            backgroundClip.start();
        }
    }

    // Suona una musica **una sola volta** (senza interferire con la BGM)
    public static void playOneShotMusic(String path) {
        new Thread(() -> {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(AudioManager.class.getResource(path));
                Clip oneShotClip = AudioSystem.getClip();
                oneShotClip.open(audioIn);
                oneShotClip.start();
                // attende che termini per liberare risorse
                Thread.sleep(oneShotClip.getMicrosecondLength() / 1000);
                oneShotClip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
