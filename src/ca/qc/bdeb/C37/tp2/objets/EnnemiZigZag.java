/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.C37.tp2.objets;

import ca.qc.bdeb.C37.tp2.window.Vue;
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
 * @author Danmasta97, jerome
 */
public class EnnemiZigZag extends ObjetJeu {

    public static final int L = 50, H = 55, V = 4;
    
    private int velX = 3;
    
    private final int CADENCE = 50;

    public BufferedImage[] enemySprite;
    public int frame = 4;
    public int timer = 0;
    public int ready;
    ControlleurObjets controlleur;
    

    public EnnemiZigZag(float x, ControlleurObjets controlleur, IdObjet id) {
        super(x, -25, id);
        setImg();
        this.controlleur = controlleur;
        ready = 0;
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
        if (++timer >= 3) {
            ++frame;
            timer = 0;
        }
        if (frame >= 8) {
            frame -= 4;
        }
        if(ready > 0){
            --ready;
        }
        y += V;
        x += velX;
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
    
    /**
     * 
     * @param objets 
     */
    private void gererCollision(LinkedList<ObjetJeu> objets) {
        for (int i = 0; i < objets.size(); i++) {
            ObjetJeu temp = objets.get(i);
            
            if (temp.getId() == IdObjet.TirNormal) {
                if (contact().intersects(temp.contact())) {
                    controlleur.enleverObjet(temp);
                    controlleur.enleverObjet(this);
                }
            } else if (temp.id == IdObjet.Joueur){
                if (getX() + 50f >= temp.getX() && getX() - 50f 
                         <= temp.getX() && ready == 0) {
                     
                    controlleur.ajouterObjet(new TirEnnemi(getX() + 20f,
                        getY() + 50f, IdObjet.TirEnnemi));
                    
                    ready = CADENCE;
                        
                }
                
                if (getX() <= 0 || getX() >= Vue.L - L) {
                    velX = -velX;
                }
            }
        }
    }
}

