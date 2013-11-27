/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.C37.tp2.objets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Danmasta97
 */
public class Ennemi extends ObjetJeu {
    public static final int L = 50, H = 55, V = 8;
    
    public BufferedImage playerSprite[];
    public int frame = 0;
    public int timer = 0;
    ControlleurObjets controlleur;

    public Ennemi(float x, float y, IdObjet id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
        if(++timer >= 3){
            ++frame;
            timer = 0;
        }
        if(frame >= 4) frame = 0;
        y += V;
    }

    @Override
    public void render(Graphics g) {
        this.img = playerSprite[frame];
        g.drawImage(img, (int)x, (int)y, L, H, null);
    }

    @Override
    public Rectangle contact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
