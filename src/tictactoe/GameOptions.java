/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Mantas
 */
public class GameOptions extends JPanel {
    
    private static final String TURN = " turn";
    
    private JRadioButton player1;
    private JRadioButton player2;
    private JLabel turn;
    
    public GameOptions() {
        player1 = new JRadioButton("Player 1");
        player2 = new JRadioButton("Player 2");
        turn = new JLabel("label");
        setLayout(new BorderLayout());
        add(player1, BorderLayout.PAGE_START);
        add(player2, BorderLayout.PAGE_END);
        add(turn, BorderLayout.CENTER);
    }
    
    public void setTurnLabel(Player player) {
        turn.setText(player.getName() + TURN);
    }
    
    
    
    
}
