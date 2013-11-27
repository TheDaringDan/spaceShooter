package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Jeu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author jerome
 */
public class CtrlClavier extends KeyAdapter {
    
    ControlleurObjets controlleur;
    
    public CtrlClavier (ControlleurObjets controlleur) {
        this.controlleur = controlleur;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int touche = e.getKeyCode();
        ObjetJeu temp;
        
        if (Jeu.ctrl == IdCtrl.CLAVIER) {
            for (int i = 0; i < controlleur.objets.size(); i++) {

                temp = controlleur.objets.get(i);

                if (temp.getId() == IdObjet.Joueur) {
                    if (touche == KeyEvent.VK_RIGHT) {
                        temp.setVelX(1);
                    }
                    else if (touche == KeyEvent.VK_LEFT) {
                        temp.setVelX(-1);
                    }

                    if (touche == KeyEvent.VK_DOWN) {
                        temp.setVelY(1);
                    }
                    else if (touche == KeyEvent.VK_UP) {
                        temp.setVelY(-1);
                    }
                }
            }
        }
        
        // Quitter
        if (touche == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
        
        // Changer les contrôles
        if (touche == KeyEvent.VK_C) {
            if (Jeu.ctrl == IdCtrl.CLAVIER) {
                Jeu.ctrl = IdCtrl.SOURIS;
            }
            else if (Jeu.ctrl == IdCtrl.SOURIS) {
                Jeu.ctrl = IdCtrl.CLAVIER;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int touche = e.getKeyCode();
        
        for (int i = 0; i < controlleur.objets.size(); i++) {
            ObjetJeu temp = controlleur.objets.get(i);
            
            if (temp.getId() == IdObjet.Joueur) {
                if (touche == KeyEvent.VK_RIGHT) {
                    temp.setVelX(0);
                }
                else if (touche == KeyEvent.VK_LEFT) {
                    temp.setVelX(0);
                }
                
                if (touche == KeyEvent.VK_DOWN) {
                    temp.setVelY(0);
                }
                else if (touche == KeyEvent.VK_UP) {
                    temp.setVelY(0);
                }
            }
        }
    }
}
