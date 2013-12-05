package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.audio.AudioJeu;
import ca.qc.bdeb.C37.tp2.window.Jeu;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Danmasta97, jerome
 */
public class TirJoueur extends ObjetJeu{
    
    public final static int L = 13, H = 22, V = 12;
    private static int ready;
    
    private final static AudioJeu sonTir = new AudioJeu("sfx/laser1.wav");

    public TirJoueur(float x, float y, IdObjet id) {
        super(x, y, id);
        setImg();
        ready = 10;
        sonTir.play();
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
        y -= V;
        
        if (y < -H) {
            controlleur.enleverObjet(this);
            Jeu.decrementerScore(1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int)y, L, H, null);
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, L, H - 10);
    }

    @Override
    public void setImg() {
        File file = new File("res/playerNormalPew.png");
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.img = img;
    }
    
    public static boolean isReady(){
        return ready == 0;
    }
    
    public static void decrementerReady(){
        if(ready > 0) ready--;
    }
    
    public static void reset() {
        ready = 0;
    }
}
