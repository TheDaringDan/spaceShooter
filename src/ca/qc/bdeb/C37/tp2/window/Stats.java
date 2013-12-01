package ca.qc.bdeb.C37.tp2.window;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author jerome
 */
public class Stats {
    
    Jeu jeu;
    
    float x, y;
    
    public Stats(float x, float y, Jeu jeu) {
        this.x = x;
        this.y = y;
        this.jeu = jeu;
    }
    
    public void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(this.toString(), x, y);
    }
    
    @Override
    public String toString() {
        return "Niveau: " + jeu.getNiveau() + " Score: " + jeu.getScore() +
                " Vie: " + jeu.getVie();
    }
}
