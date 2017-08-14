/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tictactoe.game.Constants;

/**
 *
 * @author Mantas
 */
public class GameMenuChooseLevel extends JPanel {
    
    
    private static final String CHOOSE_LEVEL = "Choose level: ";
    private static final String[] LEVELS = {"Random", "Easy", "Impossible"};
    
    private final JLabel chooseLevel;
    private final JComboBox levels;
//    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);

    public GameMenuChooseLevel() {
        chooseLevel = new JLabel(CHOOSE_LEVEL);
        levels = new JComboBox(LEVELS);
        chooseLevel.setFont(Constants.MENU_FONT);
        levels.setFont(Constants.MENU_FONT);
        
        setUp();
    }
    
    private void setUp() {
        setLayout(new BorderLayout());
        add(chooseLevel, BorderLayout.WEST);
        add(levels, BorderLayout.CENTER);
    }
    
    public JComboBox giveLevel() {
        return levels;
    }
}
