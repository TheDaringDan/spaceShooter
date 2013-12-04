package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Jeu;
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
    
    private Random rand = new Random();
    private int conteurMobs;
    private int mobsToSpawn;
    private int mobTimer = 1;
        
    private boolean pUpSpawned = false;
    
    /**
     * 
     */
    public void tick() {
            
        conteurMobs = 0;
        
        for (int i = 0; i < objets.size(); i++) {
            objetTemp = objets.get(i);
            objetTemp.tick(this);
            
            if(objetTemp.getId() == IdObjet.EnnemiNormal){
                
                ++conteurMobs; 
            }
        }
        
        if (!TirJoueur.isReady()) {
            TirJoueur.decrementerReady();
        }
        
        gererMobs();
        
        spawnPowerUps();
        
    }
    
    /**
     * 
     * @param g 
     */
    public void render(Graphics g) {
        for (int i = 0; i < objets.size(); i++) {
            objetTemp = objets.get(i);
            objetTemp.render(g);
        }
    }
    
    /**
     * 
     * @param objet 
     */
    public void ajouterObjet(ObjetJeu objet) {
        this.objets.add(objet);
    }
    
    /**
     * 
     * @param objet 
     */
    public void enleverObjet(ObjetJeu objet) {
        if (objet.getId() == IdObjet.EnnemiNormal) {
            Jeu.incrementerScore(10 + Jeu.getNiveau());
        }
        else if (objet.getId() == IdObjet.EnnemiZigZag) {
            Jeu.incrementerScore(10 + Jeu.getNiveau() * 2);
        }
        this.objets.remove(objet);
    }
    
    /**
     * 
     */
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
    
    /**
     * 
     */
    public void gererMobs(){
        
        if (mobsToSpawn <= 0 && conteurMobs <= 0) {
            monterNiveau();
            mobTimer = 200;
            mobsToSpawn = Jeu.getNiveau() * 3;
        }
        
        if(mobsToSpawn > 0 && mobTimer <= 0){
            if (mobsToSpawn % 5 == 0) {
                this.ajouterObjet(new EnnemiZigZag(rand.nextFloat() * 
                    (Vue.L - 50f), IdObjet.EnnemiZigZag));
            } else {
                this.ajouterObjet(new Ennemi(rand.nextFloat() * 
                    (Vue.L - 50f), IdObjet.EnnemiNormal));
            }
            --mobsToSpawn;
            mobTimer = rand.nextInt(80) + 150 / (Jeu.getNiveau() * 3 / 2); 
        }
        
        --mobTimer;
    }
    
    /**
     * 
     */
    public void spawnPowerUps() {
        
        if (!pUpSpawned) {
            if (Jeu.getNiveau() % 5 == 0) {
                pUpSpawned = true;
                this.ajouterObjet(new PowerUp(Vue.L/2-20,0,
                        IdObjet.PowerUpRouge));
            }
            else if (Jeu.getNiveau() % 3 == 0) {
                pUpSpawned = true;
                this.ajouterObjet(new PowerUp(Vue.L/2-20, 0,
                        IdObjet.PowerUpVert));
            }
            else if (Jeu.getNiveau() % 2 == 0) {
                pUpSpawned = true;
                this.ajouterObjet(new PowerUp(Vue.L/2-20, 0,
                        IdObjet.PowerUpBleu));
            }
        }
    }
    
    /**
     * 
     */
    private void monterNiveau() {
        Jeu.monterNiveau();
        pUpSpawned = false;
    }
} 
