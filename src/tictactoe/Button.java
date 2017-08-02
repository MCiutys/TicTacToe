/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Mantas
 */
public class Button extends JButton {
    
    public Button(String name) {
        super(name);
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, CENTER, false));
        this.setForeground(Color.WHITE);
    }
    
    
    
}
