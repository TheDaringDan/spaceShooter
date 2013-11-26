/*
 * 
 */

package ca.qc.bdeb.C37.tp2.window;

import java.awt.Canvas;

/**
 *
 * @author jerome
 */
public class Jeu extends Canvas implements Runnable {
    
    private boolean running = false;
    private Thread thread;
    
    public synchronized void start() {
        // Sécurité
        if (running) return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        
    }
    
    
    
}
