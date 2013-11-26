package ca.qc.bdeb.C37.tp2.objets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author jerome
 */
public abstract class ObjetJeu {
    // Position
    protected float x, y;
    
    // Velocité
    protected float velX = 0, velY = 0;
    
    // Identificateur d'objet
    protected IdObjet id;
    
    // Apparence de l'objet
    protected Image img;
    
    /**
     * Constructeur.
     * 
     * @param x position horizontale
     * @param y position verticale
     * @param id identificateur
     */
    public ObjetJeu(float x, float y, IdObjet id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<ObjetJeu> objets);
    
    public abstract void render(Graphics g);
    
    public abstract Rectangle contact();
    
    public abstract void setImg();
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getVelX() {
        return velX;
    }
    
    public float getVelY() {
        return velY;
    }
    
    public IdObjet getId() {
        return id;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public void setVelX(float velX) {
        this.velX = velX;
    }
    
    public void setVelY(float velY) {
        this.velY = velY;
    }
}
