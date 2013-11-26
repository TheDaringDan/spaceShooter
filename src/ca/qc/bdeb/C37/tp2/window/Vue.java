package ca.qc.bdeb.C37.tp2.window;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author jerome
 */
public class Vue {
    
    /**
     * Constructeur.
     * 
     * @param l largeur
     * @param h hauteur
     * @param titre
     * @param jeu 
     */
    public Vue(int l, int h, String titre, Jeu jeu) {
        
        BufferedImage curseur =
                new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        
        jeu.setPreferredSize(new Dimension(l, h));
        jeu.setMaximumSize(new Dimension(l, h));
        jeu.setMinimumSize(new Dimension(l, h));
        
        JFrame frame = new JFrame(titre);
        frame.add(jeu);
        frame.getContentPane().setCursor(Toolkit.getDefaultToolkit()
                .createCustomCursor(curseur, new Point(0, 0), "blank"));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        jeu.start();
    }
    
}
