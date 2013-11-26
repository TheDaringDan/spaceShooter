package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.ControlleurObjets;
import ca.qc.bdeb.C37.tp2.window.Jeu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author jerome
 */
public class CtrlSouris implements MouseMotionListener, MouseListener {

    ControlleurObjets controlleur;
    
    public CtrlSouris (ControlleurObjets controlleur) {
        this.controlleur = controlleur;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ObjetJeu temp;
        if (Jeu.ctrl == IdCtrl.SOURIS) {
            for (int i = 0; i < controlleur.objets.size(); i++) {
                temp = controlleur.objets.get(i);

                if (temp.id == IdObjet.Joueur) {;
                    temp.setVelX(e.getX() - temp.getX());
                    temp.setVelY(e.getX() - temp.getX());
                }
                else if (temp.id == IdObjet.Pointeur) {
                    temp.setX(e.getX());
                    temp.setY(e.getY());
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
