package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Jeu;
import static ca.qc.bdeb.C37.tp2.window.Vue.splitImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jerome
 */
public class Joueur extends ObjetJeu {
    
    /**
     * Informations du vaisseau
     * L = largeur, H = hauteur, V = vitesse
     */
    public static final int L = 50, H = 55, V = 8;
    
    /**
     * Importance des dommages subits
     */
    private final int DEGAT_LASER = 20, DEGAT_COLLISION = 50;
    
    /**
     * Graphiques
     */
    public BufferedImage playerSprite[];
    public BufferedImage playerDamaged;
    /**
     * Pour contrôle de l'image
     */
    public int frame = 0, damaged = 0, timer;
    boolean exploding = false;
    
    private final Jeu jeu;
    
    /**
     * Pour calculer la vitesse diagonale
     */
    float delta;

    public Joueur(float x, float y, Jeu jeu, IdObjet id) {
        
        super(x, y, id);
        setImg();
        this.jeu = jeu;
        timer = 0;
        
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
        if(++timer >= 3){
            frame++;
            timer = 0;
        }
        if(frame >= 4 && exploding == false) {
            frame = 0;
        }
        else if (exploding && frame == 3) {
            try {
                jeu.gameOver();
            } catch (Throwable ex) {
                System.out.println(ex.toString());
            }
        }
        
        // Même vitesse peut importe la direction
        if (velX != 0 || velY != 0) {
            delta = (float) Math.sqrt(velX * velX + velY * velY);
            x += Joueur.V * velX/delta;
            y += Joueur.V * velY/delta;
        }
        
        gererCollision(controlleur);
    }
    
    /**
     * Gestion des conséquences aux collisions
     * 
     * @param controlleur 
     */
    private void gererCollision(ControlleurObjets controlleur) {
        for (int i = 0; i < controlleur.objets.size(); i++) {
            ObjetJeu temp = controlleur.objets.get(i);
            
            // Limites de déplacements
            if (temp.getId() == IdObjet.Frontiere) {
                if (contactHaut().intersects(temp.contact())) {
                    y = temp.getY() - 5;
                    velY = 0;
                }
                if (contactBas().intersects(temp.contact())) {
                    y = temp.getY() - H - 3;
                    velY = 0;
                }
                if (contactGauche().intersects(temp.contact())) {
                    x = temp.getX() + 2;
                    velX = 0;
                }
                if (contactDroite().intersects(temp.contact())) {
                    x = temp.getX() - (L);
                    velX = 0;
                }
            }
            // Dommages reçus (laser)
            else if (temp.getId() == IdObjet.TirEnnemi) {
                if (contact().intersects(temp.contact())) {
                    endommager(DEGAT_LASER);
                    controlleur.enleverObjet(temp);
                }
                
            }
            // Dommages reçus (collisions)
            else if (temp.getId() == IdObjet.EnnemiNormal ||
                    temp.getId() == IdObjet.EnnemiZigZag) {
                
                if (contact().intersects(temp.contact())) {
                    endommager(DEGAT_COLLISION);
                    controlleur.enleverObjet(temp);
                }
            }
            // Contrôles avec la souris -> arrêt au pointeur
            else if (temp.getId() == IdObjet.Pointeur &&
                        jeu.getCtrl() == IdCtrl.SOURIS) {
                
                if (x + L*4/7 <= temp.getX()) {
                    velX = temp.getX() - (x + L/2);
                }
                else if (x + L*3/7 >= temp.getX()) {
                    velX = temp.getX() - (x + L/2);
                }
                else {
                    velX = 0;
                }
                
                if (y + H*4/7 <= temp.getY()) {
                    velY = temp.getY() - (y + H/2);
                }
                else if (y + H*3/7 >= temp.getY()) {
                    velY = temp.getY() - (y + H/2);
                }
                else {
                    velY = 0;
                }
            }
        }
    }

    /**
     * Affichage dynamique
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        this.img = playerSprite[frame];
        if (damaged > 0) {
            if(damaged % 2 == 0){
                this.img = playerDamaged;
            }
            --damaged;
        }
        g.drawImage(img, (int)x, (int)y, L, H, null);
        
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.red);
//        g2d.draw(contact());
//        g2d.draw(contactHaut());
//        g2d.draw(contactBas());
//        g2d.draw(contactGauche());
//        g2d.draw(contactDroite());
    }
 
    /**
     * 
     */
    @Override
    public final void setImg() {
        BufferedImage playerImg;
        playerSprite = new BufferedImage[4];
        try {
            playerImg = ImageIO.read(new File("res/player.png"));
        } catch (IOException e) {
            playerImg = null;
        }
        BufferedImage temp[] = splitImage(playerImg, 4, 2);
        System.arraycopy(temp, 4, playerSprite, 0, 4);
        playerDamaged = temp[0];
        
        this.img = playerSprite[frame];
    }

    /**
     * @return Zone de contact pour les collisions dangereuses
     */
    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y + 10, L, H - 10);
    }
    
//    public Rectangle contactCentre() {
//        return new Rectangle((int)x + L/2-5, (int)y + H/2-7, 10, 14);
//    }

    /**
     * @return Zone de contact pour les collisions avec la frontière supérieure
     */
    public Rectangle contactHaut() {
        return new Rectangle((int)x + 20, (int)y + 5, L - 40, H/2 - 5);
    }

    /**
     * @return Zone de contact pour les collisions avec la frontière inférieure
     */
    public Rectangle contactBas() {
        return new Rectangle((int)x + 20, (int)y + H/2 + 5, L - 40, H/2 - 5);
    }

    /**
     * @return Zone de contact pour les collisions avec la frontière de droite
     */
    public Rectangle contactDroite() {
        return new Rectangle((int)x + L - 8, (int)y + 10, 8, H - 20);
    }

    /**
     * @return Zone de contact pour les collisions avec la frontière de gauche
     */
    public Rectangle contactGauche() {
        return new Rectangle((int)x, (int)y + 10, 8, H - 20);
    }
    
    /**
     * Destruction du joueur -> fin de la partie
     */
    public void detruireJoueur(){
        BufferedImage playerImg;
        try {
            playerImg = ImageIO.read(new File("res/playerExploding.png"));
        } catch (IOException e) {
            playerImg = null;
        }
        playerSprite = splitImage(playerImg, 4, 1);
        
        exploding = true;
        frame = 0;
    }
    
    /**
     * @param degats subits par le vaisseau
     */
    public void endommager(int degats){
        Jeu.perdreVie(degats);
        if (Jeu.getVie() <= 0) {
            detruireJoueur();
        } else {
            damaged = 40;
        }
    }
}
