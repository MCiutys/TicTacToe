/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Mantas
 */
public class GameOptions extends JPanel {
    
    private JRadioButton player1;
    private JRadioButton player2;
    private JLabel label;
    
    public GameOptions() {
        player1 = new JRadioButton("Player 1");
        player2 = new JRadioButton("Player 2");
        label = new JLabel("Label");
        this.add(player1);
        this.add(player2);
    }
    
    
    
}
