/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 *
 * @author Mantas
 */

public class Square {
    
    private int index;
    private JToggleButton button;
    private boolean isClicked;
    private Color color;
    private Player whoseTurn;
    private Player notWhoseTurn;
    
    public Square(int i) {
        index = i;
        button = new JToggleButton();
        isClicked = false;
        color = Color.BLACK;
        whoseTurn = null;
        notWhoseTurn = null;
        
        button.setBorderPainted(false);
        button.setBorder(null);
        
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whoseTurn.addToMarked(Square.this);
                System.out.println(whoseTurn);
                
                Player temp = whoseTurn;
                whoseTurn = notWhoseTurn;
                notWhoseTurn = temp;
                
                isClicked = !isClicked;
                
                if (isClicked) {
                    button.setIcon(draw());   
                } else {
                    button.setIcon(null); 
                }
            }   
        });
    }
    
    public JToggleButton getButton() {
        return button;
    }
    
    public void setColor(Color c) {
        color = c;
    }
    
    public void setWhoseTurn(Player p) {
        whoseTurn = p;
    }
    
    public void setNotWhoseTurn(Player p) {
        notWhoseTurn = p;
    }

    
    private Icon draw() {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(color);
        drawX(g2d);
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    private void drawO(Graphics2D g2d) {
        g2d.drawOval(0, 0, 100, 100);
    }
    
    private void drawX(Graphics2D g2d) {
        g2d.drawLine(0 , 0, 100, 100);
        g2d.drawLine(0, 100, 100, 0);
    }
}
