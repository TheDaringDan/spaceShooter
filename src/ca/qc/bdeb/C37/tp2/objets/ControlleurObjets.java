package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Vue;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author jerome
 */
public class ControlleurObjets {
    
    public LinkedList<ObjetJeu> objets = new LinkedList<>();
    
    private ObjetJeu objetTemp;
    
    int niveau = 1;
    private Random rand = new Random();
    private int mobs;
    private int mobsToSpawn;
    private int timer = rand.nextInt(300);
    private int ready = 0;
    
    public void tick() {
        for (int i = 0; i < objets.size(); i++) {
            objetTemp = objets.get(i);
            
            objetTemp.tick(objets);
            if (objetTemp.getId() == IdObjet.TirNormal && objetTemp.y < 0) {
                enleverObjet(objetTemp);
            } else if (objetTemp.getId() == IdObjet.Ennemi && objetTemp.y > Vue.H) {
                objets.get(i).y = 20;
            }
            
            if(objetTemp.getId() == IdObjet.Ennemi){
                Ennemi ennemi = (Ennemi) objetTemp;
               
            }
        }
        if (!TirJoueur.isReady()) {
            TirJoueur.decrementerReady();
        }
    }
    
    public void render(Graphics g) {
        int remainingMobs = 0;
        ObjetJeu joueur = null;
        Ennemi baddy;
        for (int i = 0; i < objets.size(); i++) {
            if(objets.get(i).getId() == IdObjet.Joueur){
                joueur = objets.get(i);
            }
        }
        for (int i = 0; i < objets.size(); i++) {
            objetTemp = objets.get(i);
            objetTemp.render(g);
            if (objetTemp.getId() == IdObjet.Ennemi) {
                ++remainingMobs; 
            }
        }
        
        if (remainingMobs == 0) {
            ++niveau;
            spawnMobs();
            mobs = 0;
        }
        
        --timer;
        if(mobs < mobsToSpawn && timer <= 0){
            this.ajouterObjet(new Ennemi(rand.nextFloat() * 
                    (Vue.L - 50f) + 25f, this, IdObjet.Ennemi));
            ++mobs;
            timer = rand.nextInt(300);
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
    
    public void spawnMobs(){
        mobsToSpawn = niveau * 10 % 13;
    }
}
