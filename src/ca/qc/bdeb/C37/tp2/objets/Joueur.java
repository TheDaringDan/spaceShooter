package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.ControlleurObjets;
import ca.qc.bdeb.C37.tp2.window.Jeu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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
    
    ControlleurObjets controlleur;
    
    // Facteurs pour calculer la vitesse diagonale
    float delta;

    public Joueur(float x, float y, ControlleurObjets controlleur, IdObjet id) {
        super(x, y, id);
        setImg();
        this.controlleur = controlleur;
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
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
            }
            else if (temp.getId() == IdObjet.Pointeur) {
                
                if (contact().intersects(temp.contact()) &&
                        Jeu.ctrl == IdCtrl.SOURIS) {
                    
                    velX = 0;
                    velY = 0;
                }
                
            }
        }
    }

    @Override
    public void render(Graphics g) {
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
        File fichier;
        Image image;
        
        fichier = new File("res/vaisseau.png");
        
        try {
            image = ImageIO.read(fichier);
            
            this.img = image;
        } catch (IOException ex) {
            System.out.println("Erreur : Image non-chargée.");
        }
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x + 10, (int)y + 10, L - 20, H - 10);
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

}
