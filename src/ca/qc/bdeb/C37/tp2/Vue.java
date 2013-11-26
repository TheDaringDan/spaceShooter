/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.C37.tp2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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
        BufferedImage playerSprite[] = splitImage(playerImg, 4, 2);
        
        player =  new JLabel(new ImageIcon(playerSprite[0]));
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
        mouseMoved(me);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
        if(mouseX < 45){
            mouseX = 45;
        } else if (mouseX > 500 - 45) {
            mouseX = 500 - 45;
        }
        if(mouseY < 45){
            mouseY = 45;
        } else if (mouseY > 700 - 45) {
            mouseY = 700 - 45;
        }
        player.setBounds(mouseX-25,mouseY-40,50,50);
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
    
    /**
     * Classe qui sépare une 'sprite sheet' en tableau d'images
     * Trouvé en ligne : http://www.javalobby.org/articles/ultimate-image/
     * dans la section 'Splitting Images'
     * 
     * @param img La 'sprite sheet' au complet
     * @param cols Le nombre de collones dans la 'sprite sheet'
     * @param rows Le nombre de rangées dans la 'sprite sheet'
     * @return Un tableau d'images (sprites)
     */
    public static BufferedImage[] splitImage(BufferedImage img, int cols, 
            int rows) {  
        int w = img.getWidth()/cols;  
        int h = img.getHeight()/rows;  
        int num = 0;  
        BufferedImage imgs[] = new BufferedImage[w*h];  
        for(int y = 0; y < rows; y++) {  
            for(int x = 0; x < cols; x++) {  
                imgs[num] = new BufferedImage(w, h, img.getType());  
                // Tell the graphics to draw only one block of the image  
                Graphics2D g = imgs[num].createGraphics();  
                g.drawImage(img, 0, 0, w, h, w*x, h*y, w*x+w, h*y+h, null);  
                g.dispose();  
                num++;  
            }  
        }  
        return imgs;  
    }
}
