package ca.qc.bdeb.C37.tp2.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jerome
 */
public class GameOver extends JFrame implements ActionListener {

    private final int L = 200, H = 60;
    
    private int score;
    
    private final Jeu jeu;
    
    private final JPanel panel;
    
    private final JLabel message;
    
    private final JButton quitter;
    
    public GameOver(Jeu jeu, int score) {
        this.jeu = jeu;
        this.score = score;
        
        try {
            enregistrerScore();
        } catch (IOException ex) {
            System.out.println("Erreur de lecture du fichier.");
        }
        
        Dimension dimension = new Dimension(L, H);
        
        panel = new JPanel(new BorderLayout(0, 5));
        message = new JLabel("<html>Game Over...<br/>Score: " + score +
                "</html>");
        message.setHorizontalAlignment(JLabel.CENTER);
        
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        
        panel.setBackground(Color.black);
        
        panel.add(message, BorderLayout.NORTH);
        panel.add(quitter, BorderLayout.SOUTH);
        
        this.add(panel);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(50, 100);
        this.setLocationRelativeTo(jeu);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.requestFocus();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            System.exit(0);
        }
    }
    
    private void enregistrerScore() throws FileNotFoundException, IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("data/sb.txt"));
        int[] tabScores = new int[10];
        try {
            int temp;
            int temp2;
            String ligne = br.readLine();

            for (int i = 0; i < 10 && ligne != null; i++) {
                tabScores[i] = Integer.parseInt(ligne);
                ligne = br.readLine();
            }
            
            temp = tabScores[0];
            
            for (int i = 0; i < tabScores.length; i++) {
                if (score > tabScores[i]) {
                    temp = tabScores[i];
                    tabScores[i] = score;
                    score = 0;
                }
                else {
                    temp2 = tabScores[i];
                    tabScores[i] = temp;
                    temp = temp2;
                }
            }
            
        }
        finally {
            br.close();
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/sb.txt"));
        try {
            String ligne;
            for (int i = 0; i < tabScores.length; i++) {
                ligne = tabScores[i] + "\n";
                bw.write(ligne);
            }
        }
        finally {
            bw.close();
        }
    }
    
}
