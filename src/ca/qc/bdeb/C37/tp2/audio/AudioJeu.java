package ca.qc.bdeb.C37.tp2.audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Jerome
 */
public class AudioJeu {
    
    private Clip clip;
    
    public AudioJeu(String nomFichier) {
        
        try {
            File file = new File(nomFichier);
            if (file.exists()) {
                clip = AudioSystem.getClip();
                AudioInputStream audioIn =
                        AudioSystem.getAudioInputStream(file.toURI().toURL());
                clip.open(audioIn);
            }
            else {
                System.out.println("Fichier introuvable");
            }
        }
        catch (UnsupportedAudioFileException |
                LineUnavailableException | IOException e) {
            
            System.out.println(e.toString());
        }
    }
    
    public void play() {
        clip.setFramePosition(0);
        clip.loop(0);
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
    }
}
