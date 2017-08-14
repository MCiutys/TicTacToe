/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tictactoe.game.Constants;

/**
 *
 * @author Mantas
 */
public class GameMenuPlayTill extends JPanel {
    
    private static final String PLAY = "Play till #wins : ";
    
    private JLabel playTill;
    private JTextField number;
    
    public GameMenuPlayTill() {
        playTill = new JLabel(PLAY);
        playTill.setFont(Constants.MENU_FONT);
        number = new JTextField();
        number.setColumns(20);
        number.setFont(Constants.MENU_FONT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(number, BorderLayout.WEST);
        
        setLayout(new BorderLayout());
        add(playTill, BorderLayout.WEST);
        add(panel, BorderLayout.EAST);
    }
    
    public int getMaxWins() {
        return Integer.valueOf(number.getText());
    }
    
    public boolean isValidInteger() {
        
        if (number.getText().isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < number.getText().length(); i++) {
            if (!Character.isDigit(number.getText().charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
