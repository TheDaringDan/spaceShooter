/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.C37.tp2.objets;

import static ca.qc.bdeb.C37.tp2.Vue2.splitImage;
import ca.qc.bdeb.C37.tp2.window.Vue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Danmasta97
 */
public class Ennemi extends ObjetJeu {

    public static final int L = 50, H = 55, V = 4;

    public BufferedImage[] enemySprite;
    public int frame = 0;
    public int timer = 0;
    ControlleurObjets controlleur;
    

    public Ennemi(float x, ControlleurObjets controlleur, IdObjet id) {
        super(x, -25, id);
        setImg();
        this.controlleur = controlleur;
        System.out.println("Ennemi crée");
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
        if (++timer >= 3) {
            ++frame;
            timer = 0;
        }
        if (frame >= 4) {
            frame -= 4;
        }
        y += V;
        gererCollision(objets);
    }

    @Override
    public void render(Graphics g) {
        this.img = enemySprite[frame];
        g.drawImage(img, (int) x, (int) y, L, H, null);
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int) x, (int) y + 10, L, H - 10);
    }

    @Override
    public void setImg() {
        BufferedImage enemyImg;
        try {
            enemyImg = ImageIO.read(new File("res/baddy.png"));
        } catch (IOException e) {
            enemyImg = null;
        }
        enemySprite = splitImage(enemyImg, 4, 2);

        this.img = enemySprite[frame];
    }
    private void gererCollision(LinkedList<ObjetJeu> objets) {
        for (int i = 0; i < objets.size(); i++) {
            ObjetJeu temp = objets.get(i);
            
            if (temp.getId() == IdObjet.TirNormal) {
                if (contactHaut().intersects(temp.contact())) {
                    controlleur.enleverObjet(temp);
                    controlleur.enleverObjet(this);
                }
                if (contactBas().intersects(temp.contact())) {
                    controlleur.enleverObjet(temp);
                    controlleur.enleverObjet(this);
                }
                if (contactGauche().intersects(temp.contact())) {
                    controlleur.enleverObjet(temp);
                    controlleur.enleverObjet(this);
                }
                if (contactDroite().intersects(temp.contact())) {
                    controlleur.enleverObjet(temp);
                    controlleur.enleverObjet(this);
                }
            } else if (temp.id == IdObjet.Joueur){
                Joueur joueur = (Joueur) temp;
                if (contactHaut().intersects(temp.contact())) {
                    joueur.detruireJoueur();
                    controlleur.enleverObjet(this);
                }
                if (contactBas().intersects(temp.contact())) {
                    joueur.detruireJoueur();
                    controlleur.enleverObjet(this);
                }
                if (contactGauche().intersects(temp.contact())) {
                    joueur.detruireJoueur();
                    controlleur.enleverObjet(this);
                }
                if (contactDroite().intersects(temp.contact())) {
                    joueur.detruireJoueur();
                    controlleur.enleverObjet(this);
                }
            }
        }
    }
    
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
