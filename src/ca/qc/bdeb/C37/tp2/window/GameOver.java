package ca.qc.bdeb.C37.tp2.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jerome
 */
public class GameOver extends JFrame implements ActionListener {

    private final int L = 200, H = 90;
    
    private final int score;
    
    private final Jeu jeu;
    
    private final JPanel panel;
    
    private final JLabel message;
    
    private JButton quitter, top;
    
    public GameOver(Jeu jeu, int score) {
        this.jeu = jeu;
        this.score = score;
        
        try {
            ScoreBoard.enregistrerScore(score);
        } catch (IOException ex) {
            System.out.println("Erreur de lecture du fichier.");
        }
        
        Dimension dimension = new Dimension(L, H);
        
        panel = new JPanel(new BorderLayout(0, 5));
        message = new JLabel("<html>Game Over...<br/>Score: " + score +
                "</html>");
        message.setHorizontalAlignment(JLabel.CENTER);
        
        creerBoutons();
        
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        
        panel.setBackground(Color.black);
        
        panel.add(message, BorderLayout.NORTH);
        panel.add(top, BorderLayout.CENTER);
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
    
    private void creerBoutons() {
        top = new JButton("Top Scores");
        top.addActionListener(this);
        
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == top) {
            ScoreBoard scores = new ScoreBoard();
        }
        if (e.getSource() == quitter) {
            System.exit(0);
        }
    }
    
    
    
}
