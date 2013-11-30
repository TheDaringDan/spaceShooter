package ca.qc.bdeb.C37.tp2.window;

import java.awt.Dimension;
//import java.awt.Point;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author jerome
 */
public class Vue {
    
    /**
     * Dimensions de l'aire de jeu
     */
    public static final int L = 500, H = 700;
    
    /**
     * Constructeur.
     * 
     * @param titre
     */
    public Vue(String titre) {
        
        Jeu jeu = new Jeu();
        
        Dimension dimension = new Dimension(L, H);
        
//        BufferedImage curseur =
//                new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        
        jeu.setPreferredSize(dimension);
        jeu.setMaximumSize(dimension);
        jeu.setMinimumSize(dimension);
        
        JFrame frame = new JFrame(titre);
        
        frame.add(jeu);
//        frame.getContentPane().setCursor(Toolkit.getDefaultToolkit()
//                .createCustomCursor(curseur, new Point(0, 0), "blank"));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        jeu.start();
        
    }
    
}
