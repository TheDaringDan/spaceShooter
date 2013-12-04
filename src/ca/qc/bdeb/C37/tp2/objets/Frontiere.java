package ca.qc.bdeb.C37.tp2.objets;

//import java.awt.Color;
//import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author jerome
 */
public class Frontiere extends ObjetJeu {

    // Dimensions de la frontière
    private final int l, h;
    
    ControlleurObjets controlleur;
    
    public Frontiere(float x, float y, ControlleurObjets controlleur,
            IdObjet id, int l, int h) {
        
        super(x, y, id);
        this.l = l;
        this.h = h;
        this.controlleur = controlleur;
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
        
    }

    @Override
    public void render(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.red);
//        g2d.draw(contact());
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, l, h);
    }

    @Override
    public void setImg() {
        
    }
    
}
