package ca.qc.bdeb.C37.tp2.objets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author jerome
 */
public class Pointeur extends ObjetJeu {

    public Pointeur(float x, float y, IdObjet id) {
        super(x, y, id);
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(contact());
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, 1, 1);
    }

    @Override
    public void setImg() {
    }
    
}
