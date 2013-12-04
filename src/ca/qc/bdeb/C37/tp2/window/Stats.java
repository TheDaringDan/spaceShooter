package ca.qc.bdeb.C37.tp2.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author jerome
 */
public class Stats {
    
    float x, y;
    
    public Stats(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.orange);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(this.toString(), x, y);
    }
    
    @Override
    public String toString() {
        return "Niveau: " + Jeu.getNiveau() + " Score: " + Jeu.getScore() +
                " Vie: " + Jeu.getVie();
    }
}
