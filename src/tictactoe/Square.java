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
    
    public Square(int i) {
        index = i;
        button = new JToggleButton();
        isClicked = false;
        
        button.setBorderPainted(false);
        button.setBorder(null);
        
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    
    private Icon draw() {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(0, 0, 100, 100);
        g2d.dispose();
        return new ImageIcon(img);
    }

    
    
}
