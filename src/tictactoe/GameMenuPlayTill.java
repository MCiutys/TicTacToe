/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mantas
 */
public class GameMenuPlayTill extends JPanel {
    
    private static final String PLAY = "Play till: ";
    private static final String WINS = " wins";
    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);
    
    private JLabel playTill;
    private JTextField number;
    private JLabel wins;
    
    public GameMenuPlayTill() {
        playTill = new JLabel(PLAY);
        playTill.setFont(NAME);
        number = new JTextField();
        number.setColumns(18);
        number.setFont(NAME);
        wins = new JLabel(WINS);
        wins.setFont(NAME);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(number, BorderLayout.WEST);
        panel.add(wins, BorderLayout.EAST);
        
//        setLayout(new FlowLayout(FlowLayout.LEADING));
        setLayout(new BorderLayout());
        add(playTill, BorderLayout.WEST);
        add(panel, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));
//        add(wins, BorderLayout.EAST);
        setBorder(BorderFactory.createLineBorder(Color.yellow));
    }
    
    public int getMaxWins() {
        return Integer.valueOf(number.getText());
    }
}
