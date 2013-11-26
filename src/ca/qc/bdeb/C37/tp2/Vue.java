/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.C37.tp2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Danmasta97
 */
public class Vue implements MouseMotionListener, MouseListener{
    private int mouseX;
    private int mouseY;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame frame;
    JLabel player;
    
    public Vue(){
        mouseX = 0;
        mouseY = 0;
        JPanel panel = new JPanel();
        frame = new JFrame("Space Shooter");
        panel.setLayout(null);
        panel.addMouseMotionListener(this);
        panel.setBackground(Color.black);
        
        BufferedImage playerImg;
        try {
            playerImg = ImageIO.read(new File("res/player.png"));
        } catch(IOException e){
            playerImg = null;
        }
        ImageIcon playerSprite;
        
        player =  new JLabel(new ImageIcon(playerImg));
        panel.add(player);
        player.setBounds(225,550,50,50);
        frame.add(panel);
        frame.setLocation(dim.width/2-250, 10);
        
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        player.setBounds(me.getX()-25,me.getY()-40,50,50);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        player.setBounds(me.getX()-25,me.getY()-40,50,50);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
