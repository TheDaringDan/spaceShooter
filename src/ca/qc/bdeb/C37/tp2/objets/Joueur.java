package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Jeu;
import static ca.qc.bdeb.C37.tp2.window.Vue.splitImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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
    
    public BufferedImage playerSprite[];
    public BufferedImage playerDamaged;
    public int frame = 0;
    public int timer;
    ControlleurObjets controlleur;
    boolean exploding = false;
    int damaged = 0;
    private final int DEGAT_LASER = 20;
    private final int DEGAT_COLLISION = 50;
    
    private final Jeu jeu;
    // Facteurs pour calculer la vitesse diagonale
    float delta;

    public Joueur(float x, float y, ControlleurObjets controlleur,
            Jeu jeu, IdObjet id) {
        
        super(x, y, id);
        setImg();
        this.jeu = jeu;
        this.controlleur = controlleur;
        timer = 0;
        
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
        if(++timer >= 3){
            frame++;
            timer = 0;
        }
        if(frame >= 4 && exploding == false) {
            frame = 0;
        }
        else if (exploding && frame == 3) {
            jeu.gameOver();
        }
        
        if (velX != 0 || velY != 0) {
            delta = (float) Math.sqrt(velX * velX + velY * velY);
            x += Joueur.V * velX/delta;
            y += Joueur.V * velY/delta;
        }
        
        gererCollision(objets);
    }
    
    private void gererCollision(LinkedList<ObjetJeu> objets) {
        for (int i = 0; i < objets.size(); i++) {
            ObjetJeu temp = objets.get(i);
            
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
            } else if (temp.getId() == IdObjet.Pointeur) {
                
                if (contact().intersects(temp.contact()) &&
                        jeu.getCtrl() == IdCtrl.SOURIS) {
                    
                    velX = 0;
                    velY = 0;
                }
            } else if (temp.getId() == IdObjet.TirEnnemi) {
                if (contact().intersects(temp.contact())) {
                    endommager(DEGAT_LASER);
                    controlleur.enleverObjet(temp);
                }
                
            } else if (temp.getId() == IdObjet.Ennemi) {
                if (contact().intersects(temp.contact())) {
                    endommager(DEGAT_COLLISION);
                    controlleur.enleverObjet(temp);
                }
            }
        }
    }

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

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y + 10, L, H - 10);
    }
    
//    public Rectangle contactCentre() {
//        return new Rectangle((int)x + L/2-5, (int)y + H/2-7, 10, 14);
//    }

    public Rectangle contactHaut() {
        return new Rectangle((int)x + 20, (int)y + 5, L - 40, H/2 - 5);
    }

    public Rectangle contactBas() {
        return new Rectangle((int)x + 20, (int)y + H/2 + 5, L - 40, H/2 - 5);
    }

    public Rectangle contactDroite() {
        return new Rectangle((int)x + L - 8, (int)y + 10, 8, H - 20);
    }

    public Rectangle contactGauche() {
        return new Rectangle((int)x, (int)y + 10, 8, H - 20);
    }
    
    public void detruireJoueur(){
        BufferedImage playerImg;
        try {
            playerImg = ImageIO.read(new File("res/playerExploding.png"));
        } catch (IOException e) {
            playerImg = null;
        }
        playerSprite = splitImage(playerImg, 4, 1);
        
        exploding = true;
        TirJoueur.disableGun();
        frame = 0;
    }
    
    public void endommager(int degats){
        Jeu.perdreVie(degats);
        if (Jeu.getVie() <= 0) {
            detruireJoueur();
        } else {
            damaged = 40;
        }
    }
}
