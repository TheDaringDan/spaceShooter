package ca.qc.bdeb.C37.tp2.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Jerome
 */
@SuppressWarnings("restriction")
public class Audio implements Runnable {

    private Thread thread;
    
    @Override
    public void run() {
        thread = new Thread(this);
        thread.start();
        
        jouerMusique("sfx/555974_reedz.mp3");
    }
    
    public void jouerSon(String nomFichier) {
        FileInputStream fichier;
        
        try {
            fichier = new FileInputStream(new File(nomFichier));
            AudioStream son;
            son = new AudioStream(fichier);
            AudioPlayer.player.start(son);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
     public void jouerMusique(String nomFichier) {
        FileInputStream fichier;
        
        AudioPlayer jukebox = AudioPlayer.player;

        ContinuousAudioDataStream loop = null;
        try {
            fichier = new FileInputStream(nomFichier);
            AudioStream musique = new AudioStream(fichier);
            
            AudioData infoMusique = musique.getData();
            loop = new ContinuousAudioDataStream(infoMusique);
        }
        catch(IOException e) {
            System.out.println(e.toString());
        }

        // play background music.
        jukebox.start(loop);
    }
}
