package ca.qc.bdeb.C37.tp2.window;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author jerome
 */
public class Fenetre {
    
    /**
     * Constructeur.
     * 
     * @param l largeur
     * @param h hauteur
     * @param titre
     * @param jeu 
     */
    public Fenetre(int l, int h, String titre, Jeu jeu) {
        jeu.setPreferredSize(new Dimension(l, h));
        jeu.setMaximumSize(new Dimension(l, h));
        jeu.setMinimumSize(new Dimension(l, h));
        
        JFrame frame = new JFrame(titre);
        frame.add(jeu);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        jeu.start();
    }
    
}
