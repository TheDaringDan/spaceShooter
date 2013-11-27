package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.objets.Frontiere;
import ca.qc.bdeb.C37.tp2.objets.IdObjet;
import ca.qc.bdeb.C37.tp2.objets.ObjetJeu;
import ca.qc.bdeb.C37.tp2.window.Vue;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author jerome
 */
public class ControlleurObjets {
    
    public LinkedList<ObjetJeu> objets = new LinkedList<>();
    
    private ObjetJeu objetTemp;
    
    public void tick() {
        for (int i = 0; i < objets.size(); i++) {
            objetTemp = objets.get(i);
            
            objetTemp.tick(objets);
        }
    }
    
    public void render(Graphics g) {
        for (int i = 0; i < objets.size(); i++) {
            objetTemp = objets.get(i);
            
            objetTemp.render(g);
        }
        
    }
    
    public void ajouterObjet(ObjetJeu objet) {
        this.objets.add(objet);
    }
    
    public void enleverObjet(ObjetJeu objet) {
        this.objets.remove(objet);
    }
    
    public ObjetJeu getObjet(IdObjet id) {
        ObjetJeu temp;
        for (int i = 0; i < objets.size(); i++) {
            if (objets.get(i).getId() == id) {
                return objets.get(i);
            }
        }
        return null;
    }
    
    public void dessinerFrontieres() {
        // Haut
        ajouterObjet(new Frontiere(0, Vue.H*2/3, this,
                IdObjet.Frontiere, Vue.L, 2));
        
        // Bas
        ajouterObjet(new Frontiere(0, Vue.H-2, this,
                IdObjet.Frontiere, Vue.L, 2));
        
        // Gauche
        ajouterObjet(new Frontiere(0, Vue.H*2/3, this,
                IdObjet.Frontiere, 2, Vue.H/3));
        
        // Droite
        ajouterObjet(new Frontiere(Vue.L-2, Vue.H*2/3, this,
                IdObjet.Frontiere, 2, Vue.H/3));
    }
}
