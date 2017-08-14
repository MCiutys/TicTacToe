/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import tictactoe.game.Constants;

/**
 *
 * @author Mantas
 */
public class GameMenuSetFirst extends JPanel {
    
    private static final String WHO_FIRST = "Who starts first? ";
    private static final String PLAYER_1 = "Player 1";
    private static final String PLAYER_2 = "Player 2";
    
    
    private JLabel whoFirst;
    private JRadioButton player1;
    private JRadioButton player2;
    
    public GameMenuSetFirst() {
        whoFirst = new JLabel(WHO_FIRST);
        player1 = new JRadioButton(PLAYER_1);
        player2 = new JRadioButton(PLAYER_2);
        
        whoFirst.setFont(Constants.MENU_FONT);
        player1.setFont(Constants.MENU_FONT);
        player2.setFont(Constants.MENU_FONT);
        
        ButtonGroup group = new ButtonGroup();
        group.add(player1);
        group.add(player2);
        player1.setSelected(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(player1, BorderLayout.WEST);
        panel.add(player2, BorderLayout.EAST);
        
        setLayout(new BorderLayout());
        add(whoFirst, BorderLayout.WEST);
        add(panel, BorderLayout.EAST);
    }
    
    public boolean isFirstPlayerTurn() {
        return player1.isSelected();
    }
}
