package ca.qc.bdeb.C37.tp2.window;

import ca.qc.bdeb.C37.tp2.objets.IdCtrl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class Menu extends JFrame implements ActionListener {
    
    public static int L = 300, H = 200;
    
    private final JPanel panel, boutons;
    private JButton play, quitter, chCtrls, top;
    private JLabel ctrls;
    private final Jeu jeu;
    
    private final String instrClavier = "<html>Mouvement :<br/>flêches<br/>"
            + "<br/>Tir :<br/>espace",
            instrSouris = "<html>Mouvement :<br/>souris<br/>"
            + "<br/>Tir :<br/>clic gauche",
            instr = "<br/><br/>Contrôles :<br/>'c'<br/>"
            + "<br/>Pause :<br/>'p'</html>";
    
    public Menu(Jeu jeu) {
        this.jeu = jeu;
        
        Dimension dimension = new Dimension(L, H);
        
        panel = new JPanel(new GridLayout(1, 2));
        boutons = new JPanel(new GridLayout(4, 1));
        if (jeu.getMemCtrl() == IdCtrl.CLAVIER) {
            ctrls = new JLabel(instrClavier + instr);
        } else if (jeu.getMemCtrl() == IdCtrl.SOURIS) {
            ctrls = new JLabel(instrSouris + instr);
        }
        
        creerBouttons();
        
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        
        boutons.setPreferredSize(new Dimension(L/2, H));
        ctrls.setHorizontalAlignment(JLabel.CENTER);
        
        boutons.add(play);
        boutons.add(chCtrls);
        boutons.add(top);
        boutons.add(quitter);
        
        panel.setBackground(Color.black);
        panel.add(boutons);
        panel.add(ctrls);
        
        this.add(panel);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(50, 100);
        this.setLocationRelativeTo(jeu);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.requestFocus();
        
    }
    
    /**
     * 
     */
    private void creerBouttons() {
        play = new JButton("Play");
        quitter = new JButton("Quitter");
        chCtrls = new JButton("Contrôles");
        top = new JButton("Top Scores");
        
        play.addActionListener(this);
        quitter.addActionListener(this);
        chCtrls.addActionListener(this);
        top.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == play) {
            jeu.resume();
        }
        else if (source == chCtrls) {
            if (jeu.getMemCtrl() == IdCtrl.SOURIS) {
                jeu.setMemCtrl(IdCtrl.CLAVIER);
                ctrls.setText(instrClavier + instr);
            }
            else {
                jeu.setMemCtrl(IdCtrl.SOURIS);
                ctrls.setText(instrSouris + instr);
            }
        }
        else if (source == quitter) {
            System.exit(0);
        }
        else if (source == top) {
            ScoreBoard scores = new ScoreBoard();
        }
    }
}
