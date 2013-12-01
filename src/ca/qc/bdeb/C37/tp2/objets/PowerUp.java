package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Jeu;
import ca.qc.bdeb.C37.tp2.window.Vue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author jerome
 */
public class PowerUp extends ObjetJeu {
    
    public static final int H = 40, L = 40, V = 6;
    public PowerUp(float x, float y, IdObjet id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
        this.y += V;
        
        
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if (this.id == IdObjet.PowerUpBleu) {
            g.setColor(Color.blue);
        }
        if (this.id == IdObjet.PowerUpVert) {
            g.setColor(Color.green);
        }
        if (this.id == IdObjet.PowerUpRouge) {
            g.setColor(Color.red);
        }
        
        g2d.draw(contact());
    }
    
    private void gererCollision(LinkedList<ObjetJeu> objets) {
        for (int i = 0; i < objets.size(); i++) {
            ObjetJeu temp = objets.get(i);
            
            if (temp.getId() == IdObjet.Joueur) {
                if (this.id == IdObjet.PowerUpBleu) {
                    Jeu.incrementerScore(10);
                    Jeu.gagnerVie(10);
                }
                if (this.id == IdObjet.PowerUpVert) {
                    Jeu.incrementerScore(30);
                    Jeu.gagnerVie(20);
                }
                if (this.id == IdObjet.PowerUpRouge) {
                    Jeu.incrementerScore(50);
                    Jeu.gagnerVie(30);
                }
                
                detruireEnnemis(objets);
            }
        }
    }
    
    private void detruireEnnemis(LinkedList<ObjetJeu> objets) {
        for (int j = 0; j < objets.size(); j++) {
            if (objets.get(j).getId() == IdObjet.Ennemi) {
                objets.remove(j);
            }
        }
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, H, L);
    }

    @Override
    public void setImg() {
    }
    
}
