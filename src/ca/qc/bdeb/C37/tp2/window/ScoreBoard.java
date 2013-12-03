package ca.qc.bdeb.C37.tp2.window;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jerome
 */
public class ScoreBoard extends JFrame {

    private final int L = 200, H = 200;
    
    private final JPanel panel;
    
    private JLabel scores;
    
    public ScoreBoard() {
        Dimension dimension = new Dimension(L, H);
        
        panel = new JPanel();
        
        try {
            scores = new JLabel(lireFichier());
        }
        catch (IOException ex) {
            scores = new JLabel("Erreur de lecture du fichier.");
        }
        
        scores.setHorizontalAlignment(JLabel.CENTER);
        
        this.add(panel);
        
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        
        panel.add(scores);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.requestFocus();
    }
    
    private String lireFichier() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/sb.txt"));
        int compteur = 0;
        try {
            StringBuilder sb = new StringBuilder();
            String ligne = br.readLine();
            
            sb.append("<html>");

            while (ligne != null && ++compteur <= 10) {
                sb.append(String.format("%02d", compteur));
                sb.append(". ");
                sb.append(ligne);
                sb.append("<br/>");
                ligne = br.readLine();
            }
            
            while (++compteur <= 10) {
                sb.append(String.format("%02d", compteur));
                sb.append(". ---<br/>");
            }
            
            sb.append("</html>");
            
            return sb.toString();
        }
        finally {
            br.close();
        }
    }
    
}
