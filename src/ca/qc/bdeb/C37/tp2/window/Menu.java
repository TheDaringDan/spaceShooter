package ca.qc.bdeb.C37.tp2.window;

import ca.qc.bdeb.C37.tp2.objets.IdCtrl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jerome
 */
public class Menu extends JFrame implements ActionListener {
    
    public static int L = 300, H = 150;
    
    private final JPanel panel;
    private JButton play, quitter, ctrls;
    private final Jeu jeu;
    
    public Menu(Jeu jeu) {
        this.jeu = jeu;
        
        Dimension dimension = new Dimension(L, H);
        
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        creerBouttons();
        
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        
        panel.setBackground(Color.blue);
        panel.add(play);
        panel.add(ctrls);
        panel.add(quitter);
        
        this.add(panel);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(50, 100);
        this.setLocationRelativeTo(jeu);
        this.setVisible(true);
        
    }
    
    /**
     * 
     */
    private void creerBouttons() {
        play = new JButton("Play");
        quitter = new JButton("Quitter");
        ctrls = new JButton("Contrôles");
        
        play.addActionListener(this);
        quitter.addActionListener(this);
        ctrls.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == play) {
            jeu.resume();
        }
        else if (source == quitter) {
            System.exit(1);
        }
    }
}
