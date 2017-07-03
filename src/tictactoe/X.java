/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Mantas
 */
public class X extends JComponent {
    
    private static final int LINE_LENGTH = 50;
    
    private int x;
    private int y;
    
    public X(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawLine(x , y, x + LINE_LENGTH, y + LINE_LENGTH);
        g2d.drawLine(x, y + LINE_LENGTH, x + LINE_LENGTH, y);
    }
    
}
