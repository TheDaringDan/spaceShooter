/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.C37.tp2;

import ca.qc.bdeb.C37.tp2.window.Vue;
import ca.qc.bdeb.C37.tp2.window.Jeu;

/**
 *
 * @author Danmasta97
 */
public class TP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vue vue = new Vue(Jeu.L, Jeu.H, "Invader", new Jeu());
    }
    
}
