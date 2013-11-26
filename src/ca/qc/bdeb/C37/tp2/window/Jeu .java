package ca.qc.bdeb.C37.tp2.window;

import ca.qc.bdeb.C37.tp2.objets.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * @author jerome
 */
public class Jeu extends Canvas implements Runnable {
    
    /**
     * État du jeu
     */
    private boolean running = false;
    /**
     * Thread du jeu
     */
    private Thread thread;
    
    /**
     * Dimensions de l'aire de jeu
     */
    public static final int L = 500, H = 700;
    
    /**
     * Choix de contrôles
     */
    public static IdCtrl ctrl;
    
    /**
     * Objets
     */
    ControlleurObjets controlleur;
    Image fond;
    
    /**
     * Innitialisation du jeu
     */
    private void init() {
        
        ctrl = IdCtrl.CLAVIER;
        
        setFond();
        
        controlleur = new ControlleurObjets();
        
        // Limites de déplacement
        controlleur.dessinerFrontieres();
        
        // Ajouter le joueur
        controlleur.ajouterObjet(new Joueur(L/2-25, H*3/4,
                controlleur, IdObjet.Joueur));
        
        // Ajouter le pointeur
        controlleur.ajouterObjet(new Pointeur(0, 0, IdObjet.Pointeur));
        
        addKeyListener(new CtrlClavier(controlleur));
        
        addMouseMotionListener(new CtrlSouris(controlleur));
        
    }
    
    /**
     * Démarage de la partie
     */
    public synchronized void start() {
        // Sécurité
        if (running) return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Game Loop
     * Source : http://www.mediafire.com/download/in0truhx4fzoerf/game_loop.txt
     */
    @Override
    public void run() {
        init();
        requestFocus();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 50.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    /**
     * 
     */
    private void tick() {
        controlleur.tick();
    }
    
    /**
     * 
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        // ***Début affichage***
        g.drawImage(fond, 0, 0, null);
        
        controlleur.render(g);
        
        // ***Fin affichage*****
        g.dispose();
        bs.show();
    }
    
    /**
     * 
     */
    public void setFond() {
        File fichier;
        Image image;
        
        fichier = new File("res/8bitBackground.bmp");
        
        try {
            image = ImageIO.read(fichier);
            
            this.fond = image;
        } catch (IOException ex) {
            System.out.println("Erreur : Image non-chargée.");
        }
    }
    
}