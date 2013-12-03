package ca.qc.bdeb.C37.tp2.window;

import static ca.qc.bdeb.C37.tp2.window.Menu.H;
import static ca.qc.bdeb.C37.tp2.window.Menu.L;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jerome
 */
public class EcranGameOver extends JFrame implements ActionListener {

    private final int L = 200, H = 60;
    
    private final Jeu jeu;
    
    private final JPanel panel;
    
    private final JLabel message;
    
    private final JButton quitter;
    
    public EcranGameOver(Jeu jeu, int score) {
        this.jeu = jeu;
        
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
            System.exit(1);
        }
    }
    
}
