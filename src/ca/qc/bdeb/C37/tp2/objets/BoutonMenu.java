package ca.qc.bdeb.C37.tp2.objets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


/**
 *
 * @author jerome
 */
public class BoutonMenu extends ObjetJeu {

    String chImg;
    
    public BoutonMenu(float x, float y, IdObjet id, String chImg) {
        super(x, y, id);
        this.chImg = chImg;
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, 0, 0);
    }

    @Override
    public void setImg() {
    }
    
    
    
}
