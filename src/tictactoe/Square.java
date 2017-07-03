/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Mantas
 */
public class Square extends JButton implements ActionListener {
    
    private int number;
    private boolean isClicked;
    
    public Square(int x) {
        number = x;
        isClicked = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!isClicked) {
            super.paintComponent(g);
        } else {
            System.out.println("EXECUTED for: " + number);
            g2d.drawOval(this.getX(), this.getY(), 100, 100);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
        isClicked = !isClicked;
        System.out.println(isClicked + " " + number);
        repaint();
    }
  
}
