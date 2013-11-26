/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.C37.tp2;

/**
 *
 * @author Danmasta97
 */
public class Modele {
    private int niveau;
    private int hp;
    private int score;
    private final int LASER_HIT = 50;
    private final int SCORE_LASER = 100;
    
    public Modele(){
        niveau = 1;
        hp = 500;
        score = 0;
    }
    
    public void laserHit(){
        hp -= LASER_HIT;
        score -= SCORE_LASER;
    }
    
    public void prochainNiveau(){
        ++niveau;
    }
}
