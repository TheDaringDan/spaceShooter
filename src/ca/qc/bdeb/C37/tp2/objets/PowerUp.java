package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Jeu;
import ca.qc.bdeb.C37.tp2.window.Vue;
//import java.awt.Color;
//import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
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
public class PowerUp extends ObjetJeu {
    
    public static final int H = 40, L = 40, V = 6;
    private Image[] powerUpTypes = new BufferedImage[3];
    
    public PowerUp(float x, float y, IdObjet id) {
        super(x, y, id);
        setImg();
    }

    @Override
    public void tick(ControlleurObjets controlleur) {
        this.y += V;
        
        if (this.y > Vue.H) {
            controlleur.enleverObjet(this);
        }
        
        gererCollision(controlleur);
    }

    @Override
    public void render(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        if (this.id == IdObjet.PowerUpBleu) {
            img = powerUpTypes[0];
        }
        if (this.id == IdObjet.PowerUpVert) {
            img = powerUpTypes[1];
        }
        if (this.id == IdObjet.PowerUpRouge) {
            img = powerUpTypes[2];
        }
        
        g.drawImage(img, (int)x, (int)y, 38, 38, null);
        //g2d.draw(contact());
    }
    
    private void gererCollision(ControlleurObjets controlleur) {
        for (int i = 0; i < controlleur.objets.size(); i++) {
            ObjetJeu temp = controlleur.objets.get(i);
            
            if (temp.getId() == IdObjet.Joueur) {
                if (contact().intersects(temp.contact())) {
                    
                    if (this.id == IdObjet.PowerUpBleu) {
                        Jeu.incrementerScore(20);
                        Jeu.gagnerVie(10);
                    }
                    if (this.id == IdObjet.PowerUpVert) {
                        Jeu.incrementerScore(40);
                        Jeu.gagnerVie(20);
                    }
                    if (this.id == IdObjet.PowerUpRouge) {
                        Jeu.incrementerScore(60);
                        Jeu.gagnerVie(30);
                    }
                    
                    controlleur.enleverObjet(this);
                }
            }
        }
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, H, L);
    }

    @Override
    public void setImg() {
        File file = new File("res/pUp_H.png");
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.powerUpTypes[0] = img;
        
        file = new File("res/pUp_S.png");
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.powerUpTypes[1] = img;
        
        file = new File("res/pUp_W.png");
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.powerUpTypes[2] = img;
    }
    
}
