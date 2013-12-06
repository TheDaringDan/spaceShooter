package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.audio.AudioJeu;
import ca.qc.bdeb.C37.tp2.window.Vue;
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
public class TirEnnemi extends ObjetJeu{
    
    public final static int L = 13, H = 22, V = 8;
    public int ready = 0;
    
    private final AudioJeu sonTir;

    public TirEnnemi(float x, float y, IdObjet id) {
        super(x, y, id);
        setImg();
        ready = 25;
        
        sonTir = new AudioJeu("sfx/laser2.wav");
        sonTir.play();
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
        y += V;
        
        if (y > Vue.H) {
            controlleur.enleverObjet(this);
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
        File file = new File("res/baddyPew.png");
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.img = img;
    }
}
