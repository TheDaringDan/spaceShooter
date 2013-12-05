package ca.qc.bdeb.C37.tp2.window;

import ca.qc.bdeb.C37.tp2.audio.AudioJeu;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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
    public static final int L = 500, H = 710;
    
    private Jeu jeu;
    
    private final Dimension dimension;
    
    private final JFrame frame;
    
    /**
     * Constructeur.
     * 
     * @param titre
     */
    public Vue(String titre) {
        
        
        dimension = new Dimension(L, H);
        
        BufferedImage curseur =
                new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        
        
        frame = new JFrame(titre);
        
        startJeu();
        
        frame.setSize(L, H);
        
        frame.add(jeu);
        
        frame.getContentPane().setCursor(Toolkit.getDefaultToolkit()
                .createCustomCursor(curseur, new Point(0, 0), "blank"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public final void startJeu() {
        jeu = new Jeu();
        
        jeu.setPreferredSize(dimension);
        jeu.setMaximumSize(dimension);
        jeu.setMinimumSize(dimension);
        
        jeu.start();
    }
    
    /**
     * Classe qui sépare une 'sprite sheet' en tableau d'images Trouvé en ligne
     * : http://www.javalobby.org/articles/ultimate-image/ dans la section
     * 'Splitting Images'
     *
     * @param img La 'sprite sheet' au complet
     * @param cols Le nombre de collones dans la 'sprite sheet'
     * @param rows Le nombre de rangées dans la 'sprite sheet'
     * @return Un tableau d'images (sprites)
     */
    public static BufferedImage[] splitImage(BufferedImage img, int cols,
            int rows) {
        int w = img.getWidth() / cols;
        int h = img.getHeight() / rows;
        int num = 0;
        BufferedImage imgs[] = new BufferedImage[w * h];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                imgs[num] = new BufferedImage(w, h, img.getType());
                // Tell the graphics to draw only one block of the image  
                Graphics2D g = imgs[num].createGraphics();
                g.drawImage(img, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h, null);
                g.dispose();
                num++;
            }
        }
        return imgs;
    }
}
