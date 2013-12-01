/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.C37.tp2.objets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author Danmasta97
 */
public class TirEnnemi extends ObjetJeu{
    
    public final static int L = 13, H = 22, V = 8;

    public TirEnnemi(float x, float y, IdObjet id) {
        super(x, y, id);
        setImg();
    }

    @Override
    public void tick(LinkedList<ObjetJeu> objets) {
        y += V;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int)y, L, H, null);
    }

    @Override
    public Rectangle contact() {
        return new Rectangle((int)x, (int)y, L, H - 10);
    }

    @Override
    public void setImg() {
        File file = new File("res/baddyPew.png");
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
        }
        this.img = img;
    }
}
