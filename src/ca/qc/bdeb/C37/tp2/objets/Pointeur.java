package ca.qc.bdeb.C37.tp2.objets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jerome
 */
public class Pointeur extends ObjetJeu {
    
    public final int L = 15, H = 15;

    public Pointeur(float x, float y, IdObjet id) {
        super(x, y, id);
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int)y, L, H, null);
    }

    @Override
    public Rectangle contact() {
        return null;
    }

    @Override
    public void setImg() {
        File file = new File("res/curseur.png");
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.img = img;
    }
    
    @Override
    public float getX() {
        return x + L/2;
        
    }
    
    @Override
    public float getY() {
        return y + H/2;
        
    }
}
